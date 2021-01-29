package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cavalierfou.russianback.constant.Constant;
import com.cavalierfou.russianback.customentity.RussianAdjectiveCategoryRefCustom;
import com.cavalierfou.russianback.customentity.RussianAdjectiveCustom;
import com.cavalierfou.russianback.customentity.RussianAdjectiveEndingRefCustom;
import com.cavalierfou.russianback.entity.RussianAdjective;
import com.cavalierfou.russianback.entity.RussianAdjectiveCategoryRef;
import com.cavalierfou.russianback.entity.RussianAdjectiveEndingRef;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.RussianAdjectiveCategoryRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianAdjectiveEndingRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianAdjectiveJpaRepository;
import com.cavalierfou.russianback.repository.RussianCaseRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianGenderRefJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RussianAdjectiveService {

    @Autowired
    private RussianAdjectiveJpaRepository russianAdjectiveJpaRepository;
    @Autowired
    private RussianAdjectiveCategoryRefJpaRepository russianAdjectiveCategoryRefJpaRepository;
    @Autowired
    private RussianAdjectiveEndingRefJpaRepository russianAdjectiveEndingRefJpaRepository;
    @Autowired
    private RussianCaseRefJpaRepository russianCaseRefJpaRepository;
    @Autowired
    private RussianGenderRefJpaRepository russianGenderRefJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;

    public RussianAdjectiveCustom findById(Long id) {
        Optional<RussianAdjective> existingRussianAdjectiveOptional = russianAdjectiveJpaRepository.findById(id);
        if (existingRussianAdjectiveOptional.isPresent()) {
            return this.mapToCustom(existingRussianAdjectiveOptional.get());
        } else {
            return null;
        }
    }

    public List<RussianAdjectiveCustom> find(String translation) {
        List<RussianAdjective> russianAdjectives = (translation == null) ? russianAdjectiveJpaRepository.findAll()
                : russianAdjectiveJpaRepository.findByTranslation(translation);

        List<RussianAdjectiveCustom> russianAdjectiveCustoms = new ArrayList<>();
        if (russianAdjectives.isEmpty()) {
            return russianAdjectiveCustoms;
        }
        russianAdjectives.forEach(russianAdjective -> russianAdjectiveCustoms.add(mapToCustom(russianAdjective)));

        return russianAdjectiveCustoms;
    }

    public RussianAdjectiveCustom save(RussianAdjective russianAdjective) {
        jdbcRepository.resetSequence(Constant.RA.getValue(), Constant.RAIS.getValue());

        return mapToCustom(russianAdjectiveJpaRepository.save(russianAdjective));
    }

    public RussianAdjective update(Long id, RussianAdjective updatedRussianAdjective) {
        Optional<RussianAdjective> russianAdjectiveToUpdateOptional = russianAdjectiveJpaRepository.findById(id);
        if (russianAdjectiveToUpdateOptional.isPresent()) {
            RussianAdjective russianAdjectiveToUpdate = russianAdjectiveToUpdateOptional.get();
            russianAdjectiveToUpdate.setRoot(updatedRussianAdjective.getRoot());
            russianAdjectiveToUpdate
                    .setRussianAdjectiveCategoryRefId(updatedRussianAdjective.getRussianAdjectiveCategoryRefId());
            russianAdjectiveToUpdate.setTranslation(updatedRussianAdjective.getTranslation());

            return russianAdjectiveToUpdate;
        } else {
            return null;
        }
    }

    public void delete(Long id, boolean force) {
        if (force) {
            jdbcRepository.delete(Constant.PGH.getValue(), Constant.RAI.getValue(), id.toString());
        }
        russianAdjectiveJpaRepository.deleteById(id);
    }

    public boolean isPresent(Long id) {
        return russianAdjectiveJpaRepository.findById(id).isPresent();
    }

    public RussianAdjectiveCustom mapToCustom(RussianAdjective russianAdjective) {
        Optional<RussianAdjectiveCategoryRef> existingRussianAdjectiveCategoryRefOptional = russianAdjectiveCategoryRefJpaRepository
                .findById(russianAdjective.getRussianAdjectiveCategoryRefId());
        List<RussianAdjectiveEndingRef> existingRussianAdjectiveEndingRefs = russianAdjectiveEndingRefJpaRepository
                .findByRussianAdjectiveCategoryRefId(russianAdjective.getRussianAdjectiveCategoryRefId());

        RussianAdjectiveCustom russianAdjectiveCustom = new RussianAdjectiveCustom();
        russianAdjectiveCustom.setId(russianAdjective.getId());
        russianAdjectiveCustom.setRoot(russianAdjective.getRoot());
        russianAdjectiveCustom.setTranslation(russianAdjective.getTranslation());

        if (existingRussianAdjectiveEndingRefs != null) {
            List<RussianAdjectiveEndingRefCustom> russianAdjectiveEndingRefCustoms = new ArrayList<>();

            existingRussianAdjectiveEndingRefs.forEach(existingRussianAdjectiveEndingRef -> {
                RussianAdjectiveEndingRefCustom russianAdjectiveEndingRefCustom = new RussianAdjectiveEndingRefCustom();

                russianCaseRefJpaRepository.findById(existingRussianAdjectiveEndingRef.getRussianCaseRefId()).ifPresent(
                        russianCase -> russianAdjectiveEndingRefCustom.setRussianCase(russianCase.getValue()));

                russianGenderRefJpaRepository.findById(existingRussianAdjectiveEndingRef.getRussianGenderRefId())
                        .ifPresent(russianGender -> russianAdjectiveEndingRefCustom
                                .setRussianGender(russianGender.getValue()));

                russianAdjectiveEndingRefCustom.setValue(existingRussianAdjectiveEndingRef.getValue());

                russianAdjectiveEndingRefCustoms.add(russianAdjectiveEndingRefCustom);
                if (Constant.M.getValue().equals(russianAdjectiveEndingRefCustom.getRussianGender())
                        && Constant.N.getValue().equals(russianAdjectiveEndingRefCustom.getRussianCase())
                        && existingRussianAdjectiveCategoryRefOptional.isPresent()) {
                    var russianAdjectiveCategoryRef = existingRussianAdjectiveCategoryRefOptional.get();
                    RussianAdjectiveCategoryRefCustom russianAdjectiveCategoryRefCustom = new RussianAdjectiveCategoryRefCustom();
                    russianAdjectiveCategoryRefCustom
                            .setMasculineNominativeEnding(russianAdjectiveEndingRefCustom.getValue());
                    russianAdjectiveCategoryRefCustom.setValue(russianAdjectiveCategoryRef.getValue());
                    russianAdjectiveCustom.setRussianAdjectiveCategoryRefCustom(russianAdjectiveCategoryRefCustom);
                }
            });

            russianAdjectiveCustom.setRussianAdjectiveEndingRefCustoms(russianAdjectiveEndingRefCustoms);
        }
        return russianAdjectiveCustom;
    }

}

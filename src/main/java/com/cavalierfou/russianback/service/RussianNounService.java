package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.cavalierfou.russianback.customentity.RussianNounCategoryRefCustom;
import com.cavalierfou.russianback.customentity.RussianNounCustom;
import com.cavalierfou.russianback.customentity.RussianNounEndingRefCustom;
import com.cavalierfou.russianback.entity.RussianNoun;
import com.cavalierfou.russianback.entity.RussianNounCategoryRef;
import com.cavalierfou.russianback.entity.RussianNounEndingRef;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.RussianCaseRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounCategoryRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounEndingRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounJpaRepository;
import com.cavalierfou.russianback.repository.RussianDeclCatTypeRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianDeclensionNameRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianGenderRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianGrammaticalNumberRefJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RussianNounService {

    @Autowired
    private RussianNounJpaRepository russianNounJpaRepository;
    @Autowired
    private RussianNounCategoryRefJpaRepository russianNounCategoryRefJpaRepository;
    @Autowired
    private RussianDeclCatTypeRefJpaRepository russianDeclCatTypeRefJpaRepository;
    @Autowired
    private RussianDeclensionNameRefJpaRepository russianDeclensionNameRefJpaRepository;
    @Autowired
    private RussianGenderRefJpaRepository russianGenderRefJpaRepository;
    @Autowired
    private RussianGrammaticalNumberRefJpaRepository russianGrammaticalNumberRefJpaRepository;
    @Autowired
    private RussianNounEndingRefJpaRepository russianNounEndingRefJpaRepository;
    @Autowired
    private RussianCaseRefJpaRepository russianCaseRefJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;

    public RussianNounCustom findById(Long id) {
        Optional<RussianNoun> existingRussianNounOptional = russianNounJpaRepository.findById(id);
        if (existingRussianNounOptional.isPresent()) {
            return this.mapToCustom(existingRussianNounOptional.get());

        } else {
            return null;
        }
    }

    public List<RussianNounCustom> find(String translation) {
        // List<RussianNoun> russianNouns = (translation == null) ?
        // russianNounJpaRepository.findAll()
        // : russianNounJpaRepository.findByTranslation(translation);

        List<RussianNoun> russianNouns = russianNounJpaRepository.findAll();

        List<RussianNounCustom> russianNounCustoms = new ArrayList<>();
        if (russianNouns.isEmpty()) {
            return russianNounCustoms;
        }
        russianNouns.forEach(russianNoun -> russianNounCustoms.add(mapToCustom(russianNoun)));

        return russianNounCustoms;
    }

    public RussianNounCustom save(RussianNoun russianNoun) {
        jdbcRepository.resetSequence("russian_noun", "russian_noun_id_seq");

        return mapToCustom(russianNounJpaRepository.save(russianNoun));

    }

    // public RussianNoun update(Long id, RussianNoun updatedRussianNoun) {
    // Optional<RussianNoun> russianNounToUpdateOptional =
    // russianNounJpaRepository.findById(id);
    // if (russianNounToUpdateOptional.isPresent()) {
    // RussianNoun russianNounToUpdate = russianNounToUpdateOptional.get();
    // russianNounToUpdate.setRoot(updatedRussianNoun.getRoot());
    // russianNounToUpdate
    // .setRussianNounCategoryRefId(updatedRussianNoun.getRussianNounCategoryRefId());
    // russianNounToUpdate.setTranslation(updatedRussianNoun.getTranslation());

    // return russianNounToUpdate;
    // } else {
    // return null;
    // }
    // }

    // public void delete(Long id, boolean force) {
    // if (force) {
    // jdbcRepository.delete("player_game_history", "russian_noun_id",
    // id.toString());
    // }
    // russianNounJpaRepository.deleteById(id);
    // }

    public boolean isPresent(Long id) {
        return russianNounJpaRepository.findById(id).isPresent();
    }

    public RussianNounCustom mapToCustom(RussianNoun russianNoun) {
        Optional<RussianNounCategoryRef> existingRussianNounCategoryRefOptional = russianNounCategoryRefJpaRepository
                .findById(russianNoun.getRussianNounCategoryRefId());
        // List<RussianNounEndingRef> existingRussianNounEndingRefs =
        // russianNounEndingRefJpaRepository
        // .findByRussianNounCategoryRefId(russianNoun.getRussianNounCategoryRefId());

        RussianNounCustom russianNounCustom = new RussianNounCustom();
        russianNounCustom.setId(russianNoun.getId());
        russianNounCustom.setAnimate(russianNoun.getIsAnimate());
        russianNounCustom.setRoot(russianNoun.getRoot());
        russianNounCustom.setTranslation(russianNoun.getTranslation());

        if (existingRussianNounCategoryRefOptional.isPresent()) {
            var rncr = existingRussianNounCategoryRefOptional.get();
            RussianNounCategoryRefCustom russianNounCategoryRefCustom = new RussianNounCategoryRefCustom();
            this.russianDeclCatTypeRefJpaRepository.findById(rncr.getRussianDeclCatTypeRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianDeclCatType(data.getValue()));
            this.russianDeclensionNameRefJpaRepository.findById(rncr.getRussianDeclensionNameRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianDeclensionName(data.getValue()));
            this.russianGenderRefJpaRepository.findById(rncr.getRussianGenderRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianGender(data.getValue()));
            this.russianGrammaticalNumberRefJpaRepository.findById(rncr.getRussianGrammaticalNumberRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianGrammaticalNumber(data.getValue()));
            russianNounCustom.setRussianNounCategory(russianNounCategoryRefCustom);
        }

        List<RussianNounEndingRefCustom> russianNounEndingRefCustoms = new ArrayList<>();

        this.russianNounEndingRefJpaRepository.findByRussianNounCategoryRefId(russianNoun.getRussianNounCategoryRefId())
                .forEach(russianNounEnding -> {
                    RussianNounEndingRefCustom russianNounEndingRefCustom = new RussianNounEndingRefCustom();
                    this.russianCaseRefJpaRepository.findById(russianNounEnding.getRussianCaseRefId())
                            .ifPresent(data -> russianNounEndingRefCustom.setRussianCase(data.getValue()));
                    russianNounEndingRefCustom.setValue(russianNounEnding.getValue());
                    russianNounEndingRefCustoms.add(russianNounEndingRefCustom);

                    if ("Nominative".equals(russianNounEndingRefCustom.getRussianCase())) {
                        russianNounCustom
                                .setNominativeForm(russianNounCustom.getRoot() + russianNounEndingRefCustom.getValue());
                    }
                });

        russianNounCustom.setRussianNounEndings(russianNounEndingRefCustoms);

        return russianNounCustom;
    }

}

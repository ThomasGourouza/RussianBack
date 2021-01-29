package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.cavalierfou.russianback.customentity.RussianNounCategoryRefCustom;
import com.cavalierfou.russianback.customentity.RussianNounCustom;
import com.cavalierfou.russianback.customentity.RussianNounEndingRefCustom;
import com.cavalierfou.russianback.entity.RussianNoun;
import com.cavalierfou.russianback.entity.RussianNounCategoryRef;
import com.cavalierfou.russianback.entity.RussianNounEndingRef;
import com.cavalierfou.russianback.entity.RussianSingularPluralNounCouple;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.RussianCaseRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounCategoryRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounEndingRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounJpaRepository;
import com.cavalierfou.russianback.repository.RussianSingularPluralNounCoupleJpaRepository;
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
    private RussianSingularPluralNounCoupleJpaRepository rSPNCoupleJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;

    public RussianNounCustom findById(Long id) {
        Optional<RussianNoun> existingRussianNounOptional = russianNounJpaRepository.findById(id);
        if (existingRussianNounOptional.isPresent()) {
            return mapToCustom(existingRussianNounOptional.get());

        } else {
            return null;
        }
    }

    public List<RussianNounCustom> find(String translation, boolean singularPlural) {

        List<RussianNoun> russianNouns = (translation != null) ? russianNounJpaRepository.findByTranslation(translation)
                : russianNounJpaRepository.findAll();

        if (translation != null && singularPlural && !russianNouns.isEmpty()) {
            List<RussianNounCustom> russianNounCustoms = new ArrayList<>();

            RussianNounCustom russianNoun1 = mapToCustom(russianNouns.get(0));
            russianNounCustoms.add(russianNoun1);

            boolean isSingular = "Singular".equals(russianNoun1.getRussianNounCategory().getRussianGrammaticalNumber());

            var rns = isSingular ? rSPNCoupleJpaRepository.findByRussianSingularNounId(russianNoun1.getId())
                    : rSPNCoupleJpaRepository.findByRussianPluralNounId(russianNoun1.getId());

            if (!rns.isEmpty()) {
                Long russianNoun2Id = isSingular ? rns.get(0).getRussianPluralNounId()
                        : rns.get(0).getRussianSingularNounId();

                russianNounJpaRepository.findById(russianNoun2Id).ifPresent(rn -> {
                    RussianNounCustom russianNoun2 = mapToCustom(rn);
                    russianNounCustoms.add(russianNoun2);
                });
            }

            return russianNounCustoms;
        }

        return russianNouns.stream().map(russianNoun -> mapToCustom(russianNoun)).collect(Collectors.toList());
    }

    public RussianNounCustom save(RussianNoun russianNoun) {
        jdbcRepository.resetSequence("russian_noun", "russian_noun_id_seq");

        return mapToCustom(russianNounJpaRepository.save(russianNoun));

    }

    public boolean associateSingularPlural(RussianSingularPluralNounCouple russianSingularPluralNounCouple) {

        Long singularNounId = russianSingularPluralNounCouple.getRussianSingularNounId();
        Long pluralNounId = russianSingularPluralNounCouple.getRussianPluralNounId();

        var existingNounId1 = rSPNCoupleJpaRepository.findByRussianSingularNounId(singularNounId);
        var existingNounId2 = rSPNCoupleJpaRepository.findByRussianSingularNounId(pluralNounId);
        var existingNounId3 = rSPNCoupleJpaRepository.findByRussianPluralNounId(singularNounId);
        var existingNounId4 = rSPNCoupleJpaRepository.findByRussianPluralNounId(pluralNounId);

        if (existingNounId1.isEmpty() && existingNounId2.isEmpty() && existingNounId3.isEmpty()
                && existingNounId4.isEmpty()) {
            jdbcRepository.resetSequence("russian_singular_plural_noun_couple",
                    "russian_singular_plural_noun_couple_id_seq");

            rSPNCoupleJpaRepository.save(russianSingularPluralNounCouple);

            return true;
        }

        return false;
    }

    public RussianNoun update(Long id, RussianNoun updatedRussianNoun) {
        Optional<RussianNoun> russianNounToUpdateOptional = russianNounJpaRepository.findById(id);
        if (russianNounToUpdateOptional.isPresent()) {
            RussianNoun russianNounToUpdate = russianNounToUpdateOptional.get();
            russianNounToUpdate.setRoot(updatedRussianNoun.getRoot());
            russianNounToUpdate.setRussianNounCategoryRefId(updatedRussianNoun.getRussianNounCategoryRefId());
            russianNounToUpdate.setTranslation(updatedRussianNoun.getTranslation());
            russianNounToUpdate.setAnimate(updatedRussianNoun.getIsAnimate());

            return russianNounToUpdate;
        } else {
            return null;
        }
    }

    public void delete(Long id, boolean force) {
        if (force) {
            jdbcRepository.delete("player_game_history", "russian_noun_id", id.toString());
        }

        dissociateSingularPlural(id);
        russianNounJpaRepository.deleteById(id);
    }

    public boolean isPresent(Long id) {
        return russianNounJpaRepository.findById(id).isPresent();
    }

    public boolean isCouplePresent(Long nounId) {

        return !rSPNCoupleJpaRepository.findByRussianSingularNounId(nounId).isEmpty()
                || !rSPNCoupleJpaRepository.findByRussianPluralNounId(nounId).isEmpty();
    }

    public void dissociateSingularPlural(Long nounId) {
        var coupleFromSingular = rSPNCoupleJpaRepository.findByRussianSingularNounId(nounId);
        var coupleFromPlural = rSPNCoupleJpaRepository.findByRussianPluralNounId(nounId);

        if (!coupleFromSingular.isEmpty()) {
            rSPNCoupleJpaRepository.deleteById(coupleFromSingular.get(0).getId());
        }
        if (!coupleFromPlural.isEmpty()) {
            rSPNCoupleJpaRepository.deleteById(coupleFromPlural.get(0).getId());
        }
    }

    public RussianNounCustom mapToCustom(RussianNoun russianNoun) {
        Optional<RussianNounCategoryRef> existingRussianNounCategoryRefOptional = russianNounCategoryRefJpaRepository
                .findById(russianNoun.getRussianNounCategoryRefId());

        RussianNounCustom russianNounCustom = new RussianNounCustom();
        russianNounCustom.setId(russianNoun.getId());
        russianNounCustom.setAnimate(russianNoun.getIsAnimate());
        russianNounCustom.setRoot(russianNoun.getRoot());
        russianNounCustom.setTranslation(russianNoun.getTranslation());

        if (existingRussianNounCategoryRefOptional.isPresent()) {
            var rncr = existingRussianNounCategoryRefOptional.get();
            RussianNounCategoryRefCustom russianNounCategoryRefCustom = new RussianNounCategoryRefCustom();
            russianDeclCatTypeRefJpaRepository.findById(rncr.getRussianDeclCatTypeRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianDeclCatType(data.getValue()));
            russianDeclensionNameRefJpaRepository.findById(rncr.getRussianDeclensionNameRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianDeclensionName(data.getValue()));
            russianGenderRefJpaRepository.findById(rncr.getRussianGenderRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianGender(data.getValue()));
            russianGrammaticalNumberRefJpaRepository.findById(rncr.getRussianGrammaticalNumberRefId())
                    .ifPresent(data -> russianNounCategoryRefCustom.setRussianGrammaticalNumber(data.getValue()));
            russianNounCustom.setRussianNounCategory(russianNounCategoryRefCustom);
        }

        List<RussianNounEndingRefCustom> russianNounEndingRefCustoms = new ArrayList<>();

        String[] nominativeAccusativeGenitive = new String[3];

        russianNounEndingRefJpaRepository.findByRussianNounCategoryRefId(russianNoun.getRussianNounCategoryRefId())
                .forEach(russianNounEnding -> {
                    RussianNounEndingRefCustom russianNounEndingRefCustom = new RussianNounEndingRefCustom();
                    russianCaseRefJpaRepository.findById(russianNounEnding.getRussianCaseRefId())
                            .ifPresent(data -> russianNounEndingRefCustom.setRussianCase(data.getValue()));
                    russianNounEndingRefCustom.setValue(russianNounEnding.getValue());

                    if (!"N/G".equals(russianNounEnding.getValue())) {
                        russianNounEndingRefCustoms.add(russianNounEndingRefCustom);
                    }
                    if ("Nominative".equals(russianNounEndingRefCustom.getRussianCase())) {
                        russianNounCustom
                                .setNominativeForm(russianNounCustom.getRoot() + russianNounEndingRefCustom.getValue());
                        nominativeAccusativeGenitive[0] = russianNounEndingRefCustom.getValue();
                    }
                    if ("Accusative".equals(russianNounEndingRefCustom.getRussianCase())) {
                        nominativeAccusativeGenitive[1] = russianNounEndingRefCustom.getValue();
                    }
                    if ("Genitive".equals(russianNounEndingRefCustom.getRussianCase())) {
                        nominativeAccusativeGenitive[2] = russianNounEndingRefCustom.getValue();
                    }
                });

        if ("N/G".equals(nominativeAccusativeGenitive[1])) {

            RussianNounEndingRefCustom russianNounEndingRefCustom = new RussianNounEndingRefCustom();
            russianNounEndingRefCustom.setRussianCase("Accusative");
            russianNounEndingRefCustom.setValue(russianNounCustom.getIsAnimate() ? nominativeAccusativeGenitive[2]
                    : nominativeAccusativeGenitive[0]);
            russianNounEndingRefCustoms.add(russianNounEndingRefCustom);
        }

        russianNounCustom.setRussianNounEndings(russianNounEndingRefCustoms);

        return russianNounCustom;
    }

}

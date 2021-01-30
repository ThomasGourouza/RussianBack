package com.cavalierfou.russianback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.cavalierfou.russianback.constant.Constant;
import com.cavalierfou.russianback.customentity.RussianNounCustom;
import com.cavalierfou.russianback.entity.MemoryRussianSpecificNounEnding;
import com.cavalierfou.russianback.entity.RussianNoun;
import com.cavalierfou.russianback.entity.RussianNounCategoryRef;
import com.cavalierfou.russianback.entity.RussianSingularPluralNounCouple;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.MemoryRussianSpecificNounEndingJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounCategoryRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounJpaRepository;
import com.cavalierfou.russianback.repository.RussianSingularPluralNounCoupleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RussianNounService {

    @Autowired
    private RussianReferenceService russianReferenceService;
    @Autowired
    private RussianNounJpaRepository russianNounJpaRepository;
    @Autowired
    private RussianNounCategoryRefJpaRepository russianNounCategoryRefJpaRepository;
    @Autowired
    private RussianSingularPluralNounCoupleJpaRepository rSPNCoupleJpaRepository;
    @Autowired
    private MemoryRussianSpecificNounEndingJpaRepository memoryRSNEndingJpaRepository;
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

    public List<RussianNounCustom> find(String translation) {

        List<RussianNoun> russianNouns = (translation != null) ? russianNounJpaRepository.findByTranslation(translation)
                : russianNounJpaRepository.findAll();

        return russianNouns.stream().map(russianNoun -> mapToCustom(russianNoun)).collect(Collectors.toList());
    }

    public RussianNounCustom save(RussianNoun russianNoun) {
        jdbcRepository.resetSequence(Constant.RN.getValue(), Constant.RNIS.getValue());

        return mapToCustom(russianNounJpaRepository.save(russianNoun));

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
            jdbcRepository.delete(Constant.PGH.getValue(), Constant.RNI.getValue(), id.toString());
        }

        jdbcRepository.delete(Constant.MRSNE.getValue(), Constant.RNI.getValue(), id.toString());
        dissociateSingularPlural(id);
        removeRule(id, null);
        russianNounJpaRepository.deleteById(id);
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
            jdbcRepository.resetSequence(Constant.RSPNC.getValue(), Constant.RSPNCIS.getValue());

            rSPNCoupleJpaRepository.save(russianSingularPluralNounCouple);

            return true;
        }

        return false;
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

    public void addRule(MemoryRussianSpecificNounEnding memoryRussianSpecificNounEnding) {
        jdbcRepository.resetSequence(Constant.MRSNE.getValue(), Constant.MRSNEIS.getValue());

        memoryRSNEndingJpaRepository.save(memoryRussianSpecificNounEnding);
    }

    public void removeRule(Long nounId, Long russianDeclSpecEndingRefId) {
        if (russianDeclSpecEndingRefId != null) {
            memoryRSNEndingJpaRepository
                    .findByRussianNounIdAndRussianDeclSpecEndingRefId(nounId, russianDeclSpecEndingRefId)
                    .forEach(memory -> memoryRSNEndingJpaRepository.deleteById(memory.getId()));
        } else {
            memoryRSNEndingJpaRepository.findByRussianNounId(nounId)
                    .forEach(memory -> memoryRSNEndingJpaRepository.deleteById(memory.getId()));
        }
    }

    public boolean isPresent(Long id) {
        return russianNounJpaRepository.findById(id).isPresent();
    }

    public boolean isCouplePresent(Long nounId) {

        return !rSPNCoupleJpaRepository.findByRussianSingularNounId(nounId).isEmpty()
                || !rSPNCoupleJpaRepository.findByRussianPluralNounId(nounId).isEmpty();
    }

    public boolean isRulePresent(Long nounId) {
        return !memoryRSNEndingJpaRepository.findByRussianNounId(nounId).isEmpty();
    }

    public RussianNounCustom mapToCustom(RussianNoun russianNoun) {
        Optional<RussianNounCategoryRef> existingRussianNounCategoryRefOptional = russianNounCategoryRefJpaRepository
                .findById(russianNoun.getRussianNounCategoryRefId());

        RussianNounCustom russianNounCustom = new RussianNounCustom();
        russianNounCustom.setId(russianNoun.getId());
        russianNounCustom.setAnimate(russianNoun.getIsAnimate());
        russianNounCustom.setRoot(russianNoun.getRoot());
        russianNounCustom.setTranslation(russianNoun.getTranslation());
        existingRussianNounCategoryRefOptional.ifPresent(rncr -> russianNounCustom.setRussianNounCategory(
                russianReferenceService.mapRNCRC(rncr, russianNounCustom, russianNounCustom.getIsAnimate())));

        boolean isSingular = Constant.S.getValue()
                .equals(russianNounCustom.getRussianNounCategory().getRussianGrammaticalNumber());
        var rns = isSingular ? rSPNCoupleJpaRepository.findByRussianSingularNounId(russianNounCustom.getId())
                : rSPNCoupleJpaRepository.findByRussianPluralNounId(russianNounCustom.getId());
        if (!rns.isEmpty()) {
            Long singularPluralCoupleNounId = isSingular ? rns.get(0).getRussianPluralNounId()
                    : rns.get(0).getRussianSingularNounId();
            russianNounCustom.setSingularPluralCoupleNounId(singularPluralCoupleNounId);
        }

        return russianNounCustom;
    }

}

package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.cavalierfou.russianback.constant.Constant;
import com.cavalierfou.russianback.customentity.RussianAdjectiveCategoryRefCustom;
import com.cavalierfou.russianback.customentity.RussianAdjectiveEndingRefCustom;
import com.cavalierfou.russianback.customentity.RussianDeclSpecEndingRefCustom;
import com.cavalierfou.russianback.customentity.RussianInterrogativeWordRefCustom;
import com.cavalierfou.russianback.customentity.RussianNounCategoryRefCustom;
import com.cavalierfou.russianback.customentity.RussianNounCustom;
import com.cavalierfou.russianback.customentity.RussianNounEndingRefCustom;
import com.cavalierfou.russianback.entity.RussianAdjectiveCategoryRef;
import com.cavalierfou.russianback.entity.RussianCaseRef;
import com.cavalierfou.russianback.entity.RussianDeclCatTypeRef;
import com.cavalierfou.russianback.entity.RussianDeclSpecEndingRef;
import com.cavalierfou.russianback.entity.RussianDeclensionNameRef;
import com.cavalierfou.russianback.entity.RussianGenderRef;
import com.cavalierfou.russianback.entity.RussianGrammaticalNumberRef;
import com.cavalierfou.russianback.entity.RussianInterrogativeWordRef;
import com.cavalierfou.russianback.entity.RussianNounCategoryRef;
import com.cavalierfou.russianback.entity.RussianRoleRef;
import com.cavalierfou.russianback.repository.MemoryRussianSpecificNounEndingJpaRepository;
import com.cavalierfou.russianback.repository.RussianAdjectiveCategoryRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianAdjectiveEndingRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianCaseRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianDeclCatTypeRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianDeclSpecEndingRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianDeclSpecRuleRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianDeclensionNameRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianGenderRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianGrammaticalNumberRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianInterrogativeWordJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounCategoryRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianNounEndingRefJpaRepository;
import com.cavalierfou.russianback.repository.RussianRoleRefJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RussianReferenceService {

    @Autowired
    private RussianDeclCatTypeRefJpaRepository rDCTypeRefJpaRepository;
    @Autowired
    private RussianDeclensionNameRefJpaRepository rDNameRefJpaRepository;
    @Autowired
    private RussianGrammaticalNumberRefJpaRepository rGNumberRefJpaRepository;
    @Autowired
    private RussianGenderRefJpaRepository rGenderRefJpaRepository;
    @Autowired
    private RussianNounCategoryRefJpaRepository rNounCategoryRefJpaRepository;
    @Autowired
    private RussianAdjectiveCategoryRefJpaRepository rAdjectiveCategoryRefJpaRepository;
    @Autowired
    private RussianDeclCatTypeRefJpaRepository russianDeclCatTypeRefJpaRepository;
    @Autowired
    private RussianDeclensionNameRefJpaRepository russianDeclensionNameRefJpaRepository;
    @Autowired
    private RussianGenderRefJpaRepository russianGenderRefJpaRepository;
    @Autowired
    private RussianNounEndingRefJpaRepository russianNounEndingRefJpaRepository;
    @Autowired
    private RussianCaseRefJpaRepository russianCaseRefJpaRepository;
    @Autowired
    private RussianDeclSpecEndingRefJpaRepository rDSEndingRefJpaRepository;
    @Autowired
    private RussianDeclSpecRuleRefJpaRepository rDSRuleRefJpaRepository;
    @Autowired
    private MemoryRussianSpecificNounEndingJpaRepository memoryRSNEndingJpaRepository;
    @Autowired
    private RussianInterrogativeWordJpaRepository rInterrogativeWordJpaRepository;
    @Autowired
    private RussianRoleRefJpaRepository russianRoleRefJpaRepository;
    @Autowired
    private RussianAdjectiveEndingRefJpaRepository russianAdjectiveEndingRefJpaRepository;

    public List<RussianDeclCatTypeRef> findDeclCatType() {
        return rDCTypeRefJpaRepository.findAll();
    }

    public List<RussianDeclensionNameRef> findDeclensionName() {
        return rDNameRefJpaRepository.findAll();
    }

    public List<RussianGrammaticalNumberRef> findGrammaticalNumber() {
        return rGNumberRefJpaRepository.findAll();
    }

    public List<RussianGenderRef> findGender() {
        return rGenderRefJpaRepository.findAll();
    }

    public List<RussianNounCategoryRefCustom> findNounCategory(boolean isNounAnimate) {
        List<RussianNounCategoryRefCustom> russianNounCategoryRefCustoms = new ArrayList<>();

        rNounCategoryRefJpaRepository.findAll().forEach(russianNounCategoryRef -> russianNounCategoryRefCustoms
                .add(mapRNCRC(russianNounCategoryRef, null, isNounAnimate)));

        return russianNounCategoryRefCustoms;
    }

    public RussianNounCategoryRefCustom findNounCategoryById(Long id, boolean isNounAnimate) {
        Optional<RussianNounCategoryRef> existingRussianNounCategoryRefOptional = rNounCategoryRefJpaRepository
                .findById(id);
        if (existingRussianNounCategoryRefOptional.isPresent()) {
            return mapRNCRC(existingRussianNounCategoryRefOptional.get(), null, isNounAnimate);
        } else {
            return null;
        }
    }

    public List<RussianRoleRef> findRole() {
        return russianRoleRefJpaRepository.findAll();
    }

    public List<RussianCaseRef> findCase() {
        return russianCaseRefJpaRepository.findAll();
    }

    public List<RussianInterrogativeWordRefCustom> findInterrogativeWord() {
        List<RussianInterrogativeWordRefCustom> rIWordRefCustoms = new ArrayList<>();
        rInterrogativeWordJpaRepository.findAll().forEach(rIWordRef -> rIWordRefCustoms.add(mapRIWC(rIWordRef)));

        return rIWordRefCustoms;
    }

    public List<RussianAdjectiveCategoryRefCustom> findAdjectiveCategory() {
        List<RussianAdjectiveCategoryRefCustom> russianAdjectiveCategoryRefCustoms = new ArrayList<>();

        rAdjectiveCategoryRefJpaRepository.findAll()
                .forEach(russianAdjectiveCategoryRef -> russianAdjectiveCategoryRefCustoms
                        .add(mapRACRC(russianAdjectiveCategoryRef)));

        return russianAdjectiveCategoryRefCustoms;
    }

    public RussianAdjectiveCategoryRefCustom findAdjectiveCategoryById(Long id) {
        Optional<RussianAdjectiveCategoryRef> existingRussianAdjectiveCategoryRefOptional = rAdjectiveCategoryRefJpaRepository
                .findById(id);
        if (existingRussianAdjectiveCategoryRefOptional.isPresent()) {
            return mapRACRC(existingRussianAdjectiveCategoryRefOptional.get());
        } else {
            return null;
        }
    }

    private RussianInterrogativeWordRefCustom mapRIWC(RussianInterrogativeWordRef rIWordRef) {
        RussianInterrogativeWordRefCustom rIWordRefCustom = new RussianInterrogativeWordRefCustom();
        russianCaseRefJpaRepository.findById(rIWordRef.getRussianCaseRefId())
                .ifPresent(rCase -> rIWordRefCustom.setRussianCase(rCase.getValue()));
        russianRoleRefJpaRepository.findById(rIWordRef.getRussianRoleRefId())
                .ifPresent(rRole -> rIWordRefCustom.setRussianRole(rRole.getValue()));
        rIWordRefCustom.setValue(rIWordRef.getValue());

        return rIWordRefCustom;
    }

    public RussianNounCategoryRefCustom mapRNCRC(RussianNounCategoryRef rncr, RussianNounCustom russianNounCustom,
            boolean isAnimate) {
        RussianNounCategoryRefCustom russianNounCategoryRefCustom = new RussianNounCategoryRefCustom();
        russianNounCategoryRefCustom.setId(rncr.getId());
        russianDeclCatTypeRefJpaRepository.findById(rncr.getRussianDeclCatTypeRefId())
                .ifPresent(data -> russianNounCategoryRefCustom.setRussianDeclCatType(data.getValue()));
        russianDeclensionNameRefJpaRepository.findById(rncr.getRussianDeclensionNameRefId())
                .ifPresent(data -> russianNounCategoryRefCustom.setRussianDeclensionName(data.getValue()));
        russianGenderRefJpaRepository.findById(rncr.getRussianGenderRefId())
                .ifPresent(data -> russianNounCategoryRefCustom.setRussianGender(data.getValue()));
        rGNumberRefJpaRepository.findById(rncr.getRussianGrammaticalNumberRefId())
                .ifPresent(data -> russianNounCategoryRefCustom.setRussianGrammaticalNumber(data.getValue()));

        List<RussianNounEndingRefCustom> russianNounEndingRefCustoms = new ArrayList<>();
        String[] nominativeAccusativeGenitive = new String[3];

        russianNounEndingRefJpaRepository.findByRussianNounCategoryRefId(rncr.getId()).forEach(russianNounEnding -> {

            RussianNounEndingRefCustom russianNounEndingRefCustom = new RussianNounEndingRefCustom();

            Long nounId = russianNounCustom != null ? russianNounCustom.getId() : null;
            List<RussianDeclSpecEndingRefCustom> russianDeclSpecEndingRefs = rDSEndingRefJpaRepository
                    .findByRussianNounEndingRefId(russianNounEnding.getId()).stream()
                    .map(rdser -> mapRDSERC(rdser, nounId)).collect(Collectors.toList());

            russianNounEndingRefCustom.setSpecificEndingRules(russianDeclSpecEndingRefs);
            russianCaseRefJpaRepository.findById(russianNounEnding.getRussianCaseRefId())
                    .ifPresent(data -> russianNounEndingRefCustom.setRussianCase(data.getValue()));
            russianNounEndingRefCustom.setValue(russianNounEnding.getValue());
            russianNounEndingRefCustoms.add(russianNounEndingRefCustom);

            String[] endingValue = new String[1];
            russianNounEndingRefCustom.getSpecificEndingRules().stream()
                    .filter(rule -> rule.isApplied() && rule.getValue() != null).findFirst()
                    .ifPresentOrElse(r -> endingValue[0] = r.getValue(),
                            () -> endingValue[0] = russianNounEndingRefCustom.getValue());

            if (Constant.N.getValue().equals(russianNounEndingRefCustom.getRussianCase())) {
                if (russianNounCustom != null) {
                    russianNounCustom
                            .setNominativeForm(russianNounCustom.getRoot() + russianNounEndingRefCustom.getValue());
                }
                nominativeAccusativeGenitive[0] = endingValue[0];
            }
            if (Constant.A.getValue().equals(russianNounEndingRefCustom.getRussianCase())) {
                nominativeAccusativeGenitive[1] = endingValue[0];
            }
            if (Constant.G.getValue().equals(russianNounEndingRefCustom.getRussianCase())) {
                nominativeAccusativeGenitive[2] = endingValue[0];
            }
        });

        if (Constant.NG.getValue().equals(nominativeAccusativeGenitive[1])) {
            russianNounEndingRefCustoms.stream()
                    .filter(rNEndingRefCustom -> Constant.NG.getValue().equals(rNEndingRefCustom.getValue())
                            && rNEndingRefCustom.getSpecificEndingRules().size() > 1)
                    .findFirst().ifPresent(rNEnding -> {
                        Pattern nominative = Pattern.compile(Constant.N.getValue(), Pattern.CASE_INSENSITIVE);
                        rNEnding.getSpecificEndingRules().forEach(rule -> {
                            if (nominative.matcher(rule.getRule()).find()) {
                                rule.setValue(nominativeAccusativeGenitive[0]);
                                rule.setApplied(!isAnimate);
                            } else {
                                rule.setValue(nominativeAccusativeGenitive[2]);
                                rule.setApplied(isAnimate);
                            }
                        });
                    });
        }
        russianNounCategoryRefCustom.setRussianNounEndings(russianNounEndingRefCustoms);

        return russianNounCategoryRefCustom;
    }

    private RussianDeclSpecEndingRefCustom mapRDSERC(RussianDeclSpecEndingRef rdser, Long nounId) {
        RussianDeclSpecEndingRefCustom rdserCustom = new RussianDeclSpecEndingRefCustom();
        rdserCustom.setId(rdser.getId());

        if (!Constant.NG.getValue().equals(rdser.getValue())) {
            rdserCustom.setValue(rdser.getValue());
            if (nounId != null) {
                rdserCustom.setApplied(!memoryRSNEndingJpaRepository
                        .findByRussianNounIdAndRussianDeclSpecEndingRefId(nounId, rdser.getId()).isEmpty());
            } else {
                rdserCustom.setApplied(false);
            }
        } else {
            // the correct value is set later
            rdserCustom.setApplied(false);
        }

        rDSRuleRefJpaRepository.findById(rdser.getRussianDeclSpecRuleRefId())
                .ifPresent(rule -> rdserCustom.setRule(rule.getValue()));

        return rdserCustom;
    }

    public RussianAdjectiveCategoryRefCustom mapRACRC(RussianAdjectiveCategoryRef russianAdjectiveCategoryRef) {
        RussianAdjectiveCategoryRefCustom russianAdjectiveCategoryRefCustom = new RussianAdjectiveCategoryRefCustom();
        russianAdjectiveCategoryRefCustom.setId(russianAdjectiveCategoryRef.getId());
        russianAdjectiveCategoryRefCustom.setValue(russianAdjectiveCategoryRef.getValue());
        russianAdjectiveCategoryRefCustom.setEndings(mapRAERC(russianAdjectiveCategoryRef.getId()));

        return russianAdjectiveCategoryRefCustom;
    }

    private List<RussianAdjectiveEndingRefCustom> mapRAERC(Long russianAdjectiveCategoryRefId) {
        List<RussianAdjectiveEndingRefCustom> russianAdjectiveEndingRefCustoms = new ArrayList<>();
        russianAdjectiveEndingRefJpaRepository.findByRussianAdjectiveCategoryRefId(russianAdjectiveCategoryRefId)
                .forEach(existingRussianAdjectiveEndingRef -> {
                    RussianAdjectiveEndingRefCustom russianAdjectiveEndingRefCustom = new RussianAdjectiveEndingRefCustom();
                    russianCaseRefJpaRepository.findById(existingRussianAdjectiveEndingRef.getRussianCaseRefId())
                            .ifPresent(russianCase -> russianAdjectiveEndingRefCustom
                                    .setRussianCase(russianCase.getValue()));
                    russianGenderRefJpaRepository.findById(existingRussianAdjectiveEndingRef.getRussianGenderRefId())
                            .ifPresent(russianGender -> russianAdjectiveEndingRefCustom
                                    .setRussianGender(russianGender.getValue()));
                    russianAdjectiveEndingRefCustom.setValue(existingRussianAdjectiveEndingRef.getValue());

                    russianAdjectiveEndingRefCustoms.add(russianAdjectiveEndingRefCustom);
                });

        return russianAdjectiveEndingRefCustoms;
    }

}

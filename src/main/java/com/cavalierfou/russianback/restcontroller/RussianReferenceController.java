package com.cavalierfou.russianback.restcontroller;

import java.util.List;

import com.cavalierfou.russianback.customentity.RussianInterrogativeWordRefCustom;
import com.cavalierfou.russianback.customentity.RussianNounCategoryRefCustom;
import com.cavalierfou.russianback.entity.RussianCaseRef;
import com.cavalierfou.russianback.entity.RussianDeclCatTypeRef;
import com.cavalierfou.russianback.entity.RussianDeclensionNameRef;
import com.cavalierfou.russianback.entity.RussianGenderRef;
import com.cavalierfou.russianback.entity.RussianGrammaticalNumberRef;
import com.cavalierfou.russianback.entity.RussianInterrogativeWordRef;
import com.cavalierfou.russianback.entity.RussianRoleRef;
import com.cavalierfou.russianback.service.RussianReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/russian_reference")
class RussianReferenceController {

    @Autowired
    private RussianReferenceService russianReferenceService;

    @GetMapping("/declension_category_type")
    public ResponseEntity<List<RussianDeclCatTypeRef>> getDeclCatType() {
        try {
            return new ResponseEntity<>(russianReferenceService.findDeclCatType(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/declension_name")
    public ResponseEntity<List<RussianDeclensionNameRef>> getDeclensionName() {
        try {
            return new ResponseEntity<>(russianReferenceService.findDeclensionName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/grammatical_number")
    public ResponseEntity<List<RussianGrammaticalNumberRef>> getGrammaticalNumber() {
        try {
            return new ResponseEntity<>(russianReferenceService.findGrammaticalNumber(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/russian_gender")
    public ResponseEntity<List<RussianGenderRef>> getGender() {
        try {
            return new ResponseEntity<>(russianReferenceService.findGender(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/noun_category")
    public ResponseEntity<List<RussianNounCategoryRefCustom>> getNounCategory(
            @RequestParam(value = "is_noun_animate", required = false) Boolean isNounAnimate) {
        try {
            List<RussianNounCategoryRefCustom> russianNounCategoryRefCustoms = russianReferenceService
                    .findNounCategory(isNounAnimate != null ? isNounAnimate : false);
            if (russianNounCategoryRefCustoms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(russianNounCategoryRefCustoms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/noun_category/{id}")
    public ResponseEntity<RussianNounCategoryRefCustom> getNounCategoryById(@PathVariable("id") Long id,
            @RequestParam(value = "is_noun_animate", required = false) Boolean isNounAnimate) {

        RussianNounCategoryRefCustom russianNounCategoryRefCustom = russianReferenceService.findNounCategoryById(id,
                isNounAnimate != null ? isNounAnimate : false);

        if (russianNounCategoryRefCustom != null) {
            return new ResponseEntity<>(russianNounCategoryRefCustom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/russian_role")
    public ResponseEntity<List<RussianRoleRef>> getRole() {
        try {
            return new ResponseEntity<>(russianReferenceService.findRole(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/russian_case")
    public ResponseEntity<List<RussianCaseRef>> getCase() {
        try {
            return new ResponseEntity<>(russianReferenceService.findCase(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/interrogative_word")
    public ResponseEntity<List<RussianInterrogativeWordRefCustom>> getInterrogativeWord() {
        try {
            return new ResponseEntity<>(russianReferenceService.findInterrogativeWord(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
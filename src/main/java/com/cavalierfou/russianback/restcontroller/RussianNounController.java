package com.cavalierfou.russianback.restcontroller;

import java.util.List;
import com.cavalierfou.russianback.customentity.RussianNounCustom;
import com.cavalierfou.russianback.entity.MemoryRussianSpecificNounEnding;
import com.cavalierfou.russianback.entity.RussianNoun;
import com.cavalierfou.russianback.entity.RussianSingularPluralNounCouple;
import com.cavalierfou.russianback.service.RussianNounService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/v1/noun")
class RussianNounController {

    @Autowired
    private RussianNounService russianNounService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<RussianNounCustom>> getRussianNouns(
            @RequestParam(value = "translation", required = false) String translation) {
        try {
            List<RussianNounCustom> russianNounCustoms = russianNounService.find(translation);
            if (russianNounCustoms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(russianNounCustoms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{id}")
    public ResponseEntity<RussianNounCustom> getById(@PathVariable("id") Long id) {
        RussianNounCustom existingRussianNounCustom = russianNounService.findById(id);
        if (existingRussianNounCustom != null) {
            return new ResponseEntity<>(existingRussianNounCustom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("{id}")
    public ResponseEntity<RussianNounCustom> update(@PathVariable("id") Long id,
            @RequestBody RussianNoun updatedRussianNoun) {
        RussianNoun russianNoun = russianNounService.update(id, updatedRussianNoun);
        if (russianNoun == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                return new ResponseEntity<>(russianNounService.save(russianNoun), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<RussianNounCustom> create(@RequestBody RussianNoun russianNounToSave) {
        try {
            return new ResponseEntity<>(russianNounService.save(russianNounToSave), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id,
            @RequestParam(value = "force", required = false) boolean force) {
        if (russianNounService.isPresent(id)) {
            try {
                russianNounService.delete(id, force);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/singular_plural")
    public ResponseEntity<HttpStatus> associate(
            @RequestBody RussianSingularPluralNounCouple russianSingularPluralNounCouple) {
        if (russianNounService.isPresent(russianSingularPluralNounCouple.getRussianSingularNounId())
                && russianNounService.isPresent(russianSingularPluralNounCouple.getRussianPluralNounId())) {
            try {
                return russianNounService.associateSingularPlural(russianSingularPluralNounCouple)
                        ? new ResponseEntity<>(HttpStatus.CREATED)
                        : new ResponseEntity<>(HttpStatus.CONFLICT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/singular_plural")
    public ResponseEntity<HttpStatus> dissociateByNounId(
            @RequestParam(value = "noun_id", required = true) Long nounId) {
        if (russianNounService.isCouplePresent(nounId)) {
            try {
                russianNounService.dissociateSingularPlural(nounId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/specific_rule")
    public ResponseEntity<HttpStatus> saveRule(
            @RequestBody List<MemoryRussianSpecificNounEnding> memoryRussianSpecificNounEndings) {
        boolean[] found = new boolean[1];
        found[0] = true;
        memoryRussianSpecificNounEndings.forEach(spec -> {
            if (!russianNounService.isPresent(spec.getRussianNounId())) {
                found[0] = false;
            }
        });
        if (found[0]) {
            try {
                russianNounService.addRules(memoryRussianSpecificNounEndings);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/specific_rule")
    public ResponseEntity<HttpStatus> deleteRule(@RequestParam(value = "noun_id", required = true) Long nounId,
            @RequestParam(value = "spec_id", required = false) Long russianDeclSpecEndingRefId) {
        if (russianNounService.isRulePresent(nounId)) {
            try {
                russianNounService.removeRule(nounId, russianDeclSpecEndingRefId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
package com.cavalierfou.russianback.restcontroller;

import java.util.List;

import com.cavalierfou.russianback.customentity.RussianNounCustom;
// import com.cavalierfou.russianback.customentity.RussianNounCustom;
import com.cavalierfou.russianback.entity.RussianNoun;
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

@RestController
@RequestMapping("/api/v1/noun")
class RussianNounController {

    @Autowired
    private RussianNounService russianNounService;

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

    @GetMapping("{id}")
    public ResponseEntity<RussianNounCustom> getById(@PathVariable("id") Long id) {
        RussianNounCustom existingRussianNounCustom = russianNounService.findById(id);
        if (existingRussianNounCustom != null) {
            return new ResponseEntity<>(existingRussianNounCustom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RussianNounCustom> create(@RequestBody RussianNoun russianNounToSave) {
        try {
            return new ResponseEntity<>(russianNounService.save(russianNounToSave), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    // @PutMapping("{id}")
    // public ResponseEntity<RussianNoun> update(@PathVariable("id") Long id,
    //         @RequestBody RussianNoun updatedRussianNoun) {
    //     RussianNoun russianNoun = russianNounService.update(id, updatedRussianNoun);
    //     if (russianNoun == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     } else {
    //         try {
    //             return new ResponseEntity<>(russianNounService.save(russianNoun), HttpStatus.OK);
    //         } catch (Exception e) {
    //             return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    //         }
    //     }
    // }

    // @DeleteMapping("{id}")
    // public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id,
    //         @RequestParam(value = "force", required = false) boolean force) {
    //     if (russianNounService.isPresent(id)) {
    //         try {
    //             russianNounService.delete(id, force);
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         } catch (Exception e) {
    //             return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    //         }
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}
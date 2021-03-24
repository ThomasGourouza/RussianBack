package com.cavalierfou.russianback.restcontroller;

import java.util.List;
import com.cavalierfou.russianback.customentity.RussianAdjectiveCustom;
import com.cavalierfou.russianback.entity.RussianAdjective;
import com.cavalierfou.russianback.service.RussianAdjectiveService;
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
@RequestMapping("/api/v1/adjective")
class RussianAdjectiveController {

    @Autowired
    private RussianAdjectiveService russianAdjectiveService;

    @CrossOrigin(origins = "http://localhost:53184")
    @GetMapping
    public ResponseEntity<List<RussianAdjectiveCustom>> getRussianAdjectives(
            @RequestParam(value = "translation", required = false) String translation) {
        try {
            List<RussianAdjectiveCustom> russianAdjectiveCustoms = russianAdjectiveService.find(translation);
            if (russianAdjectiveCustoms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(russianAdjectiveCustoms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @GetMapping("{id}")
    public ResponseEntity<RussianAdjectiveCustom> getById(@PathVariable("id") Long id) {
        RussianAdjectiveCustom existingRussianAdjectiveCustom = russianAdjectiveService.findById(id);
        if (existingRussianAdjectiveCustom != null) {
            return new ResponseEntity<>(existingRussianAdjectiveCustom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @PostMapping
    public ResponseEntity<RussianAdjectiveCustom> create(@RequestBody RussianAdjective russianAdjectiveToSave) {
        try {
            return new ResponseEntity<>(russianAdjectiveService.save(russianAdjectiveToSave), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @PutMapping("{id}")
    public ResponseEntity<RussianAdjectiveCustom> update(@PathVariable("id") Long id,
            @RequestBody RussianAdjective updatedRussianAdjective) {
        RussianAdjective russianAdjective = russianAdjectiveService.update(id, updatedRussianAdjective);
        if (russianAdjective == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                return new ResponseEntity<>(russianAdjectiveService.save(russianAdjective), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id,
            @RequestParam(value = "force", required = false) boolean force) {
        if (russianAdjectiveService.isPresent(id)) {
            try {
                russianAdjectiveService.delete(id, force);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
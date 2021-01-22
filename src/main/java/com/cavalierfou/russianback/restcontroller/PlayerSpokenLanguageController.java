package com.cavalierfou.russianback.restcontroller;

import java.util.List;
import com.cavalierfou.russianback.customentity.PlayerSpokenLanguageCustom;
import com.cavalierfou.russianback.entity.PlayerSpokenLanguage;
import com.cavalierfou.russianback.service.PlayerSpokenLanguageService;
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
@RequestMapping("/api/v1/player/spokenLanguage")
class PlayerSpokenLanguageController {

    @Autowired
    private PlayerSpokenLanguageService playerSpokenLanguageService;

    @GetMapping
    public ResponseEntity<List<PlayerSpokenLanguageCustom>> getPlayerSpokenLanguage(
            @RequestParam(value = "ids", required = false) String ids,
            @RequestParam(value = "playerId", required = false) Long playerId,
            @RequestParam(value = "languageIds", required = false) String languageIds) {
        try {
            List<PlayerSpokenLanguageCustom> playerCustoms = playerSpokenLanguageService.find(ids, playerId,
                    languageIds);
            if (playerCustoms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(playerCustoms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerSpokenLanguageCustom> getById(@PathVariable("id") Long id) {
        PlayerSpokenLanguageCustom existingPlayerSpokenLanguageCustom = playerSpokenLanguageService.findById(id);
        if (existingPlayerSpokenLanguageCustom != null) {
            return new ResponseEntity<>(existingPlayerSpokenLanguageCustom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<List<PlayerSpokenLanguageCustom>> create(
            @RequestBody List<PlayerSpokenLanguage> playerSpokenLanguagesToSave) {
        try {
            return new ResponseEntity<>(playerSpokenLanguageService.save(playerSpokenLanguagesToSave),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PlayerSpokenLanguageCustom> update(@PathVariable("id") Long id,
            @RequestBody PlayerSpokenLanguage updatedPlayerSpokenLanguage) {
        PlayerSpokenLanguage playerSpokenLanguage = playerSpokenLanguageService.update(id, updatedPlayerSpokenLanguage);
        if (playerSpokenLanguage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                return new ResponseEntity<>(playerSpokenLanguageService.save(List.of(playerSpokenLanguage)).get(0),
                        HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        if (playerSpokenLanguageService.isPresent(id)) {
            try {
                playerSpokenLanguageService.delete(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
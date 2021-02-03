package com.cavalierfou.russianback.restcontroller;

import java.util.List;
import com.cavalierfou.russianback.entity.BirthCountryRef;
import com.cavalierfou.russianback.entity.GenderRef;
import com.cavalierfou.russianback.entity.ImageRef;
import com.cavalierfou.russianback.entity.LanguageRef;
import com.cavalierfou.russianback.entity.LevelRef;
import com.cavalierfou.russianback.service.PlayerReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reference/player")
class ReferencePlayerController {

    @Autowired
    private PlayerReferenceService playerReferenceService;

    @GetMapping("/country")
    public ResponseEntity<List<BirthCountryRef>> getCountry() {
        try {
            return new ResponseEntity<>(playerReferenceService.findCountry(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gender")
    public ResponseEntity<List<GenderRef>> getPlayerGender() {
        try {
            return new ResponseEntity<>(playerReferenceService.findGender(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/image")
    public ResponseEntity<List<ImageRef>> getImage() {
        try {
            return new ResponseEntity<>(playerReferenceService.findImage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/language")
    public ResponseEntity<List<LanguageRef>> getLanguage() {
        try {
            return new ResponseEntity<>(playerReferenceService.findLanguage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/language/level")
    public ResponseEntity<List<LevelRef>> getLevel() {
        try {
            return new ResponseEntity<>(playerReferenceService.findLevel(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
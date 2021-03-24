package com.cavalierfou.russianback.restcontroller;

import java.util.List;

import com.cavalierfou.russianback.customentity.PlayerGameCustom;
import com.cavalierfou.russianback.entity.PlayerGame;
import com.cavalierfou.russianback.service.PlayerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/v1/player/game")
class PlayerGameController {

    @Autowired
    private PlayerGameService playerGameService;

    @CrossOrigin(origins = "http://localhost:53184")
    @GetMapping
    public ResponseEntity<List<PlayerGameCustom>> getPlayerGames(
            @RequestParam(value = "player_id", required = false) Long playerId) {
        try {
            List<PlayerGameCustom> playerGames = playerGameService.find(playerId);
            if (playerGames.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(playerGames, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @GetMapping("{id}")
    public ResponseEntity<PlayerGameCustom> getById(@PathVariable("id") Long id) {
        PlayerGameCustom existingPlayerGame = playerGameService.findById(id);
        if (existingPlayerGame != null) {
            return new ResponseEntity<>(existingPlayerGame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @PostMapping
    public ResponseEntity<PlayerGameCustom> create(@RequestBody PlayerGame playerGameToSave) {
        try {
            return new ResponseEntity<>(playerGameService.save(playerGameToSave), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @CrossOrigin(origins = "http://localhost:53184")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        if (playerGameService.isPresent(id)) {
            try {
                playerGameService.delete(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
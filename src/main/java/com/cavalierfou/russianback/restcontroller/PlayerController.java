package com.cavalierfou.russianback.restcontroller;

import java.util.List;
import com.cavalierfou.russianback.customentity.PlayerCustom;
import com.cavalierfou.russianback.entity.Player;
import com.cavalierfou.russianback.service.PlayerService;
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
@RequestMapping("/api/v1/player")
class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerCustom>> getPlayers(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "languageIds", required = false) String languageIds) {
        try {
            List<PlayerCustom> playerCustoms = playerService.find(login, languageIds);
            if (playerCustoms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(playerCustoms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerCustom> getById(@PathVariable("id") Long id) {
        PlayerCustom existingPlayerCustom = playerService.findById(id);
        if (existingPlayerCustom != null) {
            return new ResponseEntity<>(existingPlayerCustom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PlayerCustom> create(@RequestBody Player playerToSave) {
        try {
            return new ResponseEntity<>(playerService.save(playerToSave), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PlayerCustom> update(@PathVariable("id") Long id, @RequestBody Player updatedPlayer) {
        Player player = playerService.update(id, updatedPlayer);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                return new ResponseEntity<>(playerService.save(player), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id,
            @RequestParam(value = "force", required = false) boolean force) {
        if (playerService.isPresent(id)) {
            try {
                playerService.delete(id, force);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cavalierfou.russianback.constant.Constant;
import com.cavalierfou.russianback.customentity.PlayerCustom;
import com.cavalierfou.russianback.customentity.PlayerSpokenLanguageCustom;
import com.cavalierfou.russianback.entity.BirthCountryRef;
import com.cavalierfou.russianback.entity.GenderRef;
import com.cavalierfou.russianback.entity.ImageRef;
import com.cavalierfou.russianback.entity.Player;
import com.cavalierfou.russianback.entity.PlayerSpokenLanguage;
import com.cavalierfou.russianback.repository.BirthCountryRefJpaRepository;
import com.cavalierfou.russianback.repository.GenderRefJpaRepository;
import com.cavalierfou.russianback.repository.ImageRefJpaRepository;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.PlayerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;
    @Autowired
    private BirthCountryRefJpaRepository birthCountryRefJpaRepository;
    @Autowired
    private GenderRefJpaRepository genderRefJpaRepository;
    @Autowired
    private ImageRefJpaRepository imageRefJpaRepository;
    @Autowired
    private PlayerSpokenLanguageService playerSpokenLanguageService;

    public PlayerCustom findById(Long id) {
        Optional<Player> existingPlayerOptional = playerJpaRepository.findById(id);
        if (existingPlayerOptional.isPresent()) {
            return this.mapToCustom(existingPlayerOptional.get());
        } else {
            return null;
        }
    }

    public List<PlayerCustom> find(String login, String password, String languageIds) {
        List<Player> players;
        if (login != null || password != null) {
            if (login != null && password != null) {
                players = playerJpaRepository.findByLoginAndPassword(login, password);
            } else {
                return new ArrayList<>();
            }
        } else if (languageIds != null) {
            List<PlayerSpokenLanguageCustom> playerSpokenLanguages = playerSpokenLanguageService.find(null, null,
                    languageIds);
            players = playerJpaRepository.findByIdIn(playerSpokenLanguages.stream()
                    .map(playerSpokenLanguage -> playerSpokenLanguage.getPlayerId()).collect(Collectors.toList()));
        } else {
            players = playerJpaRepository.findAll();
        }
        List<PlayerCustom> playerCustoms = new ArrayList<>();
        if (players.isEmpty()) {
            return playerCustoms;
        }
        players.forEach(player -> playerCustoms.add(mapToCustom(player)));

        return playerCustoms;
    }

    public PlayerCustom save(Player player) {
        jdbcRepository.resetSequence(Constant.P.getValue(), Constant.PIS.getValue());
        jdbcRepository.resetSequence(Constant.PSL.getValue(), Constant.PSLIS.getValue());

        Player savedPlayer = playerJpaRepository.save(player);
        if (player.getPlayerSpokenLanguages() != null && !player.getPlayerSpokenLanguages().isEmpty()) {
            player.getPlayerSpokenLanguages()
                    .forEach(playerSpokenLanguage -> playerSpokenLanguage.setPlayerId(savedPlayer.getId()));
            playerSpokenLanguageService.save(player.getPlayerSpokenLanguages());
        }
        return mapToCustom(savedPlayer);
    }

    public Player update(Long id, Player updatedPlayer) {
        Optional<Player> playerToUpdateOptional = playerJpaRepository.findById(id);
        if (playerToUpdateOptional.isPresent()) {
            Player playerToUpdate = playerToUpdateOptional.get();
            playerToUpdate.setBirthCountryRefId(updatedPlayer.getBirthCountryRefId());
            playerToUpdate.setBirthDate(updatedPlayer.getBirthDate());
            playerToUpdate.setEmail(updatedPlayer.getEmail());
            playerToUpdate.setFirstName(updatedPlayer.getFirstName());
            playerToUpdate.setGenderRefId(updatedPlayer.getGenderRefId());
            playerToUpdate.setImageRefId(updatedPlayer.getImageRefId());
            playerToUpdate.setLastName(updatedPlayer.getLastName());
            playerToUpdate.setLogin(updatedPlayer.getLogin());
            playerToUpdate.setPassword(updatedPlayer.getPassword());
            playerToUpdate.setPhone(updatedPlayer.getPhone());
            return playerToUpdate;
        } else {
            return null;
        }
    }

    public void delete(Long id, boolean force) {
        jdbcRepository.delete(Constant.PSL.getValue(), Constant.PI.getValue(), id.toString());
        if (force) {
            jdbcRepository.delete(Constant.PGH.getValue(), Constant.PGI.getValue(), "(select id from "
                    + Constant.PG.getValue() + " where " + Constant.PI.getValue() + " = " + id.toString() + ")");
            jdbcRepository.delete(Constant.PG.getValue(), Constant.PI.getValue(), id.toString());
        }
        playerJpaRepository.deleteById(id);
    }

    public boolean isPresent(Long id) {
        return playerJpaRepository.findById(id).isPresent();
    }

    public PlayerCustom mapToCustom(Player player) {
        Optional<BirthCountryRef> birthCountryRefOptional = birthCountryRefJpaRepository
                .findById(player.getBirthCountryRefId());
        Optional<GenderRef> genderRefOptional = genderRefJpaRepository.findById(player.getGenderRefId());
        Optional<ImageRef> imageRefOptional = imageRefJpaRepository.findById(player.getImageRefId());
        List<PlayerSpokenLanguage> playerSpokenLanguages = player.getPlayerSpokenLanguages();

        PlayerCustom playerCustom = new PlayerCustom();
        playerCustom.setId(player.getId());
        playerCustom.setBirthDate(player.getBirthDate());
        playerCustom.setEmail(player.getEmail());
        playerCustom.setFirstName(player.getFirstName());
        playerCustom.setLastName(player.getLastName());
        playerCustom.setLogin(player.getLogin());
        playerCustom.setPhone(player.getPhone());
        birthCountryRefOptional.ifPresent(birthCountryRef -> playerCustom.setBirthCountry(birthCountryRef.getValue()));
        genderRefOptional.ifPresent(genderRef -> playerCustom.setGender(genderRef.getValue()));
        imageRefOptional.ifPresent(imageRef -> playerCustom.setImageUrl(imageRef.getValue()));

        List<PlayerSpokenLanguageCustom> playerSpokenLanguageCustoms = new ArrayList<>();
        if (playerSpokenLanguages != null && !playerSpokenLanguages.isEmpty()) {
            playerSpokenLanguageCustoms = playerSpokenLanguageService.find(null, player.getId(), null);
        }
        playerCustom.setPlayerSpokenLanguages(playerSpokenLanguageCustoms);

        return playerCustom;
    }
}

package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.cavalierfou.russianback.customentity.PlayerSpokenLanguageCustom;
import com.cavalierfou.russianback.entity.LanguageRef;
import com.cavalierfou.russianback.entity.LevelRef;
import com.cavalierfou.russianback.entity.PlayerSpokenLanguage;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.LanguageRefJpaRepository;
import com.cavalierfou.russianback.repository.LevelRefJpaRepository;
import com.cavalierfou.russianback.repository.PlayerSpokenLanguageJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerSpokenLanguageService {

    @Autowired
    private PlayerSpokenLanguageJpaRepository playerSpokenLanguageJpaRepository;
    @Autowired
    private LanguageRefJpaRepository languageRefJpaRepository;
    @Autowired
    private LevelRefJpaRepository levelRefJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;
    @Autowired
    private Utils utils;

    public PlayerSpokenLanguageCustom findById(Long id) {
        Optional<PlayerSpokenLanguage> existingPlayerOptional = playerSpokenLanguageJpaRepository.findById(id);
        if (existingPlayerOptional.isPresent()) {
            return this.mapToCustom(existingPlayerOptional.get());
        } else {
            return null;
        }
    }

    public List<PlayerSpokenLanguageCustom> find(String ids, Long playerId, String languageIds) {
        List<Long> idList = utils.mapRequestParamIdsToListIds(ids);
        List<Long> languageIdList = utils.mapRequestParamIdsToListIds(languageIds);

        List<PlayerSpokenLanguage> playerSpokenLanguages;
        if (!idList.isEmpty()) {
            playerSpokenLanguages = playerSpokenLanguageJpaRepository.findByIdIn(idList);
        } else if (playerId != null) {
            playerSpokenLanguages = playerSpokenLanguageJpaRepository.findByPlayerId(playerId);
        } else if(!languageIdList.isEmpty()) {
            playerSpokenLanguages = playerSpokenLanguageJpaRepository.findByLanguageRefIdIn(languageIdList);
        } else {
            playerSpokenLanguages = playerSpokenLanguageJpaRepository.findAll();
        }
        return (playerSpokenLanguages.isEmpty()) ? new ArrayList<>()
                : playerSpokenLanguages.stream().map(playerSpokenLanguage -> mapToCustom(playerSpokenLanguage))
                        .collect(Collectors.toList());
    }

    public List<PlayerSpokenLanguageCustom> save(List<PlayerSpokenLanguage> playerSpokenLanguages) {
        jdbcRepository.resetSequence("player_spoken_language", "player_spoken_language_id_seq");
        List<PlayerSpokenLanguageCustom> playerSpokenLanguagesCustom = new ArrayList<>();
        if (playerSpokenLanguages != null && !playerSpokenLanguages.isEmpty()) {
            playerSpokenLanguages.forEach(playerSpokenLanguage -> playerSpokenLanguagesCustom
                    .add(mapToCustom(playerSpokenLanguageJpaRepository.save(playerSpokenLanguage))));
        }
        return playerSpokenLanguagesCustom;
    }

    public PlayerSpokenLanguage update(Long id, PlayerSpokenLanguage updatedPlayerSpokenLanguage) {
        Optional<PlayerSpokenLanguage> playerSpokenLanguageToUpdateOptional = playerSpokenLanguageJpaRepository
                .findById(id);
        if (playerSpokenLanguageToUpdateOptional.isPresent()) {
            PlayerSpokenLanguage playerSpokenLanguageToUpdate = playerSpokenLanguageToUpdateOptional.get();
            playerSpokenLanguageToUpdate.setCertification(updatedPlayerSpokenLanguage.getCertification());
            playerSpokenLanguageToUpdate.setLanguageRefId(updatedPlayerSpokenLanguage.getLanguageRefId());
            playerSpokenLanguageToUpdate.setLevelRefId(updatedPlayerSpokenLanguage.getLevelRefId());
            playerSpokenLanguageToUpdate.setPlayerId(updatedPlayerSpokenLanguage.getPlayerId());
            return playerSpokenLanguageToUpdate;
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        playerSpokenLanguageJpaRepository.deleteById(id);
    }

    public boolean isPresent(Long id) {
        return playerSpokenLanguageJpaRepository.findById(id).isPresent();
    }

    public PlayerSpokenLanguageCustom mapToCustom(PlayerSpokenLanguage playerSpokenLanguage) {
        Optional<LanguageRef> languageRefOptional = languageRefJpaRepository
                .findById(playerSpokenLanguage.getLanguageRefId());
        Optional<LevelRef> levelRefOptional = levelRefJpaRepository.findById(playerSpokenLanguage.getLevelRefId());
        PlayerSpokenLanguageCustom playerSpokenLanguageCustom = new PlayerSpokenLanguageCustom();
        playerSpokenLanguageCustom.setId(playerSpokenLanguage.getId());
        playerSpokenLanguageCustom.setPlayerId(playerSpokenLanguage.getPlayerId());
        playerSpokenLanguageCustom.setCertification(playerSpokenLanguage.getCertification());
        languageRefOptional.ifPresent(languageRef -> playerSpokenLanguageCustom.setLanguage(languageRef.getValue()));
        levelRefOptional.ifPresent(levelRef -> playerSpokenLanguageCustom.setLevel(levelRef.getValue()));
        return playerSpokenLanguageCustom;
    }
}

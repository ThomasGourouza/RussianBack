package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.cavalierfou.russianback.constant.Constant;
import com.cavalierfou.russianback.customentity.PlayerGameCustom;
import com.cavalierfou.russianback.customentity.PlayerGameHistoryCustom;
import com.cavalierfou.russianback.entity.PlayerGame;
import com.cavalierfou.russianback.entity.PlayerGameHistory;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.PlayerGameHistoryJpaRepository;
import com.cavalierfou.russianback.repository.PlayerGameJpaRepository;
import com.cavalierfou.russianback.repository.RussianCaseRefJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerGameService {

    @Autowired
    private PlayerGameJpaRepository playerGameJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;
    @Autowired
    private PlayerGameHistoryJpaRepository pGHistoryJpaRepository;
    @Autowired
    private RussianCaseRefJpaRepository rusianCaseRefJpaRepository;

    public PlayerGameCustom findById(Long id) {
        Optional<PlayerGame> existingPlayerGameOptional = playerGameJpaRepository.findById(id);
        if (existingPlayerGameOptional.isPresent()) {
            return mapToCustom(existingPlayerGameOptional.get());
        } else {
            return null;
        }
    }

    public List<PlayerGameCustom> find(Long playerId) {
        List<PlayerGame> playerGames = playerId != null ? playerGameJpaRepository.findByPlayerId(playerId)
                : playerGameJpaRepository.findAll();

        List<PlayerGameCustom> playerGameCustoms = new ArrayList<>();
        if (playerGames.isEmpty()) {
            return playerGameCustoms;
        }
        playerGames.forEach(playerGame -> playerGameCustoms.add(mapToCustom(playerGame)));

        return playerGameCustoms;
    }

    public PlayerGameCustom save(PlayerGame playerGame) {
        jdbcRepository.resetSequence(Constant.PG.getValue(), Constant.PGIS.getValue());
        jdbcRepository.resetSequence(Constant.PGH.getValue(), Constant.PGHIS.getValue());

        PlayerGame savedPlayerGame = playerGameJpaRepository.save(playerGame);
        if (playerGame.getPlayerGameHistories() != null && !playerGame.getPlayerGameHistories().isEmpty()) {
            playerGame.getPlayerGameHistories()
                    .forEach(playerGameHistory -> playerGameHistory.setPlayerGameId(savedPlayerGame.getId()));
            pGHistoryJpaRepository.saveAll(playerGame.getPlayerGameHistories());
        }
        return mapToCustom(savedPlayerGame);
    }

    public void delete(Long id) {
        jdbcRepository.delete(Constant.PGH.getValue(), Constant.PGI.getValue(), id.toString());
        playerGameJpaRepository.deleteById(id);
    }

    public boolean isPresent(Long id) {
        return playerGameJpaRepository.findById(id).isPresent();
    }

    public PlayerGameCustom mapToCustom(PlayerGame playerGame) {
        PlayerGameCustom playerGameCustom = new PlayerGameCustom();
        playerGameCustom.setGame(playerGame.getGame());
        playerGameCustom.setDateTime(playerGame.getDateTime());
        playerGameCustom.setScore(playerGame.getScore());

        List<PlayerGameHistoryCustom> playerGameHistoryCustoms = new ArrayList<>();
        playerGame.getPlayerGameHistories()
                .forEach(playerGameHistory -> playerGameHistoryCustoms.add(mapPGH(playerGameHistory)));

        playerGameCustom.setHistory(playerGameHistoryCustoms);

        return playerGameCustom;
    }

    private PlayerGameHistoryCustom mapPGH(PlayerGameHistory playerGameHistory) {
        PlayerGameHistoryCustom playerGameHistoryCustom = new PlayerGameHistoryCustom();
        playerGameHistoryCustom.setItemNumber(playerGameHistory.getItemNumber());
        playerGameHistoryCustom.setIscorrect(playerGameHistory.getIscorrect());
        playerGameHistoryCustom.setRussianAdjectiveId(playerGameHistory.getRussianAdjectiveId());
        playerGameHistoryCustom.setRussianNounId(playerGameHistory.getRussianNounId());
        rusianCaseRefJpaRepository.findById(playerGameHistory.getRussianCaseRefId())
                .ifPresent(russianCase -> playerGameHistoryCustom.setRussianCase(russianCase.getValue()));

        return playerGameHistoryCustom;
    }
}

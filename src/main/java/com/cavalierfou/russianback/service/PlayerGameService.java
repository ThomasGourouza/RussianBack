package com.cavalierfou.russianback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.cavalierfou.russianback.constant.Constant;
import com.cavalierfou.russianback.entity.PlayerGame;
import com.cavalierfou.russianback.repository.JdbcRepository;
import com.cavalierfou.russianback.repository.PlayerGameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerGameService {

    @Autowired
    private PlayerGameJpaRepository playerGameJpaRepository;
    @Autowired
    private JdbcRepository jdbcRepository;

    public PlayerGame findById(Long id) {
        Optional<PlayerGame> existingPlayerGameOptional = playerGameJpaRepository.findById(id);
        if (existingPlayerGameOptional.isPresent()) {
            return this.mapToCustom(existingPlayerGameOptional.get());
        } else {
            return null;
        }
    }

    public List<PlayerGame> find() {

        List<PlayerGame> playerGames = playerGameJpaRepository.findAll();

        List<PlayerGame> playerGameCustoms = new ArrayList<>();
        if (playerGames.isEmpty()) {
            return playerGameCustoms;
        }
        playerGames.forEach(playerGame -> playerGameCustoms.add(mapToCustom(playerGame)));

        return playerGameCustoms;
    }

    public PlayerGame save(PlayerGame playerGame) {
        jdbcRepository.resetSequence(Constant.PG.getValue(), Constant.PGIS.getValue());
        jdbcRepository.resetSequence(Constant.PGH.getValue(), Constant.PGHIS.getValue());

        PlayerGame savedPlayerGame = playerGameJpaRepository.save(playerGame);
        if (playerGame.getPlayerGameHistories() != null && !playerGame.getPlayerGameHistories().isEmpty()) {
            playerGame.getPlayerGameHistories()
                    .forEach(playerGameHistory -> playerGameHistory.setPlayerGameId(savedPlayerGame.getId()));
            // playerGameHistoryService.save(playerGame.getPlayerGameHistories());
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

    public PlayerGame mapToCustom(PlayerGame playerGame) {

        return playerGame;
    }
}

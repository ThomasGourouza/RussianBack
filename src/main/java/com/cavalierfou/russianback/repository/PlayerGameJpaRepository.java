package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.PlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerGameJpaRepository extends JpaRepository<PlayerGame, Long> {
    
}
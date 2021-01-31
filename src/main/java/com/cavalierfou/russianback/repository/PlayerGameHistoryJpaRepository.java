package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.PlayerGameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerGameHistoryJpaRepository extends JpaRepository<PlayerGameHistory, Long> {
    
}
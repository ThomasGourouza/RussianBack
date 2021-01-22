package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.LevelRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRefJpaRepository extends JpaRepository<LevelRef, Long> {
    
}
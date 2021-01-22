package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.LanguageRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRefJpaRepository extends JpaRepository<LanguageRef, Long> {
    
}
package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianGrammaticalNumberRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianGrammaticalNumberRefJpaRepository extends JpaRepository<RussianGrammaticalNumberRef, Long> {
    
}
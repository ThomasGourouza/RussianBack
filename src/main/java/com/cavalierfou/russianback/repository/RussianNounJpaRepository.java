package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianNoun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianNounJpaRepository extends JpaRepository<RussianNoun, Long> {

    public List<RussianNoun> findByTranslation(String translation);
    
}
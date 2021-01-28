package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianNounEndingRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianNounEndingRefJpaRepository extends JpaRepository<RussianNounEndingRef, Long> {

    public List<RussianNounEndingRef> findByRussianNounCategoryRefId(Long russianNounCategoryRefId);
    
}
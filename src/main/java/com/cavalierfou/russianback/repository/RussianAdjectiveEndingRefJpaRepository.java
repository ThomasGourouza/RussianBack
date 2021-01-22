package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianAdjectiveEndingRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianAdjectiveEndingRefJpaRepository extends JpaRepository<RussianAdjectiveEndingRef, Long>{
    
    public List<RussianAdjectiveEndingRef> findByRussianAdjectiveCategoryRefId(long russianAdjectiveCategoryRefId);
}
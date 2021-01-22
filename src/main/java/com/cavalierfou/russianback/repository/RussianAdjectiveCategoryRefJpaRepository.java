package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianAdjectiveCategoryRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianAdjectiveCategoryRefJpaRepository extends JpaRepository<RussianAdjectiveCategoryRef, Long>{
    
}
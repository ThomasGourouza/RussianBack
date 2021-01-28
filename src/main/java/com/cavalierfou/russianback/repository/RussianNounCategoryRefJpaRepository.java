package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianNounCategoryRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianNounCategoryRefJpaRepository extends JpaRepository<RussianNounCategoryRef, Long> {
    
}
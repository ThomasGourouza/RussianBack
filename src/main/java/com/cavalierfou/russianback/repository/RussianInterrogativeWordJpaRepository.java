package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianInterrogativeWordRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianInterrogativeWordJpaRepository extends JpaRepository<RussianInterrogativeWordRef, Long> {
    
}
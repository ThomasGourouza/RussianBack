package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.GenderRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRefJpaRepository extends JpaRepository<GenderRef, Long> {
    
}
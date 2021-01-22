package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.BirthCountryRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthCountryRefJpaRepository extends JpaRepository<BirthCountryRef, Long> {
    
}
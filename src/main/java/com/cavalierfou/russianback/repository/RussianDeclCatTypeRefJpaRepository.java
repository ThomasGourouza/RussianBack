package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianDeclCatTypeRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianDeclCatTypeRefJpaRepository extends JpaRepository<RussianDeclCatTypeRef, Long> {
    
}
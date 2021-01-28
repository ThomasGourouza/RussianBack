package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianDeclensionNameRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianDeclensionNameRefJpaRepository extends JpaRepository<RussianDeclensionNameRef, Long> {
    
}
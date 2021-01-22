package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.ImageRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRefJpaRepository extends JpaRepository<ImageRef, Long> {
    
}
package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianDeclSpecEndingRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianDeclSpecEndingRefJpaRepository extends JpaRepository<RussianDeclSpecEndingRef, Long> {
    
    public List<RussianDeclSpecEndingRef> findByRussianNounEndingRefId(Long russianNounEndingRefId);

}
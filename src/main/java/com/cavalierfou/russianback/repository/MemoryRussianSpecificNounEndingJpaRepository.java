package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.MemoryRussianSpecificNounEnding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRussianSpecificNounEndingJpaRepository extends JpaRepository<MemoryRussianSpecificNounEnding, Long> {

    public List<MemoryRussianSpecificNounEnding> findByRussianNounIdAndRussianDeclSpecEndingRefId(Long russianNounId, Long russianDeclSpecEndingRefId);

}
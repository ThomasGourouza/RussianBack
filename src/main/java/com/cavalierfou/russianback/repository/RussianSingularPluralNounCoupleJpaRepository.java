package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianSingularPluralNounCouple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianSingularPluralNounCoupleJpaRepository extends JpaRepository<RussianSingularPluralNounCouple, Long> {

    public List<RussianSingularPluralNounCouple> findByRussianPluralNounId(Long russianPluralNounId);
    public List<RussianSingularPluralNounCouple> findByRussianSingularNounId(Long russianSingularNounId);
    
}
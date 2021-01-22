package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianAdjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianAdjectiveJpaRepository extends JpaRepository<RussianAdjective, Long>{

    public List<RussianAdjective> findByTranslation(String translation);
}
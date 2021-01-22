package com.cavalierfou.russianback.repository;

import java.util.List;

import com.cavalierfou.russianback.entity.PlayerSpokenLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSpokenLanguageJpaRepository extends JpaRepository<PlayerSpokenLanguage, Long> {

    public List<PlayerSpokenLanguage> findByIdIn(List<Long> ids);
    public List<PlayerSpokenLanguage> findByLanguageRefIdIn(List<Long> languageRefId);
    public List<PlayerSpokenLanguage> findByPlayerId(Long playerId);

}

package com.cavalierfou.russianback.service;

import java.util.List;

import com.cavalierfou.russianback.entity.BirthCountryRef;
import com.cavalierfou.russianback.entity.GenderRef;
import com.cavalierfou.russianback.entity.ImageRef;
import com.cavalierfou.russianback.entity.LanguageRef;
import com.cavalierfou.russianback.entity.LevelRef;
import com.cavalierfou.russianback.repository.BirthCountryRefJpaRepository;
import com.cavalierfou.russianback.repository.GenderRefJpaRepository;
import com.cavalierfou.russianback.repository.ImageRefJpaRepository;
import com.cavalierfou.russianback.repository.LanguageRefJpaRepository;
import com.cavalierfou.russianback.repository.LevelRefJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerReferenceService {

    @Autowired
    private BirthCountryRefJpaRepository birthCountryRefJpaRepository;
    @Autowired
    private GenderRefJpaRepository genderRefJpaRepository;
    @Autowired
    private ImageRefJpaRepository imageRefJpaRepository;
    @Autowired
    private LanguageRefJpaRepository languageRefJpaRepository;
    @Autowired
    private LevelRefJpaRepository levelRefJpaRepository;

    public List<BirthCountryRef> findCountry() {
        return birthCountryRefJpaRepository.findAll();
    }

    public List<GenderRef> findGender() {
        return genderRefJpaRepository.findAll();
    }

    public List<ImageRef> findImage() {
        return imageRefJpaRepository.findAll();
    }

    public List<LanguageRef> findLanguage() {
        return languageRefJpaRepository.findAll();
    }

    public List<LevelRef> findLevel() {
        return levelRefJpaRepository.findAll();
    }

}

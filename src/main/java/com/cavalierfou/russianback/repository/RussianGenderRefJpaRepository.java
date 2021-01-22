package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianGenderRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianGenderRefJpaRepository extends JpaRepository<RussianGenderRef, Long> {

}

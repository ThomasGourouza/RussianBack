package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianCaseRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianCaseRefJpaRepository extends JpaRepository<RussianCaseRef, Long> {

}

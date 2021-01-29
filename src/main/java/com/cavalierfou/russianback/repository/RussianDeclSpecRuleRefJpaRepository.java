package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianDeclSpecRuleRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianDeclSpecRuleRefJpaRepository extends JpaRepository<RussianDeclSpecRuleRef, Long> {

}
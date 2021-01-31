package com.cavalierfou.russianback.repository;

import com.cavalierfou.russianback.entity.RussianRoleRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RussianRoleRefJpaRepository extends JpaRepository<RussianRoleRef, Long> {

}

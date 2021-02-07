package com.cavalierfou.russianback.repository;

import java.util.List;
import com.cavalierfou.russianback.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerJpaRepository extends JpaRepository<Player, Long> {

    public List<Player> findByIdIn(List<Long> ids);
	public List<Player> findByLoginAndPassword(String login, String password);

}

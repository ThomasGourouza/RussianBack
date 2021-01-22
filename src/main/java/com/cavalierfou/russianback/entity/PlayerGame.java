package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "player_game" database table.
 * 
 */
@Entity
@Table(name="\"player_game\"")
@NamedQuery(name="PlayerGame.findAll", query="SELECT p FROM PlayerGame p")
public class PlayerGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"date_time\"")
	private Date dateTime;

	@Column(name="\"game\"")
	private long game;

	@Column(name="\"player_id\"")
	private long playerId;

	@Column(name="\"score\"")
	private long score;

	// bi-directional many-to-one association to PlayerSpokenLanguage
	@OneToMany(mappedBy="playerGameId")
	private List<PlayerGameHistory> playerGameHistories;

	public PlayerGame() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public long getGame() {
		return this.game;
	}

	public void setGame(long game) {
		this.game = game;
	}

	public long getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getScore() {
		return this.score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public List<PlayerGameHistory> getPlayerGameHistories() {
		return this.playerGameHistories;
	}

}
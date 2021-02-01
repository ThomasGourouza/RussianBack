package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "player_game_history" database table.
 * 
 */
@Entity
@Table(name="\"player_game_history\"")
@NamedQuery(name="PlayerGameHistory.findAll", query="SELECT p FROM PlayerGameHistory p")
public class PlayerGameHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"item_number\"")
	private Long itemNumber;

	@Column(name="\"iscorrect\"")
	private boolean iscorrect;

	@Column(name="\"player_game_id\"")
	private long playerGameId;

	@Column(name="\"russian_adjective_id\"")
	private long russianAdjectiveId;

	@Column(name="\"russian_case_ref_id\"")
	private long russianCaseRefId;

	@Column(name="\"russian_noun_id\"")
	private long russianNounId;

	public PlayerGameHistory() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(long itemNumber) {
		this.itemNumber = itemNumber;
	}

	public boolean getIscorrect() {
		return this.iscorrect;
	}

	public void setIscorrect(boolean iscorrect) {
		this.iscorrect = iscorrect;
	}

	public long getPlayerGameId() {
		return this.playerGameId;
	}

	public void setPlayerGameId(long playerGameId) {
		this.playerGameId = playerGameId;
	}

	public long getRussianAdjectiveId() {
		return this.russianAdjectiveId;
	}

	public void setRussianAdjectiveId(long russianAdjectiveId) {
		this.russianAdjectiveId = russianAdjectiveId;
	}

	public long getRussianCaseRefId() {
		return this.russianCaseRefId;
	}

	public void setRussianCaseRefId(long russianCaseRefId) {
		this.russianCaseRefId = russianCaseRefId;
	}

	public long getRussianNounId() {
		return this.russianNounId;
	}

	public void setRussianNounId(long russianNounId) {
		this.russianNounId = russianNounId;
	}

}
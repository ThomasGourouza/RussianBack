package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "player_spoken_language" database table.
 * 
 */
@Entity
@Table(name="\"player_spoken_language\"")
@NamedQuery(name="PlayerSpokenLanguage.findAll", query="SELECT p FROM PlayerSpokenLanguage p")
public class PlayerSpokenLanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"certification\"")
	private String certification;

	@Column(name="\"language_ref_id\"")
	private long languageRefId;

	@Column(name="\"level_ref_id\"")
	private long levelRefId;

	@Column(name="\"player_id\"")
	private long playerId;

	public PlayerSpokenLanguage() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public long getLanguageRefId() {
		return this.languageRefId;
	}

	public void setLanguageRefId(long languageRefId) {
		this.languageRefId = languageRefId;
	}

	public long getLevelRefId() {
		return this.levelRefId;
	}

	public void setLevelRefId(long levelRefId) {
		this.levelRefId = levelRefId;
	}

	public long getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

}
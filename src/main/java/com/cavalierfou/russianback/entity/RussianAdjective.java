package com.cavalierfou.russianback.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the "russian_adjective" database table.
 * 
 */
@Entity
@Table(name="\"russian_adjective\"")
@NamedQuery(name="RussianAdjective.findAll", query="SELECT r FROM RussianAdjective r")
public class RussianAdjective implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"root\"")
	private String root;

	@Column(name="\"russian_adjective_category_ref_id\"")
	private long russianAdjectiveCategoryRefId;

	@Column(name="\"translation\"")
	private String translation;

	public RussianAdjective() {
		// default constructor
	}

	public RussianAdjective(long id, String root, long russianAdjectiveCategoryRefId, String translation) {
		this.id = id;
		this.root = root;
		this.russianAdjectiveCategoryRefId = russianAdjectiveCategoryRefId;
		this.translation = translation;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoot() {
		return this.root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public long getRussianAdjectiveCategoryRefId() {
		return this.russianAdjectiveCategoryRefId;
	}

	public void setRussianAdjectiveCategoryRefId(long russianAdjectiveCategoryRefId) {
		this.russianAdjectiveCategoryRefId = russianAdjectiveCategoryRefId;
	}

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

}
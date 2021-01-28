package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_noun" database table.
 * 
 */
@Entity
@Table(name="\"russian_noun\"")
@NamedQuery(name="RussianNoun.findAll", query="SELECT r FROM RussianNoun r")
public class RussianNoun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"is_animate\"")
	private boolean isAnimate;

	@Column(name="\"root\"")
	private String root;

	@Column(name="\"russian_noun_category_ref_id\"")
	private long russianNounCategoryRefId;

	@Column(name="\"translation\"")
	private String translation;

	public RussianNoun() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getIsAnimate() {
		return this.isAnimate;
	}

	public void setIsAnimate(boolean isAnimate) {
		this.isAnimate = isAnimate;
	}

	public String getRoot() {
		return this.root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public long getRussianNounCategoryRefId() {
		return this.russianNounCategoryRefId;
	}

	public void setRussianNounCategoryRefId(long russianNounCategoryRefId) {
		this.russianNounCategoryRefId = russianNounCategoryRefId;
	}

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

}
package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_noun_ending_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_noun_ending_ref\"")
@NamedQuery(name="RussianNounEndingRef.findAll", query="SELECT r FROM RussianNounEndingRef r")
public class RussianNounEndingRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_case_ref_id\"")
	private long russianCaseRefId;

	@Column(name="\"russian_noun_category_ref_id\"")
	private long russianNounCategoryRefId;

	@Column(name="\"value\"")
	private String value;

	public RussianNounEndingRef() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRussianCaseRefId() {
		return this.russianCaseRefId;
	}

	public void setRussianCaseRefId(long russianCaseRefId) {
		this.russianCaseRefId = russianCaseRefId;
	}

	public long getRussianNounCategoryRefId() {
		return this.russianNounCategoryRefId;
	}

	public void setRussianNounCategoryRefId(long russianNounCategoryRefId) {
		this.russianNounCategoryRefId = russianNounCategoryRefId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
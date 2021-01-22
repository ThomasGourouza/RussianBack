package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_adjective_ending_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_adjective_ending_ref\"")
@NamedQuery(name="RussianAdjectiveEndingRef.findAll", query="SELECT r FROM RussianAdjectiveEndingRef r")
public class RussianAdjectiveEndingRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_adjective_category_ref_id\"")
	private long russianAdjectiveCategoryRefId;

	@Column(name="\"russian_case_ref_id\"")
	private long russianCaseRefId;

	@Column(name="\"russian_gender_ref_id\"")
	private long russianGenderRefId;

	@Column(name="\"value\"")
	private String value;

	public RussianAdjectiveEndingRef() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRussianAdjectiveCategoryRefId() {
		return this.russianAdjectiveCategoryRefId;
	}

	public void setRussianAdjectiveCategoryRefId(long russianAdjectiveCategoryRefId) {
		this.russianAdjectiveCategoryRefId = russianAdjectiveCategoryRefId;
	}

	public long getRussianCaseRefId() {
		return this.russianCaseRefId;
	}

	public void setRussianCaseRefId(long russianCaseRefId) {
		this.russianCaseRefId = russianCaseRefId;
	}

	public long getRussianGenderRefId() {
		return this.russianGenderRefId;
	}

	public void setRussianGenderRefId(long russianGenderRefId) {
		this.russianGenderRefId = russianGenderRefId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
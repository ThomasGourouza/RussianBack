package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "russian_interrogative_word_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_interrogative_word_ref\"")
@NamedQuery(name="RussianInterrogativeWordRef.findAll", query="SELECT r FROM RussianInterrogativeWordRef r")
public class RussianInterrogativeWordRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_case_ref_id\"")
	private long russianCaseRefId;

	@Column(name="\"russian_role_ref_id\"")
	private long russianRoleRefId;

	@Column(name="\"value\"")
	private String value;

	public RussianInterrogativeWordRef() {
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

	public long getRussianRoleRefId() {
		return this.russianRoleRefId;
	}

	public void setRussianRoleRefId(long russianRoleRefId) {
		this.russianRoleRefId = russianRoleRefId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_decl_spec_ending_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_decl_spec_ending_ref\"")
@NamedQuery(name="RussianDeclSpecEndingRef.findAll", query="SELECT r FROM RussianDeclSpecEndingRef r")
public class RussianDeclSpecEndingRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_decl_spec_rule_ref_id\"")
	private long russianDeclSpecRuleRefId;

	@Column(name="\"russian_noun_ending_ref_id\"")
	private long russianNounEndingRefId;

	@Column(name="\"value\"")
	private String value;

	public RussianDeclSpecEndingRef() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRussianDeclSpecRuleRefId() {
		return this.russianDeclSpecRuleRefId;
	}

	public void setRussianDeclSpecRuleRefId(long russianDeclSpecRuleRefId) {
		this.russianDeclSpecRuleRefId = russianDeclSpecRuleRefId;
	}

	public long getRussianNounEndingRefId() {
		return this.russianNounEndingRefId;
	}

	public void setRussianNounEndingRefId(long russianNounEndingRefId) {
		this.russianNounEndingRefId = russianNounEndingRefId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
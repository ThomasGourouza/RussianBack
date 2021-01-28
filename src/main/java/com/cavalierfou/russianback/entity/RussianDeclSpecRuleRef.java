package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_decl_spec_rule_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_decl_spec_rule_ref\"")
@NamedQuery(name="RussianDeclSpecRuleRef.findAll", query="SELECT r FROM RussianDeclSpecRuleRef r")
public class RussianDeclSpecRuleRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"value\"")
	private String value;

	public RussianDeclSpecRuleRef() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
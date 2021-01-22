package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_case_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_case_ref\"")
@NamedQuery(name="RussianCaseRef.findAll", query="SELECT r FROM RussianCaseRef r")
public class RussianCaseRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"value\"", nullable=false, length=50)
	private String value;

	public RussianCaseRef() {
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
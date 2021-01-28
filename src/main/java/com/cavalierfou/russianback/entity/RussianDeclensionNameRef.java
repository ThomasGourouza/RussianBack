package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_declension_name_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_declension_name_ref\"")
@NamedQuery(name="RussianDeclensionNameRef.findAll", query="SELECT r FROM RussianDeclensionNameRef r")
public class RussianDeclensionNameRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"value\"")
	private String value;

	public RussianDeclensionNameRef() {
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
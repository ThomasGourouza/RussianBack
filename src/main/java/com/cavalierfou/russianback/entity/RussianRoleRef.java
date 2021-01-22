package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "russian_role_ref" database table.
 * 
 */
@Entity
@Table(name="\"russian_role_ref\"")
@NamedQuery(name="RussianRoleRef.findAll", query="SELECT r FROM RussianRoleRef r")
public class RussianRoleRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"value\"", nullable=false, length=50)
	private String value;

	public RussianRoleRef() {
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
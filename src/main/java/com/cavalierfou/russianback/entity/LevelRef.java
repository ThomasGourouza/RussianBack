package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "level_ref" database table.
 * 
 */
@Entity
@Table(name="\"level_ref\"")
@NamedQuery(name="LevelRef.findAll", query="SELECT l FROM LevelRef l")
public class LevelRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"name\"", nullable=false, length=50)
	private String name;

	@Column(name="\"value\"", nullable=false, length=50)
	private String value;


	public LevelRef() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
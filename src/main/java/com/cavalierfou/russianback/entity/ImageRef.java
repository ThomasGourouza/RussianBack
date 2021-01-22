package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "image_ref" database table.
 * 
 */
@Entity
@Table(name="\"image_ref\"")
@NamedQuery(name="ImageRef.findAll", query="SELECT i FROM ImageRef i")
public class ImageRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"", unique=true, nullable=false)
	private long id;

	@Column(name="\"value\"", nullable=false, length=500)
	private String value;

	public ImageRef() {
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
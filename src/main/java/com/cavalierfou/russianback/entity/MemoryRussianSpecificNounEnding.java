package com.cavalierfou.russianback.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "memory_russian_specific_noun_ending" database table.
 * 
 */
@Entity
@Table(name="\"memory_russian_specific_noun_ending\"")
@NamedQuery(name="MemoryRussianSpecificNounEnding.findAll", query="SELECT m FROM MemoryRussianSpecificNounEnding m")
public class MemoryRussianSpecificNounEnding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private long id;

	@Column(name="\"russian_noun_ending_ref_id\"")
	private long russianNounEndingRefId;

	@Column(name="\"russian_noun_id\"")
	private long russianNounId;

	@Column(name="\"value\"")
	private String value;

	public MemoryRussianSpecificNounEnding() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRussianNounEndingRefId() {
		return this.russianNounEndingRefId;
	}

	public void setRussianNounEndingRefId(long russianNounEndingRefId) {
		this.russianNounEndingRefId = russianNounEndingRefId;
	}

	public long getRussianNounId() {
		return this.russianNounId;
	}

	public void setRussianNounId(long russianNounId) {
		this.russianNounId = russianNounId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
package com.cavalierfou.russianback.customentity;

import java.util.List;

public class RussianAdjectiveCategoryRefCustom {

	private Long id;

	private String masculineNominativeEnding;

	private String value;

	private List<RussianAdjectiveEndingRefCustom> endings;

	public RussianAdjectiveCategoryRefCustom() {
		// default constructor
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasculineNominativeEnding() {
		return this.masculineNominativeEnding;
	}

	public void setMasculineNominativeEnding(String masculineNominativeEnding) {
		this.masculineNominativeEnding = masculineNominativeEnding;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<RussianAdjectiveEndingRefCustom> getEndings() {
		return this.endings;
	}

	public void setEndings(List<RussianAdjectiveEndingRefCustom> endings) {
		this.endings = endings;
	}

}
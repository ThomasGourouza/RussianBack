package com.cavalierfou.russianback.customentity;

import java.util.List;

import com.cavalierfou.russianback.entity.RussianDeclSpecEndingRef;

public class RussianNounEndingRefCustom {

	private String russianCase;

	private String value;

	private List<RussianDeclSpecEndingRefCustom> specificEndingRules;

	public RussianNounEndingRefCustom() {
		// default constructor
	}

	public String getRussianCase() {
		return russianCase;
	}

	public void setRussianCase(String russianCase) {
		this.russianCase = russianCase;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<RussianDeclSpecEndingRefCustom> getSpecificEndingRules() {
		return specificEndingRules;
	}

	public void setSpecificEndingRules(List<RussianDeclSpecEndingRefCustom> russianDeclSpecEndingRefCustom) {
		this.specificEndingRules = russianDeclSpecEndingRefCustom;
	}

}
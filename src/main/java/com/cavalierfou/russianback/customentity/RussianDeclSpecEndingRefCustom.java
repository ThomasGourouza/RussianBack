package com.cavalierfou.russianback.customentity;

public class RussianDeclSpecEndingRefCustom {

	private Long id;

	private String rule;

	private String value;

	private boolean isApplied;

	public RussianDeclSpecEndingRefCustom() {
		// default constructor
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String russianDeclSpecRule) {
		this.rule = russianDeclSpecRule;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isApplied() {
		return isApplied;
	}

	public void setApplied(boolean isApplied) {
		this.isApplied = isApplied;
	}

}
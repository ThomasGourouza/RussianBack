package com.cavalierfou.russianback.customentity;

import java.util.List;

public class RussianNounCustom {

	private long id;

	private boolean isAnimate;

	private String root;

	private String nominativeForm;

	private RussianNounCategoryRefCustom russianNounCategory;

	private String translation;

	private List<RussianNounEndingRefCustom> russianNounEndings;

	public RussianNounCustom() {
		// default constructor
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getIsAnimate() {
		return this.isAnimate;
	}

	public void setAnimate(boolean isAnimate) {
		this.isAnimate = isAnimate;
	}

	public String getRoot() {
		return this.root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getNominativeForm() {
		return nominativeForm;
	}

	public void setNominativeForm(String nominativeForm) {
		this.nominativeForm = nominativeForm;
	}

	public RussianNounCategoryRefCustom getRussianNounCategory() {
		return this.russianNounCategory;
	}

	public void setRussianNounCategory(RussianNounCategoryRefCustom russianNounCategory) {
		this.russianNounCategory = russianNounCategory;
	}

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public List<RussianNounEndingRefCustom> getRussianNounEndings() {
		return russianNounEndings;
	}

	public void setRussianNounEndings(List<RussianNounEndingRefCustom> russianNounEndings) {
		this.russianNounEndings = russianNounEndings;
	}

}
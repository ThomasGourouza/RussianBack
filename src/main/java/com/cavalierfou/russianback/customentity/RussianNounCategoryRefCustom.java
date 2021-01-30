package com.cavalierfou.russianback.customentity;

import java.util.List;

public class RussianNounCategoryRefCustom {

    private Long id;
    
	private String russianDeclensionName;
    
	private String russianGender;
    
    private String russianGrammaticalNumber;
    
	private String russianDeclCatType;
    
    private List<RussianNounEndingRefCustom> russianNounEndings;

	public RussianNounCategoryRefCustom() {
		// default constructor
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRussianDeclCatType() {
        return russianDeclCatType;
    }

    public void setRussianDeclCatType(String russianDeclCatType) {
        this.russianDeclCatType = russianDeclCatType;
    }

    public String getRussianDeclensionName() {
        return russianDeclensionName;
    }

    public void setRussianDeclensionName(String russianDeclensionName) {
        this.russianDeclensionName = russianDeclensionName;
    }

    public String getRussianGender() {
        return russianGender;
    }

    public void setRussianGender(String russianGender) {
        this.russianGender = russianGender;
    }

    public String getRussianGrammaticalNumber() {
        return russianGrammaticalNumber;
    }

    public void setRussianGrammaticalNumber(String russianGrammaticalNumber) {
        this.russianGrammaticalNumber = russianGrammaticalNumber;
    }

    public List<RussianNounEndingRefCustom> getRussianNounEndings() {
        return russianNounEndings;
    }

    public void setRussianNounEndings(List<RussianNounEndingRefCustom> russianNounEndings) {
        this.russianNounEndings = russianNounEndings;
    }

}
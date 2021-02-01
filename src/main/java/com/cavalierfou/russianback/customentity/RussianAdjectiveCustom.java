package com.cavalierfou.russianback.customentity;

public class RussianAdjectiveCustom {

    private long id;

    private String root;
    
    private String translation;
    
    private RussianAdjectiveCategoryRefCustom category;

    public RussianAdjectiveCustom() {
        // default constructor
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoot() {
        return this.root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public RussianAdjectiveCategoryRefCustom getCategory() {
        return this.category;
    }

    public void setCategory(RussianAdjectiveCategoryRefCustom category) {
        this.category = category;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
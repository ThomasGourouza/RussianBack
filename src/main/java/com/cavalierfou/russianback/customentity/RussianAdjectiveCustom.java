package com.cavalierfou.russianback.customentity;

import java.util.List;

public class RussianAdjectiveCustom {

    private long id;

    private String root;

    private RussianAdjectiveCategoryRefCustom russianAdjectiveCategoryRefCustom;

    private String translation;

    private List<RussianAdjectiveEndingRefCustom> russianAdjectiveEndingRefCustoms;

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

    public RussianAdjectiveCategoryRefCustom getRussianAdjectiveCategoryRefCustom() {
        return this.russianAdjectiveCategoryRefCustom;
    }

    public void setRussianAdjectiveCategoryRefCustom(
            RussianAdjectiveCategoryRefCustom russianAdjectiveCategoryRefCustom) {
        this.russianAdjectiveCategoryRefCustom = russianAdjectiveCategoryRefCustom;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public List<RussianAdjectiveEndingRefCustom> getRussianAdjectiveEndingRefCustoms() {
        return this.russianAdjectiveEndingRefCustoms;
    }

    public void setRussianAdjectiveEndingRefCustoms(List<RussianAdjectiveEndingRefCustom> russianAdjectiveEndingRefCustoms) {
        this.russianAdjectiveEndingRefCustoms = russianAdjectiveEndingRefCustoms;
    }

}
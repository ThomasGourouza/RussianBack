package com.cavalierfou.russianback.customentity;

public class PlayerGameHistoryCustom {

    private long id;

	private boolean iscorrect;

	private RussianAdjectiveEndingRefCustom russianAdjectiveEndingRefCustom;

    private String russianAdjectiveRoot;
    
    private String russianAdjectiveTranslation;

	public PlayerGameHistoryCustom() {
		// default constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIscorrect() {
        return iscorrect;
    }

    public void setIscorrect(boolean iscorrect) {
        this.iscorrect = iscorrect;
    }

    public RussianAdjectiveEndingRefCustom getRussianAdjectiveEndingRefCustom() {
        return russianAdjectiveEndingRefCustom;
    }

    public void setRussianAdjectiveEndingRefCustom(RussianAdjectiveEndingRefCustom russianAdjectiveEndingRefCustom) {
        this.russianAdjectiveEndingRefCustom = russianAdjectiveEndingRefCustom;
    }

    public String getRussianAdjectiveRoot() {
        return russianAdjectiveRoot;
    }

    public void setRussianAdjectiveRoot(String russianAdjectiveRoot) {
        this.russianAdjectiveRoot = russianAdjectiveRoot;
    }

    public String getRussianAdjectiveTranslation() {
        return russianAdjectiveTranslation;
    }

    public void setRussianAdjectiveTranslation(String russianAdjectiveTranslation) {
        this.russianAdjectiveTranslation = russianAdjectiveTranslation;
    }
    
}

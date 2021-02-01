package com.cavalierfou.russianback.customentity;

public class PlayerGameHistoryCustom {

	private Long itemNumber;

	private boolean iscorrect;

	private long russianAdjectiveId;

	private long russianNounId;
    
	private String russianCase;

	public PlayerGameHistoryCustom() {
		// default constructor
	}

	public long getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(long itemNumber) {
		this.itemNumber = itemNumber;
	}

	public boolean getIscorrect() {
		return this.iscorrect;
	}

	public void setIscorrect(boolean iscorrect) {
		this.iscorrect = iscorrect;
	}

	public long getRussianAdjectiveId() {
		return this.russianAdjectiveId;
	}

	public void setRussianAdjectiveId(long russianAdjectiveId) {
		this.russianAdjectiveId = russianAdjectiveId;
	}

	public long getRussianNounId() {
        return this.russianNounId;
	}
    
	public void setRussianNounId(long russianNounId) {
        this.russianNounId = russianNounId;
	}
    
    public String getRussianCase() {
        return this.russianCase;
    }

    public void setRussianCase(String russianCase) {
        this.russianCase = russianCase;
    }

}
package com.cavalierfou.russianback.customentity;

import java.util.Date;
import java.util.List;

public class PlayerGameCustom {

	private long game;
    
	private Date dateTime;

    private long score;
    
    private List<PlayerGameHistoryCustom> history;

	public PlayerGameCustom() {
		// default constructor
	}

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public long getGame() {
        return game;
    }

    public void setGame(long game) {
        this.game = game;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public List<PlayerGameHistoryCustom> getHistory() {
        return history;
    }

    public void setHistory(List<PlayerGameHistoryCustom> history) {
        this.history = history;
    }
}

package com.cavalierfou.russianback.customentity;

import java.util.Date;
import java.util.List;

public class PlayerGameCustom {
    
    private long id;

	private Date dateTime;

	private long game;

    private long score;
    
    private List<PlayerGameHistoryCustom> playerGameHistoryCustoms;

	public PlayerGameCustom() {
		// default constructor
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<PlayerGameHistoryCustom> getPlayerGameHistoryCustoms() {
        return playerGameHistoryCustoms;
    }

    public void setPlayerGameHistoryCustoms(List<PlayerGameHistoryCustom> playerGameHistoryCustoms) {
        this.playerGameHistoryCustoms = playerGameHistoryCustoms;
    }
}

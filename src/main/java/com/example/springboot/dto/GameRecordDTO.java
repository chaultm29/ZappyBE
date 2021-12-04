package com.example.springboot.dto;

import java.sql.Date;

public class GameRecordDTO {

	
	private String gameName;
	private Date timeCreated;
	private Long timePlayed;
	private Long score;
	
	public GameRecordDTO() {
	}

	public GameRecordDTO(String gameName, Date timeCreated, Long timePlayed, Long score) {
		super();
		this.gameName = gameName;
		this.timeCreated = timeCreated;
		this.timePlayed = timePlayed;
		this.score = score;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Long getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(Long timePlayed) {
		this.timePlayed = timePlayed;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

}

package com.example.springboot.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_records")
public class GameRecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "time_created")
	private Date timeCreated;
	
	@Column(name = "time_played")
	private Long timePlayed;
	
	@Column(name = "score")
	private Long score;
	
	@Column(name = "game_name")
	private String gameName;

	@Column(name = "user_id")
	private Long userId;
	
	public GameRecordEntity() {
		this.timeCreated = Date.valueOf(LocalDate.now(ZoneId.of("GMT+07:00")));
	}

	public GameRecordEntity(Long id, Long timePlayed, Long score, String gameName, Long userId) {
		super();
		this.id = id;
		this.timeCreated = Date.valueOf(LocalDate.now(ZoneId.of("GMT+07:00")));;
		this.timePlayed = timePlayed;
		this.score = score;
		this.gameName = gameName;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

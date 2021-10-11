package com.example.springboot.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class HistoryEntity {
	@Id
	@Column(name = "history_id")
	private Long id;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "score")
	private String score;

	@Column(name = "room_code")
	private String roomCode;

	@Column(name = "play_time")
	private String playTime;

	public HistoryEntity() {
		super();
	}

	public HistoryEntity(Long id, Date createdTime, String score, String roomCode, String playTime) {
		super();
		this.id = id;
		this.createdTime = createdTime;
		this.score = score;
		this.roomCode = roomCode;
		this.playTime = playTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

}

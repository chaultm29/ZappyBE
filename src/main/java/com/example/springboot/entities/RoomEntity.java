package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity {

	@Id
	@Column(name = "room_id")
	private Long id;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "level")
	private String level;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "max_player")
	private String maxPlayer;

	@Column(name = "room_code")
	private String roomCode;
	
	@ManyToMany
	@JoinTable(name = "room_question", 
			joinColumns = @JoinColumn(name = "room_id"), 
			inverseJoinColumns = @JoinColumn(name = "question_id"))
	private Set<QuestionEntity> questionEntities;
	
//	@ManyToOne
//	@JoinColumn(name = )

	public RoomEntity() {
		super();
	}

	public RoomEntity(Long id, String roomName, String level, boolean enabled, String maxPlayer, String roomCode) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.level = level;
		this.enabled = enabled;
		this.maxPlayer = maxPlayer;
		this.roomCode = roomCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(String maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

}

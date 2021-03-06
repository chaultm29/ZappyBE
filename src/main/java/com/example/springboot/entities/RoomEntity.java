package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@JoinTable(name = "room_question_rf", 
			joinColumns = @JoinColumn(name = "question_id"), 
			inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<QuestionEntity> questionEntities;

	@ManyToMany
	@JoinTable(name = "room_skill_rf", 
		joinColumns = @JoinColumn(name = "skill_id"), 
		inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<SkillEntity> skillEntities;

//	@ManyToOne
//	@JoinColumn(name = "game_id")
//	private GameEntity gameEntity;

	@ManyToMany
	@JoinTable(name = "room_lesson_rf", 
				joinColumns = @JoinColumn(name = "lesson_id"), 
				inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<LessonEntity> lessonEntities;

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

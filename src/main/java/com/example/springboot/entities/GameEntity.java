package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class GameEntity {
	@Id
	@Column(name = "game_id")
	private Long id;

	@Column(name = "image_link")
	private String imageLink;

	@Column(name = "description")
	private String description;

	@Column(name = "game_name")
	private String gameName;

	@ManyToMany
	@JoinTable(name = "game_questiontype", 
			joinColumns = @JoinColumn(name = "game_id"), 
			inverseJoinColumns = @JoinColumn(name = "type_id"))
	private Set<QuestionTypeEntity> questionTypeEntities;
	
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<RoomEntity> roomEntities;

	public GameEntity() {
	}

	public GameEntity(Long id, String imageLink, String description, String gameName) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.description = description;
		this.gameName = gameName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}

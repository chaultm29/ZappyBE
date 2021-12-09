package com.example.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "achievements")
public class AchievementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "desciption")
	private String desciption;

	@Column(name = "achieve_condition")
	private String condition;

	@Column(name = "image_link")
	private String imageLink;
	
	@JsonIgnore
	@OneToMany(mappedBy = "achievenmentEntity",cascade = CascadeType.ALL)
	private Set<UserAchievenmentEntity> userAchievenmentEntities;
	
	public AchievementEntity() {
	}

	public AchievementEntity(Long id, String name, String desciption, String condition, Set<UserAchievenmentEntity> userAchievenmentEntities) {
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		this.condition = condition;
		this.userAchievenmentEntities = userAchievenmentEntities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Set<UserAchievenmentEntity> getUserAchievenmentEntities() {
		return userAchievenmentEntities;
	}

	public void setUserAchievenmentEntities(Set<UserAchievenmentEntity> userAchievenmentEntities) {
		this.userAchievenmentEntities = userAchievenmentEntities;
	}
}

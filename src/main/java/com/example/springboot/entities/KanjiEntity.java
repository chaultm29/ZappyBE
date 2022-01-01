package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kanjis")
public class KanjiEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "kanji_character")
	private String character;

	@Column(name = "onyomi")
	private String onyomi;

	@Column(name = "kunyomi")
	private String kunyomi;

	@Column(name = "chinese")
	private String chinese;

	@Column(name = "vietnamese")
	private String vietnamese;

	@Column(name = "description")
	private String description;

	@Column(name = "image_link")
	private String imageLink;

	@Column(name = "gif_link")
	private String gifLink;

//	@ManyToMany(mappedBy = "kanjiEntities")
//	private Set<UserEntity> userEntities;

	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private LessonEntity lessonEntity;

	public KanjiEntity() {
	}

	public KanjiEntity(Long id, String character, String onyomi, String kunyomi, String chinese, String vietnamese,
			String description, String imageLink, String gifLink) {
		super();
		this.id = id;
		this.character = character;
		this.onyomi = onyomi;
		this.kunyomi = kunyomi;
		this.chinese = chinese;
		this.vietnamese = vietnamese;
		this.description = description;
		this.imageLink = imageLink;
		this.gifLink = gifLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getOnyomi() {
		return onyomi;
	}

	public void setOnyomi(String onyomi) {
		this.onyomi = onyomi;
	}

	public String getKunyomi() {
		return kunyomi;
	}

	public void setKunyomi(String kunyomi) {
		this.kunyomi = kunyomi;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getVietnamese() {
		return vietnamese;
	}

	public void setVietnamese(String vietnamese) {
		this.vietnamese = vietnamese;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getGifLink() {
		return gifLink;
	}

	public void setGifLink(String gifLink) {
		this.gifLink = gifLink;
	}

//	public Set<UserEntity> getUserEntities() {
//		return userEntities;
//	}
//
//	public void setUserEntities(Set<UserEntity> userEntities) {
//		this.userEntities = userEntities;
//	}

	public LessonEntity getLessonEntity() {
		return lessonEntity;
	}

	public void setLessonEntity(LessonEntity lessonEntity) {
		this.lessonEntity = lessonEntity;
	}

}

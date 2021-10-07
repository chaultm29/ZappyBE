package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kanji")
public class KanjiEntity {
	@Id
	@Column(name = "kanji_id")
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
	
	@ManyToMany(mappedBy = "kanjiEntities")
	private Set<UserEntity> userEntities;

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

	public String getImageLinh() {
		return imageLink;
	}

	public void setImageLinh(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getGifLink() {
		return gifLink;
	}

	public void setGifLink(String gifLink) {
		this.gifLink = gifLink;
	}

}

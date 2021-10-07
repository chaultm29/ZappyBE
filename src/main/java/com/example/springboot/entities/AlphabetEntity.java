package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alphabet")
public class AlphabetEntity {
	@Id
	@Column(name = "alphabet_id")
	private Long id;

	@Column(name = "alphabet_character")
	private String character;

	@Column(name = "image_link")
	private String imageLink;

	@Column(name = "description")
	private String description;

	@Column(name = "is_hiragana")
	private String isHiragana;

	public AlphabetEntity() {
		super();
	}

	public AlphabetEntity(Long id, String character, String imageLink, String description, String isHiragana) {
		super();
		this.id = id;
		this.character = character;
		this.imageLink = imageLink;
		this.description = description;
		this.isHiragana = isHiragana;
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

	public String getIsHiragana() {
		return isHiragana;
	}

	public void setIsHiragana(String isHiragana) {
		this.isHiragana = isHiragana;
	}

}

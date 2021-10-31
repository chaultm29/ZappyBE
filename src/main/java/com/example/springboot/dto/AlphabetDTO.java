package com.example.springboot.dto;

public class AlphabetDTO {

	private Long id;
	private String character;
	private String description;
	private String imageLink;

	public AlphabetDTO() {
		super();
	}

	public AlphabetDTO(Long id, String character, String description, String imageLink) {
		super();
		this.id = id;
		this.character = character;
		this.description = description;
		this.imageLink = imageLink;
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

	

}

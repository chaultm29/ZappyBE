package com.example.springbootdto;

public class AlphabetDTO {

	private Long id;
	private String character;
	private String desciption;
	private String imageLink;

	public AlphabetDTO() {
		super();
	}

	public AlphabetDTO(Long id, String character, String desciption, String imageLink) {
		super();
		this.id = id;
		this.character = character;
		this.desciption = desciption;
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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

}

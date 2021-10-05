package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vocabulary")
public class VocabularyEntity {
	@Id
	@Column(name = "vocabulary_id")
	private Long id;

	@Column(name = "image_link")
	private String imageLink;

	@Column(name = "vocabulary")
	private String vocabulary;

	@Column(name = "meaning")
	private String meaning;

	@Column(name = "example")
	private String example;

	@Column(name = "example_mean")
	private String exampleMean;

	@Column(name = "example_image_link")
	private String exampleImageLink;

	public VocabularyEntity() {
		super();
	}

	public VocabularyEntity(Long id, String imageLink, String vocabulary, String meaning, String example,
			String exampleMean, String exampleImageLink) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.vocabulary = vocabulary;
		this.meaning = meaning;
		this.example = example;
		this.exampleMean = exampleMean;
		this.exampleImageLink = exampleImageLink;
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

	public String getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(String vocabulary) {
		this.vocabulary = vocabulary;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getExampleMean() {
		return exampleMean;
	}

	public void setExampleMean(String exampleMean) {
		this.exampleMean = exampleMean;
	}

	public String getExampleImageLink() {
		return exampleImageLink;
	}

	public void setExampleImageLink(String exampleImageLink) {
		this.exampleImageLink = exampleImageLink;
	}

}

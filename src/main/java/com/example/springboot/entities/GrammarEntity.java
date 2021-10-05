package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Grammar")
public class GrammarEntity {
	@Id
	@Column(name = "grammar_id")
	private Long id;

	@Column(name = "grammar")
	private String grammar;

	@Column(name = "explanation")
	private String explanation;

	@Column(name = "example")
	private String example;

	@Column(name = "example_image_link")
	private String exampleImageLink;

	public GrammarEntity() {
		super();
	}

	public GrammarEntity(Long id, String grammar, String explanation, String example, String exampleImageLink) {
		super();
		this.id = id;
		this.grammar = grammar;
		this.explanation = explanation;
		this.example = example;
		this.exampleImageLink = exampleImageLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrammar() {
		return grammar;
	}

	public void setGrammar(String grammar) {
		this.grammar = grammar;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getExampleImageLink() {
		return exampleImageLink;
	}

	public void setExampleImageLink(String exampleImageLink) {
		this.exampleImageLink = exampleImageLink;
	}

}

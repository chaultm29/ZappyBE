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
@Table(name = "grammars")
public class GrammarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "grammar")
	private String grammar;

	@Column(name = "explanation")
	private String explanation;

	@Column(name = "example")
	private String example;

	@Column(name = "example_image_link")
	private String exampleImageLink;
	
	@ManyToMany(mappedBy = "grammarEntities")
	private Set<UserEntity> userEntities;
	
	@ManyToOne
	@JoinColumn(name = "lession_id")
	private LessonEntity lessonEntity;

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

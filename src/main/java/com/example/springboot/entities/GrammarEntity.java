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

	@Column(name = "grammar_meaning")
	private String grammarMeaning;

	@Column(name = "explanation_meaning")
	private String explanationMeaning;

	@Column(name = "example_meaning")
	private String exampleMeaning;

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

	public GrammarEntity(Long id, String grammar, String explanation, String grammarMeaning, String explanationMeaning,
			String exampleMeaning, String exampleImageLink, Set<UserEntity> userEntities, LessonEntity lessonEntity) {
		super();
		this.id = id;
		this.grammar = grammar;
		this.explanation = explanation;
		this.grammarMeaning = grammarMeaning;
		this.explanationMeaning = explanationMeaning;
		this.exampleMeaning = exampleMeaning;
		this.exampleImageLink = exampleImageLink;
		this.userEntities = userEntities;
		this.lessonEntity = lessonEntity;
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

	public String getGrammarMeaning() {
		return grammarMeaning;
	}

	public void setGrammarMeaning(String grammarMeaning) {
		this.grammarMeaning = grammarMeaning;
	}

	public String getExplanationMeaning() {
		return explanationMeaning;
	}

	public void setExplanationMeaning(String explanationMeaning) {
		this.explanationMeaning = explanationMeaning;
	}

	public String getExampleMeaning() {
		return exampleMeaning;
	}

	public void setExampleMeaning(String exampleMeaning) {
		this.exampleMeaning = exampleMeaning;
	}

	public String getExampleImageLink() {
		return exampleImageLink;
	}

	public void setExampleImageLink(String exampleImageLink) {
		this.exampleImageLink = exampleImageLink;
	}

	public Set<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	public LessonEntity getLessonEntity() {
		return lessonEntity;
	}

	public void setLessonEntity(LessonEntity lessonEntity) {
		this.lessonEntity = lessonEntity;
	}

}

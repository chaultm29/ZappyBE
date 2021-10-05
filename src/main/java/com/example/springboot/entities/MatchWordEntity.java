package com.example.springboot.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Matchword")
public class MatchWordEntity {
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "word_origin")
	private String wordOrigin;

	@Column(name = "word_equivalent")
	private String wordEquivalent;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private QuestionEntity questionEntity;

	public MatchWordEntity() {
		super();
	}

	public MatchWordEntity(Long id, String wordOrigin, String wordEquivalent) {
		super();
		this.id = id;
		this.wordOrigin = wordOrigin;
		this.wordEquivalent = wordEquivalent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWordOrigin() {
		return wordOrigin;
	}

	public void setWordOrigin(String wordOrigin) {
		this.wordOrigin = wordOrigin;
	}

	public String getWordEquivalent() {
		return wordEquivalent;
	}

	public void setWordEquivalent(String wordEquivalent) {
		this.wordEquivalent = wordEquivalent;
	}

}

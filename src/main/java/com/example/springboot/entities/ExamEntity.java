package com.example.springboot.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "exams")
public class ExamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "score")
	private int score;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "time")
	private Date time;

	@ManyToMany
	@JoinTable(name = "exam_question_rf", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
	private Set<QuestionEntity> questionEntities;

	@ManyToOne
	private UserEntity user;

	public ExamEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamEntity(Long id, int score, Date createdDate, Date time, Set<QuestionEntity> questionEntities,
			UserEntity user) {
		super();
		this.id = id;
		this.score = score;
		this.createdDate = createdDate;
		this.time = time;
		this.questionEntities = questionEntities;
		this.user = user;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<QuestionEntity> getQuestionEntities() {
		return questionEntities;
	}

	public void setQuestionEntities(Set<QuestionEntity> questionEntities) {
		this.questionEntities = questionEntities;
	}

}

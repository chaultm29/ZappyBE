package com.example.springboot.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
	private Integer time;
	
	@ManyToMany
	@JoinTable(name = "exam_question_rf", 
			joinColumns = @JoinColumn(name = "question_id"), 
			inverseJoinColumns = @JoinColumn(name = "exam_id"))
	private Set<QuestionEntity> questionEntities;

	@ManyToOne
	private UserEntity user;

	public ExamEntity() {
		this.createdDate = Date.valueOf(LocalDate.now(ZoneId.of("GMT+07:00")));
	}

	public ExamEntity(Long id, int score, Integer time, Set<QuestionEntity> questionEntities,UserEntity user) {
		super();
		this.id = id;
		this.score = score;
		this.createdDate = Date.valueOf(LocalDate.now(ZoneId.of("GMT+07:00")));;
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

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Set<QuestionEntity> getQuestionEntities() {
		return questionEntities;
	}

	public void setQuestionEntities(Set<QuestionEntity> questionEntities) {
		this.questionEntities = questionEntities;
	}
	
}

package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class QuestionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_link")
	private String image_link;

	@Column(name = "question")
	private String question;

	@OneToMany(mappedBy = "questionEntity", cascade = CascadeType.ALL)
	private Set<AnswerEntity> answerEntities;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private QuestionTypeEntity questionTypeEntity;

	@ManyToOne
	@JoinColumn(name = "skill_id")
	private SkillEntity skillEntity;

	@ManyToMany(mappedBy = "questionEntities")
	private Set<RoomEntity> roomEntities;

	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private LessonEntity lessonEntity;
	
	@ManyToMany(mappedBy = "questionEntities")
	private Set<ExamEntity> examEntities;

	public QuestionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionEntity(Long id, String image_link, String question, Set<AnswerEntity> answerEntities,
			QuestionTypeEntity questionTypeEntity, SkillEntity skillEntity, Set<RoomEntity> roomEntities,
			LessonEntity lessonEntity, Set<ExamEntity> examEntities) {
		super();
		this.id = id;
		this.image_link = image_link;
		this.question = question;
		this.answerEntities = answerEntities;
		this.questionTypeEntity = questionTypeEntity;
		this.skillEntity = skillEntity;
		this.roomEntities = roomEntities;
		this.lessonEntity = lessonEntity;
		this.examEntities = examEntities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<AnswerEntity> getAnswerEntities() {
		return answerEntities;
	}

	public void setAnswerEntities(Set<AnswerEntity> answerEntities) {
		this.answerEntities = answerEntities;
	}

	public QuestionTypeEntity getQuestionTypeEntity() {
		return questionTypeEntity;
	}

	public void setQuestionTypeEntity(QuestionTypeEntity questionTypeEntity) {
		this.questionTypeEntity = questionTypeEntity;
	}

	public SkillEntity getSkillEntity() {
		return skillEntity;
	}

	public void setSkillEntity(SkillEntity skillEntity) {
		this.skillEntity = skillEntity;
	}

	public Set<RoomEntity> getRoomEntities() {
		return roomEntities;
	}

	public void setRoomEntities(Set<RoomEntity> roomEntities) {
		this.roomEntities = roomEntities;
	}

	public LessonEntity getLessonEntity() {
		return lessonEntity;
	}

	public void setLessonEntity(LessonEntity lessonEntity) {
		this.lessonEntity = lessonEntity;
	}

	public Set<ExamEntity> getExamEntities() {
		return examEntities;
	}

	public void setExamEntities(Set<ExamEntity> examEntities) {
		this.examEntities = examEntities;
	}

	

}

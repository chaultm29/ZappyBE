package com.example.springboot.dto;

public class QuestionBaseDTO {
	private Long questionID;
	private String typeName;
	private String lessonName;
	private String skillName;
	private String question;
	private Long idAnswer;
	private boolean correctAnsw;
	private String imageLinkAnsw;
	private String nameAnswer;
	private String imgLinkQuestion;

	public QuestionBaseDTO() {
	}

	public QuestionBaseDTO(Long questionID, String typeName, String lessonName, String skillName, String question,
			Long idAnswer, boolean correctAnsw, String imageLinkAnsw, String nameAnswer, String imgLinkQuestion) {
		this.questionID = questionID;
		this.typeName = typeName;
		this.lessonName = lessonName;
		this.skillName = skillName;
		this.question = question;
		this.idAnswer = idAnswer;
		this.correctAnsw = correctAnsw;
		this.imageLinkAnsw = imageLinkAnsw;
		this.nameAnswer = nameAnswer;
		this.imgLinkQuestion = imgLinkQuestion;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(Long idAnswer) {
		this.idAnswer = idAnswer;
	}

	public boolean isCorrectAnsw() {
		return correctAnsw;
	}

	public void setCorrectAnsw(boolean correctAnsw) {
		this.correctAnsw = correctAnsw;
	}

	public String getImageLinkAnsw() {
		return imageLinkAnsw;
	}

	public void setImageLinkAnsw(String imageLinkAnsw) {
		this.imageLinkAnsw = imageLinkAnsw;
	}

	public String getNameAnswer() {
		return nameAnswer;
	}

	public void setNameAnswer(String nameAnswer) {
		this.nameAnswer = nameAnswer;
	}

	public String getImgLinkQuestion() {
		return imgLinkQuestion;
	}

	public void setImgLinkQuestion(String imgLinkQuestion) {
		this.imgLinkQuestion = imgLinkQuestion;
	}
}

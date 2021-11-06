package com.example.springboot.dto;

import com.example.springboot.entities.AnswerEntity;

import java.util.List;
import java.util.Set;

public class QuestionExamDTO {
    private Long questionID;
    private String typeName;
    private String lessonName;
    private String question;
    private List<AnswerDTO> answer;
    private String imgeLink;

    public QuestionExamDTO() {
    }

    public QuestionExamDTO(Long questionID, String typeName, String lessonName, String question, List<AnswerDTO> answer, String imgeLink) {
        this.questionID = questionID;
        this.typeName = typeName;
        this.lessonName = lessonName;
        this.question = question;
        this.answer = answer;
        this.imgeLink = imgeLink;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDTO> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AnswerDTO> answer) {
        this.answer = answer;
    }

    public String getImgeLink() {
        return imgeLink;
    }

    public void setImgeLink(String imgeLink) {
        this.imgeLink = imgeLink;
    }
}

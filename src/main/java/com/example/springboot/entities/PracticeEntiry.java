package com.example.springboot.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "practice")
public class PracticeEntiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntities;


    @ManyToMany
    @JoinTable(name = "practice_question_rf",
            joinColumns = @JoinColumn(name = "practice_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<QuestionEntity> questionEntities;

    public PracticeEntiry() {
    }

    public PracticeEntiry(Long id, int score, UserEntity userEntities, Set<QuestionEntity> questionEntities) {
        this.id = id;
        this.score = score;
        this.userEntities = userEntities;
        this.questionEntities = questionEntities;
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

    public UserEntity getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(UserEntity userEntities) {
        this.userEntities = userEntities;
    }

    public Set<QuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(Set<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }
}

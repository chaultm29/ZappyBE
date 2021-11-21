package com.example.springboot.dto;

import java.sql.Date;

public class ExamDTO {
    private int score;
    private Date createdDate;
    private Integer time;

    public ExamDTO() {
    }

    public ExamDTO(int score, Date createdDate, Integer time) {
        this.score = score;
        this.createdDate = createdDate;
        this.time = time;
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
}

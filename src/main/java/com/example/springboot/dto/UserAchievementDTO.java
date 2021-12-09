package com.example.springboot.dto;

import java.util.Date;

public class UserAchievementDTO {
    private String name;
    private Date dateCreate;

    public UserAchievementDTO() {
    }

    public UserAchievementDTO(String name, Date dateCreate) {
        this.name = name;
        this.dateCreate = dateCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}

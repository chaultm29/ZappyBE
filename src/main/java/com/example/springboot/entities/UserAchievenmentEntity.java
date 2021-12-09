package com.example.springboot.entities;


import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "users_achievenment")
public class UserAchievenmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    private Date dateCreated;


    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AchievementEntity achievenmentEntity;

    public UserAchievenmentEntity() {
    	this.dateCreated = Date.valueOf(LocalDate.now(ZoneId.of("GMT+07:00")));
    }

    public UserAchievenmentEntity(Long id, UserEntity user, AchievementEntity achievenmentEntity) {
        this.id = id;
        this.dateCreated = Date.valueOf(LocalDate.now(ZoneId.of("GMT+07:00")));
        this.user = user;
        this.achievenmentEntity = achievenmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateOfBirth) {
        this.dateCreated = dateOfBirth;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AchievementEntity getAchievenmentEntity() {
        return achievenmentEntity;
    }

    public void setAchievenmentEntity(AchievementEntity achievenmentEntity) {
        this.achievenmentEntity = achievenmentEntity;
    }
}

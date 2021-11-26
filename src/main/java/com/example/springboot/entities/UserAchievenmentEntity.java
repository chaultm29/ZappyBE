package com.example.springboot.entities;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users_achievenment")
public class UserAchievenmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    private Date dateCreate;


    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AchievenmentEntity achievenmentEntity;

    public UserAchievenmentEntity() {
    }

    public UserAchievenmentEntity(Long id, Date dateCreate, UserEntity user, AchievenmentEntity achievenmentEntity) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.user = user;
        this.achievenmentEntity = achievenmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateOfBirth) {
        this.dateCreate = dateOfBirth;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AchievenmentEntity getAchievenmentEntity() {
        return achievenmentEntity;
    }

    public void setAchievenmentEntity(AchievenmentEntity achievenmentEntity) {
        this.achievenmentEntity = achievenmentEntity;
    }
}

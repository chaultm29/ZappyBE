package com.example.springboot.repositories;


import com.example.springboot.entities.UserAchievenmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.AchievenmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AchievenmentRepository extends JpaRepository<AchievenmentEntity, Long>{
    @Query("SELECT a from AccountEntity ac inner join ac.userEntity u inner  join  u.userAchievenmentEntities a inner join  a.achievenmentEntity ach where ac.username=:username")
    List<UserAchievenmentEntity> getAchievenment(@Param("username") String username);

}

package com.example.springboot.repositories;


import com.example.springboot.entities.PracticeEntiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<PracticeEntiry, Long> {
    @Query("select  pa from  AccountEntity a inner join  a.userEntity u inner join  u.paPracticeEntiries pa where a.username = :username order by pa.id desc ")
    List<PracticeEntiry> getPracticeByUserName(@Param("username") String username);
    @Query("select COUNT(p) from AccountEntity a inner join  a.userEntity u inner join u.paPracticeEntiries p inner join p.questionEntities q where a.username=:username and q.skillEntity.id=:idSkill and p.score>= 80")
    Integer getIdsPracticeBySkillUserScore(@Param("username")String username, @Param("idSkill") Long idSkill);
    @Query("select COUNT(p) from AccountEntity a inner join  a.userEntity u inner join u.paPracticeEntiries p inner join p.questionEntities q where a.username=:username and q.skillEntity.id=:idSkill ")
    Integer getIdsPracticeBySkillUser(@Param("username")String username, @Param("idSkill") Long idSkill);
    @Query("select SUM(p.score) from AccountEntity a inner join a.userEntity u inner join u.paPracticeEntiries p where a.username=:username GROUP BY p.userEntities.id")
    Integer totalScorePracticeByUsername(@Param("username") String username);
}

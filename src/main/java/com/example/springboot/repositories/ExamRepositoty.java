package com.example.springboot.repositories;

import com.example.springboot.entities.ExamEntity;
import com.example.springboot.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepositoty extends JpaRepository<ExamEntity, Long> {

	@Query("select  e from  AccountEntity a inner join  a.userEntity u inner join  u.examEntities e where a.username = :username order by e.createdDate desc ")
	List<ExamEntity> getExamByUserName(@Param("username") String username);

	@Query("select sum(e.score) from AccountEntity a inner join  a.userEntity u inner  join u.examEntities e where a.username=:username  group by a.username")
	Integer totalScoreExamByUsername(@Param("username") String username);

	@Query("select e.score from AccountEntity a inner join  a.userEntity u inner  join u.examEntities e where a.username=:username order by e.createdDate desc ")
	List<Integer> totalScore10ExamByUsername(@Param("username") String username, Pageable pageable);
}

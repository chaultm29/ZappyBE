package com.example.springboot.repositories;

import com.example.springboot.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.AnswerEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
	@Transactional
	@Modifying
	@Query(value = "delete from AnswerEntity a where a.questionEntity.id = :id ")
	void deleteByIdQuestion(@Param("id") Long id);
}

package com.example.springboot.repositories;

import com.example.springboot.entities.QuestionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.LessonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
	@Query("select l.id from LessonEntity l where l.lessonName =:lessonName")
	Long getIdLessonByName(@Param("lessonName") String lessonName);

	@Query("select l from LessonEntity l where l.lessonName =:lessonName")
	LessonEntity getByName(@Param("lessonName") String lessonName);
}

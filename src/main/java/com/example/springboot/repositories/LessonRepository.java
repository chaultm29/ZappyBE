package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.LessonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
	@Query("select l.id from LessonEntity l where l.lessonName =:lessonName")
	Long getIdLessonByName(@Param("lessonName") String lessonName);

	@Query("select l from LessonEntity l where l.lessonName =:lessonName")
	LessonEntity getByName(@Param("lessonName") String lessonName);
	
	@Query(value = "SELECT distinct lesson_id FROM zappy.practice_question_rf\r\n"
			+ "inner join zappy.practice on practice_question_rf.practice_id = practice.id\r\n"
			+ "inner join zappy.questions on practice_question_rf.question_id = questions.id \r\n"
			+ "where user_id = :uid and skill_id = :skid and score >= 80 order by lesson_id asc", nativeQuery = true)
	List<Long> getLearntLesson(@Param("uid") Long uid, @Param("skid") Long skid);
}

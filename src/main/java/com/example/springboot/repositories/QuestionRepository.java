package com.example.springboot.repositories;

import com.example.springboot.dto.QuestionBaseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	@Query("select new com.example.springboot.dto.QuestionBaseDTO(q.id,qt.typeName,l.lessonName,sk.skillName,q.question,"
			+ "a.id,a.correct,a.image_link,a.answer,q.image_link) "
			+ "from QuestionEntity q left  join q.answerEntities a left join q.lessonEntity l left  join q.questionTypeEntity qt left  join q.skillEntity sk order by q.id")
	List<QuestionBaseDTO> getAllQuestion();
}

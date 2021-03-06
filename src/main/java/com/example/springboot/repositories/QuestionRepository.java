package com.example.springboot.repositories;

import com.example.springboot.dto.QuestionBaseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	@Query("select new com.example.springboot.dto.QuestionBaseDTO(q.id,qt.typeName,l.lessonName,sk.skillName,q.question,"
			+ "a.id,a.correct,a.image_link,a.answer,q.image_link) "
			+ "from QuestionEntity q left  join q.answerEntities a left join q.lessonEntity l left  join q.questionTypeEntity qt left  join q.skillEntity sk where q.isEnabled=true  order by q.id")
	List<QuestionBaseDTO> getAllQuestion();


	@Query("select DISTINCT q from QuestionEntity q left  join q.answerEntities a left join q.lessonEntity l left  join q.questionTypeEntity qt left  join q.skillEntity sk where q.isEnabled=true  order by q.id")
	List<QuestionEntity> getAllQuestionEnable();

	@Query("select q from QuestionEntity q left  join q.answerEntities a left join q.lessonEntity l left  join q.questionTypeEntity qt left  join q.skillEntity sk where q.isEnabled=true and q.id = :id order by q.id")
	QuestionEntity getQuestionEnableByID(@Param("id") Long id);

	@Query("select q from QuestionEntity q where q.isEnabled=true and q.lessonEntity.id = :idLess and q.skillEntity.id = :idSkill and q.questionTypeEntity.id = :idType")
	List<QuestionEntity> getQuestionByLessonAndTypeAndSkill(@Param("idLess") Long idLess,
			@Param("idSkill") Long idSkill, @Param("idType") Long idType);

	@Query("select DISTINCT q.lessonEntity.id from QuestionEntity q where q.isEnabled=true ")
	List<Long> getIDLessonExit();

	@Query("select DISTINCT q.questionTypeEntity.id from QuestionEntity q where q.isEnabled=true  ")
	List<Long> getIDTypeExit();

	@Query("select DISTINCT q.skillEntity.id from QuestionEntity q where q.isEnabled=true  ")
	List<Long> getIDSkillExit();

	@Query("select q from QuestionEntity q inner join q.answerEntities a where q.id = :ids and a.answer = :value and a.correct = true and q.isEnabled=true ")
	QuestionEntity getAllQuestionByAnswer(@Param("ids") Long id, @Param("value") String value);

	@Query("select q from QuestionEntity q where q.id in :ids and  q.isEnabled=true ")
	List<QuestionEntity> getAllQuestionByIDs(@Param("ids") List<Long> id);

	@Query("select q from QuestionEntity q where q.question = :question and q.isEnabled=true")
	QuestionEntity getQuestion(@Param("question") String question);

	@Query(value = "select * from questions q where  q.type_id = 1 and q.enabled = 1 order by RAND() limit 1", nativeQuery = true)
	Optional<QuestionEntity> getQuestionBingoGame();
}

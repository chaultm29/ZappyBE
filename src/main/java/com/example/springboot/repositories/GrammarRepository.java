package com.example.springboot.repositories;

import java.util.List;

import com.example.springboot.dto.GetAllKanjiDTO;
import com.example.springboot.dto.GetAllVocabularyDTO;
import com.example.springboot.dto.GrammarBaseDTO;
import com.example.springboot.entities.KanjiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.GrammarEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot.entities.GrammarEntity;

public interface GrammarRepository extends JpaRepository<GrammarEntity, Long> {
	@Query(value = "select * from grammars where lesson_id = ?1", nativeQuery = true)
	public List<GrammarEntity> getByLessonId(Long lesson_id);

	@Query("select new com.example.springboot.dto.GrammarBaseDTO(g.id,l.lessonName,g.grammar,"
			+ "g.explanation,g.grammarMeaning,g.example,g.exampleMeaning,g.exampleImageLink)  from GrammarEntity g left join g.lessonEntity l")
	List<GrammarBaseDTO> getAllGrammar();

	@Query("select new com.example.springboot.dto.GrammarBaseDTO(g.id,l.lessonName,g.grammar,"
			+ "g.explanation,g.grammarMeaning,g.example,g.exampleMeaning,g.exampleImageLink)  from GrammarEntity g left join g.lessonEntity l where g.id=:id")
	GrammarBaseDTO getGrammarDTOById(@Param("id") Long id);

	@Query("select g from GrammarEntity g left join g.lessonEntity l where g.id=:id")
	GrammarEntity getGrammarEntityById(@Param("id") Long id);

	@Query("select g from GrammarEntity g  where g.grammar = :grammar")
	GrammarEntity getGrammar(@Param("grammar") String grammar);
}

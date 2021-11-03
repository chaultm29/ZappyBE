package com.example.springboot.repositories;

import java.util.List;

import com.example.springboot.dto.GetAllVocabularyDTO;
import com.example.springboot.dto.VocabularyBaseDTO;
import com.example.springboot.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.VocabularyEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Long> {
	@Query(value = "select * from vocabularies where lesson_id = ?1", nativeQuery = true)
	public List<VocabularyEntity> getByLessonId(Long lesson_id);

	@Query("select new com.example.springboot.dto.GetAllVocabularyDTO(v.id,l.lessonName,v.vocabulary,v.meaning,v.imageLink,v.example, v.exampleMeaning,v.exampleImageLink)  from VocabularyEntity v left join v.lessonEntity l")
	List<GetAllVocabularyDTO> getAllVocabulary();

	@Query("select v from VocabularyEntity v left join v.lessonEntity l where v.id=:id")
	VocabularyEntity getVocabularyById(@Param("id") Long id);

	@Query("select new com.example.springboot.dto.VocabularyBaseDTO(v.id,l.lessonName,v.vocabulary,v.meaning,v.imageLink,v.example, v.exampleMeaning,v.exampleImageLink)  from VocabularyEntity v left join v.lessonEntity l where v.id =:id")
	VocabularyBaseDTO getVocabularyDTOById(@Param("id") Long id);
}

package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.VocabularyEntity;

public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Long> {
	@Query(value = "select * from vocabularies where lesson_id = ?1", nativeQuery = true)
	public List<VocabularyEntity> getByLessonId(Long lesson_id);
}

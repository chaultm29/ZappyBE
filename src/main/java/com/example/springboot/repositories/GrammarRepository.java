package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.GrammarEntity;

public interface GrammarRepository extends JpaRepository<GrammarEntity, Long> {
	@Query(value = "select * from grammars where lesson_id = ?1", nativeQuery = true)
	public List<GrammarEntity> getByLessonId(Long lesson_id);
}

package com.example.springboot.repositories;

import com.example.springboot.entities.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.QuestionTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionTypeRepository extends JpaRepository<QuestionTypeEntity, Long> {
	@Query(value = "select qt from QuestionTypeEntity qt where qt.typeName = :name ")
	QuestionTypeEntity getByName(@Param("name") String name);
}

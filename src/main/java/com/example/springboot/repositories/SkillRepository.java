package com.example.springboot.repositories;

import com.example.springboot.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.SkillEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
	@Query(value = "select s from SkillEntity s where s.skillName = :name ")
	SkillEntity getByName(@Param("name") String name);
}

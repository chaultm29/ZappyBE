package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.LessonEntity;

public interface LessonRepository extends JpaRepository<LessonEntity, Long>{

}

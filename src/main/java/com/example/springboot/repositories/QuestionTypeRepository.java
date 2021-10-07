package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.QuestionTypeEntity;

public interface QuestionTypeRepository extends JpaRepository<QuestionTypeEntity, Long>{

}

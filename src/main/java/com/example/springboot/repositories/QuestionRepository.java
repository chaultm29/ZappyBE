package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{

}

package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long>{

}

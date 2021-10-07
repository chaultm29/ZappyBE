package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.VocabularyEntity;

public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Long>{

}

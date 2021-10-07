package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.GrammarEntity;

public interface GrammarRepository extends JpaRepository<GrammarEntity, Long>{

}

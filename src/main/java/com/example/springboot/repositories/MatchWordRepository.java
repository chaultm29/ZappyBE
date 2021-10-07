package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.MatchWordEntity;

public interface MatchWordRepository extends JpaRepository<MatchWordEntity, Long>{

}

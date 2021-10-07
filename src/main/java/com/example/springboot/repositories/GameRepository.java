package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.GameEntity;

public interface GameRepository extends JpaRepository<GameEntity, Long>{

}

package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.SkillEntity;

public interface SkillRepository extends JpaRepository<SkillEntity, Long>{

}

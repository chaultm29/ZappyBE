package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.KanjiEntity;

public interface KanjiRepository extends JpaRepository<KanjiEntity, Long>{

}

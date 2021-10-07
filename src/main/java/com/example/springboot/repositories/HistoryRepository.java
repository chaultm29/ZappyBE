package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.HistoryEntity;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long>{

}

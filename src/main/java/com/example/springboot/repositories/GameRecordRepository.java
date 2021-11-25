package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboot.entities.GameRecordEntity;

@Repository
public interface GameRecordRepository extends JpaRepository<GameRecordEntity, Long>{
	List<GameRecordEntity> findByUserId(Long uid);
}

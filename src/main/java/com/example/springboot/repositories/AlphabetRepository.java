package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.AlphabetEntity;

public interface AlphabetRepository extends JpaRepository<AlphabetEntity, Long>{

}

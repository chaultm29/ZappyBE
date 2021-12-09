package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.AWSConfigEntity;

public interface AWSConfigRepository extends JpaRepository<AWSConfigEntity, Long> {

}

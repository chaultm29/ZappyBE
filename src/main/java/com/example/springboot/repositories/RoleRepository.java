package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	List<RoleEntity> findByName(String name);
}

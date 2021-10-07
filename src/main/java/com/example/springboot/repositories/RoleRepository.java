package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}

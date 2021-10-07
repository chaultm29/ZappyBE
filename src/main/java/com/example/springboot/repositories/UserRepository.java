package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}

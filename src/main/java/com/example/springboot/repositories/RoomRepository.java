package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Long>{

}

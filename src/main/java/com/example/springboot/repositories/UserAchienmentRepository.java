package com.example.springboot.repositories;

import com.example.springboot.entities.UserAchievenmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAchienmentRepository extends JpaRepository<UserAchievenmentEntity,Long> {

}

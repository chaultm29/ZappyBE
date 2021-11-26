package com.example.springboot.services;

import com.example.springboot.converters.AchievementConverter;
import com.example.springboot.dto.UserAchievenmentDTO;
import com.example.springboot.entities.UserAchievenmentEntity;
import com.example.springboot.repositories.AchievenmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievenmentService {
    @Autowired
    AchievenmentRepository achievenmentRepository;
    @Autowired
    AchievementConverter achievementConverter;
    public List<UserAchievenmentDTO> getAchievement(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserAchievenmentEntity> list = achievenmentRepository.getAchievenment(username);
        return achievementConverter.toDTOs(list);
    }

}

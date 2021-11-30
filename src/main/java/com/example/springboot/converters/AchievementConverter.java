package com.example.springboot.converters;

import com.example.springboot.dto.UserAchievenmentDTO;
import com.example.springboot.entities.UserAchievenmentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AchievementConverter {

    public UserAchievenmentDTO toDTO(UserAchievenmentEntity userAchievenmentEntity) {
        UserAchievenmentDTO userAchievenmentDTO = new UserAchievenmentDTO();
        userAchievenmentDTO.setName(userAchievenmentEntity.getAchievenmentEntity().getName());
        userAchievenmentDTO.setDateCreate(userAchievenmentEntity.getDateCreate());
        return userAchievenmentDTO;
    }

    public List<UserAchievenmentDTO> toDTOs(List<UserAchievenmentEntity> listEntities) {
        ArrayList<UserAchievenmentDTO> roleDTOs = new ArrayList<UserAchievenmentDTO>();
        for (UserAchievenmentEntity roleEntity : listEntities) {
            roleDTOs.add(toDTO(roleEntity));
        }
        return roleDTOs;
    }

}

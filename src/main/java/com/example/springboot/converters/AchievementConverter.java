package com.example.springboot.converters;

import com.example.springboot.dto.AchievementDTO;
import com.example.springboot.dto.UserAchievementDTO;
import com.example.springboot.entities.AchievementEntity;
import com.example.springboot.entities.UserAchievenmentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AchievementConverter {

    public UserAchievementDTO toDTO(UserAchievenmentEntity userAchievenmentEntity) {
        UserAchievementDTO userAchievenmentDTO = new UserAchievementDTO();
        userAchievenmentDTO.setName(userAchievenmentEntity.getAchievenmentEntity().getName());
        userAchievenmentDTO.setDateCreate(userAchievenmentEntity.getDateCreated());
        return userAchievenmentDTO;
    }

    public List<UserAchievementDTO> toDTOs(List<UserAchievenmentEntity> listEntities) {
        ArrayList<UserAchievementDTO> listDTOs = new ArrayList<UserAchievementDTO>();
        for (UserAchievenmentEntity userAchievenmentEntity : listEntities) {
        	listDTOs.add(toDTO(userAchievenmentEntity));
        }
        return listDTOs;
    }
    
    public AchievementDTO toDTO(AchievementEntity achievementEntity) {
    	AchievementDTO achievementDTO = new AchievementDTO();
    	achievementDTO.setId(achievementEntity.getId());
    	achievementDTO.setName(achievementEntity.getName());
    	achievementDTO.setDesciption(achievementEntity.getDesciption());
    	achievementDTO.setCondition(achievementEntity.getCondition());
    	achievementDTO.setImageLink(achievementEntity.getImageLink());
        return achievementDTO;
    }
    
    public List<AchievementDTO> toAchieDTOs(List<AchievementEntity> listEntities) {
        ArrayList<AchievementDTO> listDTOs = new ArrayList<AchievementDTO>();
        for (AchievementEntity entity : listEntities) {
        	listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }

}

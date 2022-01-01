package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.LessonDTO;
import com.example.springboot.entities.LessonEntity;

@Component
public class LessonConverter {
	public LessonEntity toEntity(LessonDTO lessonDTO) {
		LessonEntity lessonEntity = new LessonEntity();
		lessonEntity.setId(lessonDTO.getId());
		return lessonEntity;
	}

	public LessonDTO toDTO(LessonEntity lessonEntity) {
		LessonDTO lessonDTO = new LessonDTO();
		lessonDTO.setId(lessonEntity.getId());
		lessonDTO.setLessonName(lessonEntity.getLessonName());
		return lessonDTO;
	}

	public List<LessonDTO> toDTOs(List<LessonEntity> listEntities) {
		ArrayList<LessonDTO> lessonDTOs = new ArrayList<LessonDTO>();
		for (LessonEntity lessonEntity : listEntities) {
			lessonDTOs.add(toDTO(lessonEntity));
		}
		return lessonDTOs;
	}
	
	public LessonDTO toDTO(LessonEntity lessonEntity, boolean learnt) {
		LessonDTO lessonDTO = new LessonDTO();
		lessonDTO.setId(lessonEntity.getId());
		lessonDTO.setLessonName(lessonEntity.getLessonName());
		lessonDTO.setIsLearnt(learnt);
		return lessonDTO;
	}
	
	public List<LessonDTO> toDTOs(List<LessonEntity> listEntities, List<Long> listLearnt) {
		ArrayList<LessonDTO> lessonDTOs = new ArrayList<LessonDTO>();
		for (LessonEntity lessonEntity : listEntities) {
			boolean learnt = listLearnt.contains(lessonEntity.getId());
			lessonDTOs.add(toDTO(lessonEntity, learnt));
		}
		return lessonDTOs;
	}

}

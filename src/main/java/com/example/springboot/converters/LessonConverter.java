package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.entities.LessonEntity;
import com.example.springbootdto.LessonDTO;

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

}

package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.VocabularyDTO;
import com.example.springboot.entities.VocabularyEntity;


@Component
public class VocabularyConverter {
	public VocabularyEntity toEntity(VocabularyDTO vocabularyDTO) {
		VocabularyEntity vocabularyEntity = new VocabularyEntity();
		vocabularyEntity.setImageLink(vocabularyDTO.getImageLink());
		vocabularyEntity.setId(vocabularyDTO.getId());
		vocabularyEntity.setVocabulary(vocabularyDTO.getVocabulary());
		vocabularyEntity.setMeaning(vocabularyDTO.getMeaning());
		vocabularyEntity.setExample(vocabularyDTO.getExample());
		vocabularyEntity.setExampleMeaning(vocabularyDTO.getExampleMeaning());
		vocabularyEntity.setExampleImageLink(vocabularyDTO.getExampleImageLink());
		return vocabularyEntity;
	}

	public VocabularyDTO toDTO(VocabularyEntity vocabularyEntity) {
		VocabularyDTO vocabularyDTO = new VocabularyDTO();
		vocabularyDTO.setId(vocabularyEntity.getId());
		vocabularyDTO.setImageLink(vocabularyEntity.getImageLink());
		vocabularyDTO.setVocabulary(vocabularyEntity.getVocabulary());
		vocabularyDTO.setMeaning(vocabularyEntity.getMeaning());
		vocabularyDTO.setExample(vocabularyEntity.getExample());
		vocabularyDTO.setExampleMeaning(vocabularyEntity.getExampleMeaning());
		vocabularyDTO.setExampleImageLink(vocabularyEntity.getExampleImageLink());
		vocabularyDTO.setLesson_id(vocabularyEntity.getLessonEntity().getId());
		return vocabularyDTO;
	}

	public List<VocabularyDTO> toDTOs(List<VocabularyEntity> listEntities) {
		ArrayList<VocabularyDTO> vocabularyDTOs = new ArrayList<VocabularyDTO>();
		for (VocabularyEntity vocabularyEntity : listEntities) {
			vocabularyDTOs.add(toDTO(vocabularyEntity));
		}
		return vocabularyDTOs;
	}

}

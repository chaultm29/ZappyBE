package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.VocabularyConverter;
import com.example.springboot.entities.VocabularyEntity;
import com.example.springboot.repositories.VocabularyRepository;
import com.example.springbootdto.VocabularyDTO;

@Service
public class VocabularyService {
	@Autowired
	VocabularyRepository vocabularyRepository;

	@Autowired
	VocabularyConverter vocabularyConverter;

	public List<VocabularyDTO> getByLessionId(Long lesson_id) {
		List<VocabularyEntity> vocabularyEntities = vocabularyRepository.getByLessonId(lesson_id);
		return vocabularyConverter.toDTOs(vocabularyEntities);
	}

}

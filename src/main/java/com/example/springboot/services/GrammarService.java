package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.GrammarConverter;
import com.example.springboot.dto.GrammarDTO;
import com.example.springboot.entities.GrammarEntity;
import com.example.springboot.repositories.GrammarRepository;

@Service
public class GrammarService {
	@Autowired
	GrammarRepository grammarRepository;

	@Autowired
	GrammarConverter grammarConverter;

	public List<GrammarDTO> getByLessionId(Long lesson_id) {
		List<GrammarEntity> grammarEntities = grammarRepository.getByLessonId(lesson_id);
		return grammarConverter.toDTOs(grammarEntities);
	}

}

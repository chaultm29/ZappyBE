package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.KanjiConverter;
import com.example.springboot.dto.KanjiDTO;
import com.example.springboot.entities.KanjiEntity;
import com.example.springboot.repositories.KanjiRepository;

@Service
public class KanjiService {
	@Autowired
	KanjiRepository kanjiRepository;

	@Autowired
	KanjiConverter kanjiConverter;

	public List<KanjiDTO> getByLessionId(Long lesson_id) {
		List<KanjiEntity> kanjiEntities = kanjiRepository.getByLessonId(lesson_id);
		return kanjiConverter.toDTOs(kanjiEntities);
	}

}

package com.example.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AlphabetConverter;
import com.example.springboot.converters.KanjiConverter;
import com.example.springboot.entities.AlphabetEntity;
import com.example.springboot.entities.KanjiEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AlphabetRepository;
import com.example.springboot.repositories.KanjiRepository;
import com.example.springbootdto.KanjiDTO;

@Service
public class KanjiService {
	@Autowired
	KanjiRepository kanjiRepository;

	@Autowired
	KanjiConverter kanjiConverter;

	public List<KanjiDTO> getByLessionId(Long lesson_id) {
		List<KanjiEntity> kanjiEntities = kanjiRepository.getByLessonId(lesson_id);
//		List<KanjiEntity> kanjiEntitiesLesson = new ArrayList<>();
//		for (KanjiEntity kanjiEntity : kanjiEntities) {
//			if (kanjiEntity.getLessonEntity().getId() == lesson_id) {
//				kanjiEntitiesLesson.add(kanjiEntity);
//			}
//		}
		return kanjiConverter.toDTOs(kanjiEntities);
	}

}

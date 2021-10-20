package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.LessonConverter;
import com.example.springboot.entities.LessonEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.LessonRepository;
import com.example.springbootdto.LessonDTO;

@Service
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	LessonConverter lessonConverter;

	public LessonDTO get(Long id) {
		LessonEntity lessonEntity = lessonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("lesson not exist with id :" + id));
		return lessonConverter.toDTO(lessonEntity);
	}
}

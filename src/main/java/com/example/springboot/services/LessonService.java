package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.LessonConverter;
import com.example.springboot.dto.LessonDTO;
import com.example.springboot.entities.LessonEntity;
import com.example.springboot.entities.UserEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.LessonRepository;
import com.example.springboot.repositories.UserRepository;

@Service
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	LessonConverter lessonConverter;
	
	@Autowired
	UserRepository userRepository;

	public LessonDTO get(Long id) {
		LessonEntity lessonEntity = lessonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("lesson not exist with id :" + id));
		return lessonConverter.toDTO(lessonEntity);
	}

	public List<LessonDTO> getLessonBySkill(Long skid) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		List<LessonEntity> lessonEntities = lessonRepository.findAll();
		List<Long> lessonLearnt = lessonRepository.getLearntLesson(userEntity.getId(), skid);
		return lessonConverter.toDTOs(lessonEntities, lessonLearnt);
	}
}

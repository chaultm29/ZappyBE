package com.example.springboot.services;

import java.util.List;

import com.example.springboot.dto.VocabularyBaseDTO;
import com.example.springboot.dto.GetAllVocabularyDTO;
import com.example.springboot.entities.LessonEntity;
import com.example.springboot.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.VocabularyConverter;
import com.example.springboot.dto.VocabularyDTO;
import com.example.springboot.entities.VocabularyEntity;
import com.example.springboot.repositories.VocabularyRepository;

@Service
public class VocabularyService {
	@Autowired
	VocabularyRepository vocabularyRepository;

	@Autowired
	VocabularyConverter vocabularyConverter;
	@Autowired
	LessonRepository lessonRepository;

	public List<VocabularyDTO> getByLessionId(Long lesson_id) {
		List<VocabularyEntity> vocabularyEntities = vocabularyRepository.getByLessonId(lesson_id);
		return vocabularyConverter.toDTOs(vocabularyEntities);
	}

	public void addVocabulary(VocabularyBaseDTO addVocabularyDTO) {
		VocabularyEntity vo = new VocabularyEntity();
		LessonEntity lessonEntity = new LessonEntity();
		lessonEntity.setId(lessonRepository.getIdLessonByName(addVocabularyDTO.getLessonName()));
		lessonEntity.setLessonName(addVocabularyDTO.getLessonName());
		vo.setLessonEntity(lessonEntity);
		vo.setVocabulary(addVocabularyDTO.getVocabulary());
		vo.setMeaning(addVocabularyDTO.getMeaning());
		vo.setImageLink(addVocabularyDTO.getImageLink());
		vo.setExample(addVocabularyDTO.getExample());
		vo.setExampleMeaning(addVocabularyDTO.getExampleMeaning());
		vo.setExampleImageLink(addVocabularyDTO.getExampleImageLink());
		vocabularyRepository.save(vo);
	}

	public void updateVocabulary(VocabularyBaseDTO addVocabularyDTO, Long idLesson) {
		VocabularyEntity vo = vocabularyRepository.getVocabularyById(idLesson);
		LessonEntity lessonEntity = new LessonEntity();
		lessonEntity.setId(lessonRepository.getIdLessonByName(addVocabularyDTO.getLessonName()));
		lessonEntity.setLessonName(addVocabularyDTO.getLessonName());
		vo.setLessonEntity(lessonEntity);
		vo.setVocabulary(addVocabularyDTO.getVocabulary());
		vo.setMeaning(addVocabularyDTO.getMeaning());
		vo.setImageLink(addVocabularyDTO.getImageLink());
		vo.setExample(addVocabularyDTO.getExample());
		vo.setExampleMeaning(addVocabularyDTO.getExampleMeaning());
		vo.setExampleImageLink(addVocabularyDTO.getExampleImageLink());
		vocabularyRepository.save(vo);
	}

	public void deleteVocabulary(Long idLesson) {
		VocabularyEntity vo = vocabularyRepository.getVocabularyById(idLesson);
		vocabularyRepository.delete(vo);
	}

	public VocabularyBaseDTO getVocabularyById(Long id) {
		VocabularyBaseDTO vocabulary = vocabularyRepository.getVocabularyDTOById(id);
		return vocabulary;
	}

	public List<GetAllVocabularyDTO> getAllVocabulary() {
		List<GetAllVocabularyDTO> listVocabulary = vocabularyRepository.getAllVocabulary();
		return listVocabulary;
	}

}

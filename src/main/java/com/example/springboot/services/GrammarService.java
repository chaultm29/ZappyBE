package com.example.springboot.services;

import java.util.List;

import com.example.springboot.dto.GrammarBaseDTO;
import com.example.springboot.entities.LessonEntity;
import com.example.springboot.repositories.LessonRepository;
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
	LessonRepository lessonRepository;
	@Autowired
	GrammarConverter grammarConverter;

	public List<GrammarDTO> getByLessionId(Long lesson_id) {
		List<GrammarEntity> grammarEntities = grammarRepository.getByLessonId(lesson_id);
		return grammarConverter.toDTOs(grammarEntities);
	}

	public List<GrammarBaseDTO> getAllGrammar() {
		List<GrammarBaseDTO> listGrammars = grammarRepository.getAllGrammar();
		return listGrammars;
	}

	public GrammarBaseDTO getGrammarById(Long id) {
		GrammarBaseDTO grammar = grammarRepository.getGrammarDTOById(id);
		return grammar;
	}

	public String addGrammar(GrammarBaseDTO grammarBaseDTO) {
//		GrammarEntity grammarEntity = grammarRepository.getGrammar(grammarBaseDTO.getGrammar());
//		if(grammarEntity != null){
//			return "Đã tồn tại "+ grammarEntity.getGrammar()+" trong hệ thống";
//		}
		GrammarEntity grammar = new GrammarEntity();
		LessonEntity lessonEntity = new LessonEntity();
		lessonEntity.setId(lessonRepository.getIdLessonByName(grammarBaseDTO.getLessonName()));
		lessonEntity.setLessonName(grammarBaseDTO.getLessonName());
		grammar.setLessonEntity(lessonEntity);
		grammar.setGrammar(grammarBaseDTO.getGrammar());
		grammar.setGrammarMeaning(grammarBaseDTO.getGrammarMeaning());
		grammar.setExample(grammarBaseDTO.getExample());
		grammar.setExplanation(grammarBaseDTO.getExplanation());
		grammar.setExampleImageLink(grammarBaseDTO.getExampleImageLink());
		grammarRepository.save(grammar);
		return "Thêm " + grammarBaseDTO.getGrammar() + " thành công";
	}

	public void updateGrammar(GrammarBaseDTO grammarBaseDTO, Long id) {
		GrammarEntity grammar = grammarRepository.getGrammarEntityById(id);
		LessonEntity lessonEntity = new LessonEntity();
		lessonEntity.setId(lessonRepository.getIdLessonByName(grammarBaseDTO.getLessonName()));
		lessonEntity.setLessonName(grammarBaseDTO.getLessonName());
		grammar.setLessonEntity(lessonEntity);
		grammar.setGrammar(grammarBaseDTO.getGrammar());
		grammar.setGrammarMeaning(grammarBaseDTO.getGrammarMeaning());
		grammar.setExample(grammarBaseDTO.getExample());
		grammar.setExplanation(grammarBaseDTO.getExplanation());
		grammar.setExampleImageLink(grammarBaseDTO.getExampleImageLink());
		grammarRepository.save(grammar);
	}

	public void deleteGrammar(Long id) {
		GrammarEntity grammar = grammarRepository.getGrammarEntityById(id);
		grammarRepository.delete(grammar);
	}
}
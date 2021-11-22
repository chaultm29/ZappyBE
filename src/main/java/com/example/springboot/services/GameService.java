package com.example.springboot.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.QuestionConverter;
import com.example.springboot.dto.QuestionGameDTO;
import com.example.springboot.entities.AnswerEntity;
import com.example.springboot.entities.QuestionEntity;
import com.example.springboot.repositories.AnswerRepository;
import com.example.springboot.repositories.QuestionRepository;

import javassist.NotFoundException;

@Service
public class GameService {
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionConverter questionConverter;
	
	@Autowired
	AnswerRepository answerRepository;
	
	public QuestionGameDTO getCurrentQuestion(List<Integer> listQuestionId) {
		QuestionEntity currentQuestion;
		do {
		currentQuestion = questionRepository.getQuestionBingoGame();
		}
		while(doesExist(currentQuestion.getId(), listQuestionId));
		return questionConverter.toQuestionGameDTOs(currentQuestion);
		}
	
	//check if questionId existed in list 
	private boolean doesExist(Long questionId,List<Integer> listQuestionId) {
		for(Integer id: listQuestionId) {
			if(Long.valueOf(id)==questionId) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCorrect(Long questionId, Long answerId) throws NotFoundException {
		AnswerEntity answerEntity = answerRepository.findById(answerId).get();
		for (AnswerEntity aEntity : questionRepository.findById(questionId).get().getAnswerEntities()) {
			if (aEntity.getId() == answerId) return answerEntity.isCorrect();
		}
		throw new NotFoundException("Not Found!");
	}
}

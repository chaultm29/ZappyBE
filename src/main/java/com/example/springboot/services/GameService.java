package com.example.springboot.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.QuestionConverter;
import com.example.springboot.dto.QuestionGameDTO;
import com.example.springboot.dto.RequireBingoQuestionDTO;
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
	
	public QuestionGameDTO getCurrentQuestion(RequireBingoQuestionDTO requireBingo) throws Exception {
		if(requireBingo.getLessonIds().isEmpty()) throw new NotFoundException("list lessons can not be empty"); 
		QuestionEntity currentQuestion;
		List<Long> listLessionId = questionRepository.getIDLessonExit();
		if (!doesExist(listLessionId, requireBingo.getLessonIds())) throw new Exception();
		do {
			currentQuestion = questionRepository.getQuestionBingoGame()
					.orElseThrow(() -> new Exception());
		}
		while(doesExist(currentQuestion.getId().intValue(), requireBingo.getQuestionIds()) ||
				!doesExist(currentQuestion.getLessonEntity().getId().intValue(), requireBingo.getLessonIds()) ||
				!hasAnswers(currentQuestion));
		return questionConverter.toQuestionGameDTOs(currentQuestion);
	}
	
	//check if Id existed in list 
	private boolean doesExist(int id,List<Integer> listId) {
		for(int _id: listId) {
			if(_id == id) {
				return true;
			}
		}
		return false;
	}
	
	private boolean doesExist(List<Long> listIdRepo ,List<Integer> listId) {
		for (Long id : listIdRepo) {
			if (doesExist(id.intValue(), listId)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasAnswers(QuestionEntity questionEntity) {
		return questionEntity.getAnswerEntities().size()==4;
	}
	
	public boolean isCorrect(Long questionId, Long answerId) throws NotFoundException {
		AnswerEntity answerEntity = answerRepository.findById(answerId).get();
		for (AnswerEntity aEntity : questionRepository.findById(questionId).get().getAnswerEntities()) {
			if (aEntity.getId() == answerId) return answerEntity.isCorrect();
		}
		throw new NotFoundException("Not Found!");
	}
}

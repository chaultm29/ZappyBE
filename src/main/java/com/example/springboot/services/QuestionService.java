package com.example.springboot.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.example.springboot.dto.QuestionBaseDTO;
import com.example.springboot.entities.AnswerEntity;
import com.example.springboot.entities.QuestionEntity;
import com.example.springboot.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.QuestionConverter;
import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.QuestionRepository;

@Service
public class QuestionService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionConverter questionConverter;

	@Autowired
	private AnswerRepository answerRepository;

	public List<QuestionDTO> get() {
		List<QuestionEntity> questionEntities = questionRepository.getAllQuestionEnable();
		List<QuestionDTO> questionDTOS = (questionEntities != null && questionEntities.size() != 0)
				? questionConverter.toDTOs(questionEntities)
				: new ArrayList<>();
		return questionDTOS;
	}

	public String save(QuestionDTO questionDTO) {
//		QuestionEntity questionEntity1 = questionRepository.getQuestion(questionDTO.getQuestion());
//		if(questionEntity1 != null){
//			return "Đã tồn tại "+ questionEntity1.getQuestion()+" trong hệ thống";
//		}
		QuestionEntity questionEntity = questionConverter.toEntity(questionDTO);
		questionEntity.setEnabled(true);
		QuestionEntity afterSave = questionRepository.save(questionEntity);
		Set<AnswerEntity> answerEntitySet = new HashSet<>();
		for (AnswerEntity answerEntity : questionDTO.getAnswer()) {
			answerEntitySet.add(answerRepository.save(new AnswerEntity(answerEntity.getId(), answerEntity.isCorrect(),
					answerEntity.getImage_link(), answerEntity.getAnswer(), afterSave)));
		}
		if (afterSave != null) {
			return "Thêm " + questionDTO.getQuestion() + " thành công";
		}

		return "Không thể thêm " + questionDTO.getQuestion();
	}

	public QuestionDTO update(QuestionDTO questionDTO, Long id) {
		QuestionEntity questionEntityBase = questionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Question not exist with id :" + id));
		QuestionEntity questionEntity = questionConverter.toEntity(questionDTO);
		questionEntityBase.setImage_link(questionDTO.getImgeLink());
		questionEntityBase.setQuestion(questionEntity.getQuestion());
		
		questionEntityBase.setQuestionTypeEntity(questionEntity.getQuestionTypeEntity());
		questionEntityBase.setSkillEntity(questionEntity.getSkillEntity());
		questionEntityBase.setRoomEntities(questionEntity.getRoomEntities());
		questionEntityBase.setLessonEntity(questionEntity.getLessonEntity());
		questionEntityBase.setExamEntities(questionEntity.getExamEntities());
		QuestionEntity afterSave = questionRepository.save(questionEntityBase);
		Set<AnswerEntity> answerEntitySet = new HashSet<>();
		// answerRepository.deleteByIdQuestion(id);
		for (AnswerEntity answerEntity : questionDTO.getAnswer()) {
			answerEntitySet.add(answerRepository.save(new AnswerEntity(answerEntity.getId(), answerEntity.isCorrect(),
					answerEntity.getImage_link(), answerEntity.getAnswer(), afterSave)));
		}
		afterSave.setAnswerEntities(answerEntitySet);
		return questionConverter.toDTO(afterSave);
	}

	public void delete(Long id) {
		QuestionEntity questionEntity = questionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Question not exist with id :" + id));

//		answerRepository.deleteByIdQuestion(id);

//		questionRepository.delete(questionEntity);
		questionEntity.setEnabled(false);
		questionRepository.save(questionEntity);
	}

	public QuestionDTO get(Long id) {
		QuestionEntity questionEntity = questionRepository.getQuestionEnableByID(id);
		QuestionDTO questionDTO = (questionEntity != null) ? questionConverter.toDTO(questionEntity)
				: new QuestionDTO();
		return questionDTO;
	}

	public List<QuestionDTO> getAllQuestion() {
		List<QuestionBaseDTO> listQuestion = questionRepository.getAllQuestion();
		List<QuestionDTO> lisResult = new ArrayList<>();
		for (int i = 0; i < listQuestion.size(); i++) {
			Long idQuestion = listQuestion.get(i).getQuestionID();
			if (i == 0) {
				QuestionDTO questionDTO = new QuestionDTO();
				questionDTO.setQuestionID(listQuestion.get(0).getQuestionID());
				questionDTO.setQuestion(listQuestion.get(0).getQuestion());
			}
			for (int j = i + 1; j < listQuestion.size(); j++) {

			}
		}
		return lisResult;
	}

}
package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.AnswerDTO;
import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.entities.AnswerEntity;
import com.example.springboot.entities.QuestionEntity;


@Component
public class QuestionConverter {

	public QuestionEntity toEntity(QuestionDTO questionDTO) {
		QuestionEntity questionEntity = new QuestionEntity();
		return questionEntity;
	}

	public QuestionDTO toDTO(QuestionEntity questionEntity) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setLesson(questionEntity.getLessonEntity().getLessonName());
		questionDTO.setType(questionEntity.getQuestionTypeEntity().getTypeName());
		questionDTO.setQuestion(questionEntity.getQuestion());
		questionDTO.setImgLink(questionEntity.getImage_link());
		questionDTO.setQuestionID(questionEntity.getId());
		questionDTO.setSkill(questionEntity.getSkillEntity().getSkillName());

		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		for (AnswerEntity answerEntity : questionEntity.getAnswerEntities()) {
			AnswerDTO answerDTO = new AnswerDTO();
			answerDTO.setId(answerEntity.getId());
			answerDTO.setAnswer(answerEntity.getAnswer());
			answerDTOs.add(answerDTO);
		}
		questionDTO.setAnswerEntities((List) answerDTOs);
		return questionDTO;
	}

	public List<QuestionDTO> toDTOs(List<QuestionEntity> listEntities) {
		ArrayList<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		for (QuestionEntity questionEntity : listEntities) {
			questionDTOs.add(toDTO(questionEntity));
		}
		return questionDTOs;
	}
}

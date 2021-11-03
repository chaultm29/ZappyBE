package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot.entities.QuestionTypeEntity;
import com.example.springboot.repositories.LessonRepository;
import com.example.springboot.repositories.QuestionRepository;
import com.example.springboot.repositories.QuestionTypeRepository;
import com.example.springboot.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.dto.AnswerDTO;
import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.entities.AnswerEntity;
import com.example.springboot.entities.QuestionEntity;


@Component
public class QuestionConverter {

	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private SkillRepository skillRepository;

	public QuestionEntity toEntity(QuestionDTO questionDTO) {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setQuestion(questionDTO.getQuestion());
		questionEntity.setQuestionTypeEntity(questionTypeRepository.getByName(questionDTO.getTypeName()));
		questionEntity.setLessonEntity(lessonRepository.getByName(questionDTO.getLessonName()));
		questionEntity.setSkillEntity(skillRepository.getByName(questionDTO.getSkillName()));
		return questionEntity;
	}


	public QuestionDTO toDTO(QuestionEntity questionEntity) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setLessonName((questionEntity.getLessonEntity()!=null)?questionEntity.getLessonEntity().getLessonName():"");
		questionDTO.setTypeName((questionEntity.getQuestionTypeEntity()!=null)?questionEntity.getQuestionTypeEntity().getTypeName():"");
		questionDTO.setQuestion(questionEntity.getQuestion());
		questionDTO.setImgeLink(questionEntity.getImage_link());
		questionDTO.setQuestionID(questionEntity.getId());
		questionDTO.setSkillName((questionEntity.getSkillEntity()!=null)?questionEntity.getSkillEntity().getSkillName():"");

//		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
//		for (AnswerEntity answerEntity : questionEntity.getAnswerEntities()) {
//			AnswerDTO answerDTO = new AnswerDTO();
//			answerDTO.setId(answerEntity.getId());
//			answerDTO.setAnswer(answerEntity.getAnswer());
//			answerDTOs.add(answerDTO);
//		}
		questionDTO.setAnswer(questionEntity.getAnswerEntities());
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

	
	
	

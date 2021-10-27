package com.example.springboot.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.QuestionConverter;
import com.example.springboot.entities.QuestionEntity;
import com.example.springboot.repositories.QuestionRepository;
import com.example.springbootdto.QuestionDTO;
import com.example.springbootdto.SettingExamDTO;

@Service
public class QuestionService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionConverter questionConverter;

	public List<QuestionDTO> get() {
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		return questionConverter.toDTOs(questionEntities);
	}

	public List<QuestionDTO> get(SettingExamDTO settingExamDTO) {
		String sb = "SELECT * FROM zappy.questions WHERE 0 = 0 ";
		if (!settingExamDTO.getIdLessonList().isEmpty()) {
			sb += " and ( lesson_id = " + settingExamDTO.getIdLessonList().get(0);
			for (int i = 1; i < settingExamDTO.getIdLessonList().size(); i++) {
				sb += " or lesson_id = " + settingExamDTO.getIdLessonList().get(i);
			}
			sb += " ) ";
		}

		if (!settingExamDTO.getQuestionTypeList().isEmpty()) {
			sb += " and ( type_id = " + settingExamDTO.getQuestionTypeList().get(0);
			for (int i = 1; i < settingExamDTO.getQuestionTypeList().size(); i++) {
				sb += " or type_id = " + settingExamDTO.getQuestionTypeList().get(i);
			}
			sb += " ) ";
		}

		if (!settingExamDTO.getSkillList().isEmpty()) {
			sb += " and ( skill_id = " + settingExamDTO.getSkillList().get(0);
			for (int i = 1; i < settingExamDTO.getSkillList().size(); i++) {
				sb += " or skill_id = " + settingExamDTO.getSkillList().get(i);
			}
			sb += " ) ";
		}
		sb += " ORDER BY RAND () LIMIT " + settingExamDTO.getNumberOfQuestion();
		List<QuestionEntity> questionEntities = new ArrayList<>();
		try {
			Query query = entityManager.createNativeQuery(sb, QuestionEntity.class);
			questionEntities = query.getResultList();
		} catch (Exception e) {
		}
		return questionConverter.toDTOs(questionEntities);

	}
}

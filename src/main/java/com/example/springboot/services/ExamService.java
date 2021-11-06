package com.example.springboot.services;

import com.example.springboot.converters.ExamConverter;
import com.example.springboot.converters.QuestionConverter;
import com.example.springboot.dto.*;
import com.example.springboot.entities.AnswerEntity;
import com.example.springboot.entities.QuestionEntity;
import com.example.springboot.repositories.QuestionRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamService {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	ExamConverter examConverter;

	public List<QuestionExamDTO> questionExamDTOList(QuestionRequireDTO questionRequireDTO) {
		Random rd = new Random();
		List<QuestionExamDTO> questionExamDTOS = new ArrayList<>();
		// get list id lesson, skill, type exit in question
		List<Long> listIDLessonExit = questionRepository.getIDLessonExit();
		List<Long> listIDSkillExit = questionRepository.getIDSkillExit();
		List<Long> listIDTypeExit = questionRepository.getIDTypeExit();

		// remove id lesson, skill, type not exit in question
		for (int i = questionRequireDTO.getLessonIds().size() - 1; i >= 0; i--) {
			if (!listIDLessonExit.contains(questionRequireDTO.getLessonIds().get(i))) {
				questionRequireDTO.getLessonIds().remove(i);
			}
		}
		for (int i = questionRequireDTO.getSkillIds().size() - 1; i >= 0; i--) {
			if (!listIDSkillExit.contains(questionRequireDTO.getSkillIds().get(i))) {
				questionRequireDTO.getSkillIds().remove(i);
			}
		}
		for (int i = questionRequireDTO.getTypeIds().size() - 1; i >= 0; i--) {
			if (!listIDTypeExit.contains(questionRequireDTO.getTypeIds().get(i))) {
				questionRequireDTO.getTypeIds().remove(i);
			}
		}
		// set up count number question type
		List<Integer> listCountLesson = new ArrayList<>();
		List<Integer> listCountSkill = new ArrayList<>();
		List<Integer> listCountType = new ArrayList<>();

		// check ko ton tai cau hoi nao tuong ung
		if (questionRequireDTO.getLessonIds().size() == 0 || questionRequireDTO.getTypeIds().size() == 0
				|| questionRequireDTO.getSkillIds().size() == 0) {
			return questionExamDTOS;
		}

		// get surplus lesson, skill, type
		int checkSurplusLesson = questionRequireDTO.getNumberOfQuestion() % questionRequireDTO.getLessonIds().size();
		int checkSurplusSkill = questionRequireDTO.getNumberOfQuestion() % questionRequireDTO.getSkillIds().size();
		int checkSurplusType = questionRequireDTO.getNumberOfQuestion() % questionRequireDTO.getTypeIds().size();

		// get count base lesson, skill, type
		int checkLesson = questionRequireDTO.getNumberOfQuestion() / questionRequireDTO.getLessonIds().size();
		int checkSkill = questionRequireDTO.getNumberOfQuestion() / questionRequireDTO.getSkillIds().size();
		int checkType = questionRequireDTO.getNumberOfQuestion() / questionRequireDTO.getTypeIds().size();

		// input number list count base

		for (int i = 0; i < questionRequireDTO.getLessonIds().size(); i++) {
			listCountLesson.add(checkLesson);
		}
		for (int i = 0; i < questionRequireDTO.getSkillIds().size(); i++) {
			listCountSkill.add(checkSkill);
		}
		for (int i = 0; i < questionRequireDTO.getTypeIds().size(); i++) {
			listCountType.add(checkType);
		}

		// input more number list count base

		for (int i = 0; i < checkSurplusLesson; i++) {
			int index = rd.nextInt(listCountLesson.size());
			listCountLesson.set(index, listCountLesson.get(index) + 1);
		}
		for (int i = 0; i < checkSurplusSkill; i++) {
			int index = rd.nextInt(listCountSkill.size());
			listCountSkill.set(index, listCountSkill.get(index) + 1);
		}
		for (int i = 0; i < checkSurplusType; i++) {
			int index = rd.nextInt(listCountType.size());
			listCountType.set(index, listCountType.get(index) + 1);
		}

		List<QuestionNotExitDTO> questionNotExitDTOS = new ArrayList<>();
		List<QuestionNotExitDTO> questionExitDTOS = new ArrayList<>();
		List<QuestionEntity> listSupport = new ArrayList<>();

		for (int i = 0; i < questionRequireDTO.getNumberOfQuestion(); i++) {
			int lessonindex = 0;
			int skillindex = 0;
			int typeindex = 0;
			int count = 0;
			Boolean checkBreak = false;
			while (true) {
				lessonindex = (listCountLesson.size() == 1) ? 0 : rd.nextInt(listCountLesson.size());
				skillindex = (listCountSkill.size() == 1) ? 0 : rd.nextInt(listCountSkill.size());
				typeindex = (listCountType.size() == 1) ? 0 : rd.nextInt(listCountType.size());
				QuestionNotExitDTO questionNotExitDTO = new QuestionNotExitDTO(
						questionRequireDTO.getLessonIds().get(lessonindex),
						questionRequireDTO.getSkillIds().get(skillindex),
						questionRequireDTO.getTypeIds().get(typeindex));
				// check ton tai trong list tra ve
				// !questionExitDTOS.contains(questionNotExitDTO)
				if (!questionNotExitDTOS.contains(questionNotExitDTO)
						&& !questionExitDTOS.contains(questionNotExitDTO)) {
					break;
				} else {
					count++;
					if (count >= listCountLesson.size() * listCountSkill.size() * listCountType.size() * 10) {
						for (int j = 0; j < listSupport.size(); j++) {
							if (questionExamDTOS.size() < questionRequireDTO.getNumberOfQuestion()) {
								QuestionEntity questionEntity = listSupport.get(j);
								// delete anser if type name id 2 : 4
								if (questionEntity.getQuestionTypeEntity().getId().equals(2l)
										|| questionEntity.getQuestionTypeEntity().getId().equals(4l)) {
									questionEntity.setAnswerEntities(new HashSet<>());
								}
								questionExamDTOS.add(examConverter.toDTO(questionEntity));
							} else {
								break;
							}
						}
						checkBreak = true;
						break;
					}
				}
			}
			if (checkBreak) {
				break;
			}
			List<QuestionEntity> questionEntities = questionRepository.getQuestionByLessonAndTypeAndSkill(
					questionRequireDTO.getLessonIds().get(lessonindex),
					questionRequireDTO.getSkillIds().get(skillindex), questionRequireDTO.getTypeIds().get(typeindex));
			if (questionEntities != null && questionEntities.size() != 0) {
				questionExitDTOS.add(new QuestionNotExitDTO(questionRequireDTO.getLessonIds().get(lessonindex),
						questionRequireDTO.getSkillIds().get(skillindex),
						questionRequireDTO.getTypeIds().get(typeindex)));
				int indexChoise = (questionEntities.size() != 1) ? rd.nextInt(questionEntities.size()) : 0;
				QuestionEntity questionEntity = questionEntities.get(indexChoise);

				// delete anser if type name id 2 : 4
				if (questionEntity.getQuestionTypeEntity().getId().equals(2l)
						|| questionEntity.getQuestionTypeEntity().getId().equals(4l)) {
					questionEntity.setAnswerEntities(new HashSet<>());
				}
				questionExamDTOS.add(examConverter.toDTO(questionEntity));
				listCountLesson.set(lessonindex, listCountLesson.get(lessonindex) - 1);
				listCountSkill.set(skillindex, listCountSkill.get(skillindex) - 1);
				listCountType.set(typeindex, listCountType.get(typeindex) - 1);
				if (listCountLesson.get(lessonindex) == 0) {
					listCountLesson.remove(lessonindex);
					questionRequireDTO.getLessonIds().remove(lessonindex);
				}
				if (listCountSkill.get(skillindex) == 0) {
					listCountSkill.remove(skillindex);
					questionRequireDTO.getSkillIds().remove(skillindex);
				}
				if (listCountType.get(typeindex) == 0) {
					listCountType.remove(typeindex);
					questionRequireDTO.getTypeIds().remove(typeindex);
				}
				questionEntities.remove(indexChoise);
				listSupport.addAll(questionEntities);
			} else {
				questionNotExitDTOS.add(new QuestionNotExitDTO(questionRequireDTO.getLessonIds().get(lessonindex),
						questionRequireDTO.getSkillIds().get(skillindex),
						questionRequireDTO.getTypeIds().get(typeindex)));
				i--;
			}
		}
		return questionExamDTOS;
	}

	public List<Long> getResultQuestion(QuestionResultDTO questionResultDTO) {
		List<Long> idQuestion = new ArrayList<>();
		List<Long> idQuestionUserchoose = new ArrayList<>();
		for (AnswerDTO id : questionResultDTO.getAnswerDTOs()) {
			QuestionEntity questionEntities = questionRepository.getAllQuestionByAnswer(id.getId(), id.getAnswer());
			if (questionEntities != null) {
				idQuestion.add(id.getId());
			}
		}
		return idQuestion;
	}

	public QuestionResultDetailDTO getResultDetailQuestion(QuestionResultDTO questionResultDTO) {
		QuestionResultDetailDTO questionAnswer = new QuestionResultDetailDTO();
		int numberOfCorrect = 0;
		int numberOfQuestion = questionResultDTO.getAnswerDTOs().size();
		int score = 0;
		List<AnswerDTO> correct = new ArrayList<>();
		List<Long> idQuestionUserchoose = new ArrayList<>();
		for (AnswerDTO id : questionResultDTO.getAnswerDTOs()) {
			idQuestionUserchoose.add(id.getId());
		}
		List<QuestionEntity> questionEntities = questionRepository.getAllQuestionByIDs(idQuestionUserchoose);
		for (QuestionEntity questionEntity : questionEntities) {
			int j = 0;
			for (AnswerEntity answerEntity : questionEntity.getAnswerEntities()) {
				if (answerEntity.isCorrect()) {
					correct.add(new AnswerDTO(questionEntity.getId(), answerEntity.getAnswer()));
				}
				for (int i = j; i < questionResultDTO.getAnswerDTOs().size(); i++) {
					if (questionEntity.getId().equals(questionResultDTO.getAnswerDTOs().get(i).getId())) {
						j = i;
						if (answerEntity.isCorrect() && answerEntity.getAnswer()
								.equals(questionResultDTO.getAnswerDTOs().get(i).getAnswer())) {
							numberOfCorrect++;
						}
						break;
					}
				}
			}
			questionResultDTO.getAnswerDTOs().remove(j);
		}
		questionAnswer.setCorrect(correct);
		questionAnswer.setNumberOfCorrect(numberOfCorrect);
		questionAnswer.setScore((numberOfCorrect * 100 / numberOfQuestion));
		return questionAnswer;
	}

}
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
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		return questionConverter.toDTOs(questionEntities);
	}

	public Boolean save(QuestionDTO questionDTO) {
		QuestionEntity questionEntity = questionConverter.toEntity(questionDTO);
		QuestionEntity afterSave = questionRepository.save(questionEntity);
		Set<AnswerEntity> answerEntitySet = new HashSet<>();
		for (AnswerEntity answerEntity : questionDTO.getAnswer()) {
			answerEntitySet.add(answerRepository.save(new AnswerEntity(answerEntity.getId(), answerEntity.isCorrect(),
					answerEntity.getImage_link(), answerEntity.getAnswer(), afterSave)));
		}
		if(afterSave!=null) {
			return true;
		}
		return false;
	}

	public QuestionDTO update(QuestionDTO questionDTO, Long id) {
		QuestionEntity questionEntityBase = questionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Question not exist with id :" + id));
		QuestionEntity questionEntity = questionConverter.toEntity(questionDTO);
		questionEntityBase.setImage_link(questionEntity.getImage_link());
		questionEntityBase.setQuestion(questionEntity.getQuestion());
		questionEntityBase.setQuestionTypeEntity(questionEntity.getQuestionTypeEntity());
		questionEntityBase.setSkillEntity(questionEntity.getSkillEntity());
		questionEntityBase.setRoomEntities(questionEntity.getRoomEntities());
		questionEntityBase.setLessonEntity(questionEntity.getLessonEntity());
		questionEntityBase.setExamEntities(questionEntity.getExamEntities());
		QuestionEntity afterSave = questionRepository.save(questionEntityBase);
		Set<AnswerEntity> answerEntitySet = new HashSet<>();
		answerRepository.deleteByIdQuestion(id);
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

		answerRepository.deleteByIdQuestion(id);

		questionRepository.delete(questionEntity);
	}

	public QuestionDTO get(Long id) {
		QuestionEntity questionEntity = questionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Question not exist with id :" + id));
		return questionConverter.toDTO(questionEntity);
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

//	public List<QuestionDTO> get(SettingExamDTO settingExamDTO) {// all dataa
//		List<QuestionEntity> questionEntities = new ArrayList<>();
//		List<QuestionRequireDTO> questionRequireDTOs = generateQuestionsRequire(settingExamDTO);
//		for (QuestionRequireDTO questionRequireDTO : questionRequireDTOs) {
//			String sb = "SELECT * FROM zappy.questions WHERE 0 = 0 ";
//			if (questionRequireDTO.getLessonId() != -1) {
//				sb += " and lesson_id = " + questionRequireDTO.getLessonId();
//			}
//
//			if (questionRequireDTO.getSkillId() != -1) {
//				sb += " and skill_id = " + questionRequireDTO.getSkillId();
//			}
//
//			if (questionRequireDTO.getTypeId() != -1) {
//				sb += " and type_id = " + questionRequireDTO.getTypeId();
//			}
//
//			sb += " ORDER BY RAND () LIMIT 1 ";
//			try {
//				Query query = entityManager.createNativeQuery(sb, QuestionEntity.class);
//				QuestionEntity question = (QuestionEntity) query.getSingleResult();
//				questionEntities.add(question);
//			} catch (Exception e) {
//			}
//		}
//		return questionConverter.toDTOs(questionEntities);
//
//	}
//
//	private List<QuestionRequireDTO> generateQuestionsRequire(SettingExamDTO settingExamDTO) {
//		List<QuestionRequireDTO> questionRequireDTOs = new ArrayList<>();
//		/**
//		 * thuật toán, tìm tb số câu của numberqustion chia theo skill số câu còn dư
//		 * chia vào các skill bất kì 10 /3 = 3 dư 1 = [3,4,3] = [3,3,3] chia số câu theo
//		 * lesson tb 10/4 = 2 du 2 = [3,2,2,3] = [3,2,1,3] chia theo question type 10/6
//		 * = 1 dư 4 = [1,2,1,2,2,2] = [1,2,1,1,2,2] dùng thuật toán bt
//		 * questionRequireDTO1 =[2,-1,4] RequireDTO2 =[1,1,2]
//		 * 
//		 */
//		int totalNumber = settingExamDTO.getNumberOfQuestion();
//		int mean = totalNumber / settingExamDTO.getSkillList().size();
//		int arr1[] = generateArray(mean, settingExamDTO.getSkillList().size()); // mang index cac so cau hoi dã random
////		for(int i = 0; i< totalNumber % settingExamDTO.getSkillList().size(); i++) {
////			Random generator = new Random();
////			int index = generator.nextInt(settingExamDTO.getSkillList().size());
////			arr1[index] += 1;
////		}
//		// mang maf co all data arrGetData[]
//		// tao mang ressult : arrResult[]
//		for (int i = 0; i < arr1.length; i++) {
//			// arrResult[i]=arrGetData[arr1[i]];
//		}
//
//		return questionRequireDTOs;
//	}
//
//	private int[] generateArray(int n, int size) {// n=3; size = 10// sai
//		int arry[] = new int[size];
//		for (int i = 0; i < n; i++) {
//			Random generator = new Random();
//			int randomIndex = generator.nextInt(size);// id =1, id=1
//			int dem = 0;
//			for (int j = 0; j < arry.length; j++) {
//				if (arry[j] == randomIndex) {
//					dem++;
//				}
//			}
//			if (dem == 0) {
//				arry[i] = randomIndex;
//			}
//		}
//		return arry;// [1,2,3]
//	}
}
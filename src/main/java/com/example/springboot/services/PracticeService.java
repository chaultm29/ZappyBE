package com.example.springboot.services;

import com.example.springboot.converters.PracticeConverter;
import com.example.springboot.dto.*;
import com.example.springboot.entities.*;
import com.example.springboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PracticeService {
	@Autowired
	PracticeRepository practiceRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private PracticeConverter practiceConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExamRepositoty examRepositoty;
	@Autowired
	private UserAchienmentRepository userAchienmentRepository;

	public HashMap<String, Object> questionExamDTOList(QuestionRequireDTO questionRequireDTO) {
		Random rd = new Random();
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		List<QuestionExamDTO> questionExamDTOS = new ArrayList<>();
		List<QuestionEntity> questionEntitiesOutPut = new ArrayList<>();

		// save
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		PracticeEntiry practiceEntiry = new PracticeEntiry();
		practiceEntiry.setScore(0);
		practiceEntiry.setUserEntities(userEntity);

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
			practiceEntiry.setQuestionEntities(new HashSet<>(questionEntitiesOutPut));
			practiceRepository.save(practiceEntiry);
			stringObjectHashMap.put("listQuestions", questionExamDTOS);
			return stringObjectHashMap;
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
								questionEntitiesOutPut.add(questionEntity);
								questionExamDTOS.add(practiceConverter.toDTO(questionEntity));
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
				questionEntitiesOutPut.add(questionEntity);
				questionExamDTOS.add(practiceConverter.toDTO(questionEntity));
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

		practiceEntiry.setQuestionEntities(new HashSet<>(questionEntitiesOutPut));
		practiceRepository.save(practiceEntiry);
		stringObjectHashMap.put("listQuestions", questionExamDTOS);
		return stringObjectHashMap;
	}

	public List<Long> getResultQuestion(QuestionResultDTO questionResultDTO) {
		List<Long> idQuestion = new ArrayList<>();
		List<Long> idQuestionUserchoose = new ArrayList<>();
		int count = 0;
		for (AnswerDTO id : questionResultDTO.getAnswerDTOs()) {
			QuestionEntity questionEntities = questionRepository.getAllQuestionByAnswer(id.getId(), id.getAnswer());
			if (questionEntities != null) {
				idQuestion.add(id.getId());
				count++;
			}
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PracticeEntiry> examEntities = practiceRepository.getPracticeByUserName(username);
		PracticeEntiry practiceEntiry = examEntities.get(0);
		practiceEntiry.setScore(count * 100 / questionResultDTO.getAnswerDTOs().size());
		practiceRepository.save(practiceEntiry);
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
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PracticeEntiry> examEntities = practiceRepository.getPracticeByUserName(username);
		PracticeEntiry practiceEntiry = examEntities.get(0);
		practiceEntiry.setScore(numberOfCorrect * 100 /  numberOfQuestion);
		practiceRepository.save(practiceEntiry);
		getProgress();
//		getLevel();
		return questionAnswer;
	}

	public ProgressDTO getProgress() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ProgressDTO progressDTO = new ProgressDTO();
		Integer countLessonVocaPass = (practiceRepository.getIdsPracticeBySkillUserScore(username, 1l)!=null?practiceRepository.getIdsPracticeBySkillUserScore(username, 1l):0);
		
		Integer countLessonGramarPass = practiceRepository.getIdsPracticeBySkillUserScore(username, 2l)!=null?practiceRepository.getIdsPracticeBySkillUserScore(username, 2l):0;
		
		Integer countLessonKanjiPass = practiceRepository.getIdsPracticeBySkillUserScore(username, 3l)!=null?practiceRepository.getIdsPracticeBySkillUserScore(username, 3l):0;
		

		progressDTO.setVocaProgress(countLessonVocaPass);
		progressDTO.setGrammarProgess(countLessonGramarPass);
		progressDTO.setKanjiProgress(countLessonKanjiPass);
		Integer progressAll = countLessonVocaPass + countLessonGramarPass + countLessonKanjiPass;
		progressDTO.setProgressAll(progressAll);
		//Chúa tể ngôn từ
		if(countLessonVocaPass==7){
			UserEntity user = userRepository.getUserByUserName(username);
			AchievenmentEntity achievenment = new AchievenmentEntity();
			achievenment.setId(5l);
			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
			userAchievenment.setAchievenmentEntity(achievenment);
			userAchievenment.setUser(user);
			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
			userAchienmentRepository.save(userAchievenment);
		}
		//Vị thần ngữ pháp
		if(countLessonGramarPass==7){
			UserEntity user = userRepository.getUserByUserName(username);
			AchievenmentEntity achievenment = new AchievenmentEntity();
			achievenment.setId(4l);
			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
			userAchievenment.setAchievenmentEntity(achievenment);
			userAchievenment.setUser(user);
			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
			userAchienmentRepository.save(userAchievenment);
		}//Bậc thầy chữ hán
		if(countLessonKanjiPass==7){
			UserEntity user = userRepository.getUserByUserName(username);
			AchievenmentEntity achievenment = new AchievenmentEntity();
			achievenment.setId(3l);
			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
			userAchievenment.setAchievenmentEntity(achievenment);
			userAchievenment.setUser(user);
			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
			userAchienmentRepository.save(userAchievenment);
		}
		//Thần đồng ngôn ngữ
		if(progressAll==21){
			UserEntity user = userRepository.getUserByUserName(username);
			AchievenmentEntity achievenment = new AchievenmentEntity();
			achievenment.setId(6l);
			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
			userAchievenment.setAchievenmentEntity(achievenment);
			userAchievenment.setUser(user);
			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
			userAchienmentRepository.save(userAchievenment);
		}
		return progressDTO;
	}

//	public LevelDTO getLevel() {
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		LevelDTO levelDTO = new LevelDTO();
//		Integer score = practiceRepository.totalScorePracticeByUsername(username)
//				+ examRepositoty.totalScoreExamByUsername(username);		
//		//Ho ve level(level 6)
//		if(score==1000){
//			UserEntity user = userRepository.getUserByUserName(username);
//			AchievenmentEntity achievenment = new AchievenmentEntity();
//			achievenment.setId(7l);
//			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
//			userAchievenment.setAchievenmentEntity(achievenment);
//			userAchievenment.setUser(user);
//			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
//			userAchienmentRepository.save(userAchievenment);
//		}
//		//Tho san level (level 9)
//		if(score==5000){
//			UserEntity user = userRepository.getUserByUserName(username);
//			AchievenmentEntity achievenment = new AchievenmentEntity();
//			achievenment.setId(8l);
//			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
//			userAchievenment.setAchievenmentEntity(achievenment);
//			userAchievenment.setUser(user);
//			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
//			userAchienmentRepository.save(userAchievenment);
//		}
//		//Quai thu level(level 11)
//		if(score==10000){
//			UserEntity user = userRepository.getUserByUserName(username);
//			AchievenmentEntity achievenment = new AchievenmentEntity();
//			achievenment.setId(9l);
//			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
//			userAchievenment.setAchievenmentEntity(achievenment);
//			userAchievenment.setUser(user);
//			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
//			userAchienmentRepository.save(userAchievenment);
//		}
//		//Ke huy diet level(level 13)
//		if(score==20000){
//			UserEntity user = userRepository.getUserByUserName(username);
//			AchievenmentEntity achievenment = new AchievenmentEntity();
//			achievenment.setId(10l);
//			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
//			userAchievenment.setAchievenmentEntity(achievenment);
//			userAchievenment.setUser(user);
//			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
//			userAchienmentRepository.save(userAchievenment);
//		}
//		//Than thoai level(level 15)
//		if(score==30000){
//			UserEntity user = userRepository.getUserByUserName(username);
//			AchievenmentEntity achievenment = new AchievenmentEntity();
//			achievenment.setId(11l);
//			UserAchievenmentEntity userAchievenment = new UserAchievenmentEntity();
//			userAchievenment.setAchievenmentEntity(achievenment);
//			userAchievenment.setUser(user);
//			userAchievenment.setDateCreate(new java.sql.Date((new Date()).getTime()));
//			userAchienmentRepository.save(userAchievenment);
//		}
//		Integer[] levelScore = { 0, 50, 100, 200, 300, 500, 1000, 2000, 3000, 5000, 7000, 10000, 15000, 20000, 25000,
//				30000 };
//		if (score > levelScore[levelScore.length - 1]) {
//			return levelDTO;
//		}
//		for (int i = levelScore.length - 1; i > 0; i--) {
//			if (score > levelScore[i]) {
//				levelDTO.setLevel(i);
//				levelDTO.setCurentExp((levelScore[i + 1] - levelScore[i]));
//				levelDTO.setLevelExp(score - levelScore[i]);
////				levelDTO.setPercentage((score - levelScore[i]) * 100 / (levelScore[i + 1] - levelScore[i]));
//				break;
//			} else if (score == levelScore[i]) {
//				levelDTO.setLevel(i);
//				break;
//			}
//		}
//		return levelDTO;
//	}

	
}

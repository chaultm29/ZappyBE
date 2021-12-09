package com.example.springboot.services;

import com.example.springboot.converters.AchievementConverter;
import com.example.springboot.dto.AchievementDTO;
import com.example.springboot.dto.UserAchievementDTO;
import com.example.springboot.entities.AchievementEntity;
import com.example.springboot.entities.ExamEntity;
import com.example.springboot.entities.LessonEntity;
import com.example.springboot.entities.UserAchievenmentEntity;
import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.AchievementRepository;
import com.example.springboot.repositories.ExamRepositoty;
import com.example.springboot.repositories.LessonRepository;
import com.example.springboot.repositories.PracticeRepository;
import com.example.springboot.repositories.UserAchienmentRepository;
import com.example.springboot.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	AchievementRepository achievementRepository;

	@Autowired
	UserAchienmentRepository userAchienmentRepository;
	
	@Autowired
	ExamRepositoty examRepositoty;

	@Autowired
	PracticeRepository practiceRepository;

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	AchievementConverter achievementConverter;

	enum eACHIEVEMENT {
		None, QuaivatKT, ChienThanGame, BTChuHan, VTNguPhap, CTNgonTu, TDNgonNgu, HoVeLv, ThoSanLv, QuaiThuLv,
		KeHuyDietLv, ThanThoaiLv
	}

	public List<UserAchievementDTO> getUserAchievement() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<UserAchievenmentEntity> list = achievementRepository.getAchievement(username);
		return achievementConverter.toDTOs(list);
	}

	public List<AchievementDTO> checkAllAchievement() {
		List<AchievementEntity> newAchieList = new ArrayList<>();
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			List<UserAchievenmentEntity> list = achievementRepository.getAchievement(username);
			List<Integer> oldAchies = changeToIntList(list);
			int count = 0;
			List<Integer> userAchies = new ArrayList<Integer>();
			userAchies.add(checkTest100Score());
			if (checkPassSkill(username, 1)) {
				count++;
				userAchies.add(eACHIEVEMENT.CTNgonTu.ordinal());
			}
			if (checkPassSkill(username, 2)) {
				count++;
				userAchies.add(eACHIEVEMENT.VTNguPhap.ordinal());
			}
			if (checkPassSkill(username, 3)) {
				count++;
				userAchies.add(eACHIEVEMENT.BTChuHan.ordinal());
			}
			if (count == 3)
				userAchies.add(eACHIEVEMENT.TDNgonNgu.ordinal());
			userAchies.addAll(checkLevelAchievement());
			List<Integer> newAchie = subList(userAchies, oldAchies);
			for (Integer nAchie : newAchie) {
				if (nAchie == 0) continue;
				AchievementEntity achievementEntity = achievementRepository.getById((long) nAchie);
				newAchieList.add(achievementEntity);
				//save to database
				UserAchievenmentEntity userAchievenmentEntity = new UserAchievenmentEntity();
				userAchievenmentEntity.setUser(userRepository.getUserByUserName(username));
				userAchievenmentEntity.setAchievenmentEntity(achievementEntity);
				userAchienmentRepository.save(userAchievenmentEntity);
			}
		} catch (Exception e) {

		}
		return achievementConverter.toAchieDTOs(newAchieList);
	}

	private List<Integer> changeToIntList(List<UserAchievenmentEntity> list) {
		List<Integer> achieIdxes = new ArrayList<Integer>();
		for (UserAchievenmentEntity userAchievenmentEntity : list) {
			achieIdxes.add(userAchievenmentEntity.getAchievenmentEntity().getId().intValue());
		}
		return achieIdxes;
	}

	private List<Integer> subList(List<Integer> userAchie, List<Integer> oldAchies) {
		List<Integer> newAchie = new ArrayList<Integer>();
		for (Integer uAchie : userAchie) {
			if (!checkExist(uAchie, oldAchies))
				newAchie.add(uAchie);
		}
		return newAchie;
	}

	private boolean checkExist(int index, List<Integer> idxAchies) {
		for (Integer integer : idxAchies) {
			if (integer == index)
				return true;
		}
		return false;
	}

	public int checkTest100Score() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ExamEntity> exams = examRepositoty.getExamByUserName(username);
		if (exams.size() < 10) return 0;
		int n = 0;
		while (n < 10) {
			if (exams.get(n++).getScore() != 100)
				return 0;
		}
		return eACHIEVEMENT.QuaivatKT.ordinal();
	}

	public int checkPassSkill() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		// Vocabulary = 1l, Grammar = 2l, Kanji = 3l
		eACHIEVEMENT achie = eACHIEVEMENT.None;
		if (checkPassSkill(username, 1))
			achie = eACHIEVEMENT.CTNgonTu;
		if (checkPassSkill(username, 2))
			achie = eACHIEVEMENT.VTNguPhap;
		if (checkPassSkill(username, 3))
			achie = eACHIEVEMENT.BTChuHan;
		return achie.ordinal();
	}

	private boolean checkPassSkill(String username, int skill) {
		Integer countLessonPass = practiceRepository.getIdsPracticeBySkillUserScore(username, (long) skill);
		countLessonPass = countLessonPass == null ? 0 : countLessonPass;
		return countLessonPass == lessonRepository.count();
	}

	public List<Integer> checkLevelAchievement() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		Long exp = userEntity.getExp();
		List<Integer> idxAchies = new ArrayList<Integer>();
		if (exp >= 1000)
			idxAchies.add(eACHIEVEMENT.HoVeLv.ordinal());
		if (exp >= 5000)
			idxAchies.add(eACHIEVEMENT.ThoSanLv.ordinal());
		if (exp >= 10000)
			idxAchies.add(eACHIEVEMENT.QuaiThuLv.ordinal());
		if (exp >= 20000)
			idxAchies.add(eACHIEVEMENT.KeHuyDietLv.ordinal());
		if (exp >= 30000)
			idxAchies.add(eACHIEVEMENT.ThanThoaiLv.ordinal());
		return idxAchies;
	}
}

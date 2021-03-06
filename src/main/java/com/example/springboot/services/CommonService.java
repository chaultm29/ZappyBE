package com.example.springboot.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.UserRepository;

public class CommonService {
	enum eACTIVITY{Practice, Exam, GBingo, GMemory}
	
	
	public UserEntity saveExp(Integer index, Long score, UserRepository userRepository) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		Long exp = 0l;
		eACTIVITY activity = eACTIVITY.values()[index];
		switch(activity) {
		case GBingo:
			exp = score * 1/31;
			break;
		case GMemory:
			exp = score * 1/23;
			break;
		case Exam:
			exp = score * 5;
			break;
		case Practice:
			exp = score * 2;
			break;
		}
		Long userExp = userEntity.getExp();
		userEntity.setExp(userExp==null?0:userExp+exp);
		return userRepository.save(userEntity);
	}

}

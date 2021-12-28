package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.UserConverter;
import com.example.springboot.dto.LevelDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConverter userConverter;

	public UserDTO save(UserDTO userDTO) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity oldEntity = userRepository.getUserByUserName(username);
		List<UserEntity> list = userRepository.findByEmail(userDTO.getEmail());
		if (list.isEmpty()||(!list.isEmpty() && list.get(0).getEmail().equals(userDTO.getEmail()))) {
			UserEntity userEntity = userConverter.toEntity(userDTO, oldEntity);
			UserEntity afterSave = userRepository.save(userEntity);
			return userConverter.toDTO(afterSave);
		} else {
			return null;
		}
	}

	public UserDTO getProfile() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		return userConverter.toDTO(userEntity);
	}

	public UserDTO saveExp(Integer activityId, Long score) {
		CommonService common = new CommonService();
		return userConverter.toDTO(common.saveExp(activityId, score, userRepository));
	}

	public LevelDTO getLevel() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		LevelDTO levelDTO = new LevelDTO();
		levelDTO.analysisExp(userEntity.getExp());
		return levelDTO;
	}
	
	public List<UserEntity> getTop10Ranking(){
		return userRepository.getTopRanking();
	}
}

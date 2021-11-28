package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.UserConverter;
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
		UserEntity userEntity = userConverter.toEntity(userDTO);
		UserEntity afterSave = userRepository.save(userEntity);
		return userConverter.toDTO(afterSave);
	}
	
	public UserDTO getProfile() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity userEntity = userRepository.getUserByUserName(username);
		return userConverter.toDTO(userEntity);
	}
}

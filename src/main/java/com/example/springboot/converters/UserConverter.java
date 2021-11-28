package com.example.springboot.converters;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

	public UserEntity toEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setFullName(userDTO.getFullName());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setDateOfBirth(userDTO.getDateOfBirth());
		userEntity.setAvatar(userDTO.getAvatar());
		return userEntity;
	}

	public UserDTO toDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setFullName(userEntity.getFullName());
		userDTO.setPhone(userEntity.getPhone());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setDateOfBirth(userEntity.getDateOfBirth());
		userDTO.setAvatar(userEntity.getAvatar());
		return userDTO;
	}

	public List<UserDTO> toDTOs(List<UserEntity> userEntities) {
		ArrayList<UserDTO> userDTOS = new ArrayList<UserDTO>();
		for (UserEntity userEntity : userEntities) {
			userDTOS.add(toDTO(userEntity));
		}
		return userDTOS;
	}
}

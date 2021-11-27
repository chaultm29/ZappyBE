package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.GameRecordConverter;
import com.example.springboot.dto.GameRecordDTO;
import com.example.springboot.entities.GameRecordEntity;
import com.example.springboot.repositories.AccountRepository;
import com.example.springboot.repositories.GameRecordRepository;
import com.example.springboot.repositories.UserRepository;

@Service
public class GameRecordService {

	@Autowired
	GameRecordRepository gameRecordRepository;

	@Autowired
	GameRecordConverter gameRecordConverter;

	@Autowired
	UserRepository userRepository;

	public boolean save(GameRecordDTO gameRecordDTO) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Long uid = userRepository.getUserByUserName(username).getId();
		GameRecordEntity entity = gameRecordRepository.save(gameRecordConverter.toEntity(uid, gameRecordDTO));
		return true;
	}

	public List<GameRecordDTO> getRecords() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Long uid = userRepository.getUserByUserName(username).getId();
		List<GameRecordDTO> gameRecordDTOs = gameRecordConverter.toDTOs(gameRecordRepository.findByUserId(uid));
		return gameRecordDTOs;
	}

}

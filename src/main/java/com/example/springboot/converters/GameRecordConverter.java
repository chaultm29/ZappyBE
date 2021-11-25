package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.GameRecordDTO;
import com.example.springboot.entities.GameEntity;
import com.example.springboot.entities.GameRecordEntity;

@Component
public class GameRecordConverter {

	public GameRecordEntity toEntity(Long uid, GameRecordDTO gameRecordDTO) {
		GameRecordEntity gameRecordEntity = new GameRecordEntity();
		gameRecordEntity.setGameName(gameRecordDTO.getGameName());
		gameRecordEntity.setTimePlayed(gameRecordDTO.getTimePlayed());
		gameRecordEntity.setScore(gameRecordDTO.getScore());
		gameRecordEntity.setUserId(uid);
		
		return gameRecordEntity;
	}	
	
	public GameRecordDTO toDTO(GameRecordEntity gameRecordEntity) {
		GameRecordDTO gameRecordDTO = new GameRecordDTO();
		gameRecordDTO.setGameName(gameRecordEntity.getGameName());
		gameRecordDTO.setTimeCreated(gameRecordEntity.getTimeCreated());
		gameRecordDTO.setTimePlayed(gameRecordEntity.getTimePlayed());
		gameRecordDTO.setScore(gameRecordEntity.getScore());
		
		return gameRecordDTO;
	}
	
	public List<GameRecordDTO> toDTOs(List<GameRecordEntity> gameRecordEntities){
		ArrayList<GameRecordDTO> gameRecordDTOs = new ArrayList<GameRecordDTO>();
		for(GameRecordEntity gameRecordEntity : gameRecordEntities) {
			gameRecordDTOs.add(toDTO(gameRecordEntity));
		}
		return gameRecordDTOs;
	}
	
}

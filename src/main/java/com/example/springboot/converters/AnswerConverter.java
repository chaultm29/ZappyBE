package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot.dto.AnswerDTO;
import com.example.springboot.entities.AnswerEntity;

public class AnswerConverter {

	public AnswerDTO toDTO(AnswerEntity answerEntity) {
		AnswerDTO answerDTO = new AnswerDTO();
		answerDTO.setId(answerEntity.getId());
		answerDTO.setAnswer(answerEntity.getAnswer());
		return answerDTO;
	}
	
//	public ArrayList<AnswerDTO> toDTOs(ArrayList<AnswerEntity> listAnswer){
//		ArrayList<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
//		
//	}
}

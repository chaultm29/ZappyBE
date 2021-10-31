package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.GrammarDTO;
import com.example.springboot.entities.GrammarEntity;

@Component
public class GrammarConverter {
	public GrammarEntity toEntity(GrammarDTO grammarDTO) {
		GrammarEntity grammarEntity = new GrammarEntity();
		grammarEntity.setExample(grammarDTO.getExample());
		grammarEntity.setExampleImageLink(grammarDTO.getExampleImageLink());
		grammarEntity.setExampleMeaning(grammarDTO.getExampleMeaning());
		grammarEntity.setExplanation(grammarDTO.getExplanation());
		grammarEntity.setGrammar(grammarDTO.getGrammar());
		grammarEntity.setGrammarMeaning(grammarDTO.getGrammarMeaning());
		grammarEntity.setId(grammarDTO.getId());
		return grammarEntity;
	}

	public GrammarDTO toDTO(GrammarEntity grammarEntity) {
		GrammarDTO grammarDTO = new GrammarDTO();
		grammarDTO.setExample(grammarEntity.getExample());
		grammarDTO.setExampleImageLink(grammarEntity.getExampleImageLink());
		grammarDTO.setExampleMeaning(grammarEntity.getExampleMeaning());
		grammarDTO.setExplanation(grammarEntity.getExplanation());
		grammarDTO.setGrammar(grammarEntity.getGrammar());
		grammarDTO.setGrammarMeaning(grammarEntity.getGrammarMeaning());
		grammarDTO.setId(grammarEntity.getId());
		return grammarDTO;
	}

	public List<GrammarDTO> toDTOs(List<GrammarEntity> listEntities) {
		ArrayList<GrammarDTO> grammarDTOs = new ArrayList<GrammarDTO>();
		for (GrammarEntity grammarEntity : listEntities) {
			grammarDTOs.add(toDTO(grammarEntity));
		}
		return grammarDTOs;
	}
}

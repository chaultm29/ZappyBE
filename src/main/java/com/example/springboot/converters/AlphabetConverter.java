package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springboot.dto.AlphabetDTO;
import com.example.springboot.entities.AlphabetEntity;

@Component
public class AlphabetConverter {

	public AlphabetEntity toEntity(AlphabetDTO alphabetDTO) {
		AlphabetEntity alphabetEntity = new AlphabetEntity();
		alphabetEntity.setId(alphabetDTO.getId());
		alphabetEntity.setCharacter(alphabetDTO.getCharacter());
		alphabetEntity.setDescription(alphabetDTO.getDescription());
		alphabetEntity.setImageLink(alphabetDTO.getImageLink());
		return alphabetEntity;
	}

	// UPDATE
	public AlphabetEntity toEntity(AlphabetDTO alphabetDTO, AlphabetEntity alphabetEntity) {
		alphabetEntity.setId(alphabetDTO.getId());
		alphabetEntity.setCharacter(alphabetDTO.getCharacter());
		alphabetEntity.setDescription(alphabetDTO.getDescription());
		alphabetEntity.setImageLink(alphabetDTO.getImageLink());
		return alphabetEntity;
	}

	public AlphabetDTO toDTO(AlphabetEntity alphabetEntity) {
		AlphabetDTO alphabetDTO = new AlphabetDTO();
		alphabetDTO.setId(alphabetEntity.getId());
		alphabetDTO.setCharacter(alphabetEntity.getCharacter());
		alphabetDTO.setDescription(alphabetEntity.getDescription());
		alphabetDTO.setImageLink(alphabetEntity.getImageLink());
		return alphabetDTO;
	}

	public List<AlphabetDTO> toDTOs(List<AlphabetEntity> listEntities) {
		ArrayList<AlphabetDTO> alphabetDTOs = new ArrayList<AlphabetDTO>();
		for (AlphabetEntity alphabetEntity : listEntities) {
			alphabetDTOs.add(toDTO(alphabetEntity));
		}
		return alphabetDTOs;
	}

}

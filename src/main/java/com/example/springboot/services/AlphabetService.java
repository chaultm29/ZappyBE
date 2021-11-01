package com.example.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AlphabetConverter;
import com.example.springboot.dto.AlphabetDTO;
import com.example.springboot.entities.AlphabetEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AlphabetRepository;

@Service
public class AlphabetService {
	@Autowired
	AlphabetRepository alphabetRepository;

	@Autowired
	AlphabetConverter alphabetConverter;

	public AlphabetDTO get(Long id) {
		AlphabetEntity alphabetEntity = alphabetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("alphabet not exist with id :" + id));
		return alphabetConverter.toDTO(alphabetEntity);
	}

	public List<AlphabetDTO> getHiragana() {
		List<AlphabetEntity> alphabetEntities = alphabetRepository.findAll();
		List<AlphabetEntity> listResultHiragana = new ArrayList<>();
		for (AlphabetEntity alphabetEntity : alphabetEntities) {
			if (alphabetEntity.getIsHiragana() == true) {
				listResultHiragana.add(alphabetEntity);
			}
		}
		return alphabetConverter.toDTOs(listResultHiragana);
	}

	public List<AlphabetDTO> getKatakana() {
		List<AlphabetEntity> alphabetEntities = alphabetRepository.findAll();
		List<AlphabetEntity> listResultKatakana = new ArrayList<>();
		for (AlphabetEntity alphabetEntity : alphabetEntities) {
			if (alphabetEntity.getIsHiragana() == false) {				
				listResultKatakana.add(alphabetEntity);
			}
		}
		return alphabetConverter.toDTOs(listResultKatakana);
	}
}

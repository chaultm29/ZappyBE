package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AlphabetConverter;
import com.example.springboot.converters.KanjiConverter;
import com.example.springboot.entities.AlphabetEntity;
import com.example.springboot.entities.KanjiEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AlphabetRepository;
import com.example.springboot.repositories.KanjiRepository;
import com.example.springbootdto.KanjiDTO;

@Service
public class KanjiService {
	@Autowired
	KanjiRepository kanjiRepository;

	@Autowired
	KanjiConverter kanjiConverter;

	public KanjiDTO get(Long id) {
		KanjiEntity kanjiEntity = kanjiRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("kanji not exist with id :" + id));
		return kanjiConverter.toDTO(kanjiEntity);
	}

}

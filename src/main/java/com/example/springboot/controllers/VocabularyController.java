package com.example.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.VocabularyDTO;
import com.example.springboot.services.VocabularyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/study")
public class VocabularyController {
	@Autowired
	private VocabularyService vocabularyService;

	@GetMapping("/vocabulary/lesson/{id}")
	public ResponseEntity<List<VocabularyDTO>> getVocabularyByLessonId(@PathVariable Long id) {
		List<VocabularyDTO> vocabularyDTO = new ArrayList<>();
		vocabularyDTO = vocabularyService.getByLessionId(id);
		return new ResponseEntity<List<VocabularyDTO>>(vocabularyDTO, HttpStatus.OK);
	}
}

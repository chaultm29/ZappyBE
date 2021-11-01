package com.example.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot.dto.VocabularyBaseDTO;
import com.example.springboot.dto.GetAllVocabularyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.dto.VocabularyDTO;
import com.example.springboot.services.VocabularyService;

@CrossOrigin(origins = {"http://localhost:3000", "https://www.zappy-nihongo.com"})
@RestController
@RequestMapping("")
public class VocabularyController {
	@Autowired
	private VocabularyService vocabularyService;

	@GetMapping("/vocabulary/lesson/{id}")
	public ResponseEntity<List<VocabularyDTO>> getVocabularyByLessonId(@PathVariable Long id) {
		List<VocabularyDTO> vocabularyDTO = new ArrayList<>();
		vocabularyDTO = vocabularyService.getByLessionId(id);
		return new ResponseEntity<List<VocabularyDTO>>(vocabularyDTO, HttpStatus.OK);
	}

	@PostMapping("/vocabulary")
	public ResponseEntity<?> addVocabulary(@RequestBody VocabularyBaseDTO addVocabularyDTO) {
		vocabularyService.addVocabulary(addVocabularyDTO);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/vocabulary/{id}")
	public ResponseEntity<?> updateVocabulary(@PathVariable Long id,
			@RequestBody VocabularyBaseDTO updateVocabularyDTO) {
		vocabularyService.updateVocabulary(updateVocabularyDTO, id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/vocabulary/{id}")
	public ResponseEntity<?> deleteVocabulary(@PathVariable Long id) {
		vocabularyService.deleteVocabulary(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/vocabulary")
	public ResponseEntity<List<GetAllVocabularyDTO>> getAllVocabulary() {
		List<GetAllVocabularyDTO> listVocabulary = new ArrayList<>();
		listVocabulary = vocabularyService.getAllVocabulary();
		return new ResponseEntity<List<GetAllVocabularyDTO>>(listVocabulary, HttpStatus.OK);
	}

	@GetMapping("/vocabulary/{id}")
	public ResponseEntity<VocabularyBaseDTO> getAllVocabularyById(@PathVariable Long id) {
		VocabularyBaseDTO vocabulary = new VocabularyBaseDTO();
		vocabulary = vocabularyService.getVocabularyById(id);
		return new ResponseEntity<VocabularyBaseDTO>(vocabulary, HttpStatus.OK);
	}
}
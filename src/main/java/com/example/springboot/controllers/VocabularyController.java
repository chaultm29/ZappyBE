package com.example.springboot.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.VocabularyEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.VocabularyRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student")
public class VocabularyController {
	@Autowired
	private VocabularyRepository vocabularyRepository;

	// get all vocabulary
	@GetMapping("/vocabulary")
	public List<VocabularyEntity> getAllVocabulary() {
		return (List<VocabularyEntity>) vocabularyRepository.findAll();
	}

	// create vocabulary rest api
	@PostMapping("/vocabulary")
	public VocabularyEntity createVocabulary(@RequestBody VocabularyEntity vocabularyEntity) {
		return vocabularyRepository.save(vocabularyEntity);
	}

	// get vocabulary by id rest api
	@GetMapping("/vocabulary/{id}")
	public ResponseEntity<VocabularyEntity> getVocabularyById(@PathVariable Long id) {
		VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vocabulary not exist with id :" + id));
		return ResponseEntity.ok(vocabularyEntity);
	}

	// update vocabulary rest api
	@PutMapping("/vocabulary/{id}")
	public ResponseEntity<VocabularyEntity> updateVocabulary(@PathVariable Long id,
			@RequestBody VocabularyEntity vocabularyDetails) {
		VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vocabulary not exist with id :" + id));

		vocabularyEntity.setExample(vocabularyDetails.getExample());
		vocabularyEntity.setExampleMean(vocabularyDetails.getExampleMean());
		vocabularyEntity.setExampleImageLink(vocabularyDetails.getExampleImageLink());
		vocabularyEntity.setImageLink(vocabularyDetails.getImageLink());
		vocabularyEntity.setVocabulary(vocabularyDetails.getVocabulary());
		vocabularyEntity.setMeaning(vocabularyDetails.getMeaning());

		VocabularyEntity updatedVocabulary = vocabularyRepository.save(vocabularyEntity);
		return ResponseEntity.ok(updatedVocabulary);
	}

	// delete vocabulary rest api
	@DeleteMapping("/vocabulary/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteVocabulary(@PathVariable Long id) {
		VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vocabulary not exist with id :" + id));

		vocabularyRepository.delete(vocabularyEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

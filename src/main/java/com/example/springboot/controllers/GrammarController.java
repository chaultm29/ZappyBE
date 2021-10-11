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

import com.example.springboot.entities.GrammarEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.GrammarRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student")
public class GrammarController {
	@Autowired
	private GrammarRepository grammarRepository;

	// get all grammar
	@GetMapping("/grammar")
	public List<GrammarEntity> getAllGrammars() {
		return (List<GrammarEntity>) grammarRepository.findAll();
	}

	// create grammar rest api
	@PostMapping("/grammar")
	public GrammarEntity createGrammar(@RequestBody GrammarEntity grammarEntity) {
		return grammarRepository.save(grammarEntity);
	}

	// get grammar by id rest api
	@GetMapping("/grammar/{id}")
	public ResponseEntity<GrammarEntity> getGrammarById(@PathVariable Long id) {
		GrammarEntity grammarEntity = grammarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Grammar not exist with id :" + id));
		return ResponseEntity.ok(grammarEntity);
	}

	// update grammar rest api
	@PutMapping("/grammar/{id}")
	public ResponseEntity<GrammarEntity> updateGrammar(@PathVariable Long id,
			@RequestBody GrammarEntity grammarDetails) {
		GrammarEntity grammarEntity = grammarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Grammar not exist with id :" + id));

		grammarEntity.setExample(grammarDetails.getExample());
		grammarEntity.setExampleImageLink(grammarDetails.getExampleImageLink());
		grammarEntity.setExplanation(grammarDetails.getExplanation());
		grammarEntity.setGrammar(grammarDetails.getGrammar());

		GrammarEntity updatedGrammar = grammarRepository.save(grammarEntity);
		return ResponseEntity.ok(updatedGrammar);
	}

	// delete grammar rest api
	@DeleteMapping("/grammar/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteGrammar(@PathVariable Long id) {
		GrammarEntity grammarEntity = grammarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Grammar not exist with id :" + id));

		grammarRepository.delete(grammarEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

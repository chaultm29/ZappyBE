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

import com.example.springboot.entities.AlphabetEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AlphabetRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student")
public class AlphabetController {
	@Autowired
	private AlphabetRepository alphabetRepository;

	// get all alphabet
	@GetMapping("/alphabet")
	public List<AlphabetEntity> getAllAlphabets() {
		return (List<AlphabetEntity>) alphabetRepository.findAll();
	}

	// create alphabet rest api
	@PostMapping("/alphabet")
	public AlphabetEntity createAlphabet(@RequestBody AlphabetEntity alphabetEntity) {
		return alphabetRepository.save(alphabetEntity);
	}

	// get alphabet by id rest api
	@GetMapping("/alphabet/{id}")
	public ResponseEntity<AlphabetEntity> getAlphabetById(@PathVariable Long id) {
		AlphabetEntity alphabetEntity = alphabetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Alphabet not exist with id :" + id));
		return ResponseEntity.ok(alphabetEntity);
	}

	// update alphabet rest api
	@PutMapping("/alphabet/{id}")
	public ResponseEntity<AlphabetEntity> updateAlphabet(@PathVariable Long id,
			@RequestBody AlphabetEntity alphabetDetails) {
		AlphabetEntity alphabetEntity = alphabetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Alphabet not exist with id :" + id));

		alphabetEntity.setCharacter(alphabetDetails.getCharacter());
		alphabetEntity.setDescription(alphabetDetails.getDescription());
		alphabetEntity.setImageLink(alphabetDetails.getImageLink());
		alphabetEntity.setIsHiragana(alphabetDetails.getIsHiragana());

		AlphabetEntity updatedAlphabet = alphabetRepository.save(alphabetEntity);
		return ResponseEntity.ok(updatedAlphabet);
	}

	// delete alphabet rest api
	@DeleteMapping("/alphabet/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAlphabet(@PathVariable Long id) {
		AlphabetEntity alphabetEntity = alphabetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Alphabet not exist with id :" + id));

		alphabetRepository.delete(alphabetEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

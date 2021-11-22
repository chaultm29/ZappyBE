package com.example.springboot.controllers;

import java.util.ArrayList;

import java.util.List;

import com.example.springboot.dto.GetAllKanjiDTO;
import com.example.springboot.dto.GrammarBaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.dto.GrammarDTO;
import com.example.springboot.services.GrammarService;

@CrossOrigin(origins =  {"http://localhost:3000", "https://www.zappy-nihongo.com"})
@RestController
@RequestMapping("/grammar")
public class GrammarController {
	@Autowired
	private GrammarService grammarService;

	@GetMapping("/lesson/{id}")
	public ResponseEntity<List<GrammarDTO>> getGrammarsByLessonId(@PathVariable Long id) {
		List<GrammarDTO> grammarDTOs = new ArrayList<>();
		grammarDTOs = grammarService.getByLessionId(id);
		return new ResponseEntity<List<GrammarDTO>>(grammarDTOs, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<GrammarBaseDTO>> getAllGrammar() {
		List<GrammarBaseDTO> listGram = new ArrayList<>();
		listGram = grammarService.getAllGrammar();
		return new ResponseEntity<List<GrammarBaseDTO>>(listGram, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GrammarBaseDTO> getGrammarById(@PathVariable Long id) {
		GrammarBaseDTO grammar = grammarService.getGrammarById(id);
		return new ResponseEntity<GrammarBaseDTO>(grammar, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<String> addGrammar(@RequestBody GrammarBaseDTO grammarBaseDTO) {
		return new ResponseEntity<String>(grammarService.addGrammar(grammarBaseDTO), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateGrammar(@PathVariable Long id, @RequestBody GrammarBaseDTO grammarBaseDTO) {
		grammarService.updateGrammar(grammarBaseDTO, id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteKanji(@PathVariable Long id) {
		grammarService.deleteGrammar(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
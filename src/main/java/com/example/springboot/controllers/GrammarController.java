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

import com.example.springboot.dto.GrammarDTO;
import com.example.springboot.services.GrammarService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/study")
public class GrammarController {
	@Autowired
	private GrammarService grammarService;

	@GetMapping("/grammar/lesson/{id}")
	public ResponseEntity<List<GrammarDTO>> getGrammarsByLessonId(@PathVariable Long id) {
		List<GrammarDTO> grammarDTOs = new ArrayList<>();
		grammarDTOs = grammarService.getByLessionId(id);
		return new ResponseEntity<List<GrammarDTO>>(grammarDTOs, HttpStatus.OK);
	}
}

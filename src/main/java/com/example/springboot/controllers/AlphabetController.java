package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.AlphabetDTO;
import com.example.springboot.services.AlphabetService;

@CrossOrigin(origins = {"http://localhost:3000", "https://www.zappy-nihongo.com"} )
@RestController
@RequestMapping("/alphabet")
public class AlphabetController {
	@Autowired
	private AlphabetService alphabetService;

	// get hiragana
	@GetMapping("/hiragana")
	public ResponseEntity<List<AlphabetDTO>> getHiragana() {
		return new ResponseEntity<List<AlphabetDTO>>(alphabetService.getHiragana(), HttpStatus.OK);
	}

	// get katakana
	@GetMapping("/katakana")
	public ResponseEntity<List<AlphabetDTO>> getKatakana() {
		return new ResponseEntity<List<AlphabetDTO>>(alphabetService.getKatakana(), HttpStatus.OK);
	}

}

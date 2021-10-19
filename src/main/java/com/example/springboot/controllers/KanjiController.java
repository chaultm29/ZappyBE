package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.exception.ResourceNotFoundException;

import com.example.springboot.services.KanjiService;

import com.example.springbootdto.KanjiDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/study")
public class KanjiController {

	@Autowired
	private KanjiService kanjiService;

	@GetMapping("/account/lesson{id}")
	public ResponseEntity<KanjiDTO> getKanjiById(@PathVariable Long id) {
		KanjiDTO kanjiDTO = new KanjiDTO();
		try {
			kanjiDTO = kanjiService.get(id);
			return new ResponseEntity<KanjiDTO>(kanjiDTO, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<KanjiDTO>(kanjiDTO, HttpStatus.NOT_FOUND);
		}
	}

}

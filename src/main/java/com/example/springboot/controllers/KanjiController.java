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
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.services.KanjiService;
import com.example.springboot.services.LessonService;
import com.example.springbootdto.KanjiDTO;
import com.example.springbootdto.LessonDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/study")
public class KanjiController {

	@Autowired
	private KanjiService kanjiService;

	@GetMapping("/kanji/lesson/{id}")
	public ResponseEntity<List<KanjiDTO>> getKanjiByLessonId(@PathVariable Long id) {
		List<KanjiDTO> kanjiDTO = new ArrayList<>();
		kanjiDTO = kanjiService.getByLessionId(id);
		return new ResponseEntity<List<KanjiDTO>>(kanjiDTO, HttpStatus.OK);
	}

}

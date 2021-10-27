package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.services.LessonService;
import com.example.springbootdto.LessonDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/study")
public class LessonController {
	@Autowired
	private LessonService lessonService;

	@GetMapping("/lesson")
	public ResponseEntity<List<LessonDTO>> getAllAccount() {
		return new ResponseEntity<List<LessonDTO>>(lessonService.get(), HttpStatus.OK);
	}
}

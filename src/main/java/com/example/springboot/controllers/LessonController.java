package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.LessonDTO;
import com.example.springboot.services.LessonService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lesson")
public class LessonController {
	@Autowired
	private LessonService lessonService;

	@GetMapping("")
	public ResponseEntity<List<LessonDTO>> getAllLesson() {
		return new ResponseEntity<List<LessonDTO>>(lessonService.get(), HttpStatus.OK);
	}
}

package com.example.springboot.controllers;

import com.example.springboot.dto.*;
import com.example.springboot.services.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class PracticeController {
	@Autowired
	PracticeService practiceService;

	@PostMapping("/practice")
	public ResponseEntity<HashMap<String, Object>> getExam(@RequestBody QuestionRequireDTO questionRequireDTO) {
		return new ResponseEntity<HashMap<String, Object>>(practiceService.questionExamDTOList(questionRequireDTO),
				HttpStatus.OK);
	}

	@PostMapping("/practice/result")
	public ResponseEntity<QuestionResultDetailDTO> getResultDetailQuestion(
			@RequestBody QuestionResultDTO questionResultDTO) {
		return new ResponseEntity<QuestionResultDetailDTO>(practiceService.getResultDetailQuestion(questionResultDTO),
				HttpStatus.OK);
	}

	@GetMapping("/progress")
	public ResponseEntity<ProgressDTO> getProgress() {
		return new ResponseEntity<ProgressDTO>(practiceService.getProgress(), HttpStatus.OK);
	}

//	@GetMapping("/level")
//	public ResponseEntity<LevelDTO> getLevel() {
//		return new ResponseEntity<LevelDTO>(practiceService.getLevel(), HttpStatus.OK);
//	}

}

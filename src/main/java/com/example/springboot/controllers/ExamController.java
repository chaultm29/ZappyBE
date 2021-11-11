package com.example.springboot.controllers;

import com.example.springboot.dto.*;
import com.example.springboot.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exam")
public class ExamController {

	@Autowired
	private ExamService examService;

	@PostMapping()
	public ResponseEntity<List<QuestionExamDTO>> getExam(@RequestBody QuestionRequireDTO questionRequireDTO) {
		return new ResponseEntity<List<QuestionExamDTO>>(examService.questionExamDTOList(questionRequireDTO),
				HttpStatus.OK);
	}

	@PostMapping("/result")
	public ResponseEntity<List<Long>> getResultQuestion(@RequestBody QuestionResultDTO questionResultDTO) {
		return new ResponseEntity<List<Long>>(examService.getResultQuestion(questionResultDTO), HttpStatus.OK);
	}

	@PostMapping("/practive")
	public ResponseEntity<QuestionResultDetailDTO> getResultDetailQuestion(
			@RequestBody QuestionResultDTO questionResultDTO) {
		return new ResponseEntity<QuestionResultDetailDTO>(examService.getResultDetailQuestion(questionResultDTO),
				HttpStatus.OK);
	}
}

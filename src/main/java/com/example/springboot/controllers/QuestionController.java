package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.services.QuestionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping()
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("/study/exam")
	public ResponseEntity<List<QuestionDTO>> getListQuestionExam() {
		return new ResponseEntity<List<QuestionDTO>>(questionService.get(), HttpStatus.OK);
	}

	@GetMapping("/content-mng/question")
	public ResponseEntity<List<QuestionDTO>> getListQuestionContentManager() {
		return new ResponseEntity<List<QuestionDTO>>(questionService.get(), HttpStatus.OK);
	}

	@PostMapping("/content-mng/question")
	public ResponseEntity<QuestionDTO> createQuestionContentManager(@RequestBody QuestionDTO questionDTO) {
		return new ResponseEntity<QuestionDTO>(questionService.save(questionDTO), HttpStatus.OK);
	}

//	@PutMapping("/study/exam")
//	public ResponseEntity<List<QuestionDTO>> getListQuestion(@RequestBody SettingExamDTO settingExamDTO) {
//		return new ResponseEntity<List<QuestionDTO>>(questionService.get(settingExamDTO), HttpStatus.OK);
//	}

}

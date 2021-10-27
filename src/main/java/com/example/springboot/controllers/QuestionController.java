package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.services.QuestionService;
import com.example.springbootdto.QuestionDTO;
import com.example.springbootdto.SettingExamDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/study")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("/exam")
	public ResponseEntity<List<QuestionDTO>> getListQuestion() {
		return new ResponseEntity<List<QuestionDTO>>(questionService.get(), HttpStatus.OK);
	}

	@PutMapping("/exam")
	public ResponseEntity<List<QuestionDTO>> getListQuestion(@RequestBody SettingExamDTO settingExamDTO) {
		return new ResponseEntity<List<QuestionDTO>>(questionService.get(settingExamDTO), HttpStatus.OK);
	}
}

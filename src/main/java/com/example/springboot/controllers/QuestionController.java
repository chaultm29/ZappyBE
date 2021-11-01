package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.services.QuestionService;

@CrossOrigin(origins = {"http://localhost:3000", "https://www.zappy-nihongo.com"})
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

//	@PostMapping("/content-mng/question")
//	public ResponseEntity<QuestionDTO> createQuestionContentManager(@RequestBody QuestionDTO questionDTO) {
//		return new ResponseEntity<QuestionDTO>(questionService.save(questionDTO), HttpStatus.OK);
//	}

	@GetMapping("/content-mng/question/{id}")
	public ResponseEntity<QuestionDTO> getQuestionContentManager(@PathVariable Long id) {
		return new ResponseEntity<QuestionDTO>(questionService.get(id), HttpStatus.OK);
	}

	@DeleteMapping("/content-mng/question/{id}")
	public ResponseEntity<?> deleteQuestionContentManager(@PathVariable Long id) {
		questionService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

//	@PutMapping("/content-mng/question/{id}")
//	public ResponseEntity<QuestionDTO> updateQuestionContentManager(@PathVariable Long id,
//			@RequestBody QuestionDTO questionDTO) {
//		return new ResponseEntity<QuestionDTO>(questionService.update(questionDTO, id), HttpStatus.OK);
//	}

//	@PutMapping("/study/exam")
//	public ResponseEntity<List<QuestionDTO>> getListQuestion(@RequestBody SettingExamDTO settingExamDTO) {
//		return new ResponseEntity<List<QuestionDTO>>(questionService.get(settingExamDTO), HttpStatus.OK);
//	}

}
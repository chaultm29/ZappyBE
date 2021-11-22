package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.dto.QuestionGameDTO;
import com.example.springboot.services.GameService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@PostMapping("bingo/currentQuestion")
	public ResponseEntity<QuestionGameDTO> getCurrentQuestion(@RequestBody List<Integer> listQuestionId){
		return new ResponseEntity<QuestionGameDTO>(gameService.getCurrentQuestion(listQuestionId), HttpStatus.OK);
	}
	
	@PostMapping("bingo/result/{qid}/{aid}")
	public ResponseEntity<Boolean> getResult(@PathVariable Long qid, @PathVariable Long aid){
		try {
			return new ResponseEntity<Boolean>(gameService.isCorrect(qid, aid), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}
}
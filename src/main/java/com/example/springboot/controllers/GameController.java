package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.GameRecordDTO;
import com.example.springboot.dto.QuestionGameDTO;
import com.example.springboot.dto.RequireBingoQuestionDTO;
import com.example.springboot.services.GameRecordService;
import com.example.springboot.services.GameService;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@Autowired
	GameRecordService gameRecordService;
	
	
	
	@PostMapping("bingo/currentQuestion")
	public ResponseEntity<QuestionGameDTO> getCurrentQuestion(@RequestBody RequireBingoQuestionDTO requireBingo) {
		try {
			return new ResponseEntity<QuestionGameDTO>(gameService.getCurrentQuestion(requireBingo), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<QuestionGameDTO>(new QuestionGameDTO(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("bingo/result/{qid}/{aid}")
	public ResponseEntity<Boolean> getResult(@PathVariable Long qid, @PathVariable Long aid){
		try {
			return new ResponseEntity<Boolean>(gameService.isCorrect(qid, aid), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/saving")
	public ResponseEntity<Boolean> save(@RequestBody GameRecordDTO gameRecordDTO){
			return new ResponseEntity<Boolean>(gameRecordService.save(gameRecordDTO), HttpStatus.OK);			
	}
	
	@GetMapping("/record")
	public ResponseEntity<List<GameRecordDTO>> getRecord(){
		return new ResponseEntity<List<GameRecordDTO>>(gameRecordService.getRecords(), HttpStatus.OK);
	}
	
	
}

package com.example.springboot.controllers;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.QuestionExamDTO;
import com.example.springboot.dto.QuestionRequireDTO;
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

    @GetMapping()
    public ResponseEntity<List<QuestionExamDTO>> getExam(@RequestBody QuestionRequireDTO questionRequireDTO) {
        return new ResponseEntity<List<QuestionExamDTO>>(examService.questionExamDTOList(questionRequireDTO), HttpStatus.OK);
    }

//    @PostMapping()
//    public ResponseEntity<AccountDTO> createAccountEntity(@RequestBody AccountDTO accountDTO) {
//        return new ResponseEntity<AccountDTO>(new AccountDTO(), HttpStatus.OK);
//    }
}

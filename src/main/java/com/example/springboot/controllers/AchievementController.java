package com.example.springboot.controllers;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.UserAchievenmentDTO;
import com.example.springboot.services.AchievenmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/achievement")
public class AchievementController {
    @Autowired
    AchievenmentService achievenmentService;
    @GetMapping("")
    public ResponseEntity<List<UserAchievenmentDTO>> getAchievement() {
        return new ResponseEntity<List<UserAchievenmentDTO>>(achievenmentService.getAchievement(), HttpStatus.OK);
    }


}

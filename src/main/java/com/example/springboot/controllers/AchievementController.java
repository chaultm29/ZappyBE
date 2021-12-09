package com.example.springboot.controllers;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.AchievementDTO;
import com.example.springboot.dto.UserAchievementDTO;
import com.example.springboot.services.AchievementService;
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
    AchievementService achievementService;
    
    @GetMapping("")
    public ResponseEntity<List<UserAchievementDTO>> getAchievement() {
        return new ResponseEntity<List<UserAchievementDTO>>(achievementService.getUserAchievement(), HttpStatus.OK);
    }
    
    @GetMapping("/check")
    public ResponseEntity<List<AchievementDTO>> checkAchievement() {
        return new ResponseEntity<List<AchievementDTO>>(achievementService.checkAllAchievement(), HttpStatus.OK);
    }

}

package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.AWSConfigEntity;
import com.example.springboot.services.AWSConfigService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/awsconfig")
public class AWSConfigController {

	@Autowired
	AWSConfigService awsConfigService;
	
	@GetMapping("")
	public ResponseEntity<List<AWSConfigEntity>> getAchievement() {
        return new ResponseEntity<List<AWSConfigEntity>>(awsConfigService.getAWSConfig(), HttpStatus.OK);
    }
}

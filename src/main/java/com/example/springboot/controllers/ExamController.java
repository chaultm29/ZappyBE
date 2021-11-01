package com.example.springboot.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "https://www.zappy-nihongo.com"})
@RestController
@RequestMapping("/exam")
public class ExamController {

}

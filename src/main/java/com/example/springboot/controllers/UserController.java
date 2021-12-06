package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.LevelDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.services.UserService;

@CrossOrigin(origins =  {"http://localhost:3000", "https://www.zappy-nihongo.com"} )
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<UserDTO> getProfile() {
		UserDTO userDTO = null;
		try {
			userDTO = userService.getProfile();
			if(userDTO==null) throw new Exception();
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/profile")
	public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO userDTO) {
		try {
			userDTO = userService.save(userDTO);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/experience")
	public ResponseEntity<UserDTO> getExp() {
		UserDTO userDTO = null;
		try {
			userDTO = userService.getProfile();
			if(userDTO==null) throw new Exception();
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/level")
	public ResponseEntity<LevelDTO> getLevel() {
		return new ResponseEntity<LevelDTO>(userService.getLevel(), HttpStatus.OK);
	}
}

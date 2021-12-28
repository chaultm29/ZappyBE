package com.example.springboot.controllers;

import java.util.List;

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
import com.example.springboot.entities.UserEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.services.UserService;

import javassist.bytecode.DuplicateMemberException;

@CrossOrigin(origins = "*")
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
			if (userDTO == null)
				throw new Exception();
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/profile")
	public ResponseEntity<String> updateProfile(@RequestBody UserDTO userDTO) {
		try {
			userDTO = userService.save(userDTO);
			if (userDTO == null)
				throw new DuplicateMemberException("");
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
		}catch(DuplicateMemberException de) {
			return new ResponseEntity<String>("Email này đã tồn tại!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Account không tìm thấy!", HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping("/experience")
//	public ResponseEntity<UserDTO> getExp() {
//		UserDTO userDTO = null;
//		try {
//			userDTO = userService.getProfile();
//			if(userDTO==null) throw new Exception();
//			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.NOT_FOUND);
//		}
//	}

	@GetMapping("/level")
	public ResponseEntity<LevelDTO> getLevel() {
		return new ResponseEntity<LevelDTO>(userService.getLevel(), HttpStatus.OK);
	}
	
	@GetMapping("/ranking")
	public ResponseEntity<List<UserEntity>> getRanking() {
		return new ResponseEntity<List<UserEntity>>(userService.getTop10Ranking(), HttpStatus.OK);
	}
}

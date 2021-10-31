package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.services.AccountService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// get all account ko cần
	@GetMapping("/account")
	public ResponseEntity<List<AccountDTO>> getAllAccount() {
		return new ResponseEntity<List<AccountDTO>>(accountService.get(), HttpStatus.OK);
	}

	// create account rest api
	@PostMapping("/account")
	public ResponseEntity<AccountDTO> createAccountEntity(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<AccountDTO>(accountService.save(accountDTO), HttpStatus.OK);
	}

	// get account by id rest api
	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
		AccountDTO accountDTO = new AccountDTO();
		try {
			accountDTO = accountService.get(id);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.NOT_FOUND);
		}
	}

	// update account rest api

	@PutMapping("/account/{id}")
	public ResponseEntity<AccountDTO> updateAccEntity(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<AccountDTO>(accountService.update(id, accountDTO), HttpStatus.OK);
	}

	// delete account rest api
	@DeleteMapping("/account/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
		try {
			accountService.delete(id);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
	}
}

package com.example.springboot.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.config.JWTResponse;
import com.example.springboot.config.JWTTokenUtil;
import com.example.springboot.config.JwtRequest;
import com.example.springboot.dto.AccountDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.services.AccountService;
import com.example.springboot.services.MyUserDetailsService;

@CrossOrigin(origins =  "*" )
@RestController
@RequestMapping("")
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
	public ResponseEntity<String> createAccountEntity(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<String>(accountService.save(accountDTO), HttpStatus.OK);
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

	@PostMapping("/resetaccount")
	public ResponseEntity<HashMap<String, Object>> resetPasswordAccountEntity(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<HashMap<String, Object>>(accountService.resetPassword(accountDTO), HttpStatus.OK);
	}

	// update account rest api
	@PutMapping("/account/{id}")
	public ResponseEntity<HashMap<String, Object>> updateAccEntity(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<HashMap<String, Object>>(accountService.update(id, accountDTO), HttpStatus.OK);
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
package com.example.springboot.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.springboot.entities.AccountEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AccountRepository;
import com.example.springboot.services.AccountService;
import com.example.springbootdto.AccountDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	// get all account
	@GetMapping("/account")
	public List<AccountEntity> getAllAccount() {
		return (List<AccountEntity>) accountRepository.findAll();
	}

	// create account rest api
	@PostMapping("/account")
	public ResponseEntity<AccountDTO> creatAccountEntity(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<AccountDTO> (accountService.save(accountDTO),
				HttpStatus.OK);
	}

	// get account by id rest api
	@GetMapping("/account/{id}")
	public ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id) {
		AccountEntity accountEntity = (AccountEntity) accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
		return ResponseEntity.ok(accountEntity);
	}

	// update account rest api

	@PutMapping("/account/{id}")
	public ResponseEntity<AccountEntity> updateAccEntity(@PathVariable Long id,
			@RequestBody AccountEntity accountDetails) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));

		accountEntity.setUsername(accountDetails.getUsername());
		accountEntity.setPassword(accountDetails.getPassword());

		AccountEntity updatedAccountEntity = accountRepository.save(accountEntity);
		return ResponseEntity.ok(updatedAccountEntity);
	}

	// delete account rest api
	@DeleteMapping("/account/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));

		accountRepository.delete(accountEntity);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

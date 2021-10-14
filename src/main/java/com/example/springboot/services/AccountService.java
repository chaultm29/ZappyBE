package com.example.springboot.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AccountConverter;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AccountRepository;
import com.example.springbootdto.AccountDTO;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountConverter accountConverter;

	public AccountDTO save(AccountDTO accountDTO) {
		AccountEntity accountEntity = accountConverter.toEntity(accountDTO);
		AccountEntity afterSave = accountRepository.save(accountEntity);
		return accountConverter.toDTO(afterSave);
	}

	public AccountDTO update(Long id, AccountDTO accountDetails) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
		AccountEntity updatAccountEntity = accountConverter.toEntity(accountDetails, accountEntity);
		AccountEntity afterSave = accountRepository.save(updatAccountEntity);

		return accountConverter.toDTO(afterSave);
	}

	public void delete(Long id) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
		accountRepository.delete(accountEntity);
	}

	public AccountDTO get(Long id) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
		return accountConverter.toDTO(accountEntity);
	}

	public List<AccountDTO> get() {
		List<AccountEntity> accountEntities = accountRepository.findAll();
		return accountConverter.toDTOs(accountEntities);
	}
}

package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AccountConverter;
import com.example.springboot.entities.AccountEntity;
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
}

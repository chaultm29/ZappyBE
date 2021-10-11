package com.example.springboot.converters;

import org.springframework.stereotype.Component;

import com.example.springboot.entities.AccountEntity;
import com.example.springbootdto.AccountDTO;

@Component
public class AccountConverter {

	public AccountEntity toEntity(AccountDTO accountDTO) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setUsername(accountDTO.getUsername());
		accountEntity.setPassword(accountDTO.getPassword());
		return accountEntity;
	}

	// UPDATE
	public AccountEntity toEntity(AccountDTO accountDTO, AccountEntity accountEntity) {
		accountEntity.setUsername(accountDTO.getUsername());
		accountEntity.setPassword(accountDTO.getPassword());
		return accountEntity;
	}

	public AccountDTO toDTO(AccountEntity accountEntity) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername(accountEntity.getUsername());
		accountDTO.setPassword(accountEntity.getPassword());
		return accountDTO;
	}

}

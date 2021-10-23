package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

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
		accountDTO.setDateOfBirth(accountEntity.getUserEntity().getDateOfBirth());
		accountDTO.setEmail(accountEntity.getUserEntity().getEmail());
		accountDTO.setFullName(accountEntity.getUserEntity().getFullName());
		accountDTO.setPhone(accountEntity.getUserEntity().getPhone());
		accountDTO.setRole(accountEntity.getRoleEntity().getName());
		accountDTO.setAvatar(accountEntity.getUserEntity().getAvatar());
		return accountDTO;
	}

	public List<AccountDTO> toDTOs(List<AccountEntity> listEntities) {
		ArrayList<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		for (AccountEntity accountEntity : listEntities) {
			accountDTOs.add(toDTO(accountEntity));
		}
		return accountDTOs;
	}

}

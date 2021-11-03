package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.repositories.RoleRepository;

@Component
public class AccountConverter {
	@Autowired
	RoleRepository roleRepository;

	public AccountEntity toEntity(AccountDTO accountDTO) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(accountDTO.getId());
		accountEntity.setUsername(accountDTO.getUsername());
//		accountEntity.setPassword(accountDTO.getPassword());
		return accountEntity;
	}

	// UPDATE
	public AccountEntity toEntity(AccountDTO accountDTO, AccountEntity accountEntity) {
		accountEntity.setId(accountDTO.getId());
		accountEntity.setUsername(accountDTO.getUsername());
		// accountEntity.setPassword(accountDTO.getPassword());
		return accountEntity;
	}

	public AccountDTO toDTO(AccountEntity accountEntity) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername(accountEntity.getUsername());
//		accountDTO.setPassword(accountEntity.getPassword());
		accountDTO.setDateOfBirth((accountEntity.getUserEntity()!=null)?accountEntity.getUserEntity().getDateOfBirth():null);
		accountDTO.setEmail((accountEntity.getUserEntity()!=null)?accountEntity.getUserEntity().getEmail():"");
		accountDTO.setFullName((accountEntity.getUserEntity()!=null)?accountEntity.getUserEntity().getFullName():"");
		accountDTO.setPhone((accountEntity.getUserEntity()!=null)?accountEntity.getUserEntity().getPhone():"");
		accountDTO.setRole((accountEntity.getRoleEntity()!=null)?accountEntity.getRoleEntity().getName():"");
		accountDTO.setAvatar((accountEntity.getUserEntity()!=null)?accountEntity.getUserEntity().getAvatar():"");
		accountDTO.setId(accountEntity.getId());
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

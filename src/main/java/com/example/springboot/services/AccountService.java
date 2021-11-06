package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AccountConverter;
import com.example.springboot.dto.AccountDTO;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.entities.RoleEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AccountRepository;
import com.example.springboot.repositories.RoleRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AccountConverter accountConverter;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public AccountEntity findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	public AccountEntity saveAccount(AccountEntity accountEntity) {
		accountEntity.setPassword(bCryptPasswordEncoder.encode(accountEntity.getPassword()));
		accountEntity.setIsEnabled(true);
		RoleEntity roleEntity = roleRepository.findById((long)3).get();// cần sửa role chỗ này
		accountEntity.setRoleEntity(roleEntity);
		return accountRepository.save(accountEntity);
	}
	public AccountDTO save(AccountDTO accountDTO) {
		AccountEntity accountEntity = accountConverter.toEntity(accountDTO);
		AccountEntity afterSave = saveAccount(accountEntity);
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

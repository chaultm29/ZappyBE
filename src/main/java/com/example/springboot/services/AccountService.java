package com.example.springboot.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.springboot.converters.UserConverter;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.UserRepository;
//import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AccountConverter;
import com.example.springboot.dto.AccountDTO;
import com.example.springboot.dto.PasswordDTO;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.entities.RoleEntity;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repositories.AccountRepository;
import com.example.springboot.repositories.RoleRepository;

import javax.transaction.Transactional;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AccountConverter accountConverter;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AccountEntity findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	public AccountEntity saveAccount(AccountEntity accountEntity) {
		accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
		accountEntity.setIsEnabled(true);
//		RoleEntity roleEntity = roleRepository.findById((long)3).get();// cần sửa role chỗ này
//		accountEntity.setRoleEntity(roleEntity);
		return accountRepository.save(accountEntity);
	}

	@Transactional
	public String save(AccountDTO accountDTO) {
		AccountEntity accountEntity1 = accountRepository.findByUsername(accountDTO.getUsername());
		if (accountEntity1 != null) {
			return "Username: " + accountEntity1.getUsername() + " đã tồn tại trong hệ thống!";
		}
		
		UserEntity userEntity1 = userRepository.getUserByEmail(accountDTO.getEmail());
		if(userEntity1!=null){
			return "Email: " + accountDTO.getEmail() + " đã tồn tại trong hệ thống!" ;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setAvatar(accountDTO.getAvatar());
		userDTO.setEmail(accountDTO.getEmail());
		userDTO.setDateOfBirth(accountDTO.getDateOfBirth());
		userDTO.setPhone(accountDTO.getPhone());
		userDTO.setFullName(accountDTO.getFullName());
		UserEntity userEntity = userConverter.toEntity(userDTO);

		AccountEntity accountEntity = accountConverter.toEntity(accountDTO);
		accountEntity.setUserEntity(userEntity);

		AccountEntity afterSave = saveAccount(accountEntity);
		return "Tạo tài khoản " + afterSave.getUsername() + " thành công";
	}

	public HashMap<String, Object> update(Long id, AccountDTO accountDetails) {
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		String mess = "";
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
		AccountEntity updatAccountEntity = accountConverter.toEntity(accountDetails, accountEntity);
		AccountEntity afterSave = accountRepository.save(updatAccountEntity);
		afterSave.setPassword("");
		mess = "Cập nhật account " + afterSave.getUsername() + " thành công";
		stringObjectHashMap.put("message", mess);
		return stringObjectHashMap;
	}

	public HashMap<String, Object> resetPassword(String username) {
		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		String mess = "";
		AccountEntity accountEntity = accountRepository.findByUsername(username);

		accountEntity.setPassword("123456");
		AccountEntity afterSave = saveAccount(accountEntity);
		mess = "Cập nhật account " + afterSave.getUsername() + " thành công";
		stringObjectHashMap.put("message", mess);
		return stringObjectHashMap;
	}

	public void delete(Long id) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
//		accountRepository.delete(accountEntity);
		accountEntity.setIsEnabled(false);
		accountRepository.save(accountEntity);
	}

	public AccountDTO get(Long id) {
		AccountEntity accountEntity = accountRepository.getAccountEnableByID(id);
		AccountDTO accountDTO = (accountEntity != null) ? accountConverter.toDTO(accountEntity) : new AccountDTO();
		accountDTO.setPasswordNew("");
		accountDTO.setPasswordOld("");
		return accountDTO;
	}

	public List<AccountDTO> get() {
		List<AccountEntity> accountEntities = accountRepository.getAllAccountEnable();
		List<AccountDTO> accountDTOS = (accountEntities != null && accountEntities.size() != 0)
				? accountConverter.toDTOs(accountEntities)
				: new ArrayList<>();
		for (AccountDTO accountDTO : accountDTOS) {
			accountDTO.setPasswordNew("");
			accountDTO.setPasswordOld("");
		}
		return accountDTOS;
	}

	public boolean changePassword(PasswordDTO passwordDTO) {
		if (!passwordDTO.isEqual()) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			AccountEntity accountEntity = accountRepository.findByUsername(username);
			if (passwordEncoder.matches(passwordDTO.getOldPassword(), accountEntity.getPassword())) {
				accountEntity.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
				accountRepository.save(accountEntity);
				return true;
			}
		}
		return false;

	}
}

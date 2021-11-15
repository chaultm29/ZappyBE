package com.example.springboot.services;

import java.util.List;

import com.example.springboot.converters.UserConverter;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.converters.AccountConverter;
import com.example.springboot.dto.AccountDTO;
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
		if(accountEntity1 != null){
			return "Username: " + accountEntity1.getUsername() + " đã tồn tại trong hệ thống!" ;
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
		return "Tạo tài khoản " + afterSave.getUsername() +" thành công" ;
	}

	public AccountDTO update(Long id, AccountDTO accountDetails) {
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not exist with id :" + id));
		AccountEntity updatAccountEntity = accountConverter.toEntity(accountDetails, accountEntity);
		AccountEntity afterSave =saveAccount(updatAccountEntity);

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

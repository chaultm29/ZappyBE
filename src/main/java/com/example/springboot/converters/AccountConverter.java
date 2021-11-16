package com.example.springboot.converters;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot.entities.UserEntity;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.dto.AccountDTO;
import com.example.springboot.entities.AccountEntity;
import com.example.springboot.repositories.RoleRepository;

@Component
public class AccountConverter {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public AccountEntity toEntity(AccountDTO accountDTO) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setIsEnabled(true);
        accountEntity.setUsername(accountDTO.getUsername());
        accountEntity.setPassword(accountDTO.getPasswordNew());
        accountEntity.setRoleEntity(roleRepository.getById(accountDTO.getRoleDTO().getId()));
        return accountEntity;
    }

    // UPDATE
    public AccountEntity toEntity(AccountDTO accountDTO, AccountEntity accountEntity) {
        accountEntity.setUsername(accountDTO.getUsername());
        accountEntity.setPassword(accountDTO.getPasswordNew());
        accountEntity.setRoleEntity(roleRepository.getById(accountDTO.getRoleDTO().getId()));
        accountEntity.setIsEnabled(true);
        UserEntity userEntity = accountEntity.getUserEntity();
        userEntity.setAvatar(accountDTO.getAvatar());
        userEntity.setEmail(accountDTO.getEmail());
        userEntity.setPhone(accountDTO.getPhone());
        userEntity.setFullName(accountDTO.getFullName());
        userEntity.setDateOfBirth(accountDTO.getDateOfBirth());
        accountEntity.setUserEntity(userEntity);
        return accountEntity;
    }

    public AccountDTO toDTO(AccountEntity accountEntity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(accountEntity.getUsername());
        accountDTO.setPasswordNew(accountEntity.getPassword());
        accountDTO.setDateOfBirth(accountEntity.getUserEntity().getDateOfBirth());
        accountDTO.setEmail(accountEntity.getUserEntity().getEmail());
        accountDTO.setFullName(accountEntity.getUserEntity().getFullName());
        accountDTO.setPhone(accountEntity.getUserEntity().getPhone());
        accountDTO.setRoleDTO(new RoleConverter().toDTO(accountEntity.getRoleEntity()));
        accountDTO.setAvatar(accountEntity.getUserEntity().getAvatar());
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
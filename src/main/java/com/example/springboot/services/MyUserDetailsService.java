package com.example.springboot.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springboot.entities.AccountEntity;
import com.example.springboot.entities.RoleDetailsEntity;
import com.example.springboot.repositories.AccountRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AccountEntity accountEntity = accountRepository.findByUsername(username);
//		if(accountEntity == null) throw new UsernameNotFoundException("Account is not found!");
//		return new User(accountEntity.getUsername(), accountEntity.getPassword(), new ArrayList<GrantedAuthority>());
//	}
//	
//	public boolean checkRole(String username, String linkUri) {
//		AccountEntity accountEntity = accountRepository.findByUsername(username);
//		if(accountEntity == null) throw new UsernameNotFoundException("Account is not found!");
//		for (RoleDetailsEntity roleDetails : accountEntity.getRoleEntity().getRoleDetailsEntities()) {
//			if(roleDetails.getLink().startsWith(linkUri)) return true;
//		}
//		return false;
//	}
//	
//	public String getRoleName(String username) {
//		AccountEntity accountEntity = accountRepository.findByUsername(username);
//		if(accountEntity == null) throw new UsernameNotFoundException("Account is not found!");
//		return accountEntity.getRoleEntity().getName();
//	}
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        if (accountEntity == null||accountEntity.getIsEnabled()==false) throw new UsernameNotFoundException("Account is not found!");
        return new User(accountEntity.getUsername(), accountEntity.getPassword(), new ArrayList<GrantedAuthority>());
    }

    public boolean checkRole(String username, String linkUri) {
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        if (accountEntity == null) throw new UsernameNotFoundException("Account is not found!");
        for (RoleDetailsEntity roleDetails : accountEntity.getRoleEntity().getRoleDetailsEntities()) {
//			if(roleDetails.getLink().startsWith(linkUri)) return true;
            if (linkUri.startsWith(roleDetails.getLink())) return true;
        }
        return false;
    }

    public String getRoleName(String username) {
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        if (accountEntity == null) throw new UsernameNotFoundException("Account is not found!");
        return accountEntity.getRoleEntity().getName();
    }
}

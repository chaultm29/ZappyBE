package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{

}

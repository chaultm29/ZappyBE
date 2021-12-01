package com.example.springboot.repositories;

import com.example.springboot.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
	AccountEntity findByUsername(String username);

	@Query("select q from AccountEntity q where q.isEnabled=true order by q.id")
	List<AccountEntity> getAllAccountEnable();

	@Query("select q from AccountEntity q where q.isEnabled=true and q.id = :id order by q.id")
	AccountEntity getAccountEnableByID(@Param("id") Long id);
}

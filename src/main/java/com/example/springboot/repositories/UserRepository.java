package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("select  a.userEntity from  AccountEntity a inner join a.userEntity where a.username = :username")
	UserEntity getUserByUserName(@Param("username") String username);
	
	@Query("select  ue from UserEntity ue  where ue.email = :email")
	UserEntity getUserByEmail(@Param("email") String email);
	
	List<UserEntity> findByEmail(String email);
	
	@Query(value="select * from users inner join accounts where accounts.user_id = users.id and role_id = 3 order by exp desc limit 10", nativeQuery = true)
	List<UserEntity> getTopRanking();
}

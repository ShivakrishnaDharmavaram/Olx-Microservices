package com.olx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	List<UserEntity> findByUsername(String username);
	
	@Query(value="SELECT COUNT(id) FROM users", nativeQuery = true)
	Integer countOfUsers();
	
	@Query(value="select count(id) from users where active=true", nativeQuery = true)
	Integer countOfActiveUsers();
}

package com.adam.spring.security.login.repository;

import java.util.Optional;
//added import
//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adam.spring.security.login.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	//adding list
	//List<User> findByName(String name);
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}

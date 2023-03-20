package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	List<User> findByfirstNameContaining(String name);
	List<User> findBylastNameContaining(String name);
	
	}

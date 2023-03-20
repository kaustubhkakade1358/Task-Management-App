package net.javaguides.springboot.service;

import java.util.List;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.model.User;

public interface UserService1 {

	User save(UserRegistrationDto registrationDto);
	
	
    //create
	public UserRegistrationDto createUser(UserRegistrationDto mDto);
	
	//update
	public UserRegistrationDto  updateUser(UserRegistrationDto mDto, Long id);

	//delete
	public void deleteUser(Long id);

	//get
	public UserRegistrationDto getSingleUser(Long id);

    //getAll
	public List<UserRegistrationDto> getAllUser();


//	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

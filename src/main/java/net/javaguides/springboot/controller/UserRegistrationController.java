package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.ApiResponse;
import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.service.UserService;

//@Controller
//@RequestMapping("/registration")
@RestController
@RequestMapping("/u")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
//	@PostMapping
//	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
//		userService.save(registrationDto);
//		return "redirect:/registration?success";
//	}
//	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	//POST -CREATE User
			@PostMapping("/user/")
			public ResponseEntity<UserRegistrationDto> createUser(@Valid 
					@RequestBody UserRegistrationDto UserRegistrationDto){
				
				UserRegistrationDto createUser = this.userService.createUser(UserRegistrationDto);
				
				return new ResponseEntity<UserRegistrationDto>(createUser,HttpStatus.CREATED);
			}

			
			//DELETE -DELETE User
			@DeleteMapping("/user/{id}")
			public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long uid){
				
				this.userService.deleteUser(uid);
				
				return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true,new Date()),HttpStatus.OK);
			}
			
			
			
			

			//PUT -UPDATE User
			@PutMapping("/user/{id}")
			public ResponseEntity<UserRegistrationDto> updateUser(@Valid @RequestBody UserRegistrationDto UserRegistrationDto, @PathVariable("id") Long uid){
				
				UserRegistrationDto updateUser = this.userService.updateUser(UserRegistrationDto, uid);
				
				return ResponseEntity.ok(updateUser);
				
			}
			
			//GET -GET SINGLE User
			@GetMapping("/user/{id}")
			public ResponseEntity<UserRegistrationDto> getSingleUser(@PathVariable("id") Long uid){
				
				UserRegistrationDto userById = this.userService.getSingleUser(uid);
				
				return ResponseEntity.ok(userById);

			}
			

			//GET -GET ALL tasks
			@GetMapping("/user/")
			public ResponseEntity<List<UserRegistrationDto>> getAllUsers(){
				
				List<UserRegistrationDto> allUsers = this.userService.getAllUser();
				
				return ResponseEntity.ok(allUsers);
			}
}

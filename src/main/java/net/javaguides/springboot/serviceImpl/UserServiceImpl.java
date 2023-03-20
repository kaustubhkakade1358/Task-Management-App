package net.javaguides.springboot.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.ModulRepo;
import net.javaguides.springboot.repository.ProjectRepo;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private ProjectRepo ProjectRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ModulRepo moduleRepo;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

//	@Override
//	public User save(UserRegistrationDto registrationDto) {
//		User user = new User(registrationDto.getFirstName(), 
//				registrationDto.getLastName(), registrationDto.getEmail(),
//				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
//		
//		return userRepository.save(user);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	
//		User user = userRepository.findByEmail(username);
//		if(user == null) {
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
//	}
//	
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}

	
	
//	//////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@Override
	public UserRegistrationDto createUser(UserRegistrationDto mDto) {
		
		User user = this.modelMapper.map(mDto, User.class);
		
		this.userRepository.save(user);
		
		UserRegistrationDto create = this.modelMapper.map(user, UserRegistrationDto.class);
		
		return create;
	}
	
	@Override
	public UserRegistrationDto updateUser(UserRegistrationDto userDto, Long id) {
		
		User user= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id",id));
		
		user.setFirstName(userDto.getFirstName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setLastName(userDto.getLastName());
		
		
		
		User save = this.userRepository.save(user);
		
		UserRegistrationDto update = this.modelMapper.map(user, UserRegistrationDto.class);
		
		return update;
	}

	@Override
	public void deleteUser(Long id) {

		User user= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id",id));

		this.userRepository.delete(user);
	}

	@Override
	public UserRegistrationDto getSingleUser(Long id) {
		
		User user= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id",id));

		UserRegistrationDto getSingle = this.modelMapper.map(user, UserRegistrationDto.class);

		return getSingle;
	}

	@Override
	public List<UserRegistrationDto> getAllUser() {
		
		List<User> findAll = this.userRepository.findAll();
		
		List<UserRegistrationDto> collect = findAll.stream().map(e->this.modelMapper.map(e, UserRegistrationDto.class)).collect(Collectors.toList());
		
		return collect;
	}


	
	

	
	
	
	
	
}

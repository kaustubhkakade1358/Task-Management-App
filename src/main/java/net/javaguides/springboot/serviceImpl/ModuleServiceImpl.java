package net.javaguides.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.javaguides.springboot.dto.ModuleDto;
import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Modul;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.ModulRepo;
import net.javaguides.springboot.repository.ProjectRepo;
import net.javaguides.springboot.repository.TaskRepo;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.ModuleService;


@Service
public class ModuleServiceImpl implements ModuleService {

	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModulRepo moduleRepo;
	
	@Autowired
	private TaskRepo taskRepo;
	
	
	@Override
	public ModuleDto createModule(ModuleDto mDto,Long userId,Long projectId) {
		
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
		
		Project project=this.projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project", "pid", projectId));
		
		List<User> users= new ArrayList<>();
		
		users.add(user);
		
		Modul module=this.modelMapper.map(mDto, Modul.class);
		
		module.setProject(project);
		module.setUsers(users);
		
		Modul m = this.moduleRepo.save(module);
		
		ModuleDto create = this.modelMapper.map(m, ModuleDto.class);

		return create;
	}

	@Override
	public ModuleDto updateModule(ModuleDto mDto, Long id) {
		
		Modul modul=this.moduleRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Modul", "mid", id));
		
		modul.setModuleName(mDto.getModuleName());
		modul.setStartDate(mDto.getStartDate());
		modul.setEndDate(mDto.getEndDate());
		modul.setStatus(mDto.getStatus());
		modul.setDetails(mDto.getDetails());


		
		
		
		
		Modul update = this.moduleRepo.save(modul);
		
		return this.modelMapper.map(update, ModuleDto.class);
	}

	
	@Override
	public void deleteModule(Long id) {
		
		Modul modul=this.moduleRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Modul", "mid", id));

		this.moduleRepo.delete(modul);
	}

	@Override
	public ModuleDto getSingleModule(Long id) {
		
		Modul modul=this.moduleRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Modul", "mid", id));
		
		ModuleDto getSingle = this.modelMapper.map(modul, ModuleDto.class);
		return getSingle;
	}

	@Override
	public List<ModuleDto> getAllModule() {
		
		List<Modul> modules=this.moduleRepo.findAll();
		List<ModuleDto> allModule = modules.stream().map((p)->this.modelMapper.map(p, ModuleDto.class)).collect(Collectors.toList());

		return allModule;
	}
	
	
	@Override
	public List<ModuleDto> getAllModulesBySearchPosts(String keyword) {
		
		List<Modul> findBymoduleNameContaining = this.moduleRepo.findBymoduleNameContaining(keyword);
		
		List<ModuleDto> getAllM = findBymoduleNameContaining.stream().map(e->this.modelMapper.map(e, ModuleDto.class)).collect(Collectors.toList());
		
		
		return getAllM;
	}

}

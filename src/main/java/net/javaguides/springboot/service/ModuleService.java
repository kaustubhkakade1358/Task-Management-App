package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.ModuleDto;
import net.javaguides.springboot.dto.ProjectDto;


public interface ModuleService {

	
	    //create
		public ModuleDto createModule(ModuleDto mDto, Long userId,Long projectId);
		
		
		//update
		public ModuleDto  updateModule(ModuleDto mDto, Long id);

		//delete
		public void deleteModule(Long id);

		//get
		public ModuleDto getSingleModule(Long id);

	    //getAll
		public List<ModuleDto> getAllModule();
		
		public List<ModuleDto> getAllModulesBySearchPosts(String keyword);
}

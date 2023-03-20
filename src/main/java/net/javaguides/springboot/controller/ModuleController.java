package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.ApiResponse;
import net.javaguides.springboot.dto.ModuleDto;
import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.service.ModuleService;

@RestController
@RequestMapping("/m")
public class ModuleController {

	
	@Autowired
	private ModuleService moduleService;
	
	//POST -CREATE Module
		@PostMapping("/user/{userId}/project/{projectId}/module/")
		public ResponseEntity<ModuleDto> createModule(@Valid 
				@RequestBody ModuleDto ModuleDto,
				@PathVariable("userId") Long userId,
				@PathVariable("projectId") Long projectId){
			
			ModuleDto createModule = this.moduleService.createModule(ModuleDto,userId,projectId);
			
			return new ResponseEntity<>(createModule,HttpStatus.CREATED);
		}

		
		//DELETE -DELETE Module
		@DeleteMapping("/module/{id}")
		public ResponseEntity<ApiResponse> deleteModule(@PathVariable("id") Long mid){
			
			this.moduleService.deleteModule(mid);
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("Module Deleted Successfully",true,new Date()),HttpStatus.OK);
		}
		
		
		
		

		//PUT -UPDATE Module
		@PutMapping("/module/{id}")
		public ResponseEntity<ModuleDto> updateModule(@Valid @RequestBody ModuleDto ModuleDto, @PathVariable("id") Long mid){
			
			ModuleDto updateModule = this.moduleService.updateModule(ModuleDto, mid);
			
			return ResponseEntity.ok(updateModule);
			
		}
		
		//GET -GET SINGLE Module
		@GetMapping("/module/{id}")
		public ResponseEntity<ModuleDto> getSingleModule(@PathVariable("id") Long mid){
			
			ModuleDto moduleById = this.moduleService.getSingleModule(mid);
			
			return ResponseEntity.ok(moduleById);

		}
		

		//GET -GET ALL tasks
		@GetMapping("/module/")
		public ResponseEntity<List<ModuleDto>> getAllModules(){
			
			List<ModuleDto> allModules = this.moduleService.getAllModule();
			
			return ResponseEntity.ok(allModules);
		}
		
//		//search
		@GetMapping("module/search/{keywords}")
		
		public ResponseEntity<List<ModuleDto>> searchProjectByTitle(@PathVariable("keywords") String keywords){
			
			List<ModuleDto> allModuleBySearchPosts = this.moduleService.getAllModulesBySearchPosts(keywords);
			
			return new ResponseEntity<List<ModuleDto>>(allModuleBySearchPosts,HttpStatus.OK);
		}	
		
	
	
	
}

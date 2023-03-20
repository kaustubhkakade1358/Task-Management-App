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
import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.service.ProjectService;

@RestController
@RequestMapping("/p")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	//POST -CREATE Project
		@PostMapping("/user/{userId}/project")
		public ResponseEntity<ProjectDto> createProject(@Valid 
				@RequestBody ProjectDto ProjectDto,
				@PathVariable("userId") Long userId){
			
			ProjectDto createProject = this.projectService.createProject(ProjectDto,userId);
			
			return new ResponseEntity<>(createProject,HttpStatus.CREATED);
		}

		
		//DELETE -DELETE Project
		@DeleteMapping("/project/{id}")
		public ResponseEntity<ApiResponse> deleteProject(@PathVariable("id") Long pid){
			
			this.projectService.deleteProject(pid);
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("Project Deleted Successfully",true,new Date()),HttpStatus.OK);
		}
		
		
		
		

		//PUT -UPDATE Project
		@PutMapping("/project/{id}")
		public ResponseEntity<ProjectDto> updateTask(@Valid @RequestBody ProjectDto ProjectDto, @PathVariable("id") Long pid){
			
			ProjectDto updateProject = this.projectService.updateProject(ProjectDto, pid);
			
			return ResponseEntity.ok(updateProject);
			
		}
		
		//GET -GET SINGLE Project
		@GetMapping("/project/{id}")
		public ResponseEntity<ProjectDto> getSingleProject(@PathVariable("id") Long pid){
			
			ProjectDto projectById = this.projectService.getProjectById(pid);
			
			return ResponseEntity.ok(projectById);

		}
		

		//GET -GET ALL project
		@GetMapping("/project/")
		public ResponseEntity<List<ProjectDto>> getAllProjects(){
			
			List<ProjectDto> allProjects = this.projectService.getAllProject1();
			
			return ResponseEntity.ok(allProjects);
		}
		
//		//search
		@GetMapping("project/search/{keywords}")
		
		public ResponseEntity<List<ProjectDto>> searchProjectByTitle(@PathVariable("keywords") String keywords){
			
			List<ProjectDto> allProjectBySearchPosts = this.projectService.getAllProjectsBySearchPosts(keywords);
			
			return new ResponseEntity<List<ProjectDto>>(allProjectBySearchPosts,HttpStatus.OK);
		}	
		
	
}

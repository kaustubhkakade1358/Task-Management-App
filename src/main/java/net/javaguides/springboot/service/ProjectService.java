package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.ProjectDto;


public interface ProjectService {

//	create
	
	ProjectDto createProject(ProjectDto project,Long userId);
	
//	update 

	ProjectDto updateProject(ProjectDto project,Long pId);
	
//	get single

	ProjectDto getProjectById(Long pId);
	
	
//	delete

	void deleteProject(Long pId);
	
	
	
//	every single post
//	get all

	List<ProjectDto> getAllProject1();
	
	
////	get all
//
//	PostResponse  getAllPost2(Long pagenumber,Long pagesize,String sortBy,String sortDir);
	
	
//	get all by category

         ProjectDto getProjectByModule(Long mId);
	
//	get all by user

	List<ProjectDto> getAllProjectByUser(Long userId);
	
	
	
//	get all by search keyword

	List<ProjectDto> getAllProjectsBySearchPosts(String keyword);
	
}

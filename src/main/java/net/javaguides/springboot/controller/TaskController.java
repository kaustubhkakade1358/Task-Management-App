package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.javaguides.springboot.dto.ApiResponse;
import net.javaguides.springboot.dto.TaskDto;
import net.javaguides.springboot.service.TaskService;

@RestController
@RequestMapping("/t")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	//POST -CREATE Comment
	@PostMapping("/user/{userId}/project/{projectId}/module/{moduleId}/tasks")
	public ResponseEntity<TaskDto> createTask(@Valid 
			@RequestBody TaskDto taskDto,
			@PathVariable("userId") Long userId,
			@PathVariable("projectId") Long projectId,
			@PathVariable("moduleId") Long moduleId){
		
		TaskDto createTask = this.taskService.createTask(taskDto,userId,projectId,moduleId);
		
		return new ResponseEntity<>(createTask,HttpStatus.CREATED);
	}

	
	//DELETE -DELETE Comment
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<ApiResponse> deleteTask(@PathVariable("id") Long tid){
		
		this.taskService.deleteTask(tid);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Task Deleted Successfully",true,new Date()),HttpStatus.OK);
	}
	
	
	
	

	//PUT -UPDATE Comment
	@PutMapping("/tasks/{id}")
	public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto TaskDto, @PathVariable("id") Long tid){
		
		TaskDto updateTask = this.taskService.updateTask(TaskDto, tid);
		
		return ResponseEntity.ok(updateTask);
		
	}
	
	//GET -GET SINGLE Comment
	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskDto> getSingleTask(@PathVariable("id") Long tid){
		
		TaskDto taskById = this.taskService.getTasksbyId(tid);
		
		return ResponseEntity.ok(taskById);

	}
	

	//GET -GET ALL tasks
	@GetMapping("/tasks/")
	public ResponseEntity<List<TaskDto>> getAllTasks(){
		
		List<TaskDto> allTasks = this.taskService.getAllTasks();
		
		return ResponseEntity.ok(allTasks);
	}
	
//	//search
	@GetMapping("posts/search/{keywords}")
	
	public ResponseEntity<List<TaskDto>> searchtaskByTitle(@PathVariable("keywords") String keywords){
		
		List<TaskDto> allPostBySearchPosts = this.taskService.getAllTasksBySearchTasks(keywords);
		
		return new ResponseEntity<List<TaskDto>>(allPostBySearchPosts,HttpStatus.OK);
	}	
	
	
	
	
	
	
	
}

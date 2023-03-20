package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.dto.TaskDto;


public interface TaskService {

    TaskDto createTask(TaskDto taskDto, Long userId,Long projectId,Long moduleId);
	
	void deleteTask(Long taskId);
	
	TaskDto updateTask(TaskDto taskDto,Long taskId);
	
	TaskDto  getTasksbyId(Long taskId);
	
	List<TaskDto> getAllTasks();
	
	List<TaskDto> getAllTasksByPost(Long projectId);
	
	List<TaskDto> getAllTasksByUser(Long userId);
	
	List<TaskDto> getAllTasksByModule(Long moduleId);
	

	List<TaskDto> getAllTasksBySearchTasks(String keyword);

}

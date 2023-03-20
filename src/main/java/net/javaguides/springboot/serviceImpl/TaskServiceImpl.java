package net.javaguides.springboot.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.TaskDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.model.Modul;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.ModulRepo;
import net.javaguides.springboot.repository.ProjectRepo;
import net.javaguides.springboot.repository.TaskRepo;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.TaskService;


@Service
public class TaskServiceImpl implements TaskService {

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
	public TaskDto createTask(TaskDto taskDto, Long userId, Long projectId, Long moduleId) {
		
		Project project=this.projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project", "pid", projectId));
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
		Modul module=this.moduleRepo.findById(moduleId).orElseThrow(()->new ResourceNotFoundException("Modul", "mid",moduleId));

		Task task = this.modelMapper.map(taskDto, Task.class);

		task.setProject(project);
		task.setUser(user);
		task.setModule(module);
		
		Task save = this.taskRepo.save(task);
		return this.modelMapper.map(save, TaskDto.class);
	}

	@Override
	public void deleteTask(Long taskId) {
		
		Task tId = this.taskRepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task", "tid",taskId));

		this.taskRepo.delete(tId);
		
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto, Long taskId) {
		
		Task task=this.taskRepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task", "tid",taskId));
		
		task.setTaskName(taskDto.getTaskName());
		task.setStartDate(taskDto.getStartDate());
		task.setEndDate(taskDto.getEndDate());
		task.setStatus(taskDto.getStatus());
		task.setDetails(taskDto.getDetails());

		Task update = this.taskRepo.save(task);
		
		
		return this.modelMapper.map(update, TaskDto.class);
	}

	@Override
	public TaskDto getTasksbyId(Long taskId) {
		
		Task findById = this.taskRepo.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task", "tid",taskId));
		
		TaskDto get = this.modelMapper.map(findById, TaskDto.class);
		
		return get;
	}

	@Override
	public List<TaskDto> getAllTasks() {

	   List<Task> findAll = this.taskRepo.findAll();
		
		
		List<TaskDto> collect = findAll.stream().map(e->this.modelMapper.map(e, TaskDto.class)).collect(Collectors.toList());
		
		return collect;
		
	}

	@Override
	public List<TaskDto> getAllTasksByPost(Long projectId) {
		
        Project p = this.projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project", "pid",projectId));
		
		Set<Task> task = p.getTasks();
		
		List<TaskDto> tByProject = (List<TaskDto>) this.modelMapper.map(task, TaskDto.class);
		
		return tByProject;
	}

	@Override
	public List<TaskDto> getAllTasksByUser(Long userId) {
		
		  User u = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
			
			Set<Task> ta = u.getTasks();
			
			List<TaskDto> tByuser = (List<TaskDto>) this.modelMapper.map(ta, TaskDto.class);
			
			return tByuser;
	}

	@Override
	public List<TaskDto> getAllTasksByModule(Long moduleId) {
		
		Modul m = this.moduleRepo.findById(moduleId).orElseThrow(()->new ResourceNotFoundException("Module", "mid",moduleId));
		
		Set<Task> ta = m.getTasks();
		
		List<TaskDto> tByModule = (List<TaskDto>) this.modelMapper.map(ta, TaskDto.class);
		
		return tByModule;
	}

	@Override
	public List<TaskDto> getAllTasksBySearchTasks(String keyword) {
		
       List<Task> findByTitleContaining = this.taskRepo.findBytaskNameContaining(keyword);
		
		List<TaskDto> collect = findByTitleContaining.stream().map((search)->this.modelMapper.map(search, TaskDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	
}

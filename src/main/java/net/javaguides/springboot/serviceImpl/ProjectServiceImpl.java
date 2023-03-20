package net.javaguides.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Modul;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.ProjectService;
import net.javaguides.springboot.repository.ModulRepo;
import net.javaguides.springboot.repository.ProjectRepo;
import net.javaguides.springboot.repository.UserRepository;


@Service
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	private ProjectRepo ProjectRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModulRepo moduleRepo;

	
	
	@Override
	public ProjectDto createProject(ProjectDto projectDto, Long userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		Project project=this.modelMapper.map(projectDto,Project.class);
		
		List<User> users= new ArrayList<>();
		
		users.add(user);
		
		project.setUsers(users);
		
		this.ProjectRepo.save(project);
		
		ProjectDto create = this.modelMapper.map(project, ProjectDto.class);

		return create;
	}

	@Override
	public ProjectDto updateProject(ProjectDto project, Long pId) {

		Project pro = this.ProjectRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException("Project","pid",pId));
		
		pro.setProjectName(project.getProjectName());
		pro.setStartDate(project.getStartDate());
		pro.setStatus(project.getStatus());
		pro.setDetails(project.getDetails());
		

		
		
		
		Project update = this.ProjectRepo.save(pro);
		
		return this.modelMapper.map(update, ProjectDto.class);
		
	}

	
	@Override
	public ProjectDto getProjectById(Long pId) {
		
		Project pro = this.ProjectRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException("Project","pid",pId));

		ProjectDto getbyId = this.modelMapper.map(pro, ProjectDto.class);
		return getbyId;
	}

	@Override
	public void deleteProject(Long pId) {
		
		Project pro = this.ProjectRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException("Project","pid",pId));

		this.ProjectRepo.delete(pro);
	}

	@Override
	public List<ProjectDto> getAllProject1() {

		List<Project> findAll = this.ProjectRepo.findAll();
		
		List<ProjectDto> collect = findAll.stream().map(e->this.modelMapper.map(e, ProjectDto.class)).collect(Collectors.toList());
		
		
		return collect;
	}

	@Override
	public ProjectDto getProjectByModule(Long mId) {
		
		Modul module = this.moduleRepo.findById(mId).orElseThrow(()->new ResourceNotFoundException("Modul","mid",mId));
		
		Project project=module.getProject();
		
		ProjectDto getPro = this.modelMapper.map(project, ProjectDto.class);
		
		return getPro;
	}

	@Override
	public List<ProjectDto> getAllProjectByUser(Long userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));

		Set<Project> projects= user.getProjects();
		
		List<ProjectDto> collect = projects.stream().map(e->this.modelMapper.map(e, ProjectDto.class)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<ProjectDto> getAllProjectsBySearchPosts(String keyword) {
		
		List<Project> findByprojectNameContaining = this.ProjectRepo.findByprojectNameContaining(keyword);
		
		List<ProjectDto> getAllP = findByprojectNameContaining.stream().map(e->this.modelMapper.map(e, ProjectDto.class)).collect(Collectors.toList());
		
		
		return getAllP;
	}

	
	

	
	
	


}

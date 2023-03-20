package net.javaguides.springboot.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.model.Modul;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.model.User;


@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {

	
    private Long pid;
	
	private String projectName;
	
	private Date startDate;
	
	private String status;
	
	private String details;
	
	private Set<Modul> modules=new HashSet<>();
	
	private Set<Task> tasks=new HashSet<>();
//	
//	private List<User> users;


}

package net.javaguides.springboot.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.model.User;


@NoArgsConstructor
@Getter
@Setter
public class ModuleDto {

	
    private Long mid;
	
	private String moduleName;
	
	private Date startDate;
	
	private Date EndDate;

	
	private String status;
	
	private String details;
	

//	private Project project;
//	
//	private List<User> users;
//	
	private Set<Task> tasks=new HashSet<>();
}

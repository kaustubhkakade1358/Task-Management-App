package net.javaguides.springboot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.javaguides.springboot.model.User;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="projects")
@NoArgsConstructor
@Getter
@Setter
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pid;
	
	private String projectName;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	private String status;
	
	@Column(name="details" ,length=500,nullable=false)
	private String details;
	
//	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="project",cascade=CascadeType.ALL)
	private Set<Modul> modules=new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="project",cascade=CascadeType.ALL)
	private Set<Task> tasks=new HashSet<>();
	
	
	@JsonIgnore
    @ManyToMany(mappedBy="projects",cascade=CascadeType.ALL)
	private List<User> users;

	
	
}

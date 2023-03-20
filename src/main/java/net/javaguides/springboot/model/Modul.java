package net.javaguides.springboot.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="modules")
@NoArgsConstructor
@Getter
@Setter
public class Modul{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mid;
	
	private String moduleName;
	
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date EndDate;

	
	private String status;
	
	@Column(name="details" ,length=500,nullable=false)
	private String details;
	
	
	@JsonIgnore
//	@JsonBackReference
	@ManyToOne
	private Project project;
	
	@JsonIgnore
//	@JsonManagedReference
	@OneToMany(mappedBy="module",cascade=CascadeType.ALL)
	private Set<Task> tasks=new HashSet<>();
	
	
	@JsonIgnore
	@ManyToMany(mappedBy="modules",cascade=CascadeType.ALL)
	private List<User> users;

	
}

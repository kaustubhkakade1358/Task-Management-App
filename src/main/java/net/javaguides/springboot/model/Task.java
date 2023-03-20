package net.javaguides.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tid;
	
    private String taskName;
    
    @Temporal(TemporalType.DATE)
	private Date startDate;
	
    
    @Temporal(TemporalType.DATE)
	private Date EndDate;
	
	private String status;
	
	@Column(name="details" ,length=500,nullable=false)
	private String details;
	
    @Temporal(TemporalType.TIME)
	private Date startTime;
    
    @Temporal(TemporalType.TIME)
	private Date endTime;
    
    
    private double totalTime;
    
	
	
	@JsonIgnore
//	@JsonBackReference
	@ManyToOne
	private Modul module;
	
	@JsonIgnore
	@ManyToOne
	private Project project;
	
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	
	
	
}

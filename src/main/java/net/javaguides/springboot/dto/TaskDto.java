package net.javaguides.springboot.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

    private Long tid;
	
    private String taskName;
	
	private Date startDate;
	
	private Date EndDate;
	
	private String status;
	
	private String details;
	
    private Date startTime;
    
	private Date endTime;
	
    private double totalTime;

}

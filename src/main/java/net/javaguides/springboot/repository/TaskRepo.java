package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import net.javaguides.springboot.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

	
	List<Task> findBytaskNameContaining(String name);

}

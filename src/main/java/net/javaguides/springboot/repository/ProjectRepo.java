package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

	List<Project> findByprojectNameContaining(String name);

}

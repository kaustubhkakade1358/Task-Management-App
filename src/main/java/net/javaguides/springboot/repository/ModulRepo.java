package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Modul;
import net.javaguides.springboot.model.Project;

public interface ModulRepo extends JpaRepository<Modul, Long> {
	List<Modul> findBymoduleNameContaining(String name);

}

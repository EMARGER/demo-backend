package com.atul.demo.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atul.demo.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer>{

	@Query("select p from Project p")
	List<Project> findAllProject(Pageable pageable);
	
	@Query("select count(p) from Project p")
	long countAllProject();
}

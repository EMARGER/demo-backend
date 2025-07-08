package com.atul.demo.repo;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atul.demo.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Integer>{

	@Query("select c from Client c")
	List<Client> findAllClient(Pageable pageable);
	
	@Query("select count(c) from Client c")
	long countAllClient();
}

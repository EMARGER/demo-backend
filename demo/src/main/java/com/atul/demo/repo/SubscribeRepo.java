package com.atul.demo.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.atul.demo.entity.Subscribe;

public interface SubscribeRepo extends JpaRepository<Subscribe, Integer>{

	@Query("select s from Subscribe s where s.email= :email")
	Subscribe findByemail(@Param("email") String email);  
	
	
	@Query("select s from Subscribe s")
	List<Subscribe> findAllSubscribe(Pageable pageable);
	
	@Query("select count(s) from Subscribe s")
	long countAllSubscribe();
}

package com.atul.demo.repo;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atul.demo.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

	@Query("select c from Contact c where c.email= :email")
	Contact findByemail(@Param("email") String email);  
	
	@Query("select c from Contact c where c.phoneNumber= :phoneNumber")
	Contact findByPhonenumber(@Param("phoneNumber") String phoneNumber);
	
	@Query("select c from Contact c")
	List<Contact> findAllContact(Pageable pageable);
	
	@Query("select count(c) from Contact c")
	long countAllContact();
}

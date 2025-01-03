package com.DigitalDiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DigitalDiary.entity.applicationuser;

import java.util.List;



@Repository
public interface ApplicationRepo extends JpaRepository<applicationuser, Integer> {
	
	public applicationuser save(applicationuser user);
	
//	public List<applicationuser> findByUsername(String username);
	
	public applicationuser findByUsername(String username);
	
}

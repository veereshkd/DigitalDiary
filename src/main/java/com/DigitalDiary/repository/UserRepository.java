package com.DigitalDiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DigitalDiary.entity.applicationuser;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<applicationuser, Long> {

	public applicationuser findByUsername(String username);

	public applicationuser findByEmail(String email);
		
	@Modifying
	@Query("UPDATE applicationuser au SET au.photoencode = :photoencode WHERE au.username = :username")
	void setPhoto(@Param("photoencode") byte[] photoencode, @Param("username") String username);

	@Query("SELECT au.photoencode FROM applicationuser au WHERE au.username = :username")
	byte[] getPhotoByUsername(@Param("username") String username);


}

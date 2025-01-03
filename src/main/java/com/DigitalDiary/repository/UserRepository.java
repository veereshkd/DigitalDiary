package com.DigitalDiary.repository;

import org.eclipse.angus.mail.iap.ByteArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DigitalDiary.entity.applicationuser;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<applicationuser, Integer> {

	public applicationuser findByUsername(String username);

	public applicationuser findByEmail(String email);
		
	@Modifying
    @Transactional
	@Query("update applicationuser au set au.photoencode = :photoencode WHERE au.username = :username")
    void setPhoto(@Param("photoencode") byte[] photoencode, @Param("username") String username);
	
	@Query("SELECT photoencode from applicationuser au WHERE au.username = :username")
	public byte[] getPhotoByUsername(String username);

}

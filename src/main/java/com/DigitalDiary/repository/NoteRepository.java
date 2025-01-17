package com.DigitalDiary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.NoteDetails;

import jakarta.transaction.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<diarynote, Long> {
	
	public diarynote save(diarynote notedetails);

	@Query("SELECT dn FROM diarynote dn WHERE dn.username = ?1 AND dn.date = ?2")
	public diarynote getNoteByDate(String username, String date);
	
 	@Modifying
 	@Transactional
	@Query(value = "update diarynote dn set dn.image = CONCAT(dn.image, ',', ?4), dn.file = CONCAT(dn.file, ',', ?5), dn.note = ?3 where dn.username = ?1and dn.date = ?2")
	public int noteupdate(String username, String date, String note, String image, String file);

	@Modifying
	@Transactional
	@Query(value = "update diarynote dn set dn.note = ?3 where dn.username = ?1 and dn.date = ?2")	
	public int updateNoteWithoutFiles(String username, String date, String note);

	@Modifying
	@Transactional
	@Query(value = "update diarynote dn set dn.file = CONCAT(dn.file, ',', ?4), dn.note = ?3 where dn.username = ?1and dn.date = ?2")
	public int updateNoteWithFileOnly(String username, String date, String note, String file);

	@Modifying
	@Transactional
	@Query(value = "update diarynote dn set dn.image = CONCAT(dn.image, ',', ?4), dn.note = ?3 where dn.username = ?1 and dn.date = ?2")
	public int updateNoteWithImageOnly(String username, String date, String note, String image);

}

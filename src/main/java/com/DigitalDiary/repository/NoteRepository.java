package com.DigitalDiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.NoteDetails;

@Repository
public interface NoteRepository extends JpaRepository<diarynote, String> {
	
	public diarynote save(diarynote notedetails);

}

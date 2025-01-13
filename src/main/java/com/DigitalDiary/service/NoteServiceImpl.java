package com.DigitalDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.NoteDetails;
import com.DigitalDiary.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	NoteRepository noteRepository;
	

	@Override
	public diarynote notesave(NoteDetails notedetails) {
		
		diarynote note = new diarynote();
		
		note.setUsername(notedetails.getUsername());
		note.setDate(notedetails.getDate());
		note.setImage(notedetails.getImage());
		note.setNote(notedetails.getNote());
		note.setFile(notedetails.getFile());
		
		diarynote save = noteRepository.save(note);
		return save;
		
		
	}

}

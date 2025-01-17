package com.DigitalDiary.service;

import java.lang.System.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.GetNoteDetails;
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


	@Override
	public diarynote getnote(GetNoteDetails getnotedetails) {
		String username = getnotedetails.getUsername();
		String date = getnotedetails.getDate();
		try {
			System.out.println("Username :"+username+" & Date :"+date);
			diarynote noteDetails = noteRepository.getNoteByDate(username, date);
			return noteDetails;
		} catch (Exception e) {
			System.out.println("get note :"+e.getMessage());
			return null;
		}
		
	}


	@Override
	public diarynote getnotebydate(String username, String date) {
		try {
			System.out.println("Username :"+username+" & Date :"+date);
			diarynote noteByDate = noteRepository.getNoteByDate(username, date);
			
			return noteByDate;
		} catch (Exception e) {
			
			System.out.println("get note by date :"+e.getMessage());
			
			return null;
			
		}

	}


	@Override
	public int noteupdate(NoteDetails notedetails) {
		
		String username = notedetails.getUsername();
		String date = notedetails.getDate();
		String note = notedetails.getNote();
		String image = notedetails.getImage();
		String file = notedetails.getFile();
		int noteupdate;
		if (image == null && file == null) {
			System.out.println("inside if image and file");
			noteupdate = noteRepository.updateNoteWithoutFiles(username, date, note);
		} else if (image == null) {
			noteupdate = noteRepository.updateNoteWithFileOnly(username, date, note, file);
		} else if (file == null) {
			noteupdate = noteRepository.updateNoteWithImageOnly(username, date, note, image);
		} else {
			noteupdate = noteRepository.noteupdate(username,date,note,image,file);
		}
		return noteupdate;
	}

}

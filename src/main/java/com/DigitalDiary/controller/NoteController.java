package com.DigitalDiary.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.NoteDetails;
import com.DigitalDiary.service.NoteService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	private final static Logger logger = Logger.getLogger(UserController.class.toString());

	private final HttpServletRequest request;
	
	public NoteController(HttpServletRequest request) {
		this.request = request;
	}
	
	
	@PostMapping("/api/saveDiaryEntry")
	public String notesave(@RequestBody NoteDetails notedetails) {
		
		diarynote notesave = noteService.notesave(notedetails);
		if(notesave != null)
		return "Saved";
		else {
			return "Note saved";
		}
		
		
	}

}

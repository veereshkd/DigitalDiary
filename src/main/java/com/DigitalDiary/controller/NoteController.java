package com.DigitalDiary.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.GetNoteDetails;
import com.DigitalDiary.models.NoteDetails;
import com.DigitalDiary.models.NoteResponse;
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
	public ResponseEntity<String> notesave(@RequestBody NoteDetails notedetails) {
		try {
			String username = notedetails.getUsername();
			String date = notedetails.getDate();
			
			try {
				logger.info("Note Details :"+notedetails.toString());
				diarynote getnotebydate = noteService.getnotebydate(username, date);
				
				if(getnotebydate != null) {
					logger.info("Note Details inside if:"+getnotebydate.toString());
					int notesave = noteService.noteupdate(notedetails);
					return ResponseEntity.ok("Note updated successfully.");
				}
				else {
					diarynote notesave = noteService.notesave(notedetails);
					if(notesave != null) {
						return ResponseEntity.ok("Note saved successfully.");
					}
					else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the note.");
					}
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+" : failed to get note by date");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/api/getDiaryNote")
	public ResponseEntity<NoteResponse> getNote(@RequestParam String username,@RequestParam String date) {
		NoteResponse noteResponse = new NoteResponse();
		GetNoteDetails getNoteDetails = new GetNoteDetails();
		getNoteDetails.setUsername(username);
		getNoteDetails.setDate(date);
		diarynote getnote = noteService.getnote(getNoteDetails);
		if (getnote != null) {

			if(date.equals(getnote.getDate())) {
				noteResponse.setDate(getnote.getDate());
				noteResponse.setNote(getnote.getNote());
				noteResponse.setImage(getnote.getImage());
				noteResponse.setFile(getnote.getFile());
				return ResponseEntity.ok(noteResponse);
			}
		}
		return ResponseEntity.ok(noteResponse);
	}

}

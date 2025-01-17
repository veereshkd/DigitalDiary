package com.DigitalDiary.service;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.GetNoteDetails;
import com.DigitalDiary.models.NoteDetails;

public interface NoteService {
	
	public diarynote notesave(NoteDetails notedetails);
	
	public int noteupdate(NoteDetails notedetails);

	public diarynote getnote(GetNoteDetails getnotedetails);
	
	public diarynote getnotebydate(String username, String date);

}

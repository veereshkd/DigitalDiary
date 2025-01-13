package com.DigitalDiary.service;

import com.DigitalDiary.entity.diarynote;
import com.DigitalDiary.models.NoteDetails;

public interface NoteService {
	
	public diarynote notesave(NoteDetails notedetails);

}

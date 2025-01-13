package com.DigitalDiary.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class diarynote {
	
	@Id
	@Nonnull
	@Column(name = "username")
	private String username;
	
	@Nonnull
	@Column(name = "date")
	private String date;
	
	@Nonnull
	@Column(name = "note")
	private String note;
	
	@Nonnull
	@Column(name = "image")
	private String image;
	
	@Nonnull
	@Column(name = "file")
	private String file;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	

}

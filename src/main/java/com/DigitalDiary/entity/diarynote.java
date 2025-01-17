package com.DigitalDiary.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "diarynote")
public class diarynote {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Nonnull
	@Column(name = "username")
	private String username;
	
	@Nonnull
	@Column(name = "date")
	private String date;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "image")
	private String image;
	
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

	@Override
	public String toString() {
		return "diarynote [id=" + id + ", username=" + username + ", date=" + date + ", note=" + note + ", image="
				+ image + ", file=" + file + "]";
	}
	
	

}

package com.DigitalDiary.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;


import org.springframework.security.core.GrantedAuthority;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class applicationuser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Nonnull
	@Column(name = "firstname")
	private String firstname;
	
	@Nonnull
	@Column(name = "lastname")
	private String lastname;
	
	@Nonnull
	@Column(name = "issuperuser")
	private boolean issuperuser;
	
	@Nonnull
	@Column(name = "username")
	private String username;
	
	@Nonnull
	@Column(name = "email")
	private String email;
	
	@Nonnull
	@Column(name = "password")
	private String password;
	
	@Column(name = "photoencode")
	private byte[] photoencode;
	
	@Nonnull
	@Column(name = "phone_number")
	private long phone_number;
	
	@Nonnull
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dob")
	private String dob;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "entry_date_time")
	private Date entryDateTime;
	
	@Column(name = "ipaddress")
	private String ipaddress;
		


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public boolean isIssuperuser() {
		return issuperuser;
	}


	public void setIssuperuser(boolean issuperuser) {
		this.issuperuser = issuperuser;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public byte[] getPhotoencode() {
		return photoencode;
	}


	public void setPhotoencode(byte[] photoencode) {
		this.photoencode = photoencode;
	}


	public long getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public Date getEntryDateTime() {
		return entryDateTime;
	}


	public void setEntryDateTime(Date entryDateTime) {
		this.entryDateTime = entryDateTime;
	}


	public String getIpaddress() {
		return ipaddress;
	}


	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {
		return "applicationuser [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", issuperuser="
				+ issuperuser + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", photoencode=" + Arrays.toString(photoencode) + ", phone_number=" + phone_number + ", dob=" + dob
				+ ", entryDateTime=" + entryDateTime + ", ipaddress=" + ipaddress + "]";
	}
	




	
	
}

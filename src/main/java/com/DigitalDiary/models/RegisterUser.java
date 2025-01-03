package com.DigitalDiary.models;


import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class RegisterUser {
	
	@NonNull
	@JsonProperty("username")
	private String username;
	
	@NonNull
	@JsonProperty("email")
	private String email;
	
	@NonNull
	@JsonProperty("password")
	private String password;
	
	@NonNull
	@JsonProperty("phone_number")
	private long phone_number;
	
	@NonNull
	@JsonProperty("otp")
	private Integer otp;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@JsonProperty("dob")
	private String dob;

	@NonNull
	@JsonProperty("firstname")
	private String firstname;
	
	@NonNull
	@JsonProperty("lastname")
	private String lastname;
	
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
	

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "RegisterUser [username=" + username + ", email=" + email + ", password=" + password + ", phone_number="
				+ phone_number + ", otp=" + otp + ", dob=" + dob + ", firstname=" + firstname + ", lastname=" + lastname
				+ "]";
	}


}

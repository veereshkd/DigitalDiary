package com.DigitalDiary.models;

public class LoginResponse {
	
	private String status;
	private String statuscode;
	private String errormessage;
	private String datetime;
	private JwtResponse jwtresponse;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public JwtResponse getJwtresponse() {
		return jwtresponse;
	}
	public void setJwtresponse(JwtResponse jwtresponse) {
		this.jwtresponse = jwtresponse;
	}
	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", statuscode=" + statuscode + ", errormessage=" + errormessage
				+ ", datetime=" + datetime + ", jwtresponse=" + jwtresponse + "]";
	}
	
	
	

}

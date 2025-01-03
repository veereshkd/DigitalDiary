package com.DigitalDiary.models;

import java.util.Objects;

public class RegisterResponse {
	
	private String status;
	private String statuscode;
	private String messsage;
	private String errormessage;
	private String datetime;
	
	
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
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
	public String getMesssage() {
		return messsage;
	}
	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	@Override
	public int hashCode() {
		return Objects.hash(datetime, errormessage, messsage, status, statuscode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterResponse other = (RegisterResponse) obj;
		return Objects.equals(datetime, other.datetime) && Objects.equals(errormessage, other.errormessage)
				&& Objects.equals(messsage, other.messsage) && Objects.equals(status, other.status)
				&& Objects.equals(statuscode, other.statuscode);
	}
	
	

}

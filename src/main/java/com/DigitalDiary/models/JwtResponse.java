package com.DigitalDiary.models;

import java.util.Date;

public class JwtResponse {
	
	private String token;
	private Date tokenExpireTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getTokenExpireTime() {
		return tokenExpireTime;
	}
	public void setTokenExpireTime(Date tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}
	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", tokenExpireTime=" + tokenExpireTime + "]";
	}
	
	

}

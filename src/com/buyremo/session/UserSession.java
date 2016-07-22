package com.buyremo.session;

import java.io.Serializable;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 614203144845463226L;

	private long id;
	
	private String userName;

	private String emailId;
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private long userId;
	
	//private String FBtoken;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public String getFBtoken() {
		return FBtoken;
	}

	public void setFBtoken(String fBtoken) {
		FBtoken = fBtoken;
	}*/
	
	
}

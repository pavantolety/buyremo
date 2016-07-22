package com.buyremo.controller;

import com.buyremo.model.Login;
import com.buyremo.model.User;
import com.buyremo.session.UserSession;

public class AuthenticationHelper {

	public static UserSession populateUserSession(Login login,User user){

		UserSession userSession=new UserSession();
		userSession.setUserId(user.getId());
		userSession.setEmailId(login.getLoginId());
		userSession.setUserName(user.getUserName());
		return userSession;
	}
	
	
}

package com.buyremo.util;

import org.apache.commons.lang3.RandomStringUtils;

public class AuthenticationUtils {

	public static String getPasswordForUser(){

		return (RandomStringUtils.random(4,true, false)+RandomStringUtils.random(4,false, true));

	}

	public static String generateTokenForAuthentication(){
		String token = RandomStringUtils.random(10, true, true);
		return System.currentTimeMillis()+token;
	}
}

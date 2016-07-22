package com.buyremo.util;

import javax.servlet.http.HttpServletRequest;

public class ServerUtils {

	public static String getServerURL(HttpServletRequest request){

		StringBuilder sb=new StringBuilder();

		sb.append(request.getScheme());

		sb.append("://");

		sb.append(request.getServerName());

		sb.append(":");

		sb.append(request.getServerPort());

		sb.append("/");

		sb.append(request.getContextPath());

		return sb.toString();

	}
}

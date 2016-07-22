package com.buyremo.util.log;

import org.apache.log4j.Logger;

public class LogUtil {

	private Logger log;

	private String className;

	public LogUtil(Logger log,String className){
		this.log=log;
		this.className=className;
	}

	public void debug(String methodName,String message){
		if(log.isDebugEnabled()){
			log.debug(getLogMessage(methodName, message));
		}
	}


	public void info(String methodName,String message){
		if(log.isInfoEnabled()){
			log.info(getLogMessage(methodName, message));
		}
	}

	public void error(String methodName,String message){
		if(log.isInfoEnabled()){
			log.info(getLogMessage(methodName, message));
		}
	}

	private String getLogMessage(String methodName,String message){
		StringBuffer sb=new StringBuffer();
		sb.append(className);
		sb.append("|");
		sb.append(methodName);
		sb.append("|");
		sb.append(message);
		return sb.toString();
	}
}

package com.buyremo.config;

public interface SystemParams {

	//public static final String BASEURL = "http://localhost:8080/buyremo/";
	
	public static final String BASEURL = "http://ec2-52-24-36-161.us-west-2.compute.amazonaws.com/buyremo/";

	public static final String FROMADDRESS = "edvenswatech@gmail.com";
	
	public static final String PASSWORD = "Edvenswa2015";
	
	public static final String TEMPLATES_PATH = "com/buyremo/mail/templates/";
    
	public static final String PAYPAL_USERNAME = "edvenswatech-facilitator_api1.gmail.com";
	 
	public static final String PAYPAL_SIGNATURE = "AFcWxV21C7fd0v3bYYYRCpSSRl31A387ZmG6tWIJ7kC1t2XgupCyMrCY";
	 
	public static final String PAYPAL_PASSWORD = "1398929127";
   
	public static final String PAYPAL_APP_ID = "APP-80W284485P519543T";
	
	public static final String PAYPAL_MODE = "sandbox";
	
	public static final String PAYPAL_USERNAME2 = "syam2011-facilitator_api1.gmail.com";	
	
	public static final String PAYPAL_SIGNATURE2 = "Aahf8y7YR-LBYuiRl59lGsj-rakJA1cs8epUlHGGk5v8Xp3gellA5C3E";
	
	public static final String PAYPAL_PASSWORD2 = "YWV54TJJXXVB4JKD";
	
	public static final int COLLAB_EVENTS_PAYPAL_ID = 1; //This is a sample account for paypal.
	
	public static final String DEFAULT_SUBSCRIPTIONID = "5145363304415232";
	
	
	// Begin -> SMTP Parameters - Email Notifications
	public static final String EMAIL_PROTOCOL = "smtp";
	public static final String EMAIL_AUTH_REQUIRED = "true";
	public static final String EMAIL_TTLS_ENABLED = "true";
	public static final String EMAIL_SMTP_HOST = "smtp.gmail.com";
	public static final String EMAIL_SMTP_PORT = "587";
	// End -> SMTP Parameters - Email Notifications
	
}

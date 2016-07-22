package com.buyremo.util.paypal;

import java.util.Properties;

public class PaypalProperties {

	public static Properties getPaypalProperties(){

		Properties prop=new Properties();
		//# Connection Information
		prop.put("http.ConnectionTimeOut","5000");
		prop.put("http.Retry","1");
		prop.put("http.ReadTimeOut","30000");
		prop.put("http.MaxConnection","100");

		//# HTTP Proxy configuration
		//# If you are using proxy set http.UseProxy to true and replace the following values with your proxy parameters
		prop.put("http.ProxyPort","8080");
		prop.put("http.ProxyHost","127.0.0.1");
		prop.put("http.UseProxy","false");
		prop.put("http.ProxyUserName","");
		prop.put("http.ProxyPassword","");

		//#Set this property to true if you are using the PayPal SDK within a Google App Engine java app
		prop.put("http.GoogleAppEngine","true");

		//# Service Configuration
		prop.put("service.EndPoint","https://api.sandbox.paypal.com");

		//# Live EndPoint
		//prop.put("service.EndPoint","https://api.paypal.com");

		//# Credentials
		prop.put("clientID","AfAmNRDzzOG23wcip4dFZfjboMqL3i1b8kgUg5ZoUvp8v-DIx7eKJodn6tMW");
		prop.put("clientSecret","EMOvVxAg7I8LYDQ32DvG9vTkFCM0RFTDVYe1Jourygo3wmA90EMCdg46RM1t");

		return prop;
	}
}

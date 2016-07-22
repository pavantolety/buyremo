package com.buyremo.util.paypal;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.paypal.core.ConfigManager;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;

public class GenerateAccessToken {

	private static Logger log=Logger.getLogger(GenerateAccessToken.class);

	public static String getAccessToken() throws PayPalRESTException {

		ConfigManager.getInstance().load(PaypalProperties.getPaypalProperties());

		String clientID = ConfigManager.getInstance().getValue("clientID");

		log.debug("GenearateAccessToken|getAccessToken|ClientID : "+clientID);
		String clientSecret = ConfigManager.getInstance().getValue(
				"clientSecret");

		log.debug("GenearateAccessToken|getAccessToken|ClientSecret : "+clientSecret);

		String accessToken=new OAuthTokenCredential(clientID, clientSecret)
		.getAccessToken();

		log.debug("GenearateAccessToken|getAccessToken|accessToken : "+accessToken);

		return accessToken;
	}

	public static String getAccessToken(String clientIdStr,String clientSecretStr) throws PayPalRESTException {

		Properties prop=PaypalProperties.getPaypalProperties();

		//# Credentials
		prop.put("clientID",clientIdStr);
		prop.put("clientSecret",clientSecretStr);

		ConfigManager.getInstance().load(prop);

		String clientID = ConfigManager.getInstance().getValue("clientID");

		log.debug("GenearateAccessToken|getAccessToken|ClientID : "+clientID);
		String clientSecret = ConfigManager.getInstance().getValue(
				"clientSecret");

		log.debug("GenearateAccessToken|getAccessToken|ClientSecret : "+clientSecret);

		String accessToken=new OAuthTokenCredential(clientID, clientSecret)
		.getAccessToken();

		log.debug("GenearateAccessToken|getAccessToken|accessToken : "+accessToken);

		return accessToken;
	}
}

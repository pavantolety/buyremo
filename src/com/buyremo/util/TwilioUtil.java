package com.buyremo.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.common.io.BaseEncoding;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;

public final class TwilioUtil {

	private TwilioUtil() {
	}

	public static final String ACCOUNT_SID = "ACccd540ba47fdf2e973887e4bb7d1869d";

	public static final String AUTH_TOKEN = "426291535f064ca4e821212294dee2f1";

	public static final String TWILIO_BASE_URL = "https://api.twilio.com/2010-04-01";

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String FROM = "+17322534187";

	// Accounts/{AccountSid}/Messages
	private static String getMessageURL() {
		StringBuffer sb = new StringBuffer();
		sb.append(TWILIO_BASE_URL);
		sb.append("/Accounts/");
		sb.append(ACCOUNT_SID);
		sb.append("/Messages.json");
		return sb.toString();
	}

	private static String getRequestParams(String to, String body) {

		StringBuffer sb = new StringBuffer();

		sb.append("From=");
		sb.append(FROM);
		sb.append("&To=");
		sb.append(to);
		sb.append("&Body=");
		sb.append(body);

		return sb.toString();
	}

	public static String getAuthorization() {

		StringBuffer sb = new StringBuffer();
		sb.append(ACCOUNT_SID);
		sb.append(":");
		sb.append(AUTH_TOKEN);
		return sb.toString();
	}

	public static void sendSMSPost(String to, String body) throws Exception {

		String url = getMessageURL();

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		String encoding = BaseEncoding.base64().encode(
				getAuthorization().getBytes());
		con.setRequestProperty("Authorization", "Basic " + encoding);

		String urlParameters = getRequestParams(to, body);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	public static void sendSMSTwilio(String to, String body) {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID,
				AUTH_TOKEN);
		Map<String, String> params = new HashMap<String, String>();
		params.put("Body", body);
		params.put("To", to);
		params.put("From", FROM);
		SmsFactory messageFactory = client.getAccount().getSmsFactory();
		try {
			Sms message = messageFactory.create(params);
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}
	}
}

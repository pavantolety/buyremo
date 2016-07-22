package com.buyremo.util.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.ClientDetailsType;
import com.paypal.svcs.types.common.RequestEnvelope;

public class PaypalAdaptivePmtutil {

	private static final int RECEIVERS=1;

	public static Map<String,Object> payment(com.buyremo.model.PaymentRequest paymentReq){

		Map<String,Object> pmtInfo=new HashMap<String, Object>();

		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");

		PayRequest payReq = new PayRequest();

		//Currency Code
		payReq.setCurrencyCode("USD"); //Commented By Malik to generate currency code Dynamically.
		//payReq.setCurrencyCode(paymentReq.getCurrencyCode());
		

		//Set Receivers - Event
		List<Receiver> receivers = new ArrayList<Receiver>(RECEIVERS);

		Receiver receiver=populateReceiver(paymentReq);

		receivers.add(receiver);

		ReceiverList receiverlst = new ReceiverList(receivers);
		payReq.setReceiverList(receiverlst);
		payReq.setRequestEnvelope(requestEnvelope);


		//Set Client Details
		ClientDetailsType clientDetails=populateClientDetails(paymentReq);
		payReq.setClientDetails(clientDetails);

		//Set IPN Url
		payReq.setIpnNotificationUrl(paymentReq.getIpnURL());

		//Set Tracking ID //TODO: Set Tracking ID
		payReq.setTrackingId(paymentReq.getTransactionId());

		//Set Action Type
		payReq.setActionType("PAY");

		//Set Cancel and Return URL
		payReq.setCancelUrl(paymentReq.getCancelURL());
		payReq.setReturnUrl(paymentReq.getRedirectURL());

		// Configuration map containing signature credentials and other required configuration.
		// For a full list of configuration parameters refer in wiki page
		// (https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters)
		Map<String,String> configurationMap =  Configuration.getAcctAndConfig();

		// Creating service wrapper object to make an API call by loading configuration map.
		AdaptivePaymentsService service = new AdaptivePaymentsService(configurationMap);


		try {
			PayResponse resp = service.pay(payReq);

			if (resp != null) {
				pmtInfo.put("RESPONSE_OBJECT", resp);
				pmtInfo.put("lastReq", service.getLastRequest());
				pmtInfo.put("lastResp", service.getLastResponse());

				if (resp.getResponseEnvelope().getAck().toString()
						.equalsIgnoreCase("SUCCESS")) {

					pmtInfo.put("Ack", resp.getResponseEnvelope().getAck());

					/**
					 * Correlation identifier. It is a 13-character, alphanumeric string
						  (for example, db87c705a910e) that is used only by PayPal Merchant Technical Support.
							Note: You must log and store this data for every response you receive.
							PayPal Technical Support uses the information to assist with reported issues.
					 */
					pmtInfo.put("Correlation ID", resp.getResponseEnvelope().getCorrelationId());

					/**
					 * Date on which the response was sent, for example: 2012-04-02T22:33:35.774-07:00
						   Note: You must log and store this data for every response you receive.
						   PayPal Technical Support uses the information to assist with reported issues.
					 */
					pmtInfo.put("Time Stamp", resp.getResponseEnvelope().getTimestamp());

					/**
					 * The pay key, which is a token you use in other Adaptive Payment APIs
					 * (such as the Refund Method) to identify this payment.
					 * The pay key is valid for 3 hours; the payment must be approved while the
					 * pay key is valid.
					 */
					pmtInfo.put("Pay Key", resp.getPayKey());

					/**
					 * The status of the payment. Possible values are:
					    CREATED  The payment request was received; funds will be transferred once the payment is approved
					    COMPLETED The payment was successful
					    INCOMPLETE Some transfers succeeded and some failed for a parallel payment or, for a delayed chained payment, secondary receivers have not been paid
					    ERROR The payment failed and all attempted transfers failed or all completed transfers were successfully reversed
					    REVERSALERROR  One or more transfers failed when attempting to reverse a payment
					    PROCESSING The payment is in progress
					    PENDING The payment is awaiting processing
					 */
					pmtInfo.put("Payment Execution Status",resp.getPaymentExecStatus());

					if (resp.getDefaultFundingPlan() != null){
						/** Default funding plan.  */
						pmtInfo.put("Default Funding Plan", resp.getDefaultFundingPlan().getFundingPlanId());
					}

					pmtInfo.put("redirectURL",
							"https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey="
									+ resp.getPayKey());
				} else {
					pmtInfo.put("Error", resp.getError());
				}
			}
		} catch (SSLConfigurationException e) {

			e.printStackTrace();
		} catch (InvalidCredentialException e) {

			e.printStackTrace();
		} catch (HttpErrorException e) {

			e.printStackTrace();
		} catch (InvalidResponseDataException e) {

			e.printStackTrace();
		} catch (ClientActionRequiredException e) {

			e.printStackTrace();
		} catch (MissingCredentialException e) {

			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return pmtInfo;
	}

	private static Receiver populateReceiver(com.buyremo.model.PaymentRequest paymentReq){

		Receiver receiver=new Receiver();

		receiver.setAmount(NumberUtils.toDouble(paymentReq.getAmount()));

		receiver.setEmail(paymentReq.getReceiverEmail());

		//receiver.setPrimary(true);

		return receiver;
	}

	private static ClientDetailsType populateClientDetails(com.buyremo.model.PaymentRequest paymentReq){

		ClientDetailsType clientDetails=new ClientDetailsType();

		clientDetails.setApplicationId(paymentReq.getEventName());

		//TODO: FETCH OTHER PARAMETERS

		return clientDetails;
	}
}

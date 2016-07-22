package com.buyremo.util.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.buyremo.model.PaymentResponse;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;


public class PaypalUtil {

	private static Logger log=Logger.getLogger(PaypalUtil.class);

	public static Map<String,Object> payment(com.buyremo.model.PaymentRequest paymentReq) {

		Map<String,Object> paymentInfo=new HashMap<String,Object>();
		APIContext apiContext = null;
		String accessToken = null;
		try {
			accessToken = GenerateAccessToken.getAccessToken(paymentReq.getClientId(),paymentReq.getClientSecret());

			// ### Api Context
			// Pass in a `ApiContext` object to authenticate
			// the call and to send a unique request id
			// (that ensures idempotency). The SDK generates
			// a request id if you do not pass one explicitly.
			apiContext = new APIContext(accessToken);
			// Use this variant if you want to pass in a request id
			// that is meaningful in your application, ideally
			// a order id.
			//String requestId = paymentReq.getTransactionId();
			//apiContext = new APIContext(accessToken, requestId );

		} catch (PayPalRESTException e) {
			paymentInfo.put("error", e.getMessage());
			log.error(e);
		}


		// ###Details
		// Let's you specify details of a payment amount.
		Details details = new Details();
		details.setShipping("0");
		details.setSubtotal(paymentReq.getAmount());
		details.setTax("0");

		// ###Amount
		// Let's you specify a payment amount.
		Amount amount = new Amount();
		amount.setCurrency("USD"); //Commented By Malik to generate currency code Dynamically.
		//amount.setCurrency(paymentReq.getCurrencyCode());
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(paymentReq.getAmount());
		amount.setDetails(details);

		// ###Transaction
		// A transaction defines the contract of a
		// payment - what is the payment for and who
		// is fulfilling it. Transaction is created with
		// a `Payee` and `Amount` types
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(paymentReq.getDescription());


		// The Payment creation API requires a list of
		// Transaction; add the created `Transaction`
		// to a List
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		// ###Payer
		// A resource representing a Payer that funds a payment
		// Payment Method
		// as 'paypal'
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// ###Payment
		// A Payment Resource; create one using
		// the above types and intent as 'sale'
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		// ###Redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(paymentReq.getRedirectURL()+"?guid=" + paymentReq.getTransactionId());
		redirectUrls.setReturnUrl(paymentReq.getRedirectURL()+"?guid=" + paymentReq.getTransactionId());
		payment.setRedirectUrls(redirectUrls);

		// Create a payment by posting to the APIService
		// using a valid AccessToken
		// The return object contains the status;
		try {
			Payment createdPayment = payment.create(apiContext);
			// ### Map Required Attributes

			paymentInfo.put("paymentId",createdPayment.getId());

			paymentInfo.put("transactionId", paymentReq.getTransactionId());

			// ###Payment Approval Url
			Iterator<Links> links = createdPayment.getLinks().iterator();
			while (links.hasNext()) {
				Links link = links.next();
				if (link.getRel().equalsIgnoreCase("approval_url")) {
					paymentInfo.put("redirectURL", link.getHref());
				}
			}
			paymentInfo.put("response", Payment.getLastResponse());
			//map.put(guid, createdPayment.getId());
		} catch (PayPalRESTException e) {
			paymentInfo.put("error", e.getMessage());
			log.error(e);
		}
		//}
		paymentInfo.put("request", Payment.getLastRequest());

		//req.getRequestDispatcher("response.jsp").forward(req, resp);
		return paymentInfo;
	}


	public static Map<String,Object> paymentResponse(PaymentResponse paymentResp){

		Map<String,Object> paymentInfo=new HashMap<String,Object>();
		APIContext apiContext = null;
		String accessToken = null;
		try {
			accessToken = GenerateAccessToken.getAccessToken();

			// ### Api Context
			// Pass in a `ApiContext` object to authenticate
			// the call and to send a unique request id
			// (that ensures idempotency). The SDK generates
			// a request id if you do not pass one explicitly.
			apiContext = new APIContext(accessToken,paymentResp.getGuid());

			Payment payment = Payment.get(accessToken, paymentResp.getPaymentId());

			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(paymentResp.getPayerID());
			try {
				Payment newPayment=payment.execute(apiContext, paymentExecution);
				paymentInfo.put("response",newPayment.getLastResponse());
				paymentInfo.put("paypalStatus",newPayment.getState());
			} catch (PayPalRESTException e) {
				paymentInfo.put("error", e.getMessage());
				log.error(e);
			}

		} catch (PayPalRESTException e) {
			paymentInfo.put("error", e.getMessage());
			log.error(e);
		}

		return paymentInfo;
	}
}

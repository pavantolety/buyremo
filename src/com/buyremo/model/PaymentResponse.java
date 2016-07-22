package com.buyremo.model;

public class PaymentResponse {

	private String PayerID;
	private String guid;
	private String token;
	private String paymentId;

	public String getPayerID() {
		return PayerID;
	}

	public void setPayerID(String payerID) {
		PayerID = payerID;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentResponse [PayerID=");
		builder.append(PayerID);
		builder.append(", guid=");
		builder.append(guid);
		builder.append(", token=");
		builder.append(token);
		builder.append(", paymentId=");
		builder.append(paymentId);
		builder.append("]");
		return builder.toString();
	}

}

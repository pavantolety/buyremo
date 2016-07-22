package com.buyremo.model;

import java.util.Set;

public class Invitation {
	
	private long userId;
	
	private String subject;

	private String message;
	
	private String secretKey;
	
	private Set<String> inviteeEmails;
	
	private long senderId;
	
	private String senderEmail;
	
	private String dependantName;
	
	private String dependantEmail;
	
	private long dependantId;
	

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the emailAddress
	 */
	public Set<String> getInviteeEmails() {
		return inviteeEmails;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setInviteeEmails(Set<String> inviteeEmails) {
		this.inviteeEmails = inviteeEmails;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the dependantEmail
	 */
	public String getDependantEmail() {
		return dependantEmail;
	}

	/**
	 * @param dependantEmail the dependantEmail to set
	 */
	public void setDependantEmail(String dependantEmail) {
		this.dependantEmail = dependantEmail;
	}

	/**
	 * @return the senderEmail
	 */
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * @param senderEmail the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * @return the senderId
	 */
	public long getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the dependantName
	 */
	public String getDependantName() {
		return dependantName;
	}

	/**
	 * @param dependantName the dependantName to set
	 */
	public void setDependantName(String dependantName) {
		this.dependantName = dependantName;
	}

	/**
	 * @return the dependantId
	 */
	public long getDependantId() {
		return dependantId;
	}

	/**
	 * @param dependantId the dependantId to set
	 */
	public void setDependantId(long dependantId) {
		this.dependantId = dependantId;
	}
}

package com.buyremo.util;

public interface Mailer {

	public void sendMail(final String to, final String subject,
			final String body);
}

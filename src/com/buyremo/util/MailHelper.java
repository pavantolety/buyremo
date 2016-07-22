package com.buyremo.util;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.buyremo.config.SystemParams;
import com.buyremo.dao.UserDAO;
import com.buyremo.model.User;

public class MailHelper {
	
	@Autowired
	UserDAO userDAO;

	private static final Logger log = Logger.getLogger(MailHelper.class.getName());

	static MailHelper s_singleton = new MailHelper();
	
	

	public static MailHelper getInstance() {
		return s_singleton;
	}
	
	public static Properties getProperties(){
		log.debug("Begin->getProperties");
		
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", SystemParams.EMAIL_PROTOCOL);
		prop.put("mail.smtp.auth", SystemParams.EMAIL_AUTH_REQUIRED);
		prop.put("mail.smtp.starttls.enable", SystemParams.EMAIL_TTLS_ENABLED);
		//prop.put("mail.smtp.host", "smtp.mail.yahoo.com");
		 prop.put("mail.smtp.host", SystemParams.EMAIL_SMTP_HOST);
		//prop.put("mail.smtp.host", "smtp.mail.com");
		prop.put("mail.smtp.port", SystemParams.EMAIL_SMTP_PORT);
		
		log.debug("Bebin->getProperties");
		
		return prop;
		
	}
	
	public void sendActivateAccountMail(String toEmail, User user)
			throws ResourceNotFoundException, ParseErrorException, Exception {

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		VelocityContext context = new VelocityContext();
		
		String activateAccountURL = SystemParams.BASEURL
				+ "activateUser?token=" + user.getToken();
		context.put("activateAccountURL", activateAccountURL);

		Template t = ve.getTemplate(SystemParams.TEMPLATES_PATH
				+ "SendActivationEmail.vm");
		StringWriter writer = new StringWriter();

		t.merge(context, writer);

		Session session = Session.getInstance(MailHelper.getProperties(),
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								SystemParams.FROMADDRESS,
								SystemParams.PASSWORD);
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SystemParams.FROMADDRESS));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject("Activate Account");
			message.setContent(writer.toString(), "text/html;charset=utf-8");

			Transport.send(message);

			log.debug("Mail sent Sucessfully");

		} catch (MessagingException me) {
			me.printStackTrace();
		}
		log.debug("End->sendInvitationEmail");

	}
	
	public void sendDependantActivationMail(String toEmail, User user)
			throws ResourceNotFoundException, ParseErrorException, Exception {

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		VelocityContext context = new VelocityContext();
		
		String activateAccountURL = SystemParams.BASEURL
				+ "activateDependant?token=" + user.getToken();
		context.put("activateAccountURL", activateAccountURL);

		Template t = ve.getTemplate(SystemParams.TEMPLATES_PATH
				+ "SendActivationEmail.vm");
		StringWriter writer = new StringWriter();

		t.merge(context, writer);

		Session session = Session.getInstance(MailHelper.getProperties(),
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								SystemParams.FROMADDRESS,
								SystemParams.PASSWORD);
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SystemParams.FROMADDRESS));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject("Activate Account");
			message.setContent(writer.toString(), "text/html;charset=utf-8");

			Transport.send(message);

			log.debug("Mail sent Sucessfully");

		} catch (MessagingException me) {
			me.printStackTrace();
		}
		log.debug("End->sendInvitationEmail");

	}
	
	@Async
	public void sendActivationSuccessNotification(User user) throws Exception {
		
		log.debug("Begin->sendActivationSuccessNotification");
			  String subject = "[BuyRemo] Account activated successfully";

			  // String baseURL = "http://localhost:8888/";
			  String activateURL = SystemParams.BASEURL + "/login" ;
			  VelocityEngine ve = new VelocityEngine();
				ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
				ve.setProperty("classpath.resource.loader.class",ClasspathResourceLoader.class.getName());
				ve.init();
				VelocityContext context = new VelocityContext();
				context.put("userEmail",user.getEmailId());
				context.put("userName",user.getUserName());
				context.put("activateURL",activateURL );
				
				
				Template t = ve.getTemplate(SystemParams.TEMPLATES_PATH + "SendActivationSuccessNotification.vm");
				StringWriter writer = new StringWriter();

				t.merge(context, writer);

				Session session = Session.getInstance(MailHelper.getProperties(),new javax.mail.Authenticator() {
		         
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(SystemParams.FROMADDRESS, SystemParams.PASSWORD);
							}
						});
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(SystemParams.FROMADDRESS));
					message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(user.getEmailId()));
					message.setSubject(subject);
					message.setContent(writer.toString(), "text/html");

					Transport.send(message);

					System.out.println("Mail sent Sucessfully");

				} catch (MessagingException me) {
					me.printStackTrace();
				}
				log.debug("End->sendActivationSuccessNotification");				
					  
	 }
	
	@Async
	public void sendInvitationEmail(long senderId,String senderEmail, String email, String userName) throws Exception {
		log.debug("Begin->sendInvitationEmail");
		
		String subject = "[BuyRemo] Invitation to the join BuyRemo";
		String respondURL = SystemParams.BASEURL + "respondToInvitation/"+email+"/"+senderId;
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",ClasspathResourceLoader.class.getName());
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put("senderId", senderId);
		context.put("senderEmail", senderEmail);
		context.put("name", email);
		context.put("email", email);
		context.put("userName", userName);
		context.put("respondURL", respondURL);
		//context.put("baseURL", SystemParams.BASEURL+"img/");
		//context.put("logo", "CollabEvents.png");
		
		Template t = ve.getTemplate(SystemParams.TEMPLATES_PATH + "SendInvitationEmail.vm");
		StringWriter writer = new StringWriter();

		t.merge(context, writer);

		Session session = Session.getInstance(MailHelper.getProperties(),new javax.mail.Authenticator() {
         
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(SystemParams.FROMADDRESS, SystemParams.PASSWORD);
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SystemParams.FROMADDRESS));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject(subject);
			message.setContent(writer.toString(), "text/html;charset=utf-8");

			Transport.send(message);

			System.out.println("Mail sent Sucessfully");

		} catch (MessagingException me) {
			me.printStackTrace();
		}
		log.debug("End->sendInvitationEmail");		
	}

}

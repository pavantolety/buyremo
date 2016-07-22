package com.buyremo.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buyremo.constraints.PageView;
import com.buyremo.controller.AuthenticationController;
import com.buyremo.controller.InvitationController;
import com.buyremo.dao.InvitationDAO;
import com.buyremo.dao.UserDAO;
import com.buyremo.model.Invitation;
import com.buyremo.model.User;
import com.buyremo.session.UserSession;
import com.buyremo.util.MailHelper;

@Controller
public class InvitationController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	InvitationDAO invitationDAO;
	
	@Autowired
	AuthenticationController authenticationController;

	private static Logger log=Logger.getLogger(InvitationController.class);
	
	@RequestMapping(value = "/inviteDependant", method = RequestMethod.POST)
	private String inviteDependant(ModelMap modelMap, Invitation invitation, HttpSession session, BindingResult result) {

		UserSession userSession=(UserSession)session.getAttribute("userSession");
		if(userSession!=null){
		
		User userDTO1 = userDAO.getUser(userSession.getUserId());
		
		long senderId = userDTO1.getId();
		
		String senderEmail = userDTO1.getEmailId();
		
		String userName = userDTO1.getUserName();
				
		MailHelper mailer = MailHelper.getInstance(); 
		
		Set<String> emails = new HashSet<String>();
		
		if (invitation.getInviteeEmails().size()>0) {			
							
				emails = invitation.getInviteeEmails();
					
				List<String> emailsList = null;
				
				Iterator<String> iterator =  emails.iterator();
				while(iterator.hasNext()) {			
					
					String allMails = iterator.next();
					emailsList = Arrays.asList(allMails.split(","));
					emails.addAll(emailsList);
						
					}
				
				boolean isInserted = invitationDAO.insertInvitationMails(emails,userSession.getEmailId(), userSession.getUserId());
				
						Iterator<String> iterator1 = emails.iterator();
						
						while(iterator1.hasNext()) {
							
							String email = iterator1.next();
							try {
								mailer.sendInvitationEmail(senderId,senderEmail,email,userName);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							
						}		
						
				} else {
			modelMap.put("status", "failure");
			modelMap.put("message","Could not process invitation");
		}

		return "";
	}else{
			return PageView.SIGNIN;
		}
	}
	
	@RequestMapping(value = "/respondToInvitation/{email}/{id}", method = RequestMethod.GET)
	public String respondToInvitationByEventCode(ModelMap modelMap,@PathVariable String id,@PathVariable String email){ 
		
		log.debug("Begin->respondToInvitationByEventCode::");

		User user = userDAO.getUserByEmail(email);

		if (user!= null && user.getId() != 0) {
			
			modelMap.put("status", "failure");
			modelMap.put("message", "The Email you entered is already registered!");
			
		} else {
			user = new User();
			user.setEmailId(email);
			user.setId(NumberUtils.toLong(id));
		}
		modelMap.put("user", user);
		return PageView.DEPENDANTSIGNUP;
	  }
	
}

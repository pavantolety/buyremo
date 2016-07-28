package com.buyremo.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buyremo.constraints.PageView;
import com.buyremo.dao.InvitationDAO;
import com.buyremo.dao.SchedularHelperDAO;
import com.buyremo.dao.UserDAO;
import com.buyremo.model.Invitation;
import com.buyremo.model.User;
import com.buyremo.util.MailHelper;

@Controller
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	InvitationDAO invitationDAO;
	
	@Autowired
	SchedularHelperDAO schedularHelperDAO;
	
	@Autowired
	AuthenticationController authenticationController;
	
	
	private static Logger log = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/signUpSubmit", method = RequestMethod.POST)
	public String signUpSubmit(ModelMap modelMap, @Valid User user,BindingResult result) throws ResourceNotFoundException, ParseErrorException, Exception {
		log.debug("In Sign Up Submit");
		String pageView=PageView.SIGNIN;	
		pageView=handleSimpleSignupSubmit(modelMap,user,result);
		
		return pageView;
	}
	
	private String handleSimpleSignupSubmit(ModelMap modelMap, User user, BindingResult result) throws ResourceNotFoundException, ParseErrorException, Exception {
		log.debug("In Handle Simple Sign Up Submit");
		try{
		if(user != null){
			User user2 = userDAO.getUserByEmail(user.getEmailId());
			if(user2 != null){
				modelMap.put("status", "failure");
				modelMap.put("message", "The Email you entered is already registered!");				
				return PageView.SIGNIN;				
			}
			 long created = userDAO.createUser(user);
			 if(created > 0){
				 modelMap.put("status", "success");
				 modelMap.put("message", "Your account is successfully created, Check your Email to activate your account");
				 MailHelper mailer = MailHelper.getInstance();
				 User user1 = userDAO.getUserByEmail(user.getEmailId());
				 mailer.sendActivateAccountMail(user.getEmailId(), user1);
			 }else{
				 modelMap.put("status", "failure");
				 return PageView.SIGNUPERROR;
			 }
		}
		}catch (Exception e){
			e.printStackTrace();
		}
		return PageView.SIGNUPSUCCESS;
	}

	@RequestMapping(value = "/dependantSignUp", method = RequestMethod.POST)
	public String dependantSignUp(ModelMap modelMap, User user, BindingResult result) {
		
		long senderId = user.getId();
		
		try{
			if(user != null){
				User user1 = userDAO.getDepdtUserByEmail(user.getEmailId());
				if(user1 != null){
					modelMap.put("status", "failure");
					modelMap.put("message", "The Email you entered is invalid or already registered!");
				    return PageView.DEPENDANTSIGNUP;
							   	   
				  }else{
					  Invitation invitation =  invitationDAO.getInviteeByEmail(senderId, user.getEmailId());
					 
					  if(invitation != null){		    	
						  long created = userDAO.createDependant(user);
						  if(created > 0){
								modelMap.put("status", "success");
								modelMap.put("message", "Your account is successfully created, Check your Email to activate your account");
								MailHelper mailer = MailHelper.getInstance();
								User user2 = userDAO.getDepdtUserByEmail(user.getEmailId());
								mailer.sendDependantActivationMail(user.getEmailId(), user2);
						  }else{
							  modelMap.put("status", "failure");
							  return PageView.SIGNUPERROR;
						}
					  } else {
					    modelMap.put("status", "failure");
						modelMap.put("message", "The Email you entered is not referred by a BuyRemo user!");
					    return PageView.DEPENDANTSIGNUP;
					  }
				  }
			}
			}catch (Exception e){
				e.printStackTrace();
			}
		return PageView.DEPENDANTSIGNUP;
	}
	
	
	public String getUserType(long userId) {
			
			User user = userDAO.getUser(userId);
			return user.getUserType();
		
	}
		 
}


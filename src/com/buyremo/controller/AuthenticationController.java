package com.buyremo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buyremo.constraints.PageView;
import com.buyremo.dao.AspirationDAO;
import com.buyremo.dao.UserDAO;
import com.buyremo.model.Aspiration;
import com.buyremo.model.Login;
import com.buyremo.model.User;
import com.buyremo.session.UserSession;
import com.buyremo.util.MailHelper;

@Controller
public class AuthenticationController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserController userController;
	
	@Autowired
	AspirationController aspirationController;
	
	@Autowired
	AspirationDAO aspirationDAO;
	
	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);
	

	@RequestMapping(value = "/loginSubmit", method = {RequestMethod.GET,RequestMethod.POST})
	public String userLoginSubmit(ModelMap modelMap, Login login,HttpSession session,HttpServletRequest request) {
		
		if("GET".equalsIgnoreCase(request.getMethod())) {
			return PageView.SIGNIN;
		}
		String userType = null;
		if(login != null){
			User user = userDAO.validateUser(login);			
			
			if(user != null && user.getUserType().trim().equals("DEPENDANT")){		
				UserSession userSession = AuthenticationHelper.populateUserSession(login, user);
				 session.setAttribute("userSession", userSession);
				 aspirationController.getDependantAspirations(modelMap,session);
				 Aspiration depdtAspirationCount = aspirationDAO.getDepdtAspirationCount(user.getId());
				 if(depdtAspirationCount.getAspirationCount() == "0"){
					 modelMap.put("depdtAspirationCount","0");
				 }
				 userType = user.getUserType();
				 modelMap.put("userType", userType);
				 modelMap.put("depdtAspirationCount",depdtAspirationCount.getAspirationCount());
				 return PageView.USERHOME;
			}else if(user != null && user.getUserType().trim().equals("PARENT")){		
				UserSession userSession = AuthenticationHelper.populateUserSession(login, user);
				 session.setAttribute("userSession", userSession);
				 aspirationController.getAspirations(modelMap,session);
				 userType = user.getUserType();
				 modelMap.put("userType", userType);
				 return PageView.ASPIRATIONS;			 
			}else {
				modelMap.put("status","error");
				modelMap.put("message","Please enter valid login credentials to continue..");
				return PageView.SIGNIN;
			}
		}
		else{
			modelMap.put("message","error");
			return PageView.SIGNIN;
		}
		
	}
	
	
	/* Getting the list of ActiveAccount.* */
	@RequestMapping(value = "/activateUser", method = RequestMethod.GET)
	public String activateAccount(ModelMap modelMap, Login login,HttpSession session) {

		long userDTOId = userDAO.activateUser(login.getToken());
		try{
			if (0 != userDTOId)
			{ 
				User userDTO= new User();
				User user = userDAO.getUserByToken(login.getToken());
				MailHelper mailer = MailHelper.getInstance();
				mailer.sendActivationSuccessNotification(user);
				modelMap.put("status","success");
				modelMap.put("signUpMessage", "Account has been Activated successfully");
				modelMap.put("activatedEmailId",userDTO.getEmailId());	
				return PageView.ACTIVATEACCOUNT;
			}else{
				modelMap.put("status","info");
				modelMap.put("error", "Invalid Token or User has already been Activated");
				return PageView.SIGNUPERROR;
			}
		}catch (Exception e){
			e.printStackTrace();
			modelMap.put("status","error");
			modelMap.put("error", "User account could not be Activated");
		}
		return PageView.INDEX;
	}
	
	/* Getting the list of ActiveAccount.* */
	@RequestMapping(value = "/activateDependant", method = RequestMethod.GET)
	public String activateDependant(ModelMap modelMap, Login login,HttpSession session) {

		long userDTOId = userDAO.activateDedendant(login.getToken());
		try{
			if (0 != userDTOId)
			{ 
				User userDTO= new User();
				User user = userDAO.getDependantByToken(login.getToken());
				MailHelper mailer = MailHelper.getInstance();
				mailer.sendActivationSuccessNotification(user);
				modelMap.put("status","success");
				modelMap.put("signUpMessage", "Account has been Activated successfully");
				modelMap.put("activatedEmailId",userDTO.getEmailId());	
				return PageView.ACTIVATEACCOUNT;
			}else{
				modelMap.put("status","info");
				modelMap.put("error", "Invalid Token or User has already been Activated");
				return PageView.SIGNUPERROR;
			}
		}catch (Exception e){
			e.printStackTrace();
			modelMap.put("status","error");
			modelMap.put("error", "User account could not be Activated");
		}
		return PageView.INDEX;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, Login login,HttpSession session) {

		session.removeAttribute("userSession");

		return PageView.INDEX;
	}
	
	public String activateAccount(ModelMap modelMap, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

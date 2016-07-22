package com.buyremo.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buyremo.constraints.PageView;
import com.buyremo.dao.AspirationDAO;
import com.buyremo.dao.UserDAO;
import com.buyremo.dao.UserProfileDAO;
import com.buyremo.model.Aspiration;
import com.buyremo.model.Login;
import com.buyremo.model.User;
import com.buyremo.session.UserSession;


@Controller
public class DefaultController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	UserController userController;
	
	@Autowired
	AspirationDAO aspirationDAO;
	
	private static Logger log = Logger.getLogger(DefaultController.class);

	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap modelMap) {
		return PageView.INDEX;		
	}


	@RequestMapping(value = { "/login" })
	public String adminSignIn() {

		return PageView.SIGNIN;
	}
	
	@RequestMapping(value = { "/userHome" })
	public String userHome(ModelMap modelMap,HttpSession session) {
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}else{
			User user = userDAO.validateUser(userSession.getUserId());
			Aspiration aspirationCount = aspirationDAO.getAspirationCount(userSession.getUserId());
			Aspiration depdtAspirationCount = aspirationDAO.getDepdtAspirationCount(userSession.getUserId());
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount);
			System.out.println(aspirationCount);
			if(aspirationCount.getCartCount() == "0" || cartCount.getCartCount() == "0" || depdtAspirationCount.getAspirationCount() == "0"){
				modelMap.put("aspirationCount","0");
				modelMap.put("depdtAspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("depdtAspirationCount",depdtAspirationCount.getAspirationCount());
			modelMap.put("aspirationCount",aspirationCount.getAspirationCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			modelMap.put("userType", user.getUserType());
			modelMap.put("error","Please Login to Continue");
		return PageView.USERHOME;
		}
	}
	
	@RequestMapping(value = { "/searchResults" })
	public String adminSignUp(ModelMap modelMap,HttpSession session) {
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SEARCHRESULTS;
		}else{
			User user = userDAO.getDependantUser(userSession.getUserId());
			Aspiration depdtAspirationCount = aspirationDAO.getDepdtAspirationCount(userSession.getUserId());
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount);
			System.out.println(depdtAspirationCount.getAspirationCount());
			if(depdtAspirationCount.getAspirationCount() == "0" || cartCount.getCartCount() == "0"){
				modelMap.put("depdtAspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("depdtAspirationCount",depdtAspirationCount.getAspirationCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			modelMap.put("userType", user.getUserType());
			modelMap.put("error","Please Login to Continue");
		return PageView.SEARCHRESULTS;
		}
	}
	
	/*@RequestMapping(value = {"/checkout" })
	public String userCheckout(ModelMap modelMap, Login login,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}else{
			User user = userDAO.getDependantUser(userSession.getUserId());
			Aspiration aspirationCount = aspirationDAO.getAspirationCount(userSession.getUserId());
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount);
			System.out.println(aspirationCount);
			modelMap.put("aspirationCount",aspirationCount.getCartCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			modelMap.put("userType", user.getUserType());
			modelMap.put("error","Please Login to Continue");
		return PageView.CHECKOUT;
		}
	}	*/
	
	@RequestMapping(value = {"/inviteDependants" })
	public String inviteDependants(ModelMap modelMap, Login login,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}else{
			User user = userDAO.getUser(userSession.getUserId());
			Aspiration aspirationCount = aspirationDAO.getAspirationCount(userSession.getUserId());
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount);
			System.out.println(aspirationCount);
			if(aspirationCount.getAspirationCount() == "0" || cartCount.getCartCount() == "0"){
				modelMap.put("aspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("aspirationCount",aspirationCount.getAspirationCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			modelMap.put("userType", user.getUserType());
			modelMap.put("error","Please Login to Continue");
		return PageView.INVITEDEPENDANTS;
		}
	}
	
	@RequestMapping(value = {"/addItem" })
	public String addItem(ModelMap modelMap, Login login,HttpSession session) {
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.ADDASPIRATION;
		}else{
			User user = userDAO.getDependantUser(userSession.getUserId());
			Aspiration aspirationCount = aspirationDAO.getAspirationCount(userSession.getUserId());
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount);
			System.out.println(aspirationCount);
			if(aspirationCount.getCartCount() == "0" || cartCount.getCartCount() == "0"){
				modelMap.put("aspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("aspirationCount",aspirationCount.getCartCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			modelMap.put("userType", user.getUserType());
			modelMap.put("error","Please Login to Continue");
		return PageView.ADDASPIRATION;
		}
	}
	

}
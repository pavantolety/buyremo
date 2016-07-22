package com.buyremo.controller;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.buyremo.model.User;
import com.buyremo.session.UserSession;

@Controller
public class CheckoutController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AspirationDAO aspirationDAO;
	
	private static Logger LOGGER = Logger.getLogger(CheckoutController.class);
	
	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public String itemCheckout(ModelMap modelMap,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}
		try{
			Aspiration aspirationCount = aspirationDAO.getAspirationCount(userSession.getUserId());
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount.getCartCount());
			System.out.println(aspirationCount.getAspirationCount());
			if(aspirationCount.getCartCount() == "0" || cartCount.getCartCount() == "0"){
				modelMap.put("aspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("aspirationCount",aspirationCount.getAspirationCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			List<Aspiration> aspiration = userDAO.getCartItems(userSession.getUserId());				
			if(aspiration == null){
				modelMap.put("status","success");
				modelMap.put("message", "Couldnt find any aspirations at this time.");
				
			}else{
				double totalAmount = 0.0;
				Iterator<Aspiration> iterator = aspiration.iterator();
				while(iterator.hasNext()) {
					totalAmount = totalAmount + iterator.next().getProductPrice();
					
				}
				DecimalFormat df = new DecimalFormat("#.##");
				modelMap.put("totalAmount",df.format(totalAmount));
				modelMap.put("aspirationsList",aspiration);
			}
			User user = userDAO.getUser(userSession.getUserId());
			modelMap.put("userType", user.getUserType());
		}catch(Exception e){
			e.printStackTrace();
		}
		return PageView.CHECKOUT;
	}
	
	@RequestMapping(value = "/getDependants", method = {RequestMethod.GET})
	public String getDependants(ModelMap modelMap,Aspiration aspiration,HttpSession session) {
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}else{
			User user = userDAO.getUser(userSession.getUserId());
			Set<User> deptUser = userDAO.getDependantUsers(userSession.getUserId());
			modelMap.put("user", user);
			modelMap.put("dependants", deptUser);
			return "";		
		}
		
	}
}

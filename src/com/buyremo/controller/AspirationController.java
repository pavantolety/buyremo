package com.buyremo.controller;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buyremo.constraints.PageView;
import com.buyremo.dao.AspirationDAO;
import com.buyremo.dao.UserDAO;
import com.buyremo.model.AnonymousAspiration;
import com.buyremo.model.Aspiration;
import com.buyremo.model.User;
import com.buyremo.session.UserSession;

@Controller
public class AspirationController {
	
	@Autowired
	AspirationDAO aspirationDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserController userController;
	
	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);
	
	@RequestMapping(value="/requestAspiration", method=RequestMethod.POST)
	public String addAspiration(ModelMap modelMap,Aspiration aspiration,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			//build uuid
			//save uuid + aspiration in anonymus aspirations
			//modelmap uuid, userType
			return PageView.SIGNIN;
		}
		try{
			User user = userDAO.getDependantUser(userSession.getUserId());
			User user1 = userDAO.getParentUserByEmail(aspiration.getParentEmail());

			if(user.getDependantId() == user1.getId()){
				aspiration.setUserId(user1.getId());
				aspiration.setAspirantId(userSession.getUserId());
				aspiration.setAspirantEmail(userSession.getEmailId());
				boolean created = aspirationDAO.saveAspirationForUser(aspiration);
			    if(created){
				   modelMap.put("status","success");
				   modelMap.put("message", "Product added to wishlist");
			    }else{
				   modelMap.put("status","info");
				   modelMap.put("message", "Product is already Added to your wishlist");
			    }
			}else{
				//TODO
				modelMap.put("status","failure");
				modelMap.put("message", "Email you entered is not a registered Buyremo Id.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/requestAnonymousAspiration", method=RequestMethod.POST)
	public String addAnonymousAspiration(ModelMap modelMap,AnonymousAspiration anonymousAspiration,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			try{
				User user2 = userDAO.getParentUserByEmail(anonymousAspiration.getParentEmail());
				System.out.println("userId" + user2.getId());
				anonymousAspiration.setUserId(user2.getId());
				if(user2 != null){
					boolean created = aspirationDAO.saveAnonymousAspirationForUser(anonymousAspiration);
				    if(created){
					   modelMap.put("status","success");
					   modelMap.put("message", "Product added to wishlist");
				    }else{
					   modelMap.put("status","info");
					   modelMap.put("message", "Product is already Added to your wishlist");
				    }
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//build uuid
			//save uuid + aspiration in anonymus aspirations
			//modelmap uuid, userType
			return PageView.ADDASPIRATION;
		}else{
			return "";
		}
	}
	
	@RequestMapping(value="/getAspirations", method=RequestMethod.GET)
	public String getAspirations(ModelMap modelMap,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}
		try{			
			List<Aspiration> aspiration = userDAO.getAspirations(userSession.getUserId());	
			//get annonymus aspirations by email
			/*aspiration2
			 * 
			 * if (asp2 != null) {
			 * asp2.iterator
			 * iter.hasNext() {
			 * aspiration.add(iter.next)
			 */
			Aspiration aspirationCount = aspirationDAO.getAspirationCount(userSession.getUserId());//aspiration.size();
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			System.out.println(cartCount.getCartCount());
			System.out.println(aspirationCount.getAspirationCount());
			if(aspirationCount.getCartCount() == "0" || cartCount.getCartCount() == "0"){
				modelMap.put("aspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("aspirationCount",aspirationCount.getAspirationCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			if(aspiration == null){
				modelMap.put("status","success");
				modelMap.put("message", "Couldnt find any aspirations at this time.");
				
			}else{
				modelMap.put("aspirationsList",aspiration);
			}
			User user = userDAO.getUser(userSession.getUserId());
			modelMap.put("userType", user.getUserType());
		}catch(Exception e){
			e.printStackTrace();
		}
		return PageView.ASPIRATIONS;
	}
	
	@RequestMapping(value="/getMyAspirations", method=RequestMethod.GET)
	public String getDependantAspirations(ModelMap modelMap,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}
		try{	
			System.out.println(userSession.getUserId());
			List<Aspiration> aspiration = userDAO.getDependantAspirations(userSession.getUserId());	
			System.out.println(aspiration);
			Aspiration cartCount = aspirationDAO.getCartItemsCount(userSession.getUserId());
			Aspiration depdtAspirationCount = aspirationDAO.getDepdtAspirationCount(userSession.getUserId());
			System.out.println(cartCount.getCartCount());
			System.out.println(depdtAspirationCount.getAspirationCount());
			if(depdtAspirationCount.getCartCount() == "0" || cartCount.getCartCount() == "0"){
				modelMap.put("depdtAspirationCount","0");
				modelMap.put("cartCount","0");
			}
			modelMap.put("depdtAspirationCount",depdtAspirationCount.getAspirationCount());
			modelMap.put("cartCount",cartCount.getCartCount());
			if(aspiration == null){
				modelMap.put("status","success");
				modelMap.put("message", "Seems your aspirations are empty!");
				
			}else{
				modelMap.put("aspirationsList",aspiration);
			}
			User user = userDAO.getDependantUser(userSession.getUserId());
			modelMap.put("userType", user.getUserType());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return PageView.ASPIRATIONS;
	}
	
	@RequestMapping(value="/removeAspiration/{id}", method=RequestMethod.POST)
	public String removeAspirationOfDependant(ModelMap modelMap,@PathVariable long id,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession!=null) {
			boolean removed = aspirationDAO.removeAspirationByAspirationId(id);
		      if(removed){
		    	  modelMap.put("status","success");
		    	  modelMap.put("message","Item removed");
		      }else{
		    	  modelMap.put("status","error");
		    	  modelMap.put("message","Error while removing");
		      }
		      return PageView.ASPIRATIONS;
		}else {
			return PageView.INDEX;
		}	
		
	}
	
	@RequestMapping(value="/buyAspiration/{id}", method=RequestMethod.POST)
	public String addAspirationToCartById(ModelMap modelMap,@PathVariable String id,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession!=null) {
			try{
				User user = userDAO.validateUser(userSession.getUserId());
				if(user.getUserType().trim().equals("PARENT")){
					System.out.println("Inside parent user" + id);
					
					Aspiration aspiration = userDAO.getAspirationById(NumberUtils.toLong(id));
					aspiration.setAspirationId(NumberUtils.toLong(id));
					aspiration.setProductStatus("INACTIVE");
					System.out.println("fhghjkk" + aspiration);
					if(aspiration != null){
						boolean added = aspirationDAO.addAspirationToCartById(aspiration);
						if(added){
					    	modelMap.put("status","success");
					    	modelMap.put("message","Item Added to Cart");
					    	boolean removed = aspirationDAO.changeAspirationStatus(NumberUtils.toLong(id),aspiration.getProductStatus());
						      if(removed){
						    	  modelMap.put("status","success");
						    	  modelMap.put("message","Item moved to cart");
						      }else{
						    	  modelMap.put("status","error");
						    	  modelMap.put("message","Error while moving item to cart");
						      }
					    }else{
					    	modelMap.put("status","error");
					    	modelMap.put("message","Error while processing request");
					    }
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			return PageView.INDEX;
		}	
		return "";
	}
	
	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public String getAspirationsInCart(ModelMap modelMap,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession==null) {
			return PageView.SIGNIN;
		}
		try{			
			List<Aspiration> aspiration = userDAO.getCartItems(userSession.getUserId());
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
		return PageView.CART;
	}
	
	@RequestMapping(value="/moveToAspirations/{id}", method=RequestMethod.POST)
	public String moveCartItem(ModelMap modelMap,@PathVariable String id,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession!=null) {
			try{
				System.out.println("userSession");
				User user = userDAO.validateUser(userSession.getUserId());
				if(user.getUserType().trim().equals("PARENT")){
					System.out.println("Inside parent user" + id);
					
					Aspiration aspiration = userDAO.getAspirationById(NumberUtils.toLong(id));
					aspiration.setAspirationId(NumberUtils.toLong(id));
					aspiration.setProductStatus("ACTIVE");
					System.out.println("fhghjkk" + aspiration);
					if(aspiration != null){
						boolean removed = aspirationDAO.removeCartItemByAspirationId(NumberUtils.toLong(id));
						if(removed){
							boolean changed = aspirationDAO.changeAspirationStatus(NumberUtils.toLong(id),aspiration.getProductStatus());
							if(changed){
						    	  modelMap.put("status","success");
						    	  modelMap.put("message","Item moved to aspiration");
						      }else{
						    	  modelMap.put("status","error");
						    	  modelMap.put("message","Error while moving item to aspirations");
						      }
						}else{
					    	modelMap.put("status","error");
					    	modelMap.put("message","Error while processing request");
					    }
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			return PageView.INDEX;
		}	
		return "";
	}
	
	@RequestMapping(value="/removeCartItem/{id}", method=RequestMethod.POST)
	public String removeCartItem(ModelMap modelMap,@PathVariable String id,HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		if(userSession!=null) {
			boolean removed = aspirationDAO.removeCartItemByAspirationId(NumberUtils.toLong(id));
		      if(removed){
		    	  modelMap.put("status","success");
		    	  modelMap.put("message","Item removed");
		      }else{
		    	  modelMap.put("status","error");
		    	  modelMap.put("message","Error while removing");
		      }
		      return PageView.CART;
		}else {
			return PageView.INDEX;
		}
	}
	
}

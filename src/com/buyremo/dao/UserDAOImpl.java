package com.buyremo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buyremo.enums.UserStatus;
import com.buyremo.model.Aspiration;
import com.buyremo.model.Login;
import com.buyremo.model.User;
import com.buyremo.util.AuthenticationUtils;

@Repository
public class UserDAOImpl implements  UserDAO {
	
		/**
		 * Queries for storing and retrieving goes here
		 */
	
		//Create User Query
		public static final String CREATE_USER = "INSERT INTO USERS(USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,TOKEN,DATE_CREATED,DATE_UPDATED,USER_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,NOW(),NOW(),'PARENT')";
		
		//Select User by Email
		public static final String GET_USER_EMAIL = "SELECT USER_EMAIL_ID FROM USERS WHERE ID=?";
		
		//Get All Users
		public static final String GET_TOTAL_USERS = "SELECT COUNT(ID) FROM USERS WHERE USER_STATUS=?";
		
		//Get user by user Id
		public static final String GET_USER_BY_ID = "SELECT ID,USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,DATE_CREATED,DATE_UPDATED,USER_TYPE FROM USERS WHERE ID=?";
		
		//Create Dependant Query
		public static final String CREATE_DEPENDANT = "INSERT INTO DEPENDANTS(DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,TOKEN,DATE_CREATED,DATE_UPDATED,DEPDT_ON,USER_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,NOW(),NOW(),?,'DEPENDANT')";
		
		//Get dependant by dependant Id
		public static final String GET_DEPDT_USER_BY_ID = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,DEPDT_ON,USER_TYPE FROM DEPENDANTS WHERE DEPDT_ID=?";
		
		//Get dependant by dependant Id
		public static final String GET_DEPDT_USER_BY_PARENT_ID = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,DATE_CREATED,DATE_UPDATED,USER_TYPE FROM DEPENDANTS WHERE DEPDT_ON=?";
		
		//Get user by Email Id
		public static final String GET_DEPDT_USER_BY_EMAIL_ID = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,TOKEN,DATE_CREATED,DATE_UPDATED,USER_TYPE FROM DEPENDANTS WHERE DEPDT_EMAIL=?";
				
		//Get user by Email Id
		public static final String GET_USER_BY_EMAIL_ID = "SELECT ID,USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,TOKEN FROM USERS WHERE USER_EMAIL_ID=? AND USER_STATUS='INACTIVE'";
		
		//Get user by Email Id
		public static final String GET_PARENT_USER_BY_EMAIL = "SELECT ID,USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,TOKEN FROM USERS WHERE USER_EMAIL_ID=?";
		
		//Get user by Email Id
		public static final String GET_USER_BY_TOKEN = "SELECT ID,USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,TOKEN FROM USERS WHERE TOKEN=?";
		
		//Get user by Email Id
		public static final String GET_DEPDT_USER_BY_TOKEN = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,TOKEN FROM DEPENDANTS WHERE TOKEN=?";
		
		//Activate User
		public static final String ACTIVATE_USER = "UPDATE USERS SET USER_STATUS='ACTIVE' WHERE TOKEN =?";
		
		//Activate User
		public static final String ACTIVATE_DEPENDANT = "UPDATE DEPENDANTS SET DEPDT_STATUS='ACTIVE' WHERE TOKEN =?";
		
		//
		public static final String UPDATE_USER = "UPDATE USERS SET NAME=? , EMAIL_ADDRESS=?,PHONE_NUMBER=?,ROLE=?,STATUS=?,TOKEN=?,DATE_UPDATED=now(),EVENTS_COUNT=?,FB_ID=?,SUBSCRIPTION_ID=? WHERE ID=?";
		
		//Query for Change password
		public static final String UPDATE_USER_PASSWORD = "UPDATE USERS SET PASSWORD=? WHERE ID=?";
		
		//Validate user Details while login
		public static final String VALIDATE_USER = "SELECT ID,USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,USER_TYPE FROM USERS WHERE USER_EMAIL_ID=? AND USER_PASS=? AND USER_STATUS=?";
		
		//Validate user Details while login
		public static final String VALIDATE_DEPENDANT = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,DEPDT_ON,USER_TYPE FROM DEPENDANTS WHERE DEPDT_EMAIL=? AND DEPDT_PASS=? AND DEPDT_STATUS=?";
		
		//Validate user Details while login
		public static final String VALIDATE_USER_BY_ID = "SELECT ID,USER_EMAIL_ID,USER_PASS,USER_NAME,USER_MOBILE,USER_HNO,USER_ADDRESSL1,USER_ADDRESSL2,USER_ZIP,USER_STATUS,USER_TYPE FROM USERS WHERE ID=?";
				
		//Validate user Details while login
		public static final String VALIDATE_DEPENDANT_REQUEST = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,DEPDT_ON,USER_TYPE FROM DEPENDANTS WHERE DEPDT_ID=?";
		
		//Validate user Details while login
		public static final String GET_ASPIRATIONS = "SELECT A.ASPIRATION_ID,A.USER_ID,A.ASP_ID,A.ASP_EMAIL,A.PROD_ID,A.PROD_NAME,A.PROD_DESC,A.PROD_PRICE,A.PROD_CATG,A.PROD_CATG_ID,A.PROD_GAL_URL,A.PROD_VIEW_URL,A.PROD_CURR_ID,A.PROD_STATUS,A.DATE_CREATED,A.DATE_UPDATED,A.ASP_MESSAGE, (SELECT D.DEPDT_NAME FROM DEPENDANTS D WHERE DEPDT_ID = A.ASP_ID) AS DEPDT_NAME FROM ASPIRATIONS A WHERE USER_ID=? AND PROD_STATUS='ACTIVE'";
		
		//Validate user Details while login
		public static final String GET_ASPIRATIONS_BY_ID = "SELECT USER_ID,ASP_ID,ASP_EMAIL,PROD_ID,PROD_NAME,PROD_DESC,PROD_PRICE,PROD_CATG,PROD_CATG_ID,PROD_GAL_URL,PROD_VIEW_URL,PROD_CURR_ID,PROD_STATUS,DATE_CREATED,DATE_UPDATED,ASP_MESSAGE FROM ASPIRATIONS WHERE ASPIRATION_ID=?";
		
		//Validate user Details while login
		public static final String GET_CARTITEMS_BY_USER_ID = "SELECT ASPIRATION_ID,USER_ID,ASP_ID,ASP_EMAIL,PROD_ID,PROD_NAME,PROD_DESC,PROD_PRICE,PROD_CATG,PROD_CATG_ID,PROD_GAL_URL,PROD_VIEW_URL,PROD_CURR_ID,PROD_STATUS,DATE_CREATED,DATE_UPDATED,ASP_MESSAGE FROM USER_CART WHERE USER_ID=?";
				
		//Validate user Details while login
		public static final String GET_DEPDT_ASPIRATIONS = "SELECT A.ASPIRATION_ID,A.USER_ID,A.ASP_ID,A.ASP_EMAIL,A.PROD_ID,A.PROD_NAME,A.PROD_DESC,A.PROD_PRICE,A.PROD_CATG,A.PROD_CATG_ID,A.PROD_GAL_URL,A.PROD_VIEW_URL,A.PROD_CURR_ID,A.PROD_STATUS,A.DATE_CREATED,A.DATE_UPDATED,A.ASP_MESSAGE, (SELECT U.USER_NAME FROM USERS U WHERE ID = A.USER_ID) AS USER_NAME FROM ASPIRATIONS A WHERE ASP_ID=?";
	
		private JdbcTemplate jdbcTemplate;

		@Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

		
		 /**
		  * Method to Create a New user by Sign up
		  */
		@Override
		@Transactional
		public long createUser(User user) {
			String token = AuthenticationUtils.generateTokenForAuthentication();
			
			int created = jdbcTemplate.update(CREATE_USER,user.getEmailId(),user.getPassword(),user.getUserName(),user.getMobileNumber(),user.getHno(),user.getAddressLine1(),user.getAddressLine2(),user.getZip(),"INACTIVE",token);

			return created;
		}
		
		/**
		  * Method to Create a New user by Sign up
		  */
		@Override
		@Transactional
		public long createDependant(User user) {
			String token = AuthenticationUtils.generateTokenForAuthentication();
			
			int created = jdbcTemplate.update(CREATE_DEPENDANT,user.getEmailId(),user.getPassword(),user.getUserName(),user.getMobileNumber(),user.getHno(),user.getAddressLine1(),user.getAddressLine2(),user.getZip(),"INACTIVE",token,user.getId());

			return created;
		}
		
		/**
		 * Method to implement Validation while user login
		 */
	@Override
	public User validateUser(Login login) {
		List<User> userList = new ArrayList<User>();
		List<Map<String, Object>> user = jdbcTemplate.queryForList(VALIDATE_USER, new Object[] { login.getLoginId(), login.getLoginPassword(), UserStatus.ACTIVE.toString() });
		if (user.size() > 0) {
			for (Map<String, Object> map : user) {
				userList.add(retrieveUser(map));
			}
			return userList.get(0);
		} else {
			System.out.println("in depdt");
			List<Map<String, Object>> user1 = jdbcTemplate.queryForList(VALIDATE_DEPENDANT, new Object[] { login.getLoginId(), login.getLoginPassword(), UserStatus.ACTIVE.toString() });
			if (user1.size() > 0) {
				for (Map<String, Object> map : user1) {
					userList.add(retrieveDependant(map));
				}
				return userList.get(0);
			} else {
				return null;
			}
		}
	}
	
	@Override
	public User validateUser(long userId) {
		List<User> userList = new ArrayList<User>();
		List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_USER_BY_ID,userId);
		if (user.size() > 0) {
			for (Map<String, Object> map : user) {
				userList.add(retrieveUser(map));
			}
			return userList.get(0);
		} else {
			System.out.println("in depdt");
			List<Map<String, Object>> user1 = jdbcTemplate.queryForList(GET_DEPDT_USER_BY_ID,userId);
			if (user1.size() > 0) {
				for (Map<String, Object> map : user1) {
					userList.add(retrieveDependant(map));
				}
				return userList.get(0);
			} else {
				return null;
			}
		}
	}
			
		@Override
		public User validateDependantUser(long userId) {			
			List<User> userList =  new  ArrayList<User>();
			List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_DEPDT_USER_BY_ID,userId);
			for (Map<String, Object> map : user) {
				userList.add(retrieveDependants(map, userId));
			}
			return userList.get(0);
		}
		
		/**
		 * Implementation for getting a user by email Id
		 */
		@Override
		public User getUserByEmail(String email) {
			List<User> userList = new ArrayList<User>();
			List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_USER_BY_EMAIL_ID, email);
			if (user.size() > 0) {
				for (Map<String, Object> map : user) {
					userList.add(retrieveUser(map));
				}
				User userData = userList.get(0);
				return userData;
			} else {
				return null;
			}
		}
		
		/**
		 * Implementation for getting a user by email Id
		 */
		@Override
		public User getParentUserByEmail(String email) {
			List<User> userList = new ArrayList<User>();
			List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_PARENT_USER_BY_EMAIL, email);
			if (user.size() > 0) {
				for (Map<String, Object> map : user) {
					userList.add(retrieveUser(map));
				}
				User userData = userList.get(0);
				return userData;
			} else {
				return null;
			}
		}
		
		@Override
		public User getUserByToken(String token) {
			List<User> userList = new ArrayList<User>();
			List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_USER_BY_TOKEN, token);
			if (user.size() > 0) {
				for (Map<String, Object> map : user) {
					userList.add(retrieveUser(map));
				}
				User userData = userList.get(0);
				return userData;
			} else {
				return null;
			}
		}
		
		/**
		 * Method to activate a new user by email Link
		 */
		@Override
		public long activateUser(String token) {
			long isUpdated = jdbcTemplate.update(ACTIVATE_USER, token);
			return isUpdated;
		}
		
		
		@Override
		public long updateUser(User user) {
			long update = jdbcTemplate.update(UPDATE_USER, new Object[]{user.getUserName(),user.getEmailId(),user.getMobileNumber(),user.getStatus(),user.getToken(),user.getId()});
			
			return update;
		}
		
		/**
		 * Method to update password for a user by ID
		 */
		@Override
		public User updateUserPassword(User user) {
			
			jdbcTemplate.update(UPDATE_USER_PASSWORD, new Object[]{user.getPassword(), user.getId()});
			
			return getUser(user.getId());
		}
		
		/**
		 * Method to get a single user by ID
		 */
		@Override
		public User getUser(long userId) {
			 List<User> userList =  new  ArrayList<User>();
			 if(userId<1){
				 return new User();
			 }else{
				List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_USER_BY_ID,new Object[]{userId});
				for (Map<String, Object> map : user) {
					userList.add(retrieveUser(map));
				}
				if(userList.size()>0){
				return userList.get(0);
				} else {
					return new User();
				}
			 }
		}
		
		private User retrieveUser(Map<String, Object> map) {
			
			User user = new User();
			if(map.get("ID")!= null){
				user.setId(Long.parseLong(map.get("ID").toString()));
			}
			if(map.get("USER_NAME")!=null){
				user.setUserName(map.get("USER_NAME").toString());
			}
			if(map.get("USER_EMAIL_ID")!=null){
				user.setEmailId(map.get("USER_EMAIL_ID").toString());
			}
			if(map.get("USER_PASS")!=null) {
				user.setPassword(map.get("USER_PASS").toString());
			}
			if(map.get("USER_MOBILE")!=null) {
				user.setMobileNumber(map.get("USER_MOBILE").toString());
			}
			if(map.get("USER_HNO")!=null){
				user.setHno(map.get("USER_HNO").toString());
			}
			if(map.get("USER_ADDRESSL1")!=null){
				user.setAddressLine1(map.get("USER_ADDRESSL1").toString());
			}
			if(map.get("USER_ADDRESSL2")!=null){
				user.setAddressLine2(map.get("USER_ADDRESSL2").toString());
			}
			if(map.get("USER_ZIP")!=null){
				user.setZip(map.get("USER_ZIP").toString());
			}
			if(map.get("USER_STATUS")!=null){
				user.setStatus(map.get("USER_STATUS").toString());
			}
			if(map.get("TOKEN")!=null){
				user.setToken((String)map.get("TOKEN"));
			}
			if(map.get("DATE_CREATED")!=null){
				user.setDateCreated((Date)map.get("DATE_CREATED"));
			}
			if(map.get("DATE_UPDATED")!=null){
				user.setDateUpdated((Date)map.get("DATE_UPDATED"));
			}
			if(map.get("USER_TYPE")!=null){
				user.setUserType((String)map.get("USER_TYPE"));
			}
			return user;
		}


		@Override
		public User getDependantUser(long userId) {

			 List<User> userList =  new  ArrayList<User>();
			 if(userId<1){
				 return new User();
			 }else{
				List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_DEPDT_USER_BY_ID,new Object[]{userId});
				for (Map<String, Object> map : user) {
					userList.add(retrieveDependant(map));
				}
				if(userList.size()>0){
				return userList.get(0);
				} else {
					return new User();
				}
			 }
		
		}
		
		/**
		 * Implementation for getting a user by email Id
		 */
		@Override
		public User getDepdtUserByEmail(String email) {
			List<User> depdtList = new ArrayList<User>();
			List<Map<String, Object>> depdt = jdbcTemplate.queryForList(GET_DEPDT_USER_BY_EMAIL_ID, email);
			if (depdt.size() > 0) {
				for (Map<String, Object> map : depdt) {
					depdtList.add(retrieveDependant(map));
				}
				User depdtData = depdtList.get(0);
				return depdtData;
			} else {
				return null;
			}
		}
		
		@Override
		public Set<User> getDependantUsers(Long userId) {
			Set<User> users = new HashSet<User>();
			try {
				List<Map<String, Object>> sqlObjectsList = this.jdbcTemplate
						.queryForList(GET_DEPDT_USER_BY_PARENT_ID, userId);

				if (sqlObjectsList.size() != 0) {

					for (Map<String, Object> map : sqlObjectsList) {
						users.add(retrieveDependants(map, userId));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return users;
		}
		
		private User retrieveDependant(Map<String, Object> map) {
			
			User user = new User();
			if(map.get("DEPDT_ID")!= null){
				user.setId(Long.parseLong(map.get("DEPDT_ID").toString()));
			}
			if(map.get("DEPDT_NAME")!=null){
				user.setUserName(map.get("DEPDT_NAME").toString());
			}
			if(map.get("DEPDT_EMAIL")!=null){
				user.setEmailId(map.get("DEPDT_EMAIL").toString());
			}
			if(map.get("DEPDT_PASS")!=null) {
				user.setPassword(map.get("DEPDT_PASS").toString());
			}
			if(map.get("DEPDT_MOBILE")!=null) {
				user.setMobileNumber(map.get("DEPDT_MOBILE").toString());
			}
			if(map.get("DEPDT_HNO")!=null){
				user.setHno(map.get("DEPDT_HNO").toString());
			}
			if(map.get("DEPDT_ADDRESSL1")!=null){
				user.setAddressLine1(map.get("DEPDT_ADDRESSL1").toString());
			}
			if(map.get("DEPDT_ADDRESSL2")!=null){
				user.setAddressLine2(map.get("DEPDT_ADDRESSL2").toString());
			}
			if(map.get("DEPDT_ZIP")!=null){
				user.setZip(map.get("DEPDT_ZIP").toString());
			}
			if(map.get("DEPDT_STATUS")!=null){
				user.setStatus(map.get("DEPDT_STATUS").toString());
			}
			if(map.get("TOKEN")!=null){
			user.setToken((String)map.get("TOKEN"));
			}
			if(map.get("DATE_CREATED")!=null){
			user.setDateCreated((Date)map.get("DATE_CREATED"));
			}
			if(map.get("DATE_UPDATED")!=null){
			user.setDateUpdated((Date)map.get("DATE_UPDATED"));
			}
			if(map.get("DEPDT_ON")!=null){
				user.setDependantId(Long.parseLong(map.get("DEPDT_ON").toString()));
			}
			if(map.get("USER_TYPE")!=null){
				user.setUserType((String)map.get("USER_TYPE"));
			}	
			return user;
		}
		
		private User retrieveDependants(Map<String, Object> map,long userId) {
			
			User user = new User();
			if(map.get("DEPDT_ID")!= null){
				user.setId(Long.parseLong(map.get("DEPDT_ID").toString()));
			}
			if(map.get("DEPDT_NAME")!=null){
				user.setUserName(map.get("DEPDT_NAME").toString());
			}
			if(map.get("DEPDT_EMAIL")!=null){
				user.setEmailId(map.get("DEPDT_EMAIL").toString());
			}
			if(map.get("DEPDT_PASS")!=null) {
				user.setPassword(map.get("DEPDT_PASS").toString());
			}
			if(map.get("DEPDT_MOBILE")!=null) {
				user.setMobileNumber(map.get("DEPDT_MOBILE").toString());
			}
			if(map.get("DEPDT_HNO")!=null){
				user.setHno(map.get("DEPDT_HNO").toString());
			}
			if(map.get("DEPDT_ADDRESSL1")!=null){
				user.setAddressLine1(map.get("DEPDT_ADDRESSL1").toString());
			}
			if(map.get("DEPDT_ADDRESSL2")!=null){
				user.setAddressLine2(map.get("DEPDT_ADDRESSL2").toString());
			}
			if(map.get("DEPDT_ZIP")!=null){
				user.setZip(map.get("DEPDT_ZIP").toString());
			}
			if(map.get("DEPDT_STATUS")!=null){
				user.setStatus(map.get("DEPDT_STATUS").toString());
			}
			if(map.get("TOKEN")!=null){
			user.setToken((String)map.get("TOKEN"));
			}
			if(map.get("DATE_CREATED")!=null){
			user.setDateCreated((Date)map.get("DATE_CREATED"));
			}
			if(map.get("DATE_UPDATED")!=null){
			user.setDateUpdated((Date)map.get("DATE_UPDATED"));
			}
			/*if(map.get("DEPDT_ON")!=null){
				user.setDateUpdated((Date)map.get("DEPDT_ON"));
			}*/
			return user;
		}
		
		/**
		 * Method to activate a new user by email Link
		 */
		@Override
		public long activateDedendant(String token) {
			long isUpdated = jdbcTemplate.update(ACTIVATE_DEPENDANT, token);
			return isUpdated;
		}
		
		@Override
		public User getDependantByToken(String token) {
			List<User> userList = new ArrayList<User>();
			List<Map<String, Object>> user = jdbcTemplate.queryForList(GET_DEPDT_USER_BY_TOKEN, token);
			if (user.size() > 0) {
				for (Map<String, Object> map : user) {
					userList.add(retrieveUser(map));
				}
				User userData = userList.get(0);
				return userData;
			} else {
				return null;
			}
		}


		@Override
		public List<Aspiration> getAspirations(long userId) {
			List<Aspiration> aspirations = null;
			try{
				List<Map<String, Object>> sqlObjectsList = this.jdbcTemplate.queryForList(GET_ASPIRATIONS,userId);
			
				if (sqlObjectsList.size() != 0) {
					aspirations = new ArrayList<Aspiration>();
					for (Map<String, Object> map : sqlObjectsList) {
						aspirations.add(retrieveAspirations(map));
					}
				}			
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return aspirations;
		}
		
		@Override
		public List<Aspiration> getCartItems(long userId) {
			List<Aspiration> aspirations = null;
			try{
				List<Map<String, Object>> sqlObjectsList = this.jdbcTemplate.queryForList(GET_CARTITEMS_BY_USER_ID,userId);
			
				if (sqlObjectsList.size() != 0) {
					aspirations = new ArrayList<Aspiration>();
					for (Map<String, Object> map : sqlObjectsList) {
						aspirations.add(retrieveAspirations(map));
					}
				}			
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return aspirations;
		}
		
		@Override
		public List<Aspiration> getDependantAspirations(long userId) {
			List<Aspiration> aspirations = null;
			try{
				List<Map<String, Object>> sqlObjectsList = this.jdbcTemplate.queryForList(GET_DEPDT_ASPIRATIONS,userId);
			
				if (sqlObjectsList.size() != 0) {
					aspirations = new ArrayList<Aspiration>();
					for (Map<String, Object> map : sqlObjectsList) {
						aspirations.add(retrieveAspirations(map));
					}
				}			
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return aspirations;
		}
		
		private Aspiration retrieveAspirations(Map<String, Object> map) {
			
			Aspiration aspiration = new  Aspiration();
			if(map.get("ASPIRATION_ID")!= null){
				aspiration.setAspirationId(Long.parseLong(map.get("ASPIRATION_ID").toString()));
			}
			if(map.get("USER_ID")!= null){
				aspiration.setUserId(Long.parseLong(map.get("USER_ID").toString()));
			}
			if(map.get("USER_NAME")!= null){
				aspiration.setParentName(map.get("USER_NAME").toString());
			}
			if(map.get("ASP_ID")!= null){
				aspiration.setAspirantId(Long.parseLong(map.get("ASP_ID").toString()));
			}
			if(map.get("ASP_EMAIL")!=null){
				aspiration.setAspirantEmail(map.get("ASP_EMAIL").toString());
			}
			if(map.get("PROD_ID")!=null){
				aspiration.setProductId(map.get("PROD_ID").toString());
			}
			if(map.get("PROD_NAME")!=null){
				aspiration.setProductName(map.get("PROD_NAME").toString());
			}
			if(map.get("PROD_DESC")!=null) {
				aspiration.setProductDesc(map.get("PROD_DESC").toString());
			}
			if(map.get("PROD_PRICE")!=null) {
				aspiration.setProductPrice(Float.parseFloat(map.get("PROD_PRICE").toString()));
			}
			if(map.get("PROD_CATG")!=null){
				aspiration.setCategoryName(map.get("PROD_CATG").toString());
			}
			if(map.get("PROD_CATG_ID")!=null){
				aspiration.setCategoryId(map.get("PROD_CATG_ID").toString());
			}
			if(map.get("PROD_GAL_URL")!=null){
				aspiration.setGalleryUrl(map.get("PROD_GAL_URL").toString());
			}
			if(map.get("PROD_VIEW_URL")!=null){
				aspiration.setViewItemUrl(map.get("PROD_VIEW_URL").toString());
			}
			if(map.get("PROD_CURR_ID")!=null){
				aspiration.setCurrencyId(map.get("PROD_CURR_ID").toString());
			}
			if(map.get("DATE_CREATED")!=null){
				aspiration.setDateCreated((Date)map.get("DATE_CREATED"));
			}
			if(map.get("DATE_UPDATED")!=null){
				aspiration.setDateUpdated((Date)map.get("DATE_UPDATED"));
			}
			if(map.get("ASP_MESSAGE")!=null){
				aspiration.setMessage(map.get("ASP_MESSAGE").toString());
			}
			if(map.get("DEPDT_NAME")!= null){
				aspiration.setAspirantName(map.get("DEPDT_NAME").toString());
			}	
			return aspiration;
		}


		@Override
		public Aspiration getAspirationById(long aspirationId) {
			List<Aspiration> aspirationList = new ArrayList<Aspiration>();
			List<Map<String, Object>> aspiration = jdbcTemplate.queryForList(GET_ASPIRATIONS_BY_ID, aspirationId);
			if (aspiration.size() > 0) {
				for (Map<String, Object> map : aspiration) {
					aspirationList.add(retrieveAspirations(map));
				}
				Aspiration aspirationData = aspirationList.get(0);
				return aspirationData;
			} else {
				return null;
			}
		}
}

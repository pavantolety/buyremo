package com.buyremo.dao;

import java.util.List;
import java.util.Set;

import com.buyremo.model.Aspiration;
import com.buyremo.model.Login;
import com.buyremo.model.User;

public interface UserDAO {
	
	/**
	 * Method to implement Validation while user login
	 */
	public User validateUser(Login login);
	
	/**
	 * Method to update password for a user by ID
	 */
	//public Users validateUser(long userId);
	
	 /**
	  * Method to Create a New user by Sign up
	  */
	public long createUser(User user);

	public User getUser(long userId);
	
	public User getDependantUser(long userId);
	
	/**
	 * Implementation for getting a user by email Id
	 */
	public User getUserByEmail(String email);
	
	/**
	 * Implementation for getting a user by email Id
	 */
	public User getDepdtUserByEmail(String email);
	
	/**
	 * Method to activate a new user by email Link
	 */
	public long activateUser(String token);
	
	public long updateUser(User user);
    
	/**
	 * Method to update password for a user by ID
	 */
	public User updateUserPassword(User userDTO);

	User getUserByToken(String token);

	long createDependant(User user);

	long activateDedendant(String token);

	User getDependantByToken(String token);

	Set<User> getDependantUsers(Long userId);

	User getParentUserByEmail(String email);
	
	public List<Aspiration> getAspirations(long userId);

	public List<Aspiration> getDependantAspirations(long userId);
	
	Aspiration getAspirationById(long aspirationId);

	User validateDependantUser(long userId);

	User validateUser(long userId);

	List<Aspiration> getCartItems(long userId);

}

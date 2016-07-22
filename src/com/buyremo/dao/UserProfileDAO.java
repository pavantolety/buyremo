package com.buyremo.dao;

import com.buyremo.model.UserProfile;

public interface UserProfileDAO {

	public long createUserProfile(UserProfile userProfile);
	
	public UserProfile getUserProfile(long userId);
	
	public boolean updateUserProfile(UserProfile userProfile);
	
}

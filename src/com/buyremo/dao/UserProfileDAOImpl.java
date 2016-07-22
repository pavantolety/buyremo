package com.buyremo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buyremo.model.UserProfile;

@Repository
public class UserProfileDAOImpl implements UserProfileDAO {
	
	public static final String CREATE_USERPROFILE = "INSERT INTO USER_PROFILE(ID, FIRSTNAME, LASTNAME, EMAILADDRESS, PHONENUMBER, ADDRESS, LATITUDE,LONGITUDE) VALUES(?,?,?,?,?,?,?,?)"; 
	public static final String UPDATE_USERPROFILE = "UPDATE USER_PROFILE SET FIRSTNAME=?, LASTNAME=?, EMAILADDRESS=?, PHONENUMBER=?, ADDRESS=?, LATITUDE=?, LONGITUDE=? WHERE ID=?";
	
	private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	@Override
	public long createUserProfile(UserProfile userProfile) {
		
		 int i = jdbcTemplate.update(CREATE_USERPROFILE,userProfile.getUserId(),userProfile.getFirstName(),userProfile.getLastName(),userProfile.getEmailAddress(), userProfile.getMobileNumber(),userProfile.getAddress(),userProfile.getLatitude(),userProfile.getLongitude());
	        
		 if(i>0){
			 
			 return userProfile.getUserId();
		 }
		
		return 0;
	}

	@Override
	public UserProfile getUserProfile(long userId) {
		
		List<UserProfile> userProfileList = null;
		
		List<Map<String, Object>> sqlObjectsList = this.jdbcTemplate.queryForList("SELECT ID, FIRSTNAME, LASTNAME, EMAILADDRESS, PHONENUMBER, ADDRESS, LATITUDE, LONGITUDE FROM USER_PROFILE WHERE ID=?", userId);
		if(sqlObjectsList.size() != 0){
			userProfileList = new ArrayList<UserProfile>();
			for (Map<String, Object> map : sqlObjectsList) {
				userProfileList.add(retrieveUserProfile(map));
			}
			return userProfileList.get(0);
			
		}else{
			return new UserProfile();
		}
	}
	private UserProfile retrieveUserProfile(Map<String, Object> map) {
		
		UserProfile userProfile = new UserProfile();
		
		if (map.get("ID") != null) {
			userProfile.setUserId(Long.parseLong(map.get("ID").toString()));	
		}
		if(map.get("FIRSTNAME")!=null){
			userProfile.setFirstName((String) map.get("FIRSTNAME"));
		}
		if(map.get("LASTNAME")!=null){
			userProfile.setLastName((String) map.get("LASTNAME"));
		}	
		if(map.get("EMAILADDRESS")!=null){
			userProfile.setEmailAddress((String) map.get("EMAILADDRESS"));
		}
		if(map.get("PHONENUMBER")!=null){
			userProfile.setMobileNumber(map.get("PHONENUMBER").toString());
		}
		if(map.get("ADDRESS")!=null){
			userProfile.setAddress((String) map.get("ADDRESS"));
		}
		if(map.get("LATITUDE")!= null){
			userProfile.setLatitude(map.get("LATITUDE").toString());
		}
		if(map.get("LONGITUDE")!= null){
			userProfile.setLongitude(map.get("LONGITUDE").toString());
		}	
		return userProfile;
	}
	
	@Override
	public boolean updateUserProfile(UserProfile userProfile) {
		
		int update = jdbcTemplate.update(UPDATE_USERPROFILE,userProfile.getFirstName(),userProfile.getLastName(),userProfile.getEmailAddress(),userProfile.getMobileNumber(),userProfile.getAddress(),userProfile.getLatitude(),userProfile.getLongitude(),userProfile.getUserId());
		
		if(update>0) {
			return true;
		} else {
		return false;
		}
	
	}

}

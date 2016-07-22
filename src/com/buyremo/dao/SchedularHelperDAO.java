package com.buyremo.dao;

import java.util.Date;
import java.util.List;

import com.buyremo.model.SchedularHelper;
import com.buyremo.model.User;

public interface SchedularHelperDAO {

	public boolean createSchedularData(String fbToken, String postId, String eventCode,String emailAddress);
	
	public SchedularHelper getSchedularData(String postId);

	public SchedularHelper getPostId(String fbToken);

	public SchedularHelper getFbTokenbyEventCode(String eventCode);

	public boolean insertFBTokenByFbId(long fbId, User userDTO);

	String getFbTokenByEmail(String emailAddress);

	List<SchedularHelper> getSchedular(String emailAddress);

	public boolean updateFbToken(String fbToken, Date tokenExpiryDate, SchedularHelper schedularHelper);
	
	boolean updateEmail(String email, SchedularHelper schedularHelperDTO);
	
}

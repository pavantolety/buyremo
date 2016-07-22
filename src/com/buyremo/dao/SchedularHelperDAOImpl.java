package com.buyremo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buyremo.model.SchedularHelper;
import com.buyremo.model.User;

@Repository
public class SchedularHelperDAOImpl implements SchedularHelperDAO {
	
	private static final String CREATE_SCHEDULAR_DATA ="INSERT INTO FB_HELPER(EMAIL_ADDRESS, EVENT_CODE, TRIVIA_POST_ID, FB_TOKEN) VALUES(?,?,?,?) ";
	private static final String GET_SCHEDULAR_DATA_BY_POST_ID ="SELECT EMAIL_ADDRESS, EVENT_CODE, TRIVIA_POST_ID, FB_TOKEN, FB_TOKEN_CREATED_DATE, FB_TOKEN_EXPIRED_DATE FROM FB_HELPER WHERE TRIVIA_POST_ID=?";
	private static final String GET_SCHEDULAR_DATA_BY_FB_TOKEN ="SELECT EMAIL_ADDRESS, EVENT_CODE, TRIVIA_POST_ID, FB_TOKEN, FB_TOKEN_CREATED_DATE, FB_TOKEN_EXPIRED_DATE FROM FB_HELPER WHERE FB_TOKEN=?";
	private static final String GET_SCHEDULAR_DATA_BY_EVENT_CODE ="SELECT EMAIL_ADDRESS, EVENT_CODE, TRIVIA_POST_ID, FB_TOKEN, FB_TOKEN_CREATED_DATE, FB_TOKEN_EXPIRED_DATE FROM FB_HELPER WHERE EVENT_CODE=?";
	private static final String GET_SCHEDULAR_LIST_BY_EMAIL ="SELECT EMAIL_ADDRESS, EVENT_CODE, TRIVIA_POST_ID, FB_TOKEN, FB_TOKEN_CREATED_DATE, FB_TOKEN_EXPIRED_DATE FROM FB_HELPER WHERE EMAIL_ADDRESS=?";
	private static final String UPDATE_FB_TOKEN ="UPDATE FB_HELPER SET FB_TOKEN=?, FB_TOKEN_EXPIRED_DATE=?  WHERE EMAIL_ADDRESS=? ";
	private static final String UPDATE_FB_TOKEN_BY_FB_ID ="UPDATE FB_HELPER SET FB_TOKEN=?  WHERE FB_ID=? ";
	private static final String UPDATE_EMAIL ="INSERT INTO FB_HELPER(EMAIL_ADDRESS) VALUES(?)";
	
	 private JdbcTemplate jdbcTemplate;

	    @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	@Override
	public boolean createSchedularData(String fbToken, String postId,String eventCode, String emailAddress) {
		
		
		boolean isCreated = false;
		
		long result = jdbcTemplate.update(CREATE_SCHEDULAR_DATA, new Object[]{emailAddress,eventCode, postId, fbToken});
		
		if(result>0) {
			isCreated = true;
		}
		
		return isCreated;
	}

	@Override
	public SchedularHelper getSchedularData(String postId) {
		
		List<SchedularHelper> schedularHelperList = null;
		
		List<Map<String, Object>> sqlObjectsList = jdbcTemplate.queryForList(GET_SCHEDULAR_DATA_BY_POST_ID, postId);
		
		if (sqlObjectsList.size() != 0) {
			schedularHelperList = new ArrayList<SchedularHelper>();
			for (Map<String, Object> map : sqlObjectsList) {
				schedularHelperList.add(retrieveSchedular(map));
			}
			return schedularHelperList.get(0);
		}else {
			return new SchedularHelper();
		}		
	}

	@Override
	public SchedularHelper getPostId(String fbToken) {
		
		List<SchedularHelper> schedularHelperList = null;
		
		List<Map<String, Object>> sqlObjectsList = jdbcTemplate.queryForList(GET_SCHEDULAR_DATA_BY_FB_TOKEN, fbToken);
		
		if (sqlObjectsList.size() != 0) {
			schedularHelperList = new ArrayList<SchedularHelper>();
			for (Map<String, Object> map : sqlObjectsList) {
				schedularHelperList.add(retrieveSchedular(map));
			}
			
			return schedularHelperList.get(0);
		} else {
			return new SchedularHelper();
		}		
	}

	@Override
	public SchedularHelper getFbTokenbyEventCode(String eventCode) {

		List<SchedularHelper> schedularHelperList = null;

		List<Map<String, Object>> sqlObjectsList = jdbcTemplate.queryForList(GET_SCHEDULAR_DATA_BY_EVENT_CODE, eventCode);

		if (sqlObjectsList.size() != 0) {
			schedularHelperList = new ArrayList<SchedularHelper>();
			for (Map<String, Object> map : sqlObjectsList) {
				schedularHelperList.add(retrieveSchedular(map));
			}

			return schedularHelperList.get(0);
		} else {
			return new SchedularHelper();
		}

	}

	@Override
	public boolean insertFBTokenByFbId(long fbId, User user) {		
		
		boolean isCreated = false;
		
		long result = jdbcTemplate.update(UPDATE_FB_TOKEN_BY_FB_ID, new Object[]{user.getToken(), fbId});
		
		if(result>0) {
			isCreated = true;
		}
		
		return isCreated;
	}

	@Override
	public String getFbTokenByEmail(String emailAddress) {
		
		List<Map<String, Object>> sqlObjectsList = jdbcTemplate.queryForList(GET_SCHEDULAR_LIST_BY_EMAIL, emailAddress);
		
			String fbToken = (String)sqlObjectsList.get(0).get("FB_TOKEN");

		return fbToken;
	}

	@Override
	public List<SchedularHelper> getSchedular(String emailAddress) {
		
		List<SchedularHelper> schedularHelperList = new ArrayList<SchedularHelper>();
		
		List<Map<String, Object>> sqlObjectsList = jdbcTemplate.queryForList(GET_SCHEDULAR_LIST_BY_EMAIL, emailAddress);
		
		if (sqlObjectsList.size() != 0) {
			schedularHelperList = new ArrayList<SchedularHelper>();
			for (Map<String, Object> map : sqlObjectsList) {
				schedularHelperList.add(retrieveSchedular(map));
			}
		}	

		return schedularHelperList;
	}

	private SchedularHelper retrieveSchedular(Map<String, Object> map) {

		SchedularHelper schedularHelper = new SchedularHelper();
		if(map.get("EMAIL_ADDRESS")!=null){
		schedularHelper.setEmailAddress((String)map.get("EMAIL_ADDRESS"));
		}
		if(map.get("EVENT_CODE")!=null){
		schedularHelper.setEventCode((String)map.get("EVENT_CODE"));
		}
		if(map.get("FB_TOKEN")!=null){
		schedularHelper.setFbToken((String)map.get("FB_TOKEN"));
		}
		if(map.get("TRIVIA_POST_ID")!=null){
		schedularHelper.setPostId((String)map.get("TRIVIA_POST_ID"));
		}
		if(map.get("FB_TOKEN_EXPIRED_DATE")!=null){
		schedularHelper.setTokenExpiryDate((Date)map.get("FB_TOKEN_EXPIRED_DATE"));
		}
		return schedularHelper;
	}

	@Override
	public boolean updateFbToken(String fbToken, Date tokenExpiryDate,SchedularHelper schedularHelper) {		
		
		boolean isCreated = false;
		System.out.println("fb helper email::"+schedularHelper.getEmailAddress());
		long result = jdbcTemplate.update(UPDATE_FB_TOKEN, fbToken, tokenExpiryDate, schedularHelper.getEmailAddress());
		
		if(result>0) {
			isCreated = true;
		}
		
		return isCreated;
	}

	@Override
	public boolean updateEmail(String email, SchedularHelper schedularHelper) {		
		
		boolean isCreated = false;
		
		long result = jdbcTemplate.update(UPDATE_EMAIL,email);
		
		if(result>0) {
			isCreated = true;
		}
		
		return isCreated;
	}
	
}

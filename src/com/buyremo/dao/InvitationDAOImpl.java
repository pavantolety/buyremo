package com.buyremo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buyremo.model.Invitation;

@Repository
public class InvitationDAOImpl implements InvitationDAO{
	
	//Get user by Email Id
	public static final String GET_DEPDT_DEPDT_BY_EMAIL_ID = "SELECT DEPDT_ID,DEPDT_EMAIL,DEPDT_PASS,DEPDT_NAME,DEPDT_MOBILE,DEPDT_HNO,DEPDT_ADDRESSL1,DEPDT_ADDRESSL2,DEPDT_ZIP,DEPDT_STATUS,DATE_CREATED,DATE_UPDATED FROM DEPENDANTS WHERE DEPDT_ID=? AND DEPDT_EMAIL=?";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public boolean insertInvitationMails(Set<String> emails,String senderEmail, long userId) {
		long isUpdated = 0;
		for (String email : emails) {
			String emailAdress = email.trim();
			isUpdated = jdbcTemplate.update("INSERT INTO USER_INVITATIONS (INVITEE_EMAIL ,SENDER_EMAIL,SENDER_ID,DATE_CREATED) VALUES(?,?,?,NOW())",emailAdress,senderEmail, userId);
		}
		if (isUpdated > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Set<String> getInvitedEmails(long senderId, String inviteeEmail) {
		Set<String> emails = new HashSet<String>();
		List<Map<String, Object>> map = jdbcTemplate.queryForList("SELECT INVITEE_EMAIL FROM DEPDT_INVITATIONS WHERE SENDER_ID=? AND SENDER", senderId, inviteeEmail);
		Iterator<Map<String, Object>> iterator = map.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> emailObject = iterator.next();
			emails.add(emailObject.get("INVITEE_EMAIL").toString());

		}
		return emails;
	}

	@Override
	public Invitation getInviteeByEmail(long senderId, String inviteeEmail) {
		List<Invitation> inviteeList = new ArrayList<Invitation>();
		List<Map<String, Object>> invitee = jdbcTemplate.queryForList("SELECT INVITATION_ID,INVITEE_EMAIL,SENDER_ID,SENDER_EMAIL FROM USER_INVITATIONS WHERE SENDER_ID=? AND INVITEE_EMAIL=?", senderId, inviteeEmail);
		if (invitee.size() > 0) {
			for (Map<String, Object> map : invitee) {
				inviteeList.add(retrieveInvitee(map));
			}
			Invitation inviteeData = inviteeList.get(0);
			return inviteeData;
		} else {
			return null;
		}
	}
	
	private Invitation retrieveInvitee(Map<String, Object> map) {
		
		Invitation invitation = new Invitation();
		if(map.get("INVITATION_ID")!= null){
			invitation.setDependantId(Long.parseLong(map.get("INVITATION_ID").toString()));
		}
		if(map.get("INVITEE_NAME")!=null){
			invitation.setDependantName(map.get("INVITEE_NAME").toString());
		}
		if(map.get("SENDER_ID")!=null){
			invitation.setSenderId(Long.parseLong(map.get("SENDER_ID").toString()));
		}
		if(map.get("SENDER_EMAIL")!=null) {
			invitation.setSenderEmail(map.get("SENDER_EMAIL").toString());
		}
		return invitation;
	}
}

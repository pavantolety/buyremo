package com.buyremo.dao;

import java.util.Set;

import com.buyremo.model.Invitation;

public interface InvitationDAO {
	
	public boolean insertInvitationMails(Set<String> emails,String senderEmail, long userId);

	Set<String> getInvitedEmails(long senderId, String inviteeEmail);
	
	Invitation getInviteeByEmail(long senderId, String inviteeEmail);
}

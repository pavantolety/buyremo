package com.buyremo.dao;

import com.buyremo.model.AnonymousAspiration;
import com.buyremo.model.Aspiration;

public interface AspirationDAO {
	
	boolean saveAspirationForUser(Aspiration aspiration);
    
    Aspiration getAspirationCount(long userId);

	boolean changeAspirationStatus(long id, String productStatus);

	boolean addAspirationToCartById(Aspiration Aspiration);
	
	boolean removeCartItemByAspirationId(long aspirationId);
	
	boolean moveCartItemToAspirations(Aspiration aspiration);

	boolean removeAspirationByAspirationId(long id);

	Aspiration getCartItemsCount(long userId);

	boolean saveAnonymousAspirationForUser(
			AnonymousAspiration anonymousAspiration);

	Aspiration getDepdtAspirationCount(long userId);
	
}

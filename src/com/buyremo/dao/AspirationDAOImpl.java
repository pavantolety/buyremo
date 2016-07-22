package com.buyremo.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buyremo.model.AnonymousAspiration;
import com.buyremo.model.Aspiration;

@Repository
public class AspirationDAOImpl implements AspirationDAO{

	
	private static final String ADD_ASPIRATION = "INSERT INTO ASPIRATIONS(USER_ID,ASP_ID,ASP_EMAIL,PROD_ID,PROD_NAME,PROD_DESC,PROD_PRICE,PROD_CATG,PROD_CATG_ID,PROD_GAL_URL,PROD_VIEW_URL,PROD_CURR_ID,PROD_STATUS,DATE_CREATED,DATE_UPDATED,ASP_MESSAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,'ACTIVE',NOW(),NOW(),?)";
	
	private static final String ADD_ANONYMOUS_ASPIRATION = "INSERT INTO ANONYMOUS_ASPIRATIONS(USER_ID,ASPRNT_ID,ASPRNT_EMAIL,ASPRNT_NAME,ASPRNT_MOBILE,ASPRNT_HNO,ASPRNT_ADDRESSL1,ASPRNT_ADDRESSL2,ASPRNT_ZIP,PROD_ID,PROD_NAME,PROD_DESC,PROD_PRICE,PROD_CATG,PROD_CATG_ID,PROD_GAL_URL,PROD_VIEW_URL,PROD_CURR_ID,PROD_STATUS,DATE_CREATED,DATE_UPDATED,ASP_MESSAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'ACTIVE',NOW(),NOW(),?)";
	
	private static final String CHANGE_ASPIRATION_STATUS = "UPDATE ASPIRATIONS SET PROD_STATUS =? WHERE ASPIRATION_ID=?";
	
	private static final String REMOVE_ASPIRATION = "DELETE FROM ASPIRATIONS WHERE ASPIRATION_ID=?";
	
	private static final String REMOVE_CART_ITEM_BY_ID = "DELETE FROM USER_CART WHERE ASPIRATION_ID=?";
		
	private static final String CHECKOUT_ASPIRATION = "INSERT INTO USER_CART(ASPIRATION_ID,USER_ID,ASP_ID,ASP_EMAIL,PROD_ID,PROD_NAME,PROD_DESC,PROD_PRICE,PROD_CATG,PROD_CATG_ID,PROD_GAL_URL,PROD_VIEW_URL,PROD_CURR_ID,PROD_STATUS,DATE_CREATED,DATE_UPDATED,ASP_MESSAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'INIT',NOW(),NOW(),?)";
	
	private static final String MOVE_ASPIRATION = "INSERT INTO USER_CART(ASPIRATION_ID,USER_ID,ASP_ID,ASP_EMAIL,PROD_ID,PROD_NAME,PROD_DESC,PROD_PRICE,PROD_CATG,PROD_CATG_ID,PROD_GAL_URL,PROD_VIEW_URL,PROD_CURR_ID,PROD_STATUS,DATE_CREATED,DATE_UPDATED,ASP_MESSAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'ACTIVE',NOW(),NOW(),?)";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean saveAspirationForUser(Aspiration aspiration) {
		boolean updated = false;
		int i=0;
		try{
			i = jdbcTemplate.update(ADD_ASPIRATION,aspiration.getUserId(),aspiration.getAspirantId(),aspiration.getAspirantEmail(),aspiration.getProductId(),aspiration.getProductName(),aspiration.getProductDesc(),aspiration.getProductPrice(),aspiration.getCategoryName(),aspiration.getCategoryId(),aspiration.getGalleryUrl(),aspiration.getViewItemUrl(),aspiration.getCurrencyId(),aspiration.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(i!=0){
			updated=true;
		}
		return updated;
	}
	
	@Override
	public boolean saveAnonymousAspirationForUser(AnonymousAspiration anonymousAspiration) {
		boolean updated = false;
		int i=0;
		try{
			i = jdbcTemplate.update(ADD_ANONYMOUS_ASPIRATION,anonymousAspiration.getUserId(),anonymousAspiration.getAspirantId(),anonymousAspiration.getAspirantEmail(),anonymousAspiration.getAspirantName(),anonymousAspiration.getMobileNumber(),anonymousAspiration.getHno(),anonymousAspiration.getAddressLine1(),anonymousAspiration.getAddressLine2(),anonymousAspiration.getZip(),anonymousAspiration.getProductId(),anonymousAspiration.getProductName(),anonymousAspiration.getProductDesc(),anonymousAspiration.getProductPrice(),anonymousAspiration.getCategoryName(),anonymousAspiration.getCategoryId(),anonymousAspiration.getGalleryUrl(),anonymousAspiration.getViewItemUrl(),anonymousAspiration.getCurrencyId(),anonymousAspiration.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(i!=0){
			updated=true;
		}
		return updated;
	}

	@Override
	public boolean removeAspirationByAspirationId(long id) {
		boolean updated = false;
		  int i = jdbcTemplate.update(REMOVE_ASPIRATION,id);
		  if(i!=0){
		   updated= true;
		  }
		return updated;
	}

	@Override
	public Aspiration getAspirationCount(long userId) {
		Aspiration aspirationDetails = null;
		List<Map<String, Object>> user = jdbcTemplate.queryForList("SELECT COUNT(*) AS ASP_CNT FROM ASPIRATIONS WHERE USER_ID=?",userId);
		if(!user.isEmpty()) {
			for(Map<String, Object> map: user) {
				aspirationDetails = retrieveAspirations(map);
			}
		}
		return aspirationDetails;
	}
	
	@Override
	public Aspiration getDepdtAspirationCount(long userId) {
		Aspiration aspirationDetails = null;
		List<Map<String, Object>> user = jdbcTemplate.queryForList("SELECT COUNT(*) AS ASP_CNT FROM ASPIRATIONS WHERE ASP_ID=?",userId);
		if(!user.isEmpty()) {
			for(Map<String, Object> map: user) {
				aspirationDetails = retrieveAspirations(map);
			}
		}
		return aspirationDetails;
	}
	
	@Override
	public Aspiration getCartItemsCount(long userId) {
		Aspiration cartDetails = null;
		List<Map<String, Object>> user = jdbcTemplate.queryForList("SELECT COUNT(*) AS CART_CNT FROM USER_CART WHERE USER_ID=?",userId);
		if(!user.isEmpty()) {
			for(Map<String, Object> map: user) {
				cartDetails = retrieveAspirations(map);
			}
		}
		return cartDetails;
	}

	@Override
	public boolean changeAspirationStatus(long id, String productStatus) {
		boolean updated = false;
		  int i = jdbcTemplate.update(CHANGE_ASPIRATION_STATUS,productStatus,id);
		  if(i!=0){
		   updated= true;
		  }
		return updated;
	}
	
	@Override
	public boolean addAspirationToCartById(Aspiration aspiration) {
		boolean updated = false;
		  int i = jdbcTemplate.update(CHECKOUT_ASPIRATION,aspiration.getAspirationId(),aspiration.getUserId(),aspiration.getAspirantId(),aspiration.getAspirantEmail(),aspiration.getProductId(),aspiration.getProductName(),aspiration.getProductDesc(),aspiration.getProductPrice(),aspiration.getCategoryName(),aspiration.getCategoryId(),aspiration.getGalleryUrl(),aspiration.getViewItemUrl(),aspiration.getCurrencyId(),aspiration.getMessage());
		  if(i!=0){
		   updated= true;
		  }
		return updated;
	}

	@Override
	public boolean removeCartItemByAspirationId(long aspirationId) {
		boolean updated = false;
		  int i = jdbcTemplate.update(REMOVE_CART_ITEM_BY_ID,aspirationId);
		  if(i!=0){
		   updated= true;
		  }
		return updated;
	}

	@Override
	public boolean moveCartItemToAspirations(Aspiration aspiration) {
		boolean updated = false;
		  int i = jdbcTemplate.update(MOVE_ASPIRATION,aspiration.getAspirationId(),aspiration.getUserId(),aspiration.getAspirantId(),aspiration.getAspirantEmail(),aspiration.getProductId(),aspiration.getProductName(),aspiration.getProductDesc(),aspiration.getProductPrice(),aspiration.getCategoryName(),aspiration.getCategoryId(),aspiration.getGalleryUrl(),aspiration.getViewItemUrl(),aspiration.getCurrencyId(),aspiration.getMessage());
		  if(i!=0){
		   updated= true;
		  }
		return updated;
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
		if(map.get("CART_CNT")!= null) {
			aspiration.setCartCount(map.get("CART_CNT").toString());
		}
		if(map.get("ASP_CNT")!= null) {
			aspiration.setAspirationCount(map.get("ASP_CNT").toString());
		}
		if(map.get("PROD_STATUS")!= null) {
			aspiration.setProductStatus(map.get("PROD_STATUS").toString());
		}
		return aspiration;
	}

}

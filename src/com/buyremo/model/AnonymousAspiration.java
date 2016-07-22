package com.buyremo.model;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AnonymousAspiration {
	
	private long aspirationId;

	private long aspirantId;

	private String aspirantName;

	private long userId;

	private String productId;

	private String productName;

	private String productDesc;

	private float productPrice;

	private String categoryName;

	private String categoryId;

	private String galleryUrl;

	private String viewItemUrl;

	private String currencyId;

	private Date dateCreated;

	private Date dateUpdated;

	private String productStatus;

	private String aspirantEmail;

	private String parentEmail;

	private String parentName;

	private String message;

	private String aspirationCount;
	
	private String cartCount;
	
	/**
	 * @return the aspirationId
	 */
	public long getAspirationId() {
		return aspirationId;
	}

	/**
	 * @param aspirationId the aspirationId to set
	 */
	public void setAspirationId(long aspirationId) {
		this.aspirationId = aspirationId;
	}

	/**
	 * @return the aspirantId
	 */
	public long getAspirantId() {
		return aspirantId;
	}

	/**
	 * @param aspirantId the aspirantId to set
	 */
	public void setAspirantId(long aspirantId) {
		this.aspirantId = aspirantId;
	}

	/**
	 * @return the aspirantName
	 */
	public String getAspirantName() {
		return aspirantName;
	}

	/**
	 * @param aspirantName the aspirantName to set
	 */
	public void setAspirantName(String aspirantName) {
		this.aspirantName = aspirantName;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the productPrice
	 */
	public float getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the galleryUrl
	 */
	public String getGalleryUrl() {
		return galleryUrl;
	}

	/**
	 * @param galleryUrl the galleryUrl to set
	 */
	public void setGalleryUrl(String galleryUrl) {
		this.galleryUrl = galleryUrl;
	}

	/**
	 * @return the viewItemUrl
	 */
	public String getViewItemUrl() {
		return viewItemUrl;
	}

	/**
	 * @param viewItemUrl the viewItemUrl to set
	 */
	public void setViewItemUrl(String viewItemUrl) {
		this.viewItemUrl = viewItemUrl;
	}

	/**
	 * @return the currencyId
	 */
	public String getCurrencyId() {
		return currencyId;
	}

	/**
	 * @param currencyId the currencyId to set
	 */
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the dateUpdated
	 */
	public Date getDateUpdated() {
		return dateUpdated;
	}

	/**
	 * @param dateUpdated the dateUpdated to set
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	/**
	 * @return the productStatus
	 */
	public String getProductStatus() {
		return productStatus;
	}

	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return the aspirantEmail
	 */
	public String getAspirantEmail() {
		return aspirantEmail;
	}

	/**
	 * @param aspirantEmail the aspirantEmail to set
	 */
	public void setAspirantEmail(String aspirantEmail) {
		this.aspirantEmail = aspirantEmail;
	}

	/**
	 * @return the parentEmail
	 */
	public String getParentEmail() {
		return parentEmail;
	}

	/**
	 * @param parentEmail the parentEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the aspirationCount
	 */
	public String getAspirationCount() {
		return aspirationCount;
	}

	/**
	 * @param aspirationCount the aspirationCount to set
	 */
	public void setAspirationCount(String aspirationCount) {
		this.aspirationCount = aspirationCount;
	}

	/**
	 * @return the cartCount
	 */
	public String getCartCount() {
		return cartCount;
	}

	/**
	 * @param cartCount the cartCount to set
	 */
	public void setCartCount(String cartCount) {
		this.cartCount = cartCount;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the hno
	 */
	public String getHno() {
		return hno;
	}

	/**
	 * @param hno the hno to set
	 */
	public void setHno(String hno) {
		this.hno = hno;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the dependantId
	 */
	public long getDependantId() {
		return dependantId;
	}

	/**
	 * @param dependantId the dependantId to set
	 */
	public void setDependantId(long dependantId) {
		this.dependantId = dependantId;
	}

	private String userName;

	private String emailId;

	private String password;

	private String confirmPassword;

	@Size(min = 5, max = 15)
	@Pattern(regexp = "^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
	private String mobileNumber;

	private String addressLine1;
	
	private String addressLine2;

	private String city;

	private String state;

	private String zip;

	private String status;
	
	private String token;
	
	private String hno;
	
	private String userType;
	
	private long dependantId;
}

package com.buyremo.model;

import java.util.Date;

public class Aspiration {

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
	
	private String aspirationType;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the aspirantEmail
	 */
	public String getParentEmail() {
		return parentEmail;
	}

	/**
	 * @param aspirantEmail
	 *            the aspirantEmail to set
	 */
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	/**
	 * @return the aspirationId
	 */
	public long getAspirantId() {
		return aspirantId;
	}

	/**
	 * @param aspirationId
	 *            the aspirationId to set
	 */
	public void setAspirantId(long aspirantId) {
		this.aspirantId = aspirantId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
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
	 * @param productId
	 *            the productId to set
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
	 * @param productName
	 *            the productName to set
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
	 * @param productDesc
	 *            the productDesc to set
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
	 * @param productPrice
	 *            the productPrice to set
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
	 * @param categoryName
	 *            the categoryName to set
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
	 * @param categoryId
	 *            the categoryId to set
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
	 * @param galleryUrl
	 *            the galleryUrl to set
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
	 * @param viewItemUrl
	 *            the viewItemUrl to set
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
	 * @param currencyId
	 *            the currencyId to set
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
	 * @param dateCreated
	 *            the dateCreated to set
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
	 * @param dateUpdated
	 *            the dateUpdated to set
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
	 * @param productStatus
	 *            the productStatus to set
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return the userEmail
	 */
	public String getAspirantEmail() {
		return aspirantEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setAspirantEmail(String aspirantEmail) {
		this.aspirantEmail = aspirantEmail;
	}

	/**
	 * @return the aspirantName
	 */
	public String getAspirantName() {
		return aspirantName;
	}

	/**
	 * @param aspirantName
	 *            the aspirantName to set
	 */
	public void setAspirantName(String aspirantName) {
		this.aspirantName = aspirantName;
	}

	/**
	 * @return the aspirationId
	 */
	public long getAspirationId() {
		return aspirationId;
	}

	/**
	 * @param aspirationId
	 *            the aspirationId to set
	 */
	public void setAspirationId(long aspirationId) {
		this.aspirationId = aspirationId;
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName
	 *            the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	 * @return the aspirationType
	 */
	public String getAspirationType() {
		return aspirationType;
	}

	/**
	 * @param aspirationType the aspirationType to set
	 */
	public void setAspirationType(String aspirationType) {
		this.aspirationType = aspirationType;
	}

}

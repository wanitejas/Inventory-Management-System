package com.accenture.lkm.business.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/*
 *Declare fields to set or get purchaseid, transactionId, vendor name,material category id,material type id,
 *brand name, unit id,quantity,purchase amount, balance, purchase date,material category name,
  material type name,material unit name and status.
  Validate the fields using spring validation annotations.
 *Generate toString method. Add default and parameterized constructors.
 */
public class PurchaseBean {
	private int purchaseId;
	private String transactionId;
	@NotEmpty(message = "Vendor name is a required field")
	private String vendorName;
	@NotEmpty(message = "Material Category is a required field")
	private String materialCategoryId;
	
	private String materialTypeId;
	@NotEmpty(message = "Brand name is a required field")
	private String brandName;
	
	private String unitId;
	@NotNull(message = "Quantity is a required field")
	private Integer quantity;
	@NotNull(message = "Purchase Ammount is a required field")
	private Double purchaseAmount;
	
	private Double balance;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Purchase Date is a required field")
	private Date purchaseDate;
	private String materialCategoryName;
	private String materialTypeName;
	private String materialUnitName;
	private String status;
	
	public PurchaseBean() {
	}
	
	public PurchaseBean(String transactionId, String vendorName, String materialCategoryId,
			String materialTypeId, String brandName, String unitId, Integer quantity, Double purchaseAmount,
			Date purchaseDate, String materialCategoryName, String materialTypeName, String materialUnitName,
			String status) {
		
		this.transactionId = transactionId;
		this.vendorName = vendorName;
		this.materialCategoryId = materialCategoryId;
		this.materialTypeId = materialTypeId;
		this.brandName = brandName;
		this.unitId = unitId;
		this.quantity = quantity;
		this.purchaseAmount = purchaseAmount;
		this.purchaseDate = purchaseDate;
		this.materialCategoryName = materialCategoryName;
		this.materialTypeName = materialTypeName;
		this.materialUnitName = materialUnitName;
		this.status = status;
	}

	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getMaterialCategoryId() {
		return materialCategoryId;
	}
	public void setMaterialCategoryId(String materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}
	public String getMaterialTypeId() {
		return materialTypeId;
	}
	public void setMaterialTypeId(String materialTypeId) {
		this.materialTypeId = materialTypeId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getMaterialCategoryName() {
		return materialCategoryName;
	}
	public void setMaterialCategoryName(String materialCategoryName) {
		this.materialCategoryName = materialCategoryName;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public String getMaterialUnitName() {
		return materialUnitName;
	}
	public void setMaterialUnitName(String materialUnitName) {
		this.materialUnitName = materialUnitName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PurchaseBean [purchaseId=" + purchaseId + ", transactionId=" + transactionId + ", vendorName="
				+ vendorName + ", materialCategoryId=" + materialCategoryId + ", materialTypeId=" + materialTypeId
				+ ", brandName=" + brandName + ", unitId=" + unitId + ", quantity=" + quantity + ", purchaseAmount="
				+ purchaseAmount + ", balance=" + balance + ", purchaseDate=" + purchaseDate + ", materialCategoryName="
				+ materialCategoryName + ", materialTypeName=" + materialTypeName + ", materialUnitName="
				+ materialUnitName + ", status=" + status + "]";
	}
	
}

package com.accenture.lkm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 *Declare fields to set or get purchaseid, transactionId, vendor name,material category id,material type id,
 *brand name, unit id,quantity,purchase amount, purchase date and status.
 *Generate toString method. Add default and parameterized constructors.
 */
@Entity
@Table(name="purchase")
public class PurchaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private int purchaseId;
	@Column(name = "transaction_id")
	private String transactionId;
	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "material_category_id")
	private String materialCategoryId;
	@Column(name = "material_type_id")
	private String materialTypeId;
	@Column(name = "brandname")
	private String brandName;
	@Column(name = "unit_id")
	private String unitId;
	//@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "purchase_amount")
	private Double purchaseAmount;
	@Column(name = "purchase_date")
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	@Column(name = "material_category_name")
	private String materialCategoryName;
	@Column(name = "material_type_name")
	private String materialTypeName;
	@Column(name = "material_unit_name")
	private String materialUnitName;
	//@Column(name = "status")
	private String status;
	
	
	public PurchaseEntity() {
		super();
	}

	public PurchaseEntity(String transactionId, String vendorName, String materialCategoryId,
			String materialTypeId, String brandName, String unitId, Integer quantity, Double purchaseAmount,
			Date purchaseDate, String materialCategoryName, String materialTypeName, String materialUnitName,
			String status) {
		super();
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	@Override
	public String toString() {
		return "PurchaseEntity [purchaseId=" + purchaseId + ", transactionId=" + transactionId + ", vendorName="
				+ vendorName + ", materialCategoryId=" + materialCategoryId + ", materialTypeId=" + materialTypeId
				+ ", brandName=" + brandName + ", unitId=" + unitId + ", quantity=" + quantity + ", purchaseAmount="
				+ purchaseAmount + ", purchaseDate=" + purchaseDate + ", materialCategoryName=" + materialCategoryName
				+ ", materialTypeName=" + materialTypeName + ", materialUnitName=" + materialUnitName + ", status="
				+ status + "]";
	}	
}
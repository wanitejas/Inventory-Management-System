package com.accenture.lkm.business.bean;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class VendorDateRangeBean {

	@NotEmpty(message="Vendor Name is a required field")
	private String vendorName;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date fromDate;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date toDate;
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
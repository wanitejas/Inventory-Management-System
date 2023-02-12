package com.accenture.lkm.business.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class DateRangeBean {

	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date fromDate;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date toDate;
	
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
package com.accenture.lkm.services;

import java.util.Date;
import java.util.List;

import com.accenture.lkm.business.bean.PurchaseBean;

public interface PurchaseService {
	
	PurchaseBean addPurchaseDetails(PurchaseBean purchaseBean) throws Exception;

	List<PurchaseBean> getPurchaseDetails(Date fromDate, Date toDate);

	List<PurchaseBean> getVendorDetails(String vendorName, Date fromDate, Date toDate);
	
}
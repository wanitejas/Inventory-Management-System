package com.accenture.lkm.dao;

import java.util.Date;
import java.util.List;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.entity.PurchaseEntity;

public interface PurchaseDAO{
	
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception;

	public List<PurchaseBean> getPurchaseDetails(Date fromDate, Date toDate);

	public List<PurchaseBean> getVendorDetails(String vendorName, Date fromDate, Date toDate);

}
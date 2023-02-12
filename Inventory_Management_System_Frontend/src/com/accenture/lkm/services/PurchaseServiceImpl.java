package com.accenture.lkm.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.entity.PurchaseEntity;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private static Logger LOGGER = Logger.getLogger(PurchaseServiceImpl.class);

	// Auto wire PurchaseDAO here
	@Autowired
	PurchaseDAO purchaseDAO;
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details data into the purchase
	 * table. Also, this method will add a single row into the payment table
	 * with paid amount as zero to keep the track of the balance amount for a
	 * specific purchase.
	 * 
	 * @param purchaseBean
	 * @return purchaseBean
	 * @throws Exception
	 */
	@Override
	public PurchaseBean addPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		
		PurchaseBean pb = insertPurchaseDetails(purchaseBean);
		
		return pb;
	}

	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method will be called by addPurchaseDetails method to insert the
	 * data into the Purchase table.
	 * 
	 * @param purchaseBean
	 * @return purchaseBean
	 * @throws Exception
	 */
	private PurchaseBean insertPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		
		PurchaseEntity pe = new PurchaseEntity();
		BeanUtils.copyProperties(purchaseBean, pe);
		
		String tid = transactionIdGenerator(purchaseBean.getVendorName(), purchaseBean.getMaterialCategoryName(), purchaseBean.getPurchaseDate());
		pe.setTransactionId(tid);
		//pe.setMaterialTypeId(purchaseBean.getMaterialTypeId());
		//pe.setUnitId(purchaseBean.getUnitId());
		PurchaseEntity peret = purchaseDAO.savePurchaseDetail(pe);
		
		PurchaseBean pb = new PurchaseBean();
		pb.setPurchaseId(peret.getPurchaseId());
		BeanUtils.copyProperties(peret, pb);
		
		return pb;
	}

	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to generate the transaction id as per logic- P_first
	 * 3 characters of vendor name_purchase date in mmddyyyy format_first 3
	 * characters of material category purchased_primary key of purchase table
	 * 
	 * @param vendorName
	 * @param materialCategoryName
	 * @param purchaseDate
	 * @return String
	 */
	private String transactionIdGenerator(String vendorName, String materialCategoryName, Date purchaseDate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		String pdate = sdf.format(purchaseDate);
		String tid = "P_"+vendorName.substring(0,3).toUpperCase()+"_"+pdate+"_"+materialCategoryName.substring(0, 3).toUpperCase()+"_";
		
		return tid;
	}
	
	@Override
	public List<PurchaseBean> getPurchaseDetails(Date fromDate, Date toDate)
	{
		List<PurchaseBean> pblist = purchaseDAO.getPurchaseDetails(fromDate, toDate);
		return pblist;
	}


	@Override
	public List<PurchaseBean> getVendorDetails(String vendorName, Date fromDate, Date toDate) {
		
		List<PurchaseBean> pblist = purchaseDAO.getVendorDetails(vendorName, fromDate, toDate);
		
		return pblist;
	}
}
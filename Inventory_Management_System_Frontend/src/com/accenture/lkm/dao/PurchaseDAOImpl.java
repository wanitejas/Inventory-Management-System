package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for PurchaseDAO to perform the CRUD operation on
 * Purchase table <br/>
 *
 */
@Repository
@Configurable
@Transactional(value="txManager")
public class PurchaseDAOImpl implements PurchaseDAO {
	
	private static Logger LOGGER = Logger.getLogger(PurchaseDAOImpl.class);

	@PersistenceContext
	EntityManager em;
	
	/*
	 * This method inserts the Purchase Data into the Purchase table.
	 * 
	 * @param purchaseEntity
	 * 
	 * @return PurchaseEntity
	 */
	@Override
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception {
		
		
		em.persist(purchaseEntity);
		
		purchaseEntity.setTransactionId(purchaseEntity.getTransactionId().concat(Integer.toString(purchaseEntity.getPurchaseId())));
		return purchaseEntity;
	}
	
	
	public List<PurchaseBean> getPurchaseDetails(Date fromDate, Date toDate)
	{
		Query q = em.createQuery("select k from PurchaseEntity k where k.purchaseDate between ?1 and ?2");
		q.setParameter(1, fromDate);
		q.setParameter(2, toDate);
		
		List<PurchaseEntity> pelist = q.getResultList();
		List<PurchaseBean> pblist = new ArrayList<PurchaseBean>();
		
		for(PurchaseEntity pe : pelist)
		{
			PurchaseBean pb = new PurchaseBean();
			BeanUtils.copyProperties(pe, pb);
			
			pblist.add(pb);
		}
		
		return pblist;
	}


	@Override
	public List<PurchaseBean> getVendorDetails(String vendorName, Date fromDate, Date toDate) {
		
		Query q = em.createQuery("select k from PurchaseEntity k where (k.purchaseDate between ?1 and ?2) and (k.vendorName=?3)");
		q.setParameter(1, fromDate);
		q.setParameter(2, toDate);
		q.setParameter(3, vendorName);
		
		List<PurchaseEntity> pelist = q.getResultList();
		List<PurchaseBean> pblist = new ArrayList<PurchaseBean>();
		
		for(PurchaseEntity pe : pelist)
		{
			PurchaseBean pb = new PurchaseBean();
			BeanUtils.copyProperties(pe, pb);
			
			pblist.add(pb);
		}
		
		return pblist;
	}
}
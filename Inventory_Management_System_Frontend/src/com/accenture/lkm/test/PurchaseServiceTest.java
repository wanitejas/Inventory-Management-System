package com.accenture.lkm.test;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.services.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:WebContent/WEB-INF/cst-root-ctx.xml")
@Transactional
public class PurchaseServiceTest {

	@Autowired
	PurchaseService purchaseService;
	
	@Test
	public void testAddPurchaseDetails() throws Exception {
		//implementation goes here
		PurchaseBean purchaseBean=new PurchaseBean("P_ONL_03012021_THR_12", "Only Vimal", "C001", "T003", "Zara", "U003", 10, 12345.0, new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01"), "Thread", "Linen", "Yards", "Pending");
		assertNotNull(purchaseService.addPurchaseDetails(purchaseBean));
	}

}

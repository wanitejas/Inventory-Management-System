package com.accenture.lkm.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.lkm.dao.VendorDAO;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(VendorDAOTest.class);

	/*
	 * Autowire the VendorDAO object below
	 */
	
	// Your Code Here

	@Autowired
	private VendorDAO vendorDAO;
	
	/*
	 * Method - notNullVendorDAOTest()
	 * Assert only that VendorDAO object is Not null
	 */
	
	@Test
	public void notNullVendorDAOTest() {
		// Your Code Here
		Assert.assertNotNull(vendorDAO);
	}

	/*
	 * Method - findByIdVendorDAOTest()
	 * Using VendorDAO fetch an entity by its ID --> "V001" 
	 * Assert that the entity fetch and it is Not null 
	 * Assert that the name of the vendor entity fetch is equal to --> "Only Vimal"
	 */

	@Test
	public void findByIdVendorDAOTest() {
		// Your Code Here
		Assert.assertNotNull(vendorDAO.findOne("V001"));
		Assert.assertEquals("Only Vimal",vendorDAO.findOne("V001").getVendorName() );
		//System.out.println(vendorDAO.findOne("V001").getVendorName());
	}
}

package com.accenture.lkm.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.lkm.service.VendorService;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(VendorServiceTest.class);

	/*
	 * Autowire the VendorService object below
	 * 
	 */
	// Your Code Here
	private VendorService vendorService;
	
	/*
	 * Method - notNullVendorServiceTest()
	 * Assert only that VendorService object is Not null
	 * 
	 */

	@Test
	public void notNullVendorServiceTest() {
		// Your Code Here
		Assert.assertNotNull(vendorService);
	}

	/*
	 * Method - notNullGetVendorDetailsTest()
	 * Assert only that VendorService getVendorDetails is not returning a null value
	 * 
	 */

	@Test
	public void notNullGetVendorDetailsTest() {
		// Your Code Here
		Assert.assertNotNull(vendorService.getVendorDetails());
	}

	/*
	 * Method - countGetVendorDetailsTest()
	 * Assert only that VendorService getVendorDetails list size matches to 5
	 * 
	 */

	@Test
	public void countGetVendorDetailsTest() {
		// Your Code Here
		Assert.assertEquals(5,vendorService.getVendorDetails().size());
	}

	/*
	 * Method - recordGetVendorDetailsTest()
	 * Assert only that VendorService getVendorDetails first bean name matches --> "Only Vimal"
	 * 
	 */
		
	@Test
	public void recordGetVendorDetailsTest() {
		Assert.assertEquals("Only Vimal",vendorService.getVendorDetails().get(0).getVendorName());
	}

}
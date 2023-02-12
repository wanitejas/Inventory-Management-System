package com.accenture.lkm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.service.VendorService;

@RestController
public class VendorController {

	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);
	
	/*
	 * Autowire the VendorService object
	 * 
	 * */
	// Your Code Here
	@Autowired
	private VendorService vendorService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "Welcome to Spring Boot Vendor Service API!";
	}

	
	/* 
	 * Method - getVendorDetails()
	 * Fetch all the vendor details using VendorService and store it inside a List
	 * Return a ResponseEntity object passing the list of vendor details
	 * 
	 */
	
	@RequestMapping(value="/vendor/controller/getVendors", method=RequestMethod.GET)
	public ResponseEntity<List<VendorBean>> getVendorDetails() {
		// Your Code Here
	    List<VendorBean> vendorBeanList = vendorService.getVendorDetails();       
	    return new ResponseEntity<List<VendorBean>>(vendorBeanList,HttpStatus.OK);  
			
	}
}

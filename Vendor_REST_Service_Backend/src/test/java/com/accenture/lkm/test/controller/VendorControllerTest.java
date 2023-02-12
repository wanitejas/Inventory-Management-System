package com.accenture.lkm.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.controller.VendorController;
import com.accenture.lkm.service.VendorService;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest(VendorController.class)
public class VendorControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(VendorControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VendorService vendorServiceMock;

	@Test
	public void indexVendorControllerTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk())
				.andExpect(content().string("Welcome to Spring Boot Vendor Service API!"));
	}

	/*
	 * 
	 * Once you have completed the other test cases execute the below test case it should fail
	 * After modifying the VendorController class execute the below test case it should pass
	 * 
	 * */
	
	
	@Test
	public void vendorListVendorControllerTest() throws Exception {

		
		  VendorBean bean = new VendorBean("V001", "Only Vimal", "Stock Home Road,Sector 22, New Delhi-110 001", "John", "9002348970"); 
		  List<VendorBean> vendorBeans = new ArrayList<>(); 
		  vendorBeans.add(bean);
		  
		  when(vendorServiceMock.getVendorDetails()).thenReturn(vendorBeans);
		  
		  mockMvc.perform(MockMvcRequestBuilders.get("/vendor/controller/getVendors"))
		  .andExpect(status().isOk())
		  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
		  
		  // check size of json object 
		  .andExpect(jsonPath("$", hasSize(1)))
		  
		  //check if the json node exsits
		  .andExpect(jsonPath("$[0].vendorId").exists())
		  .andExpect(jsonPath("$[0].vendorName").exists())
		  .andExpect(jsonPath("$[0].vendorAddress").exists())
		  .andExpect(jsonPath("$[0].contactPerson").exists())
		  .andExpect(jsonPath("$[0].contactNumber").exists())
		  
		  //check the type of json node
		  .andExpect(jsonPath("$[0].vendorId").isString())
		  .andExpect(jsonPath("$[0].vendorName").isString())
		  .andExpect(jsonPath("$[0].vendorAddress").isString())
		  .andExpect(jsonPath("$[0].contactPerson").isString())
		  .andExpect(jsonPath("$[0].contactNumber").isString())
		  
		  //check the return value 
		  .andExpect(jsonPath("$[0].vendorId").value("V001"))
		  .andExpect(jsonPath("$[0].vendorName").value("Only Vimal"))
		  .andExpect(jsonPath("$[0].vendorAddress").value("Stock Home Road,Sector 22, New Delhi-110 001"))
		  .andExpect(jsonPath("$[0].contactPerson").value("John"))
		  .andExpect(jsonPath("$[0].contactNumber").value("9002348970"));
		 		

	}

}

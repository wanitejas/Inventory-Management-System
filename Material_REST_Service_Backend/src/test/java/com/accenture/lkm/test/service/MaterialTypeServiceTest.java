package com.accenture.lkm.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.lkm.service.MaterialService;
import com.accenture.lkm.service.MaterialTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialTypeServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MaterialTypeServiceTest.class);
	
	
	@Autowired
	MaterialTypeService materialTypeService;

	
	
	@Test
	public void notNullMaterialServiceTest() {
		// Your Code Here
		assertNotNull("null", materialTypeService);
		
	}
	
		
	
	@Test
	public void getMaterialTypeByIdTest() {
		// Your Code Here
		assertNotNull("Null", materialTypeService.getMaterialTypesBasedOnCategoryId("C001"));
		
	}
	
	
	
	@Test
	public void getMaterialTypesTest() {
		// Your Code Here
		assertEquals(7, materialTypeService.getMaterialTypes().size());

	}
	
}

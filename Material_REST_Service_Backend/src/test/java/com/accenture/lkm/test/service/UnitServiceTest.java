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
import com.accenture.lkm.service.UnitService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(UnitServiceTest.class);
	
	
	@Autowired
	UnitService unitService;

	
	
	@Test
	public void notNullUnitServiceTest() {
		// Your Code Here
		assertNotNull("null", unitService);
		
	}
	
		
	
	@Test
	public void getUnitByIdTest() {
		// Your Code Here
		assertNotNull("Null", unitService.getUnitsBasedOnCategoryId("C001"));
		
	}
	
	
	
	@Test
	public void getUnitTest() {
		// Your Code Here
		assertEquals(5, unitService.getUnits().size());

	}
	
}

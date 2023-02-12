package com.accenture.lkm.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.lkm.dao.MaterialCategoryDAO;
import com.accenture.lkm.dao.MaterialTypeDAO;
import com.accenture.lkm.dao.UnitDAO;
import com.accenture.lkm.entity.MaterialCategoryEntity;
import com.accenture.lkm.entity.MaterialTypeEntity;
import com.accenture.lkm.entity.UnitEntity;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(UnitDAOTest.class);
	
	@Autowired
	private UnitDAO unitDao;
	
	
	@Test
	public void notNullUnitDAOTest() {
		assertNotNull("Null",unitDao);
	
	}
	
			
	@Test
	public void getUnitsTest() {
		// Your Code Here
		List<UnitEntity> list = unitDao.findAll();
		assertNotNull("Null",list);
		assertEquals(5,list.size());
	}
	
}












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
import com.accenture.lkm.entity.MaterialCategoryEntity;
import com.accenture.lkm.entity.MaterialTypeEntity;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialTypeDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MaterialTypeDAOTest.class);
	
	@Autowired
	private MaterialTypeDAO materialTypeDao;
	
	
	@Test
	public void notNullMaterialTypeDAOTest() {
		assertNotNull("Null",materialTypeDao);
	
	}
	
			
	@Test
	public void findAllMaterialCategoryTest() {
		// Your Code Here
		List<MaterialTypeEntity> list = materialTypeDao.findAll();
		assertNotNull("Null",list);
		assertEquals(7,list.size());
	}
	
}












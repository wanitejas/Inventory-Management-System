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

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.controller.MaterialTypeController;
import com.accenture.lkm.controller.UnitController;
import com.accenture.lkm.service.MaterialService;
import com.accenture.lkm.service.MaterialTypeService;
import com.accenture.lkm.service.UnitService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(UnitController.class)
public class UnitControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(UnitControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UnitService unitServiceMock;
	
	
	

	@Test
	public void getUnitTest() throws Exception {

		List<UnitBean> unitBeans = new ArrayList<>();
		
		UnitBean bean = new UnitBean("U001","Metres","C001");
		unitBeans.add(bean);

		bean = new UnitBean("U002","Metres","C002");
		unitBeans.add(bean);		

		bean = new UnitBean("U003","Yards","C001");
		unitBeans.add(bean);	
		
		bean = new UnitBean("U004","Yards","C002");
		unitBeans.add(bean);
		
		bean = new UnitBean("U005","Kilograms","C003");
		unitBeans.add(bean);
		

		when(unitServiceMock.getUnits()).thenReturn(unitBeans);		
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/unit/controller/getUnitDetails"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$",hasSize(5)))

		//check if the json node exsits
        .andExpect(jsonPath("$[0].unitId").exists())
        .andExpect(jsonPath("$[0].unitName").exists())
        .andExpect(jsonPath("$[0].categoryId").exists())
        //check the type of json node
        .andExpect(jsonPath("$[0].unitId").isString())
        .andExpect(jsonPath("$[0].unitName").isString())
        .andExpect(jsonPath("$[0].categoryId").isString())
        //check the return value
        .andExpect(jsonPath("$[0].unitId").value("U001"))
        .andExpect(jsonPath("$[0].unitName").value("Metres"))
		.andExpect(jsonPath("$[0].categoryId").value("C001"));
		
	}
	
	

	/*
	 * 
	 * Uncomment the below test for testing the '/material/controller/getMaterialCategoryById/{categoryId}' mapping and it should fail
	 * After implementing the handler method in MaterialController execute the test again
	 * The below test case should pass once the MaterialController handler method is implemented properly
	 * 
	 * */	
	
	
	@Test
	public void getMaterialCategoryByIdTest() throws Exception {
		String categoryId = "C001";
		List<UnitBean> list = new ArrayList<UnitBean>();
		list.add(0, new UnitBean("U001","Metres","C001"));
		list.add(1, new UnitBean("U002","Yards","C001"));
		
		when(unitServiceMock.getUnitsBasedOnCategoryId(categoryId)).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/unit/controller/getUnitsByCategoryId/"+categoryId))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

		//check if the json node exsits
        .andExpect(jsonPath("$[0].unitId").exists())
        .andExpect(jsonPath("$[0].unitName").exists())
        .andExpect(jsonPath("$[0].categoryId").exists())
        
        //check the type of json node
        .andExpect(jsonPath("$[0].unitId").isString())
        .andExpect(jsonPath("$[0].unitName").isString())
        .andExpect(jsonPath("$[0].categoryId").isString())
        		
        .andExpect(jsonPath("$[0].unitId").value("U001"))
        .andExpect(jsonPath("$[0].unitName").value("Metres"))
        .andExpect(jsonPath("$[0].categoryId").value("C001"));
        
		
	}
	

	
}

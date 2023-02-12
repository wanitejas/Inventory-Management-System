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
import com.accenture.lkm.service.MaterialService;
import com.accenture.lkm.service.MaterialTypeService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MaterialTypeController.class)
public class MaterialTypeControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(MaterialTypeControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MaterialTypeService materialTypeServiceMock;
	
	
	

	@Test
	public void getMaterialTypeTest() throws Exception {

		List<MaterialTypeBean> materialTypeBeans = new ArrayList<>();
		
		MaterialTypeBean bean = new MaterialTypeBean("T001","Silk","C001");
		materialTypeBeans.add(bean);

		bean = new MaterialTypeBean("T002","Silk","C002");
		materialTypeBeans.add(bean);		

		bean = new MaterialTypeBean("T003","Linen","C001");
		materialTypeBeans.add(bean);	
		
		bean = new MaterialTypeBean("T004","Linen","C002");
		materialTypeBeans.add(bean);
		
		bean = new MaterialTypeBean("T005","Silk Cotton","C003");
		materialTypeBeans.add(bean);
		
		bean = new MaterialTypeBean("T006","Suit","C003");
		materialTypeBeans.add(bean);
		
		bean = new MaterialTypeBean("T007","Silk Cotton","C002");
		materialTypeBeans.add(bean);

		when(materialTypeServiceMock.getMaterialTypes()).thenReturn(materialTypeBeans);		
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/type/controller/getTypeDetails"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$",hasSize(7)))

		//check if the json node exsits
        .andExpect(jsonPath("$[0].typeId").exists())
        .andExpect(jsonPath("$[0].typeName").exists())
        .andExpect(jsonPath("$[0].categoryId").exists())
        //check the type of json node
        .andExpect(jsonPath("$[0].typeId").isString())
        .andExpect(jsonPath("$[0].typeName").isString())
        .andExpect(jsonPath("$[0].categoryId").isString())
        //check the return value
        .andExpect(jsonPath("$[0].typeId").value("T001"))
        .andExpect(jsonPath("$[0].typeName").value("Silk"))
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
	public void getMaterialTypeCategoryByIdTest() throws Exception {
		String categoryId = "C001";
		List<MaterialTypeBean> list = new ArrayList<MaterialTypeBean>();
		list.add(0, new MaterialTypeBean("T001","Silk","C001"));
		list.add(1, new MaterialTypeBean("T003","Linen","C001"));
		
		when(materialTypeServiceMock.getMaterialTypesBasedOnCategoryId(categoryId)).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/type/controller/getTypeDetailsByCategoryId/"+categoryId))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

		//check if the json node exsits
        .andExpect(jsonPath("$[0].typeId").exists())
        .andExpect(jsonPath("$[0].typeName").exists())
        .andExpect(jsonPath("$[0].categoryId").exists())
        
        //check the type of json node
        .andExpect(jsonPath("$[0].typeId").isString())
        .andExpect(jsonPath("$[0].typeName").isString())
        .andExpect(jsonPath("$[0].categoryId").isString())
        		
        .andExpect(jsonPath("$[0].typeId").value("T001"))
        .andExpect(jsonPath("$[0].typeName").value("Silk"))
        .andExpect(jsonPath("$[0].categoryId").value("C001"));
		
	}

	
}

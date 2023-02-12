 package com.accenture.lkm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PurchaseService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;
import com.accenture.lkm.web.controller.PurchaseEntryController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/cst-root-ctx.xml"})
@WebAppConfiguration
public class PurchaseEntryControllerTest {
	
	private static MockMvc mockMvc;
	
	@InjectMocks
	private PurchaseEntryController purchaseEntryController;
	
	@Mock
	PurchaseService purchaseService;
	
	@Mock
	static VendorServiceConsumer vendorServiceConsumer;
	
	@Mock
	static MaterialCategoryConsumer materialCategoryConsumer;
	
	@Mock
	static UnitServiceConsumer unitServiceConsumer;
	
	@Mock
	static MaterialTypeConsumer materialTypeConsumer;
	
	List<VendorBean> vendorBeans;
	List<MaterialCategoryBean> materialCategoryBeans ;
	PurchaseBean pBean,bean2;
	
	@Before
	public void initialize() throws Exception {
		VendorBean bean=new VendorBean("V001","Only Vimal", "Stock Home Road,Sector 22, New Delhi-110 001","John",9002348970L);
		vendorBeans = new ArrayList<VendorBean>();
		vendorBeans.add(bean);
		
		MaterialCategoryBean bean1=new MaterialCategoryBean("C001","Thread");
		materialCategoryBeans = new ArrayList<MaterialCategoryBean>();
		materialCategoryBeans.add(bean1);
		
		pBean=new PurchaseBean();
		pBean.setVendorName("Only Vimal");
		pBean.setMaterialCategoryId("C002");
		pBean.setMaterialTypeId("T002");
		pBean.setUnitId("U002");
		pBean.setBrandName("Otto");
		pBean.setQuantity(3);
		pBean.setPurchaseAmount(23000.0);
		pBean.setPurchaseDate(new Date("1-Jan-2020"));
		
		bean2=new PurchaseBean("P_ONL_03012021_THR_12", "Only Vimal", "C001", "T003", "Zara", "U003", 10, 12345.0, new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01"), "Thread", "Linen", "Yards", "Pending");	
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(purchaseEntryController).build();
	}
	

	@Test
	public void testPurchaseEntry() throws Exception {		
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/purchaseEntry.html")).andReturn();
		assertEquals("PurchaseEntry",mvcResult.getModelAndView().getViewName());
	}
	
	@Test
	public void testGenerateVendorList() throws Exception {
		when(vendorServiceConsumer.getVendorBeanList()).thenReturn(vendorBeans);
		List<VendorBean> vList=vendorServiceConsumer.getVendorBeanList();
		assertNotNull(vList);
		assertEquals("V001",vList.get(0).getVendorId());
		assertEquals("Only Vimal",vList.get(0).getVendorName());
	}

	@Test
	public void testGenerateUnitAndTypeList() throws Exception {
		List<MaterialTypeBean> typeList=new ArrayList<>();
		typeList.add(new MaterialTypeBean("T007","Silk Cotton","C002"));
		typeList.add(new MaterialTypeBean("T004","Linen","C002"));
		when(materialTypeConsumer.hitGetTypesBasedOnCategoryId("C002")).thenReturn(typeList);
		when(materialTypeConsumer.getMaterialTypeBeanList()).thenReturn(typeList);
		
		List<UnitBean> unitList=new ArrayList<>();
		unitList.add(new UnitBean("U001","Meters"));
		unitList.add(new UnitBean("U002","Yards"));
		when(unitServiceConsumer.hitGetUnitsByCategoryId("C002")).thenReturn(unitList);
		when(unitServiceConsumer.getUnitBeanList()).thenReturn(unitList);
		
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/getUnitAndTypeList.html").sessionAttr("purchaseBean", pBean)).andReturn();
		assertEquals("PurchaseEntry",mvcResult.getModelAndView().getViewName());
		assertNotNull(mvcResult.getModelAndView().getModel().get("mtbl").equals(typeList));
		assertNotNull(mvcResult.getModelAndView().getModel().get("ubl").equals(unitList));
	}
	
	@Test
	public void testGenerateCategoryList() throws MicroServiceException {
		when(materialCategoryConsumer.getMaterialCategoryBeanList()).thenReturn(materialCategoryBeans);
		List<MaterialCategoryBean> bList=materialCategoryConsumer.getMaterialCategoryBeanList();
		assertNotNull(bList);
		assertEquals("C001",bList.get(0).getCategoryId());
		assertEquals("Thread",bList.get(0).getCategoryName());
	}

	@Test
	public void testAddPurchaseDetail() throws Exception {
		
		when(purchaseService.addPurchaseDetails(pBean)).thenReturn(bean2);
		
		when(materialCategoryConsumer.hitGetMaterialCategoryById("C002")).thenReturn(new MaterialCategoryBean("C002","Cloth"));
		
		Map<String,String> map=new LinkedHashMap<String,String>();
		
		map.put("T002", "Linen");
		when(materialTypeConsumer.getCategoryTypeMap()).thenReturn(map);
		
		map=new LinkedHashMap<String,String>();
		map.put("U002","Yards");
		when(unitServiceConsumer.getUnitMap()).thenReturn(map);
		
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/addPurchaseDetail.html").sessionAttr("purchaseBean", pBean)).andReturn();
		assertEquals("PurchaseSuccess",mvcResult.getModelAndView().getViewName());
		assertNotNull(mvcResult.getModelAndView().getModel().get("purchaseBean").equals(bean2));
	}
}

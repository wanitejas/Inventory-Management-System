package com.accenture.lkm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
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

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * A controller class for receiving and handling all material purchase related
 * transactions from the User Interface. <br/>
 *
 */
@Controller
@SessionAttributes({ "purchaseBean" })
public class PurchaseEntryController {

	private static Logger LOGGER = Logger.getLogger(PurchaseEntryController.class);

	// Auto wire PurchaseService here
	@Autowired
	PurchaseService purchaseService;

	// Auto wire VendorServiceConsumer here
	@Autowired
	VendorServiceConsumer vendorServiceConsumer;
	
	// Auto wire MaterialCategoryConsumer here
	@Autowired
	MaterialCategoryConsumer materialCategoryConsumer;
	
	// Auto wire UnitServiceConsumer here
	@Autowired
	UnitServiceConsumer unitServiceConsumer;
	
	// Auto wire MaterialTypeConsumer here
	@Autowired
	MaterialTypeConsumer materialTypeConsumer;
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method sets PurchaseBean into the model attribute and redirects to
	 * PurchaseEntry.jsp.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "purchaseEntry.html", method = RequestMethod.GET)
	public ModelAndView purchaseEntry() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("PurchaseEntry");
		mv.addObject("purchaseBean",new PurchaseBean());
		
		return mv;
	}

	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the vendor list to be populated on the
	 * PurchasEntry.jsp. getVendorBeanList method of VendorServiceConsumer is
	 * called to get the vendor list.
	 * 
	 * @return vendorList - List of vendor values
	 * @throws MicroServiceException
	 */
	@ModelAttribute("vendorList")
	public List<VendorBean> generateVendorList() throws MicroServiceException {

		return vendorServiceConsumer.getVendorBeanList();
	}

	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the material category list to be populated on the
	 * PurchasEntry.jsp. getMaterialCategoryBeanList method of
	 * MaterialCategoryConsumer is called to get the material category list.
	 * 
	 * @return List - MaterialCategoryBean
	 * @throws MicroServiceException
	 */
	@ModelAttribute("categoryList")
	public List<MaterialCategoryBean> generateCategoryList() throws MicroServiceException {

		return materialCategoryConsumer.getMaterialCategoryBeanList();
	}
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the material unit and type list to be populated in
	 * PurchaseEntry.jsp for chosen material category. hitGetUnitsByCategoryId
	 * method of UnitServiceConsumer class to be called to get the list of
	 * material unit. hitGetTypesBasedOnCategoryId method of
	 * MaterialTypeConsumer class to be called to get the list of material type.
	 * 
	 * @param purchaseBean
	 * @param HttpSession
	 * @return ModelAndView
	 * @throws MicroServiceException
	 */
	@RequestMapping(value = "getUnitAndTypeList.html", method = RequestMethod.POST)
	public ModelAndView generateUnitAndTypeList(@ModelAttribute("purchaseBean") PurchaseBean purchaseBean,
			HttpSession session) throws MicroServiceException {
		
		ModelAndView mv = new ModelAndView();
		String catid = purchaseBean.getMaterialCategoryId();
		List<MaterialTypeBean> mtblist = materialTypeConsumer.hitGetTypesBasedOnCategoryId(catid);
		List<UnitBean> ublist = unitServiceConsumer.hitGetUnitsByCategoryId(catid);
		
		
		  Map<String, String> materialTypeMap = new HashMap<String, String>();
		  Map<String, String> unitMap = new HashMap<String, String>();
		  
		  for(MaterialTypeBean mtb : mtblist) { materialTypeMap.put(mtb.getTypeId(),
		  mtb.getTypeName()); }
		  
		  for(UnitBean ub : ublist) { unitMap.put(ub.getUnitId(), ub.getUnitName()); }
		 
			
		mv.setViewName("PurchaseEntry");
		mv.addObject("mtbl", materialTypeMap);
		session.setAttribute("mtbl", materialTypeMap);
		session.setAttribute("ubl", unitMap);
		mv.addObject("ubl", unitMap);
		return mv;
	}

	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details filled on
	 * PurchaseEntry.jsp in to the purchase and payment table. Upon successful
	 * insert redirects to PurchaseSuccess.jsp
	 * 
	 * @param purchaseBean
	 * @param BindingResult
	 * @param ModelMap
	 * @param HttpSession
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "addPurchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView addPurchaseDetail(@ModelAttribute("purchaseBean") @Valid PurchaseBean purchaseBean,
			BindingResult result, ModelMap map, HttpSession session) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		
		purchaseBean.setMaterialCategoryName(materialCategoryConsumer.getCategoryMap().get(purchaseBean.getMaterialCategoryId()));
        purchaseBean.setMaterialTypeName(materialTypeConsumer.getCategoryTypeMap().get(purchaseBean.getMaterialTypeId()));
        purchaseBean.setMaterialUnitName(unitServiceConsumer.getUnitMap().get(purchaseBean.getUnitId()));
		
		
		if(result.hasErrors())
		{
			mv.setViewName("PurchaseEntry");
		}
		else
		{
			System.out.println(purchaseBean);
			purchaseBean.setStatus("Pending");
			PurchaseBean pb = purchaseService.addPurchaseDetails(purchaseBean);
			
			if(pb!=null)
			{
				mv.setViewName("PurchaseSuccess");
				mv.addObject("pb", pb);
				mv.addObject("msg", "Data added successfully!");
			}
		}
		
		return mv;
	}
}
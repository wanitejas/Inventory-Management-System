package com.accenture.lkm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.accenture.lkm.business.bean.DateRangeBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.business.bean.VendorDateRangeBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PurchaseService;
import com.accenture.lkm.web.client.VendorServiceConsumer;

@Controller
public class ReportController {

	@Autowired
	PurchaseService ps;
	
	@Autowired
	VendorServiceConsumer vsc;
	
	@RequestMapping(value="loadReport.html")
	public ModelAndView loadReportPage() throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("PurchaseReport");
		mv.addObject("dateRangeBean", new DateRangeBean());
		
		return mv;	
	}
	
	
	@RequestMapping(value="getRecords.html")
	public ModelAndView getPurchaseRecords(@ModelAttribute("dateRangeBean") @Valid DateRangeBean drbean, BindingResult errors) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		
		if(errors.hasErrors())
			mv.setViewName("PurchaseReport");
		else
		{
			List<PurchaseBean> pblist = ps.getPurchaseDetails(drbean.getFromDate(), drbean.getToDate());
			mv.setViewName("PurchaseReport");
			mv.addObject("pbli",pblist);
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="loadVendorReport.html")
	public ModelAndView loadVendorReportPage() throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("VendorReport");
		mv.addObject("vendorDateRangeBean", new VendorDateRangeBean());
		
		return mv;	
	}
	
	
	@ModelAttribute("vendorList")
	public List<VendorBean> generateVendorList() throws MicroServiceException {

		return vsc.getVendorBeanList();
	}
	
	
	@RequestMapping(value="getVendorRecords.html")
	public ModelAndView getPurchaseVendorRecords(@ModelAttribute("vendorDateRangeBean") @Valid VendorDateRangeBean vdrbean, BindingResult errors) throws Exception
	{
		System.out.println("Hi");
		ModelAndView mv = new ModelAndView();
		
		if(errors.hasErrors())
			mv.setViewName("VendorReport");
		else
		{
			List<PurchaseBean> pblist = ps.getVendorDetails(vdrbean.getVendorName(), vdrbean.getFromDate(), vdrbean.getToDate());
			System.out.println(pblist);
			mv.setViewName("VendorReport");
			mv.addObject("pbli",pblist);
		}
		
		return mv;
	
	}
	
}
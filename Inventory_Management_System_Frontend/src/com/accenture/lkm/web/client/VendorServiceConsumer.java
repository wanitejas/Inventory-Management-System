package com.accenture.lkm.web.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class VendorServiceConsumer {

	private static Logger LOGGER = Logger.getLogger(VendorServiceConsumer.class);

	@Value("${VendorServiceConsumer.serviceURL}")
	private String serviceURL;

	@Value("${VendorServiceConsumer.apiURL}")
	private String apiURL;

	private List<VendorBean> vendorBeanList;

	private Map<String, VendorBean> vendorMap;

	private RestTemplate restTemplate;

	
	public Map<String, VendorBean> getVendorMap() throws MicroServiceException {
		
		vendorMap = new HashMap<String, VendorBean>();
		
		for(VendorBean vb : getVendorBeanList())
		{
			vendorMap.put(vb.getVendorId(), vb);
		}

		return vendorMap;
	}
	
	
	public List<VendorBean> getVendorBeanList() throws MicroServiceException {
		
		hitGetVendorDetails();
		return vendorBeanList;
	}

	
	public VendorServiceConsumer() {
		
		restTemplate = new RestTemplate();
	}

	
	/**
	 * This method is hitting vendor microservice to get the list of vendors
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetVendorDetails() throws MicroServiceException {

		ResponseEntity<VendorBean[]> response = restTemplate.getForEntity(serviceURL + apiURL, VendorBean[].class);
		List<VendorBean> vblist = Arrays.asList(response.getBody());
		
		this.vendorBeanList = vblist;
	}
}
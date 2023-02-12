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
import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class UnitServiceConsumer {

	private static Logger LOGGER = Logger.getLogger(UnitServiceConsumer.class);

	@Value("${MaterialServiceConsumer.serviceURL}")
	private String serviceURL;

	@Value("${UnitServiceConsumer.apiURL}")
	private String apiURL;

	@Value("${UnitServiceConsumer.apiURLByCategoryId}")
	private String apiURLByCategoryId;

	private List<UnitBean> unitBeanList;

	private Map<String, String> unitMap;

	private RestTemplate restTemplate;

	
	public Map<String, String> getUnitMap() throws MicroServiceException {
		
		unitMap = new HashMap<String, String>();
		
		for(UnitBean ub : getUnitBeanList())
		{
			unitMap.put(ub.getUnitId(), ub.getUnitName());
		}
		
		return unitMap;
	}
	
	
	public List<UnitBean> getUnitBeanList() throws MicroServiceException {
		
		hitGetUnitDetails();
		return unitBeanList;
	}

	
	public UnitServiceConsumer() {
		
		restTemplate = new RestTemplate();
	}

	
	/**
	 * This method hits material microservice to get the list of unit.
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetUnitDetails() throws MicroServiceException {

		ResponseEntity<UnitBean[]> response = restTemplate.getForEntity(serviceURL + apiURL, UnitBean[].class);
		List<UnitBean> ublist = Arrays.asList(response.getBody());
		
		this.unitBeanList = ublist;
	}

	
	/**
	 * This method hits material microservice to get the list of unit available
	 * for a given category id.
	 * 
	 * @param categoryId
	 * @return List<UnitBean>
	 * @throws MicroServiceException
	 */
	public List<UnitBean> hitGetUnitsByCategoryId(String categoryId) throws MicroServiceException {
		
		ResponseEntity<UnitBean[]> response = restTemplate.getForEntity(serviceURL + apiURLByCategoryId + categoryId, UnitBean[].class);
		List<UnitBean> ublist = Arrays.asList(response.getBody());
		
		return ublist;
	}
}
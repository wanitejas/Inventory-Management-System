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
import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class MaterialCategoryConsumer {

	private static Logger LOGGER = Logger.getLogger(MaterialCategoryConsumer.class);

	@Value("${MaterialServiceConsumer.serviceURL}")
	private String serviceURL;
	
	@Value("${MaterialCategoryConsumer.apiURL}")
	private String apiURL;
	
	@Value("${MaterialCategoryConsumer.apiURLForById}")
	private String apiURLForById;
	
	private RestTemplate restTemplate;
	
	private List<MaterialCategoryBean> materialCategoryBeanList;
	
	private Map<String, String> categoryMap;

	
	public Map<String, String> getCategoryMap() throws MicroServiceException {
		
		categoryMap = new HashMap<String,String>();
		
		for(MaterialCategoryBean mcb : getMaterialCategoryBeanList())
		{
			categoryMap.put(mcb.getCategoryId(), mcb.getCategoryName());
		}
		
		return categoryMap;
	}

	
	public List<MaterialCategoryBean> getMaterialCategoryBeanList() throws MicroServiceException {
		
		hitGetMaterialCategories();
		return materialCategoryBeanList;
	}

	
	public MaterialCategoryConsumer() {
		
		restTemplate = new RestTemplate();
	}

	
	/**
	 * This method hits material microservice to get the list of Material
	 * category.
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetMaterialCategories() throws MicroServiceException {
		
		ResponseEntity<MaterialCategoryBean[]> response = restTemplate.getForEntity(serviceURL + apiURL, MaterialCategoryBean[].class);
		List<MaterialCategoryBean> mcblist = Arrays.asList(response.getBody());
		
		this.materialCategoryBeanList = mcblist;
	}

	
	/**
	 * This method hits material microservice to get the details of Material
	 * category for given category id.
	 * 
	 * @param categoryId
	 * @return MaterialCategoryBean
	 * @throws MicroServiceException
	 */
	public MaterialCategoryBean hitGetMaterialCategoryById(String categoryId) throws MicroServiceException {
		
		ResponseEntity<MaterialCategoryBean> response = restTemplate.getForEntity(serviceURL + apiURLForById + categoryId, MaterialCategoryBean.class);
		MaterialCategoryBean mcb = response.getBody();
		
		return mcb;
	}
}
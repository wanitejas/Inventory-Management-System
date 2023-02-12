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
import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class MaterialTypeConsumer {

	private static Logger LOGGER = Logger.getLogger(MaterialTypeConsumer.class);

	@Value("${MaterialServiceConsumer.serviceURL}")
	private String serviceURL;

	@Value("${MaterialTypeConsumer.apiURL}")
	private String apiURL;

	@Value("${MaterialTypeConsumer.apiURLByCategoryId}")
	private String apiURLByCategoryId;

	private RestTemplate restTemplate;

	private List<MaterialTypeBean> materialTypeBeanList;

	private Map<String, String> categoryTypeMap;

	
	public Map<String, String> getCategoryTypeMap() throws MicroServiceException {
		
		categoryTypeMap = new HashMap<String, String>();
		
		for(MaterialTypeBean mtb : getMaterialTypeBeanList())
		{
			categoryTypeMap.put(mtb.getTypeId(), mtb.getTypeName());
		}
		
		return categoryTypeMap;
	}
	
	
	public List<MaterialTypeBean> getMaterialTypeBeanList() throws MicroServiceException {
		
		hitGetMaterialType();
		return materialTypeBeanList;
	}

	
	public MaterialTypeConsumer() {
		
		restTemplate = new RestTemplate();
	}

	
	/**
	 * This method hits material microservice to get the list of Material type.
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetMaterialType() throws MicroServiceException {

		ResponseEntity<MaterialTypeBean[]> response = restTemplate.getForEntity(serviceURL + apiURL, MaterialTypeBean[].class);
		List<MaterialTypeBean> mtblist = Arrays.asList(response.getBody());
		
		this.materialTypeBeanList = mtblist;
	}

	
	/**
	 * This method hits material microservice to get the details of Material
	 * type based on category id.
	 * 
	 * @param categoryId
	 * @return List<MaterialTypeBean>
	 * @throws MicroServiceException
	 */
	public List<MaterialTypeBean> hitGetTypesBasedOnCategoryId(String categoryId) throws MicroServiceException {
		
		ResponseEntity<MaterialTypeBean[]> response = restTemplate.getForEntity(serviceURL + apiURLByCategoryId + categoryId, MaterialTypeBean[].class);
		List<MaterialTypeBean> mtblist = Arrays.asList(response.getBody());
		
		return mtblist;
	}
}
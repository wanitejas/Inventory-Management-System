package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.dao.MaterialCategoryDAO;
import com.accenture.lkm.entity.MaterialCategoryEntity;

@Service
public class MaterialServiceImpl implements MaterialService {

	private static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);
	
	/*
	 * Autowire MaterialCategoryDAO object
	 */
	// Your Code Here
	@Autowired
	private MaterialCategoryDAO materialCategoryDAO;

	
	/*
	 * Method - getMaterialCategoryById()
	 * Use MaterialCategoryDAO object findById method to fetch the entity by --> categoryId
	 * Check if the entity is present
	 * 		initialized the materialCategoryBean object
	 * 		copy the properties value from entity to materialCategoryBean object
	 */
	@Override
	public MaterialCategoryBean getMaterialCategoryById(String categoryId) {
		
		// Your Code Here
		MaterialCategoryBean mcb = new MaterialCategoryBean();
		MaterialCategoryEntity mce = materialCategoryDAO.findOne(categoryId);
		
		if(mce!=null)
		{
			BeanUtils.copyProperties(mce, mcb);
		}
		
		return mcb;
	}

	
	/*
	 * Method - getMaterialCategories()
	 * Use the MaterialCategoryDAO to get all the MaterialCategoryEntity objects
	 * Check if list is not empty then 
	 * 		Declare a MaterialCategoryBean object with null value
	 * 		Loop through all the material categories
	 * 			Initialize a new MaterialCategoryBean object 
	 * 			Copy each property value of entity object to bean object
	 * 			Add the bean object to the materialCategoryBeans list
	 */
	@Override
	public List<MaterialCategoryBean> getMaterialCategories() {
		
		// Your Code Here
		List<MaterialCategoryBean> mcblist = new ArrayList<MaterialCategoryBean>();
		List<MaterialCategoryEntity> mcelist = materialCategoryDAO.findAll();
		
		if(mcelist!=null)
		{
			for(MaterialCategoryEntity mce : mcelist)
			{
				MaterialCategoryBean mcb = new MaterialCategoryBean();
				BeanUtils.copyProperties(mce, mcb);
				mcblist.add(mcb);
			}
		}
		
		return mcblist;
	}
}
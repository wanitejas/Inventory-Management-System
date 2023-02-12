package com.accenture.lkm.service;

import java.util.ArrayList;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.dao.MaterialTypeDAO;
import com.accenture.lkm.entity.MaterialCategoryEntity;
import com.accenture.lkm.entity.MaterialTypeEntity;
@Service
public class MaterialTypeServiceImpl implements MaterialTypeService{
	private static final Logger logger = LoggerFactory.getLogger(MaterialTypeServiceImpl.class);

	
	@Autowired
	 private MaterialTypeDAO materialTypeDao;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<MaterialTypeBean> getMaterialTypesBasedOnCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		List<MaterialTypeBean> materialTypeBeans = new ArrayList<MaterialTypeBean>();
		Query query = entityManager.createQuery("select t from MaterialTypeEntity t where t.materialCategoryEntity.categoryId = ?1 ");
		query.setParameter(1,categoryId);
		List<MaterialTypeEntity> entityList = query.getResultList();
		//System.out.println(entityList);
		if(!entityList.isEmpty() || entityList!=null) {
			
			for(MaterialTypeEntity entity:entityList) {
                MaterialTypeBean bean=new MaterialTypeBean();
                BeanUtils.copyProperties(entity, bean);
                bean.setCategoryId(entity.getMaterialCategoryEntity().getCategoryId());
                materialTypeBeans.add(bean);
            }
		}
		return materialTypeBeans;
		
	}

	@Override
	public List<MaterialTypeBean> getMaterialTypes() {
		// TODO Auto-generated method stub
		List<MaterialTypeBean> materialTypeBeans = new ArrayList<MaterialTypeBean>();
		//Query query = entityManager.createQuery("select mt from materialtypeentity mt");
		List<MaterialTypeEntity> entityList =materialTypeDao.findAll();
		//System.out.println(entityList);
		//MaterialTypeEntity materialTypeEntities  = new MaterialTypeEntity();
		if(!entityList.isEmpty() || entityList!=null) {
			
			for(MaterialTypeEntity entity:entityList) {
                MaterialTypeBean bean=new MaterialTypeBean();
                BeanUtils.copyProperties(entity, bean);
                bean.setCategoryId(entity.getMaterialCategoryEntity().getCategoryId());
                materialTypeBeans.add(bean);
            }
		}
		return materialTypeBeans;
		
	}

	
}

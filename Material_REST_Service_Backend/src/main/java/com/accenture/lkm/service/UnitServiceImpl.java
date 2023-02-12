package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.dao.UnitDAO;

import com.accenture.lkm.entity.UnitEntity;
@Service
public class UnitServiceImpl implements UnitService {
	
	@Autowired
	private UnitDAO unitDao;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<UnitBean> getUnitsBasedOnCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		List<UnitBean> unitBeans = new ArrayList<UnitBean>();
		Query query = entityManager.createQuery("select t from UnitEntity t where t.materialCategoryEntity.categoryId = ?1 ");
		query.setParameter(1,categoryId);
		List<UnitEntity> entityList = query.getResultList();
		//System.out.println(entityList);
		if(!entityList.isEmpty() || entityList!=null) {
			
			for(UnitEntity entity:entityList) {
                UnitBean bean=new UnitBean();
                BeanUtils.copyProperties(entity, bean);
                bean.setCategoryId(entity.getMaterialCategoryEntity().getCategoryId());
                unitBeans.add(bean);
            }
		}
		return unitBeans;
	}

	@Override
	public List<UnitBean> getUnits() {
		// TODO Auto-generated method stub
		List<UnitBean> unitBeans = new ArrayList<UnitBean>();
		//Query query = entityManager.createQuery("select u from UnitEntity u");
		List<UnitEntity> entityList =unitDao.findAll();
		//System.out.println(entityList);
		//MaterialTypeEntity materialTypeEntities  = new MaterialTypeEntity();
		if(!entityList.isEmpty() || entityList!=null) {
			
			for(UnitEntity entity:entityList) {
                UnitBean bean=new UnitBean();
                BeanUtils.copyProperties(entity, bean);
                bean.setCategoryId(entity.getMaterialCategoryEntity().getCategoryId());
                unitBeans.add(bean);
            }
		}
		return unitBeans;
	}

}

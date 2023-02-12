package com.accenture.lkm.service;

import java.util.List;
import com.accenture.lkm.business.bean.MaterialCategoryBean;

public interface MaterialService {
	
	MaterialCategoryBean getMaterialCategoryById(String categoryId);
	
	List<MaterialCategoryBean> getMaterialCategories();
	
}
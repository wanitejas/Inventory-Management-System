package com.accenture.lkm.service;

import java.util.List;
import com.accenture.lkm.business.bean.MaterialTypeBean;

public interface MaterialTypeService {

	List<MaterialTypeBean> getMaterialTypes();

	List<MaterialTypeBean> getMaterialTypesBasedOnCategoryId(String categoryId);

}
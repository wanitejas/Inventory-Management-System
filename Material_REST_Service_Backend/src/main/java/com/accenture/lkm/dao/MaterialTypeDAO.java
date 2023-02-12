package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.lkm.entity.MaterialTypeEntity;

public interface MaterialTypeDAO extends JpaRepository<MaterialTypeEntity, String> {
	
}
package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.lkm.entity.UnitEntity;

public interface UnitDAO extends JpaRepository<UnitEntity, String> {

}
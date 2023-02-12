package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.lkm.entity.VendorEntity;

public interface VendorDAO extends JpaRepository<VendorEntity, String> {

}

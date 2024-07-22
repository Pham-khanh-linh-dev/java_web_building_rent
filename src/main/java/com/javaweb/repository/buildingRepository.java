package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.buildingEntity;

public interface buildingRepository {
	
	public List<buildingEntity> findAll(Map<String, Object> params, List<String> typeCode);

}

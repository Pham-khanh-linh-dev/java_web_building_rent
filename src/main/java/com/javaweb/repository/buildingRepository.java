package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.buildingSearchBuilder;
import com.javaweb.repository.entity.buildingEntity;

public interface buildingRepository {
	
	public List<buildingEntity> findAll(buildingSearchBuilder buildingsearchbuilder);

	public void DeleteById(Long id);
}

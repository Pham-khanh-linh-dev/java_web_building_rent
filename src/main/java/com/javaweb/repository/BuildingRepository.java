package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>{
	
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingsearchbuilder);

	public void DeleteById(Long id);
}

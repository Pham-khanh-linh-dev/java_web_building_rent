package com.javaweb.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.buildingSearchBuilder;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.entity.buildingEntity;

@Repository
public class BuildingRepositoryImpl implements buildingRepository{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<buildingEntity> findAll(buildingSearchBuilder buildingsearchbuilder) {
		// TODO Auto-generated method stub
		//JPQL
		String sql = " FROM buildingEntity b ";
		Query query = entityManager.createQuery(sql, buildingEntity.class);
		return query.getResultList();
	}

	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	

	
}

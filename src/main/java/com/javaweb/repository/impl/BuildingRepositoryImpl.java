package com.javaweb.repository.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.buildingSearchBuilder;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.entity.buildingEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements buildingRepository{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<buildingEntity> findAll(buildingSearchBuilder buildingsearchbuilder) {
		// TODO Auto-generated method stub
		//JPQL
//		String sql_jpql = " FROM buildingEntity b ";
//		Query query = entityManager.createQuery(sql_jpql, buildingEntity.class);
//		return query.getResultList();
//		
		// SQL Native
		String sql_native = "SELECT * FROM building b WHERE b.name like '%building%' ";
		Query query_native = entityManager.createNativeQuery(sql_native, buildingEntity.class);
		return query_native.getResultList();
	}

	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	

	
}

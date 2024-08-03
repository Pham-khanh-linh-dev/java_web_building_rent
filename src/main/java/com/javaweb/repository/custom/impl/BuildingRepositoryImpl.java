package com.javaweb.repository.custom.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl{

	@PersistenceContext
	private EntityManager entityManager;
//	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingsearchbuilder) {
		// TODO Auto-generated method stub
		//JPQL
//		String sql_jpql = " FROM BuildingEntity b ";
//		Query query = entityManager.createQuery(sql_jpql, BuildingEntity.class);
//		return query.getResultList();
//		
		// SQL Native
		String sql_native = "SELECT * FROM building b WHERE b.name like '%building%' ";
		Query query_native = entityManager.createNativeQuery(sql_native, BuildingEntity.class);
		return query_native.getResultList();
	}

//	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	

	
}

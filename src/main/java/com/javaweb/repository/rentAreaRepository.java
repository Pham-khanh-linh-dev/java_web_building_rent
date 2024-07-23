package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.rentAreaEntity;

public interface rentAreaRepository {
	public List<rentAreaEntity> getValueById(Long id);
}

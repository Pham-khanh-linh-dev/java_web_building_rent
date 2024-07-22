package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.Model.buildingDTO;

public interface buildingService {

	public List<buildingDTO> findAll(Map<String, Object> params, List<String> typeCode);
	
}

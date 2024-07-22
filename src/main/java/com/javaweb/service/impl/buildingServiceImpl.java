package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Model.buildingDTO;
import com.javaweb.Model.districtDTO;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.districtRepository;
import com.javaweb.repository.entity.buildingEntity;
import com.javaweb.repository.entity.districtEntity;
import com.javaweb.service.buildingService;

@Service
public class buildingServiceImpl implements buildingService{

//	Dùng @Autowired nôm na để khởi tạo đối tượng cho interface( Cần tìm hiểu chi tiết cách hoạt động)
	@Autowired
	private buildingRepository buildingRe;
	
	@Autowired
	private districtRepository districtRe;
	
	@Override
	public List<buildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		
		List<buildingEntity> buildingEntities = buildingRe.findAll(params, typeCode);
		List<buildingDTO> result = new ArrayList<buildingDTO>();
		for(buildingEntity item: buildingEntities) {
			buildingDTO building = new buildingDTO();
			building.setName(item.getName());
			districtEntity district = districtRe.findNameById(item.getDistrictId());
			building.setAddress(item.getStreet() + "," + item.getWard() + ", " + district.getName());
			result.add(building);
		}
		return result;
	}
	
}

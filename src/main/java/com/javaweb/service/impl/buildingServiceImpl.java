package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Model.buildingDTO;
import com.javaweb.Model.districtDTO;
import com.javaweb.builder.buildingSearchBuilder;
import com.javaweb.converter.buildingDTOConverter;
import com.javaweb.converter.buildingSearchBuilderConverter;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.districtRepository;
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.repository.entity.buildingEntity;
import com.javaweb.repository.entity.districtEntity;
import com.javaweb.repository.entity.rentAreaEntity;
import com.javaweb.service.buildingService;

@Service
public class buildingServiceImpl implements buildingService{

//	Dùng @Autowired nôm na để khởi tạo đối tượng cho interface( Cần tìm hiểu chi tiết cách hoạt động)
	private buildingRepository buildingRe;
	private buildingDTOConverter buildingDTOCo;
	@Autowired
	private buildingSearchBuilderConverter buildingserachbuilderconverter;
	
	 @Autowired
	    public buildingServiceImpl(buildingRepository buildingRe, buildingDTOConverter buildingDTOCo) {
	        this.buildingRe = buildingRe;
	        this.buildingDTOCo = buildingDTOCo;
	    }
	 
	public List<buildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		buildingSearchBuilder buildingsearchbuilder = buildingserachbuilderconverter.toBuildingSearchBuilder(params, typeCode);
		
		List<buildingEntity> buildingEntities = buildingRe.findAll(buildingsearchbuilder);
		List<buildingDTO> result = new ArrayList<buildingDTO>();
		for(buildingEntity item: buildingEntities) {
			buildingDTO building = buildingDTOCo.tobuildingDTO(item);
			result.add(building);
		}
		return result;
		
	}
	
}

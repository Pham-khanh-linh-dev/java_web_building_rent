package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Model.DistrictDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{

//	Dùng @Autowired nôm na để khởi tạo đối tượng cho interface( Cần tìm hiểu chi tiết cách hoạt động)
	@Autowired
	private BuildingRepository buildingRe;
	
	@Autowired
	private BuildingDTOConverter buildingDTOCo;
	@Autowired
	private BuildingSearchBuilderConverter buildingserachbuilderconverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingsearchbuilder = buildingserachbuilderconverter.toBuildingSearchBuilder(params, typeCode);
		
		List<BuildingEntity> BuildingEntities = buildingRe.findAllBuilding(buildingsearchbuilder);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item: BuildingEntities) {
			BuildingDTO building = buildingDTOCo.tobuildingDTO(item);
			result.add(building);
		}
		return result;
	}
	
}


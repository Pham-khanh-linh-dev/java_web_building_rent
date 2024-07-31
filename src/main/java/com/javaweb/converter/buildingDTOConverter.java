package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Model.buildingDTO;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.districtRepository;
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.repository.entity.buildingEntity;
import com.javaweb.repository.entity.districtEntity;
import com.javaweb.repository.entity.rentAreaEntity;

@Component
public class buildingDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public buildingDTO tobuildingDTO(buildingEntity item) {
		buildingDTO building = modelMapper.map(item, buildingDTO.class);
		building.setAddress(item.getStreet() + "," + item.getWard() + ", " + item.getDistrict().getName());
		
		List<rentAreaEntity> rentarea = item.getRentarea();
		String areaResult = rentarea.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(areaResult);
		return building;
	}
}

package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO tobuildingDTO(BuildingEntity item) {
//		ánh xạ giá trị map 1:1 giữa 2 đối tượng
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);

//		Xử lý các giá trị đặc biệt
		building.setAddress(item.getStreet() + "," + item.getWard() + ", " + item.getDistrict().getName());
		
		List<RentAreaEntity> rentarea = item.getRentarea();
		String areaResult = rentarea.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(areaResult);
		return building;
	}
}

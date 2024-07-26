package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.Utils.mapUtil;
import com.javaweb.builder.buildingSearchBuilder;

@Component
public class buildingSearchBuilderConverter {
	public buildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeBuilding){
		buildingSearchBuilder buildingsearchbuilder = new buildingSearchBuilder.Builder()
																	.setName(mapUtil.getObject(params, "name", String.class))
																	.setStreet(mapUtil.getObject(params, "street", String.class))
																	.setWard(mapUtil.getObject(params, "ward", String.class))
																	.setDistrictid(mapUtil.getObject(params, "districtId", Integer.class))
																	.setNumberofbasement(mapUtil.getObject(params, "numberOfBasement", Integer.class))
																	.setFloorarea(mapUtil.getObject(params, "floorArea", Integer.class))
																	.setLevel(mapUtil.getObject(params, "level", String.class))
																	.setRentpricefrom(mapUtil.getObject(params, "rentPriceFrom", Integer.class))
																	.setRentpriceto(mapUtil.getObject(params, "rentPriceTo", Integer.class))
																	.setRentareafrom(mapUtil.getObject(params, "rentAreaFrom", Integer.class))
																	.setRentareato(mapUtil.getObject(params, "rentAreaTo", Integer.class))
																	.setBrokeragefee(mapUtil.getObject(params, "brokerageFee", Long.class))
																	.setManagername(mapUtil.getObject(params, "managerName", String.class))
																	.setManagerphonenumber(mapUtil.getObject(params, "managerPhoneNumber", String.class))
																	.setStaffid(mapUtil.getObject(params, "staffId", Integer.class))
																	.setTypeBuilding(typeBuilding)
																	.build();
		return buildingsearchbuilder;
	}
	
}

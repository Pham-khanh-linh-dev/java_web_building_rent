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
																	.setDistrictid(mapUtil.getObject(params, "districtid", Integer.class))
																	.setNumberofbasement(mapUtil.getObject(params, "numberofbasement", Integer.class))
																	.setFloorarea(mapUtil.getObject(params, "floorarea", Integer.class))
																	.setLevel(mapUtil.getObject(params, "level", String.class))
																	.setRentpricefrom(mapUtil.getObject(params, "rentpricefrom", Integer.class))
																	.setRentpriceto(mapUtil.getObject(params, "rentpriceto", Integer.class))
																	.setRentareafrom(mapUtil.getObject(params, "rentareafrom", Integer.class))
																	.setRentareato(mapUtil.getObject(params, "rentareato", Integer.class))
																	.setBrokeragefee(mapUtil.getObject(params, "brokeragefee", Long.class))
																	.setManagername(mapUtil.getObject(params, "managername", String.class))
																	.setManagerphonenumber(mapUtil.getObject(params, "managerphonenumber", String.class))
																	.setStaffid(mapUtil.getObject(params, "staffid", Integer.class))
																	.setTypeBuilding(typeBuilding)
																	.build();
		return buildingsearchbuilder;
	}
	
}

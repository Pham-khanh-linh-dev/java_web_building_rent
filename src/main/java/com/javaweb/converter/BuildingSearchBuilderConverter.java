package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.Utils.MapUtil;
import com.javaweb.builder.BuildingSearchBuilder;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeBuilding){
		BuildingSearchBuilder buildingsearchbuilder = new BuildingSearchBuilder.Builder()
																	.setName(MapUtil.getObject(params, "name", String.class))
																	.setStreet(MapUtil.getObject(params, "street", String.class))
																	.setWard(MapUtil.getObject(params, "ward", String.class))
																	.setDistrictid(MapUtil.getObject(params, "districtId", Integer.class))
																	.setNumberofbasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
																	.setFloorarea(MapUtil.getObject(params, "floorArea", Integer.class))
																	.setLevel(MapUtil.getObject(params, "level", String.class))
																	.setRentpricefrom(MapUtil.getObject(params, "rentPriceFrom", Integer.class))
																	.setRentpriceto(MapUtil.getObject(params, "rentPriceTo", Integer.class))
																	.setRentareafrom(MapUtil.getObject(params, "rentAreaFrom", Integer.class))
																	.setRentareato(MapUtil.getObject(params, "rentAreaTo", Integer.class))
																	.setBrokeragefee(MapUtil.getObject(params, "brokerageFee", Long.class))
																	.setManagername(MapUtil.getObject(params, "managerName", String.class))
																	.setManagerphonenumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
																	.setStaffid(MapUtil.getObject(params, "staffId", Integer.class))
																	.setTypeBuilding(typeBuilding)
																	.build();
		return buildingsearchbuilder;
	}
	
}

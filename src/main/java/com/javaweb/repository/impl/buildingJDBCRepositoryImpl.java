package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.javaweb.Utils.connectJDBCUtil;
import com.javaweb.Utils.mapUtil;
import com.javaweb.Utils.numberUtil;
import com.javaweb.Utils.stringUtil;
import com.javaweb.builder.buildingSearchBuilder;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.entity.buildingEntity;
@Repository
public class buildingJDBCRepositoryImpl implements buildingRepository{

	public static void joinTable(buildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
		String staffId = buildingsearchbuilder.getStaffid() != null ? buildingsearchbuilder.getStaffid().toString() : null;
	    if (stringUtil.checkString(staffId)) {
	        sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
	    }
	    List<String> typeCode = buildingsearchbuilder.getTypeBuilding();
	    if (typeCode != null && !typeCode.isEmpty()) {
	        sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ")
	           .append("INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
	    }
    }
	
	public static void queryNormal(buildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
//        List<String> conditions = new ArrayList<>();
//        for (Map.Entry<String, Object> it : params.entrySet()) {
//            if (!it.getKey().equals("staffId") 
//                    && !it.getKey().equals("typeCode") 
//                    && !it.getKey().startsWith("area") 
//                    && !it.getKey().startsWith("rentPrice")) {
//                String value = it.getValue().toString();
//                if (numberUtil.isLong(value)) {
//                    conditions.add("b." + it.getKey() + " = " + value + " ");
//                } else {
//                    conditions.add("b." + it.getKey() + " LIKE '%" + value + "%' ");
//                }
//            }
//        }
//        if (!conditions.isEmpty()) {
//            sql.append(" AND ").append(String.join(" AND ", conditions));
//        }
		
		
//		SỬ DỤNG JAVA REFLECTION
//		List<String> conditions = new ArrayList<>();
//		try {
//			Class buildingSearchClass = buildingSearchBuilder.class;
//			Field[] fields = buildingSearchClass.getDeclaredFields();
//			for (Field item : fields) {
////				Dùng setAccessible(true) để có quyền truy cầm vào các filed trong đối tượng buildingSearchBuilder
//				item.setAccessible(true);
//				String fieldName = item.getName();
//				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
//						&& !fieldName.startsWith("rentPrice")) {
//					String value = item.get(buildingsearchbuilder).toString();
//					if(stringUtil.checkString(value)) {
//						if (numberUtil.isLong(value)) {
//							conditions.add("b." + value + " = " + value + " ");
//						} else {
//							conditions.add("b." + value + " LIKE '%" + value + "%' ");
//						}
//					}
//					
//				}
//			}
//			if (!conditions.isEmpty()) {
//				sql.append(" AND ").append(String.join(" AND ", conditions));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		List<String> conditions = new ArrayList<>();
	    try {
	        Class<?> buildingSearchClass = buildingSearchBuilder.class;
	        Field[] fields = buildingSearchClass.getDeclaredFields();
	        for (Field item : fields) {
	            item.setAccessible(true);
	            String fieldName = item.getName();
	            if (!fieldName.equals("staffid") && !fieldName.equals("typeBuilding") && !fieldName.startsWith("rentarea")
	                    && !fieldName.startsWith("rentprice")) {
	                Object valueObj = item.get(buildingsearchbuilder);
	                if (valueObj != null) {
	                    String value = valueObj.toString();
	                    if (stringUtil.checkString(value)) {
	                        if (numberUtil.isLong(value)) {
	                            conditions.add("b." + fieldName + " = " + value + " ");
	                        } else {
	                            conditions.add("b." + fieldName + " LIKE '%" + value + "%' ");
	                        }
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if (!conditions.isEmpty()) {
            sql.append(" AND ").append(String.join(" AND ", conditions));
        }
	}
	
	public static void querySpecial(buildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
		String staffId = buildingsearchbuilder.getStaffid() != null ? buildingsearchbuilder.getStaffid().toString() : null;
	    String rentAreaTo = buildingsearchbuilder.getRentareato() != null ? buildingsearchbuilder.getRentareato().toString() : null;
	    String rentAreaFrom = buildingsearchbuilder.getRentareafrom() != null ? buildingsearchbuilder.getRentareafrom().toString() : null;
	    String rentPriceTo = buildingsearchbuilder.getRentpriceto() != null ? buildingsearchbuilder.getRentpriceto().toString() : null;
	    String rentPriceFrom = buildingsearchbuilder.getRentpricefrom() != null ? buildingsearchbuilder.getRentpricefrom().toString() : null;
	    List<String> typeCode = buildingsearchbuilder.getTypeBuilding();

	    List<String> conditions = new ArrayList<>();

	    if (stringUtil.checkString(staffId)) {
	        conditions.add("assignmentbuilding.staffId = " + staffId);
	    }
	    if (stringUtil.checkString(rentAreaFrom) || stringUtil.checkString(rentAreaTo)) {
	        List<String> rentAreaConditions = new ArrayList<>();
	        if (stringUtil.checkString(rentAreaFrom)) {
	            rentAreaConditions.add("r.value >= " + rentAreaFrom);
	        }
	        if (stringUtil.checkString(rentAreaTo)) {
	            rentAreaConditions.add("r.value <= " + rentAreaTo);
	        }
	        if (!rentAreaConditions.isEmpty()) {
	            String rentAreaCondition = String.join(" AND ", rentAreaConditions);
	            conditions.add(" EXISTS ( SELECT * From rentarea r WHERE b.id = r.buildingid AND " + rentAreaCondition + ")");
	        }
	    }

	    if (stringUtil.checkString(rentPriceFrom)) {
	        conditions.add("b.rentprice >= " + rentPriceFrom);
	    }
	    if (stringUtil.checkString(rentPriceTo)) {
	        conditions.add("b.rentprice <= " + rentPriceTo);
	    }

	    if (typeCode != null && !(typeCode.isEmpty())) {
	        String conditionTypeCode = String.join("', '", typeCode);
	        conditions.add("renttype.code IN ('" + conditionTypeCode + "')");
	    }
	    if (!conditions.isEmpty()) {
	        sql.append(" AND ").append(String.join(" AND ", conditions));
	    }
    }
	
	@Override
	public List<buildingEntity> findAll(buildingSearchBuilder buildingsearchbuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.ward, b.street, b.rentprice, b.numberofbasement, b.floorarea, b.servicefee, b. brokeragefee, b.managername, b.managerphonenumber FROM building b ");

        // Xử lý join table và câu lệnh 
        joinTable(buildingsearchbuilder, sql);
        sql.append(" WHERE 1=1 ");
        queryNormal(buildingsearchbuilder, sql);
        querySpecial(buildingsearchbuilder, sql);
        sql.append(" GROUP BY b.id ");
        // Hứng đối tượng trả về từ DB
        List<buildingEntity> buildings = new ArrayList<>();
        try (Connection conn = connectJDBCUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql.toString());) {

            while (rs.next()) {
                buildingEntity building = new buildingEntity();
                building.setId(rs.getLong("Id"));
                building.setName(rs.getString("name"));
                building.setWard(rs.getString("ward"));
                building.setDistrictId(rs.getLong("districtid"));
                building.setStreet(rs.getString("street"));
                building.setFloorarea(rs.getLong("floorarea"));
                building.setRentprice(rs.getInt("rentprice"));
                building.setServicefee(rs.getString("servicefee"));
                building.setBrokeragefee(rs.getLong("brokeragefee"));
                building.setManagername(rs.getString("managername"));
                building.setManagerphonenumber(rs.getString("managerphonenumber"));

                buildings.add(building);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi kết nối DB.");
        }
        return buildings;
    }

	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}

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

import org.springframework.stereotype.Repository;

import com.javaweb.Utils.connectJDBCUtil;
import com.javaweb.Utils.numberUtil;
import com.javaweb.Utils.stringUtil;
import com.javaweb.builder.buildingSearchBuilder;
import com.javaweb.repository.buildingRepository;
import com.javaweb.repository.entity.buildingEntity;
@Repository
public class buildingRepositoryImpl implements buildingRepository{

	public static void joinTable(buildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
        String staffId = buildingsearchbuilder.getStaffid("staffid").toString();
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
		List<String> conditions = new ArrayList<>();
		try {
			Field[] fields = buildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
						&& !fieldName.startsWith("rentPrice")) {
					String value = item.get(buildingsearchbuilder).toString();
					if (numberUtil.isLong(value)) {
						conditions.add("b." + it.getKey() + " = " + value + " ");
					} else {
						conditions.add("b." + it.getKey() + " LIKE '%" + value + "%' ");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
        String staffId = (String) params.get("staffid");
        String rentAreaTo = (String) params.get("areaTo");
        String rentAreaFrom = (String) params.get("areaFrom");
        String rentPriceTo = (String) params.get("rentPriceTo");
        String rentPriceFrom = (String) params.get("rentPriceFrom");

        List<String> conditions = new ArrayList<>();

        if (stringUtil.checkString(staffId)) {
            conditions.add("assignmentbuilding.staffId = " + staffId);
        }
        if(stringUtil.checkString(rentAreaFrom) || stringUtil.checkString(rentAreaTo)) {
        	List<String> rentAreaConditions = new ArrayList<>();
        	if (stringUtil.checkString(rentAreaFrom)) {
        		rentAreaConditions.add("r.value >= " + rentAreaFrom);
            }
            if (stringUtil.checkString(rentAreaTo)) {
            	rentAreaConditions.add("r.value <= " + rentAreaTo);
            }
            if(!rentAreaConditions.isEmpty()) {
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

//        ('pham', 'khanh',' linh')
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
        joinTable(params, typeCode, sql);
        sql.append(" WHERE 1=1 ");
        queryNormal(params, sql);
        querySpecial(params, typeCode, sql);
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
	
}

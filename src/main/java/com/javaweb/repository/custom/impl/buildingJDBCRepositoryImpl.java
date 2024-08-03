package com.javaweb.repository.custom.impl;

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

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.javaweb.Utils.ConnectJDBCUtil;
import com.javaweb.Utils.MapUtil;
import com.javaweb.Utils.NumberUtil;
import com.javaweb.Utils.StringUtil;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
@Primary
public class buildingJDBCRepositoryImpl implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	public static void joinTable(BuildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
		String staffId = buildingsearchbuilder.getStaffid() != null ? buildingsearchbuilder.getStaffid().toString() : null;
	    if (StringUtil.checkString(staffId)) {
	        sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
	    }
	    List<String> typeCode = buildingsearchbuilder.getTypeBuilding();
	    if (typeCode != null && !typeCode.isEmpty()) {
	        sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ")
	           .append("INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
	    }
    }
	
	public static void queryNormal(BuildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
//        List<String> conditions = new ArrayList<>();
//        for (Map.Entry<String, Object> it : params.entrySet()) {
//            if (!it.getKey().equals("staffId") 
//                    && !it.getKey().equals("typeCode") 
//                    && !it.getKey().startsWith("area") 
//                    && !it.getKey().startsWith("rentPrice")) {
//                String value = it.getValue().toString();
//                if (NumberUtil.isLong(value)) {
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
//			Class buildingSearchClass = BuildingSearchBuilder.class;
//			Field[] fields = buildingSearchClass.getDeclaredFields();
//			for (Field item : fields) {
////				Dùng setAccessible(true) để có quyền truy cầm vào các filed trong đối tượng BuildingSearchBuilder
//				item.setAccessible(true);
//				String fieldName = item.getName();
//				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
//						&& !fieldName.startsWith("rentPrice")) {
//					String value = item.get(buildingsearchbuilder).toString();
//					if(StringUtil.checkString(value)) {
//						if (NumberUtil.isLong(value)) {
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
	        Class<?> buildingSearchClass = BuildingSearchBuilder.class;
	        Field[] fields = buildingSearchClass.getDeclaredFields();
	        for (Field item : fields) {
	            item.setAccessible(true);
	            String fieldName = item.getName();
	            if (!fieldName.equals("staffid") && !fieldName.equals("typeBuilding") && !fieldName.startsWith("rentarea")
	                    && !fieldName.startsWith("rentprice")) {
	                Object valueObj = item.get(buildingsearchbuilder);
	                if (valueObj != null) {
	                    String value = valueObj.toString();
	                    if (StringUtil.checkString(value)) {
	                        if (NumberUtil.isLong(value)) {
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
	
	public static void querySpecial(BuildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
		String staffId = buildingsearchbuilder.getStaffid() != null ? buildingsearchbuilder.getStaffid().toString() : null;
	    String rentAreaTo = buildingsearchbuilder.getRentareato() != null ? buildingsearchbuilder.getRentareato().toString() : null;
	    String rentAreaFrom = buildingsearchbuilder.getRentareafrom() != null ? buildingsearchbuilder.getRentareafrom().toString() : null;
	    String rentPriceTo = buildingsearchbuilder.getRentpriceto() != null ? buildingsearchbuilder.getRentpriceto().toString() : null;
	    String rentPriceFrom = buildingsearchbuilder.getRentpricefrom() != null ? buildingsearchbuilder.getRentpricefrom().toString() : null;
	    List<String> typeCode = buildingsearchbuilder.getTypeBuilding();

	    List<String> conditions = new ArrayList<>();

	    if (StringUtil.checkString(staffId)) {
	        conditions.add("assignmentbuilding.staffId = " + staffId);
	    }
	    if (StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)) {
	        List<String> rentAreaConditions = new ArrayList<>();
	        if (StringUtil.checkString(rentAreaFrom)) {
	            rentAreaConditions.add("r.value >= " + rentAreaFrom);
	        }
	        if (StringUtil.checkString(rentAreaTo)) {
	            rentAreaConditions.add("r.value <= " + rentAreaTo);
	        }
	        if (!rentAreaConditions.isEmpty()) {
	            String rentAreaCondition = String.join(" AND ", rentAreaConditions);
	            conditions.add(" EXISTS ( SELECT * From rentarea r WHERE b.id = r.buildingid AND " + rentAreaCondition + ")");
	        }
	    }

	    if (StringUtil.checkString(rentPriceFrom)) {
	        conditions.add("b.rentprice >= " + rentPriceFrom);
	    }
	    if (StringUtil.checkString(rentPriceTo)) {
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
	
//	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingsearchbuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");

        // Xử lý join table và câu lệnh 
        joinTable(buildingsearchbuilder, sql);
        sql.append(" WHERE 1=1 ");
        queryNormal(buildingsearchbuilder, sql);
        querySpecial(buildingsearchbuilder, sql);
        sql.append(" GROUP BY b.id ");
        // Hứng đối tượng trả về từ DB bằng JDBC
//        List<BuildingEntity> buildings = new ArrayList<>();
//        try (Connection conn = ConnectJDBCUtil.getConnection();
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery(sql.toString());) {
//
//            while (rs.next()) {
//                BuildingEntity building = new BuildingEntity();
//                building.setId(rs.getLong("Id"));
//                building.setName(rs.getString("name"));
//                building.setWard(rs.getString("ward"));
//                //building.setDistrictId(rs.getLong("districtid"));
//                building.setStreet(rs.getString("street"));
//                building.setFloorarea(rs.getLong("floorarea"));
//                building.setRentprice(rs.getInt("rentprice"));
//                building.setServicefee(rs.getString("servicefee"));
//                building.setBrokeragefee(rs.getLong("brokeragefee"));
//                building.setManagername(rs.getString("managername"));
//                building.setManagerphonenumber(rs.getString("managerphonenumber"));
//
//                buildings.add(building);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Lỗi kết nối DB.");
//        }
        
//        Sử dụng JPA
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

//	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}

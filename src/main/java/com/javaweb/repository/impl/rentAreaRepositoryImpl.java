package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Utils.connectJDBCUtil;
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.repository.entity.rentAreaEntity;

@Repository
public class rentAreaRepositoryImpl implements rentAreaRepository{

	@Override
	public List<rentAreaEntity> getValueById(Long id) {
		String sql = "SELECT * FROM rentarea WHERE rentarea.buildingid = " + id + "; ";
		List<rentAreaEntity> rentareas = new ArrayList<>();
		try(Connection conn = connectJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()) {
				rentAreaEntity rentarea = new rentAreaEntity();
				rentarea.setValue((Long) rs.getLong("value"));
				rentareas.add(rentarea);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rentareas;
	}
}

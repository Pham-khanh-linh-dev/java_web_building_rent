package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.Utils.connectJDBCUtil;
import com.javaweb.repository.districtRepository;
import com.javaweb.repository.entity.districtEntity;

@Repository
public class districtRepositoryImpl implements districtRepository{
	@Override
	public districtEntity findNameById(Long id) {
		String sql = "SELECT d.name FROM district d WHERE d.id = " + id + "; ";
		districtEntity district = new districtEntity();
		try(Connection conn = connectJDBCUtil.getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)){
			while(rs.next()) {
				district.setName(rs.getString("name"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return district;
	}

	
}

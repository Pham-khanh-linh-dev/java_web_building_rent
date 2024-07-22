package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.districtRepository;
import com.javaweb.repository.entity.districtEntity;

@Repository
public class districtRepositoryImpl implements districtRepository{
	
	static final String DB_URL = "jdbc:mysql://localhost:3307/estatebasic";
	static final String USER = "root";
	static final String PASSWORD = "sa123";
	
	@Override
	public districtEntity findNameById(Long id) {
		String sql = "SELECT d.name FROM district d WHERE d.id = " + id + "; ";
		districtEntity district = new districtEntity();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
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

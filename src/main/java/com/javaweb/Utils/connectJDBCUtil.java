package com.javaweb.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-uat.properties")
public class connectJDBCUtil {
	@Value("${spring.datasource.url}")
	public static String DB_URL;
	
	static final String USER = "root";
	static final String PASSWORD = "sa123";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			return conn;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

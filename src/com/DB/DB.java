package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcURL="jdbc:mysql://localhost:3306/hospital";
			String jdbcUsername="root";
			String jdbcPassword="";
			return DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

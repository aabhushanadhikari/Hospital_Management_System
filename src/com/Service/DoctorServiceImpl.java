package com.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.DB.DB;

public class DoctorServiceImpl implements DoctorService{
	public static Scanner sc=new Scanner(System.in);
	@Override
	public void viewDoctors() {
		String sql="Select * from doctors";
		try {
			PreparedStatement stm=DB.getConnection().prepareStatement(sql);
			ResultSet rs=stm.executeQuery();
				System.out.println("                Doctors                            ");
				System.out.println("+--------+-------------------+--------------------+");
				System.out.println("|  ID    |      Name         |  Specialization    |");
				System.out.println("+--------+-------------------+--------------------+");
				
				while(rs.next()) {
					int id=rs.getInt("id");
					String name=rs.getString("name");
					String specialization=rs.getString("specialization");
					System.out.printf("|%-8s|%-19s|%-20s|\n",id,name,specialization);
					System.out.println("+--------+-------------------+--------------------+");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean viewDoctorsById(int id) {
		String sql="Select * from doctors where id = ?";
		try {
			PreparedStatement stm=DB.getConnection().prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs=stm.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}

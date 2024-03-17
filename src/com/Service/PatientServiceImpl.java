package com.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.DB.DB;
import com.Model.Patient;

public class PatientServiceImpl implements PatientService{
	public static Scanner sc=new Scanner(System.in);
	@Override
	public void addPatients() {
		Patient p=new Patient();
		try {
		String sql="Insert into patients(name,age,gender) values(?,?,?)";
		System.out.println("Enter the name of the patient");
		String username=sc.nextLine();
		p.setName(username);
		System.out.println("Enter the age of the patient");
		int age=Integer.parseInt(sc.nextLine());
		p.setAge(age);
		System.out.println("Enter the gender of the patient");
		String gender=sc.nextLine();
		p.setGender(gender);
		PreparedStatement stm=DB.getConnection().prepareStatement(sql);
		stm.setString(1, p.getName());
		stm.setInt(2, p.getAge());
		stm.setString(3,p.getGender());
		int rows=stm.executeUpdate();
		if(rows>0) {
			System.out.println("Inserted Successfully");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void viewPatients() {
		String sql="Select * from patients";
		try {
			PreparedStatement stm=DB.getConnection().prepareStatement(sql);
			ResultSet rs=stm.executeQuery();
			System.out.println("Patients");
			System.out.println("+--------+-------------------+---------+-------------+");
			System.out.println("|  ID    |      Name         |  Age    |   Gender    |");
			System.out.println("+--------+-------------------+---------+-------------+");
			
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				String gender=rs.getString("gender");
				System.out.printf("%-9s|%-19s|%-9s|%-13s\n|",id,name,age,gender);
				System.out.println("+--------+-------------------+---------+-------------+");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void checkPatientbyId() {
		System.out.println("Enter the id of the patient");
		int id=Integer.parseInt(sc.nextLine());
		String sql="Select * from patient where id=?";
		try {
			PreparedStatement stm=DB.getConnection().prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs=stm.executeQuery();
			if(rs.next()) {
				System.out.println("The name is"+rs.getString("name"));
				System.out.println("The age is"+rs.getInt("age"));
				System.out.println("The gender is "+rs.getString("gender"));
			}else {
				System.out.println("The id you have typed does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package com.View;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.DB.DB;
import com.Service.DoctorService;
import com.Service.DoctorServiceImpl;
import com.Service.PatientService;
import com.Service.PatientServiceImpl;

public class View {
	public static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		PatientService ser=new PatientServiceImpl();
		DoctorService ser2=new DoctorServiceImpl();
		View view=new View();
		
		while(true) {
			System.out.println("What do you want to do?");
			System.out.println("1.Create patients");
			System.out.println("2.Read Patients");
			System.out.println("3.Read doctors");
			System.out.println("4.Check Appointments");
			System.out.println("5.Exit");
			
			int ch=Integer.parseInt(sc.nextLine());
			switch(ch) {
			case 1:
				ser.addPatients();
				break;
			case 2:
				ser.viewPatients();
				break;
				
			case 3:
				ser2.viewDoctors();
				break;
			case 4:
				view.checkAppointMent();
				break;
			case 5:
				System.out.println("Exit called!");
				return;
			default:
				System.out.println("Enter valid options!!!");
				
			}
		}
		
		
	}
	
	public void checkAppointMent() {
		DoctorService dser=new DoctorServiceImpl();
		PatientService pser=new PatientServiceImpl();
		System.out.println("Enter the id of the patient");
		int pid=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the id of the doctor");
		int did=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the appointment date (yyyy-mm-dd)");
		String apdate=sc.nextLine();
		if(dser.viewDoctorsById(did) && pser.checkPatientbyId(pid)) {
			if(View.checkDoctorAvailability(did, apdate)) {
				String sql="Insert into appointments(patient_id,doctor_id,appointment_date) values (?,?,?)";
				try {
					PreparedStatement stm=DB.getConnection().prepareStatement(sql);
					stm.setInt(1, pid);
					stm.setInt(2, did);
					stm.setString(3, apdate);
					int rows=stm.executeUpdate();
					if(rows>0) {
						System.out.println("Appointment date secured");
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
		}else {
			System.out.println("This day is packed!");
		}
		}
		
	}
	public static boolean checkDoctorAvailability(int did,String apdate) {
		String query="Select count(*) from appointments where doctor_id=? and appointment_date=?";
		try {
			PreparedStatement stm=DB.getConnection().prepareStatement(query);
			stm.setInt(1, did);
			stm.setString(2, apdate);
			ResultSet rs=stm.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count==0) {
					return true;
				}
				else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

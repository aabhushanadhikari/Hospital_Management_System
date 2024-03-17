package com.View;

import java.util.Scanner;

import com.Service.PatientService;
import com.Service.PatientServiceImpl;

public class View {
	public static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		PatientService ser=new PatientServiceImpl();
		System.out.println("What do you want to do?");
		System.out.println("1.Create patients");
		System.out.println("2.Read Patients");
		int ch=Integer.parseInt(sc.nextLine());
		switch(ch) {
		case 1:
			ser.addPatients();
		case 2:
			ser.viewPatients();
		}
		
		
	}
}

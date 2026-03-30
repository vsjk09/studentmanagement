package com.project;

import java.sql.Connection;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.sql.*;

public class Main {

	static ArrayList<Student> students=new ArrayList<>();
	static Connection getConnection()
	{
		
		try {
			String url="jdbc:mysql://localhost:3306/student_db";
			String user="root";
			String password="vsjk@09";
			Connection con=DriverManager.getConnection(url,user,password);
			return con;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}}
	static void addStudents(Scanner sc) {
		try {
			Connection con = getConnection();
			System.out.println("Enter ID:  ");
			int id = sc.nextInt();
			System.out.println("Enter Name:  ");
			String name = sc.next();
			System.out.println("Enter Age:  ");
			int age = sc.nextInt();
			String query = "INSERT INTO students (id,name,age) VALUES (?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setInt(3,age);
			ps.executeUpdate();
			System.out.println("Student added!");
		}
		catch (Exception e) {
			System.out.println(e);
				}}
	static void viewStudents(Scanner sc) {
		try {
			Connection con=getConnection();
			String query="SELECT *FROM students";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while (rs.next()) {
				System.out.println( rs.getInt("id")+" " +rs.getString("name")+" "+rs.getInt("age"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	static void updateStudents(Scanner sc) {
		try {
		Connection con = getConnection();
		System.out.println("Enter ID:  ");
		int id = sc.nextInt();
		System.out.println("Enter new  Name:  ");
		String name = sc.next();
		System.out.println("Enter  new Age:  ");
		int age = sc.nextInt();
		String query = "UPDATE INTO students SET name=?,age=?, WHERE id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,name);
		ps.setInt(2,age);
		ps.setInt(3,id);
		ps.executeUpdate();
		System.out.println("Student updated!");
	}
	catch (Exception e) {
		System.out.println(e);
		}
	}
	static void deleteStudents(Scanner sc) {
		try {
		Connection con = getConnection();
		System.out.println("Enter ID to delete: ");
		int id=sc.nextInt();
		String query = " DELETE FROM students WHERE id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,id);
		ps.executeUpdate();
		System.out.println("Student deleted!");
	}
	catch (Exception e) {
		System.out.println(e);
		}
	}
	public static void main(String[] args) {
		
		Connection con =getConnection();
		if (con !=null) {
			System.out.println("Connected to database!");
		}
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.Add \n2.View \n3.Update \n4.Delete \n5.Exit");
			int choice = sc.nextInt();	
			switch(choice) {
			case 1:
				System.out.println("Add selected");
				addStudent(sc);
				break;
			case 2:
				System.out.println("View selected");
				viewStudents();
				break;
			case 3:
				System.out.println("Update selected");
				updateStudent(sc);
				break;
			case 4:
				System.out.println("Delete selected");
				deleteStudent(sc);
				break;
			case 5:
				System.out.println("Exiting...");
				break;
				default:
					System.out.println("Invalid choice!");
			}
		}
		}
			static void addStudent(Scanner sc) {
				System.out.println("Enter ID: ");
				int id =sc.nextInt();
				System.out.println("Enter name: ");
				String name = sc.next();
				System.out.println("Enter age: ");
				int age = sc.nextInt();
				students.add(new Student(id,name,age));
				System.out.println("Student Added!");
				}
			static void viewStudents() {
				for (Student s: students) {
					System.out.println(s.id + " " + s.name +" " + s.age);
				}}
				static void updateStudent(Scanner sc) {
					System.out.println("Enter ID to update: ");
					int id = sc.nextInt();
					for (Student s:students) {
						if (s.id==id) {
							System.out.println("Enter new name: ");
							s.name=sc.next();
							System.out.println("Enter new age: ");
							s.age=sc.nextInt();
							System.out.println("Updated successfully!");
							return;
							}
				}
					System.out.println("Student not found");
				}

			static void deleteStudent(Scanner sc) {
				System.out.println("Enter ID to delete:");
				int id=sc.nextInt();
				Iterator<Student> it = students.iterator();
				while(it.hasNext()) {
					Student s=it.next();
					if(s.id==id) {
						it.remove();
						System.out.println("Deleted successfully!");
						return;
					}
					}
				System.out.println("Student not found");	
				}
				}
		
		

	


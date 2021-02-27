package com;
import java.sql.*;

public class Student {
	
	public Connection connection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student", "root", "");
			
			System.out.print("Successfully connected");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;		
	}
	
	public String insertStudentDetails(String name, String phone, String email, String address, String course) {
		String result = "";
		
		try {
			Connection con = connection();
			if(con == null) {
				return "Error while connection to the database";
			}
			
			String query = "insert into students(studentID,studentName,phone,email,address,course)" + " values(?,?,?,?,?,?)";
			PreparedStatement preparedSt = con.prepareStatement(query);
			
			preparedSt.setInt(1, 0);
			preparedSt.setString(2, name);
			preparedSt.setString(3, phone);
			preparedSt.setString(4, email);
			preparedSt.setString(5, address);
			preparedSt.setString(6, course);
			
			preparedSt.execute();
			con.close();
			
			result = "Inserted successfully";
		}catch(Exception e) {
			result = "Error while inserting";
			System.err.print(e.getMessage());
		}
		return result;
	}

}

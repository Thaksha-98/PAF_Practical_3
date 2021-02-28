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
				return "Error while connecting to the database";
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
	
	public String readStudentDetails() {
		String result = "";
		
		try {
			Connection con = connection();
			if(con == null) {
				return "Error while connecting to the database";
			}
			
			result = "<table border = '1'><tr><th>Student ID</th>"
					+ "<th>Student Name</th><th>Phone</th><th>Email</th>"
					+ "<th>Address</th><th>Course</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "Select * From students";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String studentID = Integer.toString(rs.getInt("studentID"));
				String studentName = rs.getString("studentName");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String course = rs.getString("course");
				
				result += "<tr><td>" + studentID + "</td>";
				result += "<td>" + studentName + "</td>";
				result += "<td>" + phone + "</td>";
				result += "<td>" + email + "</td>";
				result += "<td>" + address + "</td>";
				result += "<td>" + course + "</td>";
				
				result += "<td><input name='btnUpdate' " + " type='button' value='Edit'></td>" 
						+ "<td><form method='post' action='Items.jsp'>" + "<input name='btnDelete' " + " type='submit' value='Delete'>" 
						+ "<input name='itemID' type='hidden' " + " value='" + studentID + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			result += "</table>";
			
		}catch(Exception e) {
			result = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return result;
		
	}

}

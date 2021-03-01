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
				
				result += "<td><form method='post' action='Students.jsp'>" 
						+ "<input name='btnGet' " + " type='submit' value='Edit'></td>"
						+ "<input name='id' type='hidden' " + " value='" + studentID + "'>" + "</form></td>"
						+ "<td><form method='post' action='Students.jsp'>"
						+ "<input name='btnDelete' " + " type='submit' value='Delete'>" 
						+ "<input name='itemID' type='hidden' " + " value='" + studentID + "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			result += "</table>";
			
		}catch(Exception e) {
			result = "Error while reading the student details.";
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public String getStudentDetail(String studentID) {
		String result = "";
		
		try {
			Connection con = connection();
			if(con == null) {
				return "Error while connecting to the database";
			}
			
			String query = " select * from students where studentID=?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			preparedSt.setInt(1, Integer.parseInt(studentID));
			ResultSet rs = preparedSt.executeQuery();
			
			while(rs.next()) {
				result = "<form method='post' action='Students.jsp'>"
					    + " Student Name: <input name='stName' type='text' value="+rs.getString("studentName")+"><br>"
						+ "	Phone: <input name='phone' type='text' pattern='[0-9]{10}' value="+rs.getString("phone")+"><br>"
					    + " Email: <input name='email' type='email' value="+rs.getString("email")+"><br>"
						+ " Address: <input name='address' type='text' value="+rs.getString("address")+"><br>"
					    + " Course: <input name='course' type='text' value="+rs.getString("course")+"><br>"
					    + "<form method='post' action='Students.jsp'>"
					    + "<input name='btnUpdate' type='submit' value='Save Change'>"
					    + "<input name='stId' type='hidden' " + " value='" + studentID + "'>" + "</form><br>";
			}
			con.close();

		}catch(Exception e) {
			result = "Error while geting the student details.";
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public String updateStudentDetail(String id, String name, String phone, String email, String address, String course) {
		String result = "";
		
		try {
			Connection con = connection();
			if(con == null) {
				return "Error while connecting to the database";
			}
			
			String query = "UPDATE students SET studentName=?, phone=?, email=?, address=?, course=? WHERE studentID=?";
			PreparedStatement preparedSt = con.prepareStatement(query);
			
			preparedSt.setString(1, name);
			preparedSt.setString(2, phone);
			preparedSt.setString(3, email);
			preparedSt.setString(4, address);
			preparedSt.setString(5, course);
			preparedSt.setInt(6, Integer.parseInt(id));

			
			preparedSt.execute();
			con.close();
			
			result = "Updated successfully";
		}catch(Exception e) {
			result = "Error while updating the student details.";
			System.err.println(e.getMessage());
		}
		
		return result;
	}

}

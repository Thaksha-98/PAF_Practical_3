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

}

<%@ page import="com.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	if(request.getParameter("studentName") != null){
    		Student std = new Student();
    		String insertMsg = std.insertStudentDetails(request.getParameter("studentName"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("address"), request.getParameter("course"));
    		session.setAttribute("insertStatus", insertMsg);
    	}
    	else if(request.getParameter("stId") != null){
    		Student stdUpdate = new Student();
    		String updateMsg = stdUpdate.updateStudentDetail(request.getParameter("stId"),request.getParameter("stName"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("address"), request.getParameter("course"));
    		session.setAttribute("updateStatus", updateMsg);
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Management</title>
</head>
<body>

	<h1>Student Management</h1>

	<%
		if(request.getParameter("id") != null){
			Student stdGet = new Student();
			out.print(stdGet.getStudentDetail(request.getParameter("id")));
		}
		else{
			out.print("<form method = 'post' action = 'Students.jsp'>"
					+ "Student Name: <input name='studentName' type='text'><br>"
					+ "Phone: <input name='phone' type='text' pattern='[0-9]{10}'><br>"
					+ "Email: <input name='email' type='email'><br>"
					+ "Address: <input name='address' type='text'><br>"
					+ "Course: <input name='course' type='text'><br>"
					+ "<input name='btnSubmit' type='submit' value='Save'>" + "</form><br>");
		}
	
	%>


	<%
		if(request.getParameter("studentName") != null ){
			out.print(session.getAttribute("insertStatus"));
		}
		else if(request.getParameter("stId") != null){
			out.print(session.getAttribute("updateStatus"));
		}
	
	%>
	
	<br>
	
	<%
 		Student stdAllDetails = new Student();
 		out.print(stdAllDetails.readStudentDetails());
	%>

</body>
</html>
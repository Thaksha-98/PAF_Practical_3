<%@ page import="com.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	if(request.getParameter("studentName") != null){
    		Student std = new Student();
    		String msg = std.insertStudentDetails(request.getParameter("studentName"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("address"), request.getParameter("course"));
    		session.setAttribute("statusMsg", msg);
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Management</title>
</head>
<body>

<h1 style= "align:center">Student Management</h1>

	<form method = "post" action = "Students.jsp" style = "align:center">
		Student Name: <input name="studentName" type="text"><br>
		Phone: <input name="phone" type="text" pattern="[0-9]{10}"><br>
		Email: <input name="email" type="email"><br>
		Address: <input name="address" type="text"><br>
		Course: <input name="course" type="text"><br>
		
		<input name="btnSubmit" type="submit" value="Insert">
	</form>
	<br>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	
	<br>
	<%
 		Student stdAllDetails = new Student();
 		out.print(stdAllDetails.readStudentDetails());
	%>

</body>
</html>
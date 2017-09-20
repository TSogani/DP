<%@page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action="ViewRegistrationServlet" method="Post">

First Name : <input type="text" name="fn"/><br>
Last Name : <input type="text" name="ln"/><br>
Gender : <input type="radio" value="male" name="gen"/> male
		<input type="radio" value="female" name="gen"/> Female 

city : <select name="city">

		<c:forEach var="city" items="${citiess.keySet()}">
			<option value=${city}> ${citiess.get(city)} </option>
		</c:forEach>

		</select>

state : <select name="state">
			<c:forEach var="state" items="${statess.keySet()}">
				<option value=${state}>${statess.get(state)}</option>
			</c:forEach>
		</select>
Country : <select name="country">
			<c:forEach var="country" items="${countryss.keySet() }">
				<option value=${country}>${countryss.get(country)}</option>
			</c:forEach>
		</select>
		
		<input type="submit">
 </form>
</body>
</html>
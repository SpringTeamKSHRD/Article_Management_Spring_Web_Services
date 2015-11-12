<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<p>author page</p>
<p>user ${name}</p>
<p>role ${role}</p>
<c:choose>
	<c:when test="${login}"><a href="logout">Logout</a></c:when>
	<c:otherwise><a href="login">Login</a></c:otherwise>
</c:choose>
<a href="author">Author</a>
<a href="admin">Admin</a>
</body>
</html>
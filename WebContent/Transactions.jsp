<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TRANSACTIONS</title>
</head>
<body>
<h1>RECENT TRANSACTIONS </h1>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/bankingapp" user="root" password="root"/>
<sql:query var="rs" dataSource="${db}">select * from transactions order by tid desc Limit 5; </sql:query>
<c:forEach items="${rs.rows}" var="i">
<br>Account Number <c:out value="${i.accno}"></c:out> <c:out value="${i.type}"></c:out> <c:out value="${i.amount}"></c:out>
</c:forEach>
<br><br><br> <a href="Account.jsp">Home</a>
</body>
</html>
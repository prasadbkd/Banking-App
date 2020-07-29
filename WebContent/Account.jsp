<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/bankingapp" user="root" password="root"/>
<sql:query var="rs" dataSource="${db}">select * from accounts</sql:query>
<c:forEach items="${rs.rows}" var="i" > 
<h1> Welcome <c:out value="${i.aname}"></c:out></h1>
<h2><br><br>THE ACCOUNT BALANCE IS : <c:out value="${i.amount}"></c:out></h2>
</c:forEach>

<form action="Tranfer" method="get">
<input type="text" name="tamt" placeholder="Amount to Transfer"> 
<input type="submit" value="Transfer">
</form>
<br><form action="Deposit" method="get">
<input type="text" name="damt" placeholder="Amount to Deposit"> <input type="submit" value="Deposit">
</form>
<br><a href="Transactions.jsp">View Transactions</a>
</body>
</html>
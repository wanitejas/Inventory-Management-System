<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Material Purchase Details</h2>
</div>
<!-- Display purchase details in table along with purchase id. -->
<style>
table {
  width:40%;
}
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 10px;
  text-align: left;
}
#t01 tr:nth-child(even) {
  background-color: #eee;
}
#t01 tr:nth-child(odd) {
 background-color: #fff;
}
#t01 th {
  background-color: black;
  color: white;
}
</style>
<center>
	<table id ="t01">
		<tr>
			<td>Purchase Id</td>
			<td><c:out value="${pb.purchaseId}"/></td>
		</tr>
		<tr>
			<td>Vendor name</td>
			<td><c:out value="${pb.vendorName}"/></td>
		</tr>
		
		<tr>
			<td>Material category</td>
			<td><c:out value="${pb.materialCategoryName}"/></td>
		</tr>
		
		<tr>
			<td>Material Type</td>
			<td><c:out value="${pb.materialTypeName}"/></td>
		</tr>
		
		<tr>
			<td>Unit</td>
			<td><c:out value="${pb.materialUnitName}"/></td>
		</tr>
		
		<tr>
			<td>Brand Name</td>
			<td><c:out value="${pb.brandName}"/></td>
		</tr>
		
		<tr>
			<td>Quantity</td>
			<td><c:out value="${pb.quantity}"/></td>
		</tr>
		
		<tr>
			<td>Purchase Amount</td>
			<td><c:out value="${pb.purchaseAmount}"/></td>
		</tr>
		
		<tr>
			<td>Purchase Date</td>
			<td><fmt:formatDate pattern="dd-MMM-yyyy" value="${pb.purchaseDate}"/></td>
		</tr>
		
		<tr>
			<td>Transaction Id</td>
			<td><c:out value="${pb.transactionId}"></c:out></td>
		</tr>	
	</table>
	
	<br>
	<font color="green">${msg}</font>
</center>
<br>

<div class="terms1">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>
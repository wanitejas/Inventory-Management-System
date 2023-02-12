<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Purchase Entry</title>

<script type="text/javascript">

function getUnitAndTypeList(id)
{    
	document.forms["form1"].action="getUnitAndTypeList.html";
    document.forms["form1"].method="post";
	document.forms["form1"].submit();
}

function displayPageElements()
{
	if(document.getElementById("category").value == ""){
		
	document.getElementById("belowTables").style.display = "none";
	}else{
		
		document.getElementById("belowTables").style.display = "block";
	}
}

</script>
</head>

<body onload="displayPageElements();" >
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Material Purchase Entry</h2>

<f:form name="form1" modelAttribute="purchaseBean"> 

	<table class="bodycontainer" >
		<tr>
			<td>Vendor Name</td>
			<td>
				<f:select path="vendorName" id="vendorName">
					<f:option label="--Select--" value="" />
					<f:options items="${vendorList}"  itemValue="vendorName" itemLabel="vendorName"/>
				</f:select>
				<font color="red"><f:errors path="vendorName" style="font-family:sans serif;"/></font>
			</td>
		</tr>
	
		<tr>
			<td>Material Category</td>
			<td>
				<f:select path="materialCategoryId" id="category" onchange="getUnitAndTypeList(category.value)" method="post">
					<f:option label="--Select--" value=""/>
					<f:options items="${categoryList}" itemValue="categoryId" itemLabel="categoryName"/>
				</f:select>
				<font color="red"><f:errors path="materialCategoryId" style="font-family:sans serif;" /></font>
			</td>
		</tr>	
	</table>
	
</f:form>
	
<f:form name="form2" modelAttribute="purchaseBean" action="addPurchaseDetail.html" method="POST">

	<table class="bodycontainer" id="belowTables">
		<tr>
			<td>Material Type</td>
			<td>
				<f:select path="materialTypeId" itemValue="typeId">
					<f:option label="--Select--" value=""/>
					<f:options items="${mtbl}" />
				</f:select>
				<font color="red"><f:errors path="materialTypeName" style="font-family:sans serif;" /></font>
			</td>		
		</tr>
		
		<tr>
			<td>Unit</td>
			<td>
				<f:select path="unitId" itemValue="">
					<f:option label="--Select--" value=""/>
					<f:options items="${ubl}" />
				</f:select>
				<font color="red"><f:errors path="materialUnitName" style="font-family:sans serif;" /></font>
			</td>
		</tr>
		
		<tr>
			<td>Brand Name</td>
			<td>
				<f:input path="brandName"/>
				<font color="red"><f:errors path="brandName" style="font-family:sans serif;" /></font>
			</td>
		</tr>
		
		<tr>
			<td>Quantity</td>
			<td>
				<f:input path="quantity"/>
				<font color="red"><f:errors path="quantity" style="font-family:sans serif;" /></font>
			</td>
		</tr>
		
		<tr>
			<td>Purchase Amount (&#8377;)</td>
			<td>
				<f:input path="purchaseAmount" />
				<font color="red"><f:errors path="purchaseAmount" style="font-family:sans serif;" /></font>
			</td>
		</tr>
		
		<tr>
			<td>Purchase Date</td>
			<td>
				<f:input path="purchaseDate" id="purchaseDate"  readonly="true" onclick="javascript:NewCssCal('purchaseDate')"/>
				<font color="red"><f:errors path="purchaseDate" style="font-family:sans serif;" /></font>
			</td>		
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="Submit"/></td>
	    </tr>
	    	
	    <tr>
	    	<td>${message}</td>
	    </tr>
	</table>
	
</f:form>

</div>
<div class="terms2">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>
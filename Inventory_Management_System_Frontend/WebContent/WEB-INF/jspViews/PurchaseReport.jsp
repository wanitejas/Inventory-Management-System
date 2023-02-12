<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<jsp:include page="include.jsp" />

<center>
	<h2>Date wise purchase report</h2>
	<form:form action="getRecords.html" modelAttribute="dateRangeBean">
		<table>
			<tr>
				<td>From Date:</td>
				<td><form:input path="fromDate" id="fromDate" readonly="true" onclick="javascript:NewCssCal('fromDate')"/></td>
				<td>To Date:</td>
				<td><form:input path="toDate" id="toDate"  readonly="true" onclick="javascript:NewCssCal('toDate')"/></td>
				<td><input type="submit" value="Search"/></td>
			</tr>
		</table>
	</form:form>
	
	<c:if test="${not empty pbli}">
	<table border="1">
		<tr>
			<th>Material Category</th>
			<th>Material Type</th>
			<th>Brand</th>
			<th>Quantity</th>
			<th>Unit</th>
			<th>Price</th>
			<th>Status</th>
			<th>Vendor</th>
		</tr>
		
		<c:forEach var="pb" items="${pbli}">
			<tr>
				<td><c:out value="${pb.getMaterialCategoryName()}"></c:out></td>
				<td><c:out value="${pb.getMaterialTypeName()}"></c:out></td>
				<td><c:out value="${pb.getBrandName()}"></c:out></td>
				<td><c:out value="${pb.getQuantity()}"></c:out></td>
				<td><c:out value="${pb.getMaterialUnitName()}"></c:out></td>
				<td><c:out value="${pb.getPurchaseAmount()}"></c:out></td>
				<td><c:out value="${pb.getStatus()}"></c:out></td>
				<td><c:out value="${pb.getVendorName()}"></c:out></td>
			</tr>	
		</c:forEach>
	</table>
	</c:if>
	<spring:hasBindErrors name="dateRangeBean">
		<font color="red"><form:errors path="*"></form:errors></font>
	</spring:hasBindErrors>
</center>
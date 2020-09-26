<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ATM Address</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">

</head>
<body>
	<h4 align="center">ATM Address</h4>
	
	<div align="center">
		<a href="${pageContext.request.contextPath}/locator/atms">Search ATM</a> | <a href="${pageContext.request.contextPath}/auth/signOut">Logout</a>
	</div>
	
	<div align="center">
			
		<table id="atmLocatorDetails" >
	      
	       <!-- Header Table -->
	       <thead>
	            <tr>
					<th>Street</th>
	                <th>House Number</th>
	                <th>Postal Code</th>
	                <th>Distance</th>
	                <th>Type</th>
	                <th>GeoLocation(lat,lng)</th>
	            </tr>
	        </thead>
	        <tbody>
					<!-- Iterating over the list sent from Controller -->
					<c:forEach var="list" items="${atmLocators}">
						<tr>
							<td>${list.address.street}</td>
							<td>${list.address.housenNumber}</td>
							<td>${list.address.postalCode}</td>
							<td>${list.distance}</td>
							<td>${list.type}</td>
							<td>${list.address.geoLocation.lat}, ${list.address.geoLocation.lng}</td>
						</tr>
					</c:forEach>
			</tbody>
	    </table>
	 </div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#atmLocatorDetails').DataTable({
			"jQueryUI" : true,
			"pageLength": 10,
			"oLanguage": {
		        "sEmptyTable": "ATMs not found"
		    },
			"lengthMenu" : [ [ 5, 10, 50, -1 ], [ 5, 10, 50, "All" ] ]
		});
	});
</script>
</html>
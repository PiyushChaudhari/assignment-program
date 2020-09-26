<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ATMs</title>

</head>
<body>
	<h4 align="center">Search Atms</h4>
	
	<div align="center">
		<a href="${pageContext.request.contextPath}/auth/signOut">Logout</a>
	</div>

	<div class="center">
		<form:form method="POST" action="${pageContext.request.contextPath}/locator/search-atms" modelAttribute="searchCityVO">

			<table align="center">

				<c:if test="${error !=null}">
					<tr>
						<td colspan="2"></td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" style="color: red;" ><c:out value="${error}" /></td>
				</tr>
				
				<tr>
					<td><form:label path="cityName">City:</form:label></td>
					<td><form:input path="cityName" value="${searchCityVO.cityName}" /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" ><input type="submit" value="Search" /></td>
				</tr>
			</table>			
		</form:form>
	</div>
	
</body>
</html>
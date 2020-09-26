
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1 align="center">Login</h1>

	<div class="center">
		<form:form method="POST" action="${pageContext.request.contextPath}/auth/signIn" modelAttribute="loginVO">

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
					<td><form:label path="email">Email:</form:label></td>
					<td><form:input path="email" value="${loginVO.email}" /></td>
				</tr>
				
				<tr>
					<td><form:label path="password">Password:</form:label></td>
					<td><form:password path="password" value="${loginVO.password}" /></td>
				</tr>

				
				<tr>
					<td colspan="2" align="center" ><input type="submit" value="Login" /></td>
				</tr>
			</table>			
		</form:form>
	</div>
</body>
</html>
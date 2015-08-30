<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Social Support</title>
</head>
<body>

<h1>Search Cases</h1>

<c:url var="saveUrl" value="/api/caserc/search/cases" />
<form:form modelAttribute="searchAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="createDate">Create Date:</form:label></td>
			<td><form:input path="createDate"/></td>
		</tr>
		<tr>
			<td><form:label path="keyword">Keyword:</form:label></td>
			<td><form:input path="keyword"/></td>
		</tr>
		<tr>
			<td><form:label path="caseStatus">Case Status:</form:label></td>
			<td><form:input path="caseStatus"/></td>
		</tr>		
	</table>
	
	<input type="submit" value="Search" />
</form:form>

</body>
</html>
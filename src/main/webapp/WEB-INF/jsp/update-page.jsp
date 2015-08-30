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

<h1>Update Cases</h1>

<c:url var="saveUrl" value="/api/caserc/update/cases" />
<form:form modelAttribute="updateAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="startId">Start ID:</form:label></td>
			<td><form:input path="startId"/></td>
			<td><form:label path="endId">End ID:</form:label></td>
			<td><form:input path="endId"/></td>
		</tr>

		<tr>
			<td><form:label path="field">Field:</form:label></td>
			<td><form:input type="radio" name="field" value="status" path="field" 
			onclick="if (this.checked) {
					document.getElementById('status').style.visibility='visible';
					document.getElementById('comment').style.visibility='hidden';
					}"/>Status</td>
			<td><form:input type="radio" name="field" value="comment" path="field"
			onclick="if (this.checked) {
					document.getElementById('status').style.visibility='hidden';
					document.getElementById('comment').style.visibility='visible';
					}"/>Comment</td>
		</tr>
		<tr>
			<td><form:label path="value">Value:</form:label></td>
			<td id="status"><form:select name="value" path="value">
					<form:option value="New">New</form:option>
					<form:option value="Answered">Answered</form:option>
					<form:option value="Pending Resolution">Pending Resolution</form:option>
					<form:option value="Awaiting Player Reply">Awaiting Player Reply</form:option>
					<form:option value="Updated by Player">Updated by Player</form:option>
					<form:option value="No Player Response">No Player Response</form:option>
					<form:option value="Escalated">Escalated</form:option>
				</form:select>
			</td>
			<td id="comment">
				<form:input path="value"/>
			</td>
		</tr>
	</table>
	<br></br>
	<input type="submit" value="Update" />
</form:form>
</body>
</html>
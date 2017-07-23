<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Subject Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Subject
</h1>

<c:url var="addAction" value="/subject/add" ></c:url>

<form:form action="${addAction}" commandName="subject">
<table>
	<c:if test="${!empty subject.subjectId && subject.subjectId!=0}">
	<tr>
		<td>
			<form:label path="subjectId">
				<spring:message text="Subject ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="subjectId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="subjectId" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="subjectCode">
				<spring:message text="Subject Code"/>
			</form:label>
		</td>
		<td>
			<form:input path="subjectCode" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="subjectDesc">
				<spring:message text="Subject Description"/>
			</form:label>
		</td>
		<td>
			<form:input path="subjectDesc" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty subject.subjectId && subject.subjectId!=0}">
				<input type="submit"
					value="<spring:message text="Edit Subject"/>" />
			</c:if>
			<c:if test="${subject.subjectId==0}">
				<input type="submit"
					value="<spring:message text="Add Subject"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Subjects List</h3>
<c:if test="${!empty listSubjects}">
	<table class="tg">
	<tr>
		<th width="80">Subject ID</th>
		<th width="120">Subject Code</th>
		<th width="120">Subject Description</th>
		<th width="120">Status</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listSubjects}" var="subject">
		<tr>
			<td>${subject.subjectId}</td>
			<td>${subject.subjectCode}</td>
			<td>${subject.subjectDesc}</td>
			<td>${subject.subjectStatus}</td>
			<td><a href="<c:url value='/editSubject/${subject.subjectId}' />" >Edit</a></td>
			<td><a href="<c:url value='/removeSubject/${subject.subjectId}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Create Pattern</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h2>
	Add a Pattern
</h2>

<c:url var="addAction" value="/patterns/initPattern" ></c:url>

<form:form action="${addAction}" commandName="paperPattern">

<table border="1">
	<tr>
		<td>
			<form:label path="paperPatternCode">
				<spring:message text="Pattern Code"/>
			</form:label>
		</td>
		<td>
			<form:input path="paperPatternCode" size="40"/>
		</td>
		
	</tr>
	<tr>
		<c:if test="${!empty listSubjects}">
			<td>
				<form:label path="subjectRules">
					<spring:message text="Subjects List"/>
				</form:label>
			</td>
			<td>
				<form:select path="subjectRules" size="3" style="width:200px">
					<form:options items="${listSubjects}" itemValue="subjectId" itemLabel="subjectDesc"/>
				</form:select>
			</td>
		</c:if>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="<spring:message text="Proceed Next"/>" /></td>
	</tr>
</table>	
</form:form>
<br>
<h3>Subjects List</h3>
<c:if test="${!empty listPaperPattern}">
<table class="tg">
	<tr>
		<th width="80">Pattern ID</th>
		<th width="120">Pattern Code</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPaperPattern}" var="paperPattern">
	<tr>
		<th width="80">${paperPattern.paperPatternId}</th>
		<th width="120">${paperPattern.paperPatternCode}</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	</c:forEach>
	
</table>

</c:if>
</body>
</html>
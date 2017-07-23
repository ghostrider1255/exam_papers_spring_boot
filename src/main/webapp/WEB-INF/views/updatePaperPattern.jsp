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

<c:url var="addAction" value="/patterns/addPaperPattern" ></c:url>

<form:form action="${addAction}" commandName="paperPatternInit">

<table border="1">
	<c:if test="${!empty paperPatternInit}">
		<tr>
			<form:label path="paperPatternCode">
				<spring:message text="Paper Pattern Code"/>
			</form:label>
			<td>
				<form:input path="paperPatternCode" disabled="true"/>
				<form:hidden path="paperPatternCode" />
			</td>
		</tr>
		<c:forEach items="${paperPatternInit.subjectRules}" var="subjectRule" varStatus="status">
		<tr>
			<td>
			${subjectRule.pSubject.subjectDesc}
			<form:hidden path="subjectRules[${status.index}].pSubject.subjectId"/>
			<%-- <form:hidden path="subjectRules[${status.index}].pSubject.subjectCode"/>
			<form:hidden path="subjectRules[${status.index}].pSubject.subjectDesc"/> --%>
			</td>
			<td><form:input path="subjectRules[${status.index}].numberOfQuestions"/></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2"><input type="submit" value="<spring:message text="Proceed Next"/>" /></td>
		</tr>
	</c:if>
	
</table>	
</form:form>
<br>
</body>
</html>
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

<c:url var="addAction" value="/exam/selectExamQuestions" ></c:url>

<form:form action="${addAction}" commandName="examPattern">
<table border="0">
	<c:if test="${!empty listPaperPattern}">
	<tr>
		<td>
			<form:label path="paperPattern">
				<spring:message text="Subjects List"/>
			</form:label>
		</td>
		<td>
			<form:select path="paperPattern" size="1" style="width:200px" multiple="false">
				<form:options items="${listPaperPattern}" itemValue="paperPatternId" itemLabel="paperPatternCode"/>
			</form:select>
		</td>
	</tr>
	</c:if>
<c:if test="${!empty exam.examId and exam.examId > 0}">
	<tr>
		<td>
			<form:label path="examId">
				<spring:message text="Exam Id"/>
			</form:label>
		</td>
		<td>
			<form:input path="examId" size="40" disabled="true"/>
			<form:hidden path="examId" />
		</td>
	</tr>
</c:if>
	<tr>
		<td>
			<form:label path="examCode">
				<spring:message text="Exam Code"/>
			</form:label>
		</td>
		<td>
			<form:input path="examCode" size="40"/>
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="examDesc">
				<spring:message text="Exam Description"/>
			</form:label>
		</td>
		<td>
			<form:input path="examDesc" size="40"/>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="<spring:message text="Proceed Next"/>" /></td>
	</tr>
</table>	
</form:form>
<br>
<h3>Exams List</h3>
</body>
</html>
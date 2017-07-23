<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Question Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h2>
	Add a Question
</h2>

<c:url var="addAction" value="/questions/addQuestion" ></c:url>

<form:form action="${addAction}" commandName="question">

<table border="1">
	<tr>
		<c:if test="${!empty listSubjects}">
			<td colspan="2">
				<form:label path="subjects">
					<spring:message text="Subjects List"/>
				</form:label>
			</td>
			<td colspan="4">
				<form:select path="subjects" size="3" >
					<form:options items="${listSubjects}" itemValue="subjectId" itemLabel="subjectDesc"/>
				</form:select>
			</td>
		</c:if>
	</tr>
	<c:if test="${!empty question.questionText}">
	<tr>
		<td>
			<form:label path="questionId">
				<spring:message text="Question ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="questionId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="questionId" />
		</td> 
	</tr>
	</c:if>
	<tr rowspan="4">
		<td>
			<form:label path="questionText">
				<spring:message text="Question"/>
			</form:label>
		</td>
		<td colspan="6">
			<form:textarea path="questionText" size="1000" rows="20" cols="50"/>
		</td>
		
	</tr>
	<tr>	
		<td>
			<form:label path="choiceOne">
				<spring:message text="Choice One"/>
			</form:label>
		</td>
		<td>
			<form:textarea path="choiceOne" size="200"/>
		</td>
		<td>
			<form:label path="choiceTwo">
				<spring:message text="Choice Two"/>
			</form:label>
		</td>
		<td>
			<form:textarea path="choiceTwo" size="200"/>
		</td>
		<td>
			<form:label path="choiceThree">
				<spring:message text="Choice Three"/>
			</form:label>
		</td>
		<td>
			<form:textarea path="choiceThree" size="200"/>
		</td>
		<td>
			<form:label path="choiceFour">
				<spring:message text="Choice Four"/>
			</form:label>
		</td>
		<td>
			<form:textarea path="choiceFour" size="200"/>
		</td>
	</tr> 
	<tr>
		<td>
			<form:label path="answer">
				<spring:message text="Select Answer"/>
			</form:label>
		</td>
		<td>
			<form:select path="answer">
				<form:option value="NONE">--select the anser--</form:option>
				<form:option value="CHOICE_ONE">Choice One</form:option>
				<form:option value="CHOICE_TWO">Choice TWO</form:option>
				<form:option value="CHOICE_THREE">Choice Three</form:option>
				<form:option value="CHOICE_FOUR">Choice Four</form:option>
			</form:select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty question.answer}">
				<input type="submit"
					value="<spring:message text="Edit Question"/>" />
			</c:if>
			<c:if test="${empty question.answer}">
				<input type="submit"
					value="<spring:message text="Add Question"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html lang="en">
<head>
	<title>Select Questions for Exams</title>
	<meta charset="utf-8">
	<meta name="viewpoint" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css">
	<style type="text/css">
	a {
	color: #0254EB
}
a:visited {
	color: #0254EB
}
a.morelink {
	text-decoration:none;
	outline: none;
}
.morecontent span {
	display: none;
}
.comment {
	width: 90%;
	background-color: #f0f0f0;
	margin: 10px;
}
	</style>
</head>
<body>
<h2>
	Select Exam Questions
</h2>

<c:url var="addAction" value="/exam/addExam" ></c:url>

<form:form action="${addAction}" commandName="examPatternInit">
	<c:if test="${!empty examQuestions}">
	
		<form:hidden path="examCode"/>
		<form:hidden path="examDesc"/>
		<form:hidden path="paperPattern.paperPatternId"/>
		<div class="container">
			<ul class="nav nav-tabs">
				<c:set var="firstTab" value="true" scope="page"/>
				<c:forEach items="${examQuestions}" var="subjectEntry">
					<c:choose>
						<c:when test="${firstTab eq true}">
							<li class="active"><a role="tab" data-toggle="tab" href='#<c:out value="${fn:replace(subjectEntry.key,' ','_')}"/>'><c:out value="${subjectEntry.key}"/></a></li>
							<c:set var="firstTab" value="${!firstTab }" scope="page"/>
						</c:when>
						<c:otherwise>	
							<li><a role="tab" data-toggle="tab" href='#<c:out value="${fn:replace(subjectEntry.key,' ','_')}"/>'><c:out value="${subjectEntry.key}"/></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<div class="tab-content">
				<c:set var="firstTab" value="true" scope="page"/>
				<c:forEach items="${examQuestions}" var="subjectEntryTab">
					<c:choose>
						<c:when test="${firstTab eq true}">
							<div id='<c:out value="${fn:replace(subjectEntryTab.key,' ','_')}"/>' class="tab-pane fade in active" role="tabpanel">
							<c:set var="ind" value="0" scope="page"/>
							<c:set var="questionBySubject" value="${subjectEntryTab.value}" />
								<c:forEach items="${questionBySubject.questions}" var="question" varStatus="status">
									<div class="comment more">
										<span id="close" onclick='this.parentNode.parentNode.removeChild(this.parentNode); return false;'><a>X</a></span>
										<br>${question.questionText}<br>
									</div>
								</c:forEach>
								<input name="examQuestions['${subjectEntryTab.key}']" value="${subjectEntryTab.value}" hidden="true">
							</div>
							<c:set var="firstTab" value="${!firstTab }" scope="page"/>
						</c:when>
						<c:otherwise>
							<div id='<c:out value="${fn:replace(subjectEntryTab.key,' ','_')}"/>' class="tab-pane fade" role="tabpanel">
							<c:set var="questionBySubject" value="${subjectEntryTab.value}" />
								<c:forEach items="${questionBySubject.questions}" var="question" varStatus="status">
									<div class="comment more">
									<span id="close" onclick='this.parentNode.parentNode.removeChild(this.parentNode); return false;'><a>X</a></span>
									<br>${question.questionText}<br>
									<%-- <form:hidden path="examQuestions[${ind}].question"/> --%>
									</div>
								</c:forEach>
								<input name="examQuestions['${subjectEntryTab.key}']" value="${subjectEntryTab.value}" hidden="true">
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	<input type="submit" value="<spring:message text="Create Exam"/>" />
	</c:if>
</form:form>
<br>
<script type="text/javascript">
$(document).ready(function() {
	var showChar = 100;
	var ellipsestext = "...";
	var moretext = "more";
	var lesstext = "less";
	$('.more').each(function() {
		var content = $(this).html();

		if(content.length > showChar) {

			var c = content.substr(0, showChar);
			var h = content.substr(showChar-1, content.length - showChar);

			var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';

			$(this).html(html);
		}

	});

	$(".morelink").click(function(){
		if($(this).hasClass("less")) {
			$(this).removeClass("less");
			$(this).html(moretext);
		} else {
			$(this).addClass("less");
			$(this).html(lesstext);
		}
		$(this).parent().prev().toggle();
		$(this).prev().toggle();
		return false;
	});
});
</script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/jquery-3.2.0.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
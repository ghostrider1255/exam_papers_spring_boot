<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

div {
	display: block;
}
#top{
	width: 100%;
	height: 180px;
	background: white;
	//border: 2px ridge red;
}
#logoBody{
	width: 1200px;
	height: 174px;
	margin: 0 auto;
	background: white;
	//border: 2px ridge red;
}

#logoCategory{
	width: 200px;
	height: 176px;
	background-color: white;
	position: absolute;
	float: left;
	//border: 1px ridge black;
}

#logoContainer{
	width: 980px;
	height: 172px;
	background-color: white;
	float: right;
	//border: 1px ridge black;
}

#categoryHeader{
	width: 200px;
	height: 30px;
	background-color: #589ADB;
	border-radius: 10px 10px 0px 0px;
	position: absolute;
	bottom: 0px;
	font-style: Bold;
	font-size: 20px;
	color: white;
	float: left;
	padding: 5px 0px 5px;
}
#horizontalMenuArea{
	position: absolute;
	top: 0px;
	width: 900px;
	height: 50px;
	background-color: #102841;
	border-radius: 10px 10px 0px 0px;
	font-style: Bold;
	font-size: 17px;
	color: white;
	bottom: 10px;
	margin-top: 137px;
	margin-left: 20px;
}

ul.horizMenu{
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	
}
li.horizMenu{
	float: left;
}
li a.horizMenu{
	display: block;
	color: white;
	text-align: center;
	padding: 20px 30px;
	text-decoration: none;
}
li a.horizMenu:hover{
	color: #589ADB;
}

li.horizMenu:first-child a.horizMenu{
	color: #589ADB;
}
.searchArea{
	//display: inline-block;
	background: none;
	height: 31px;
	width: 630px;
	margin-top: 60px;
	margin-left: 22px;
	border: 0px;
	border-radius: 0px 5px 5px 0px;	
	//border: 2px ridge red;
	
}
.searchBox{
	padding: 0px;
	height:31px;
	width:500px;
	border-radius: 10px 0px 0px 10px;
	border-color: #589ADB;
	border-right: 0px;
	background: transparent;
	font: 15px solid, "Times New Roman";
	padding: 0px 30px 0px 10px;
}
.searchBox input,
.searchBox input:active,
.searchBox input:focus{
	margin-top: -15px;
	border-color: #589ADB;
	border-right: 0px;
}
.searchButton{
	padding: 0px;
	height:36px;
	width:80px;
	background-color: #589ADB;
	border-radius: 0px 10px 10px 0px;
	border: 0px;
	color: white;
	-webkit-transition: color 2s ease-out;
    -moz-transition: color 2s ease-out;
    -ms-transition: color 2s ease-out;
    -o-transition: color 2s ease-out;
	transition: color 2s ease-out;
}
input.searchButton:hover{
	background-color: black;
}
#logo_twitter{
	right: 300px;
	width: 27px;
	background: url('images/social-media-icons.jpg') -124px -19px;
	border-radius: 10px 10px 10px 10px;
}
#logo_twitter a:hover{
	right: 300px;
	width: 27px;
	background: url('images/social-media-icons.jpg') -124px -118px;
	border-radius: 10px 10px 10px 10px;
}
#logo_gPlus{
	right: 268px;
	width: 27px;
	background: url('images/social-media-icons.jpg') -87px -19px;
	border-radius: 10px 10px 10px 10px;
}
#logo_gPlus a:hover{
	right: 268px;
	width: 27px;
	background: url('images/social-media-icons.jpg') -87px -118px;
	border-radius: 10px 10px 10px 10px;
}
#logo_fb{
	right: 234px;
	width: 27px;
	background: url('images/social-media-icons.jpg') -342px -19px;
	border-radius: 10px 10px 10px 10px;
}

#logo_fb a:hover{
	right: 234px;
	width: 27px;
	background: url('images/social-media-icons.jpg') -342px -118px;
	border-radius: 10px 10px 10px 10px;
}
#logo_nav_ul{
	position: relative;
	//border: 2px ridge red;
	height: 20px;
	width: 600px;
	float: right;
}
#logo_nav_ul li{
	margin: 0;
	padding: 0;
	list-style: none;
	position: absolute;
	top: 0;
}
#logo_nav_ul li,#logo_nav_ul a{
	height: 28px;
	display: block;
}

</style>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div id="top">
			<div id="logoBody">
				<div id="logoCategory"> 
					<div id="categoryHeader">&nbsp; &nbsp;Category</div>
				</div>
				<div id="logoContainer">
					<div>
						<ul id="logo_nav_ul">
							<li id="logo_twitter"><a href="index.html"></a></li>
							<li id="logo_gPlus"><a href="index.html"></a></li>
							<li id="logo_fb"><a href="index.html"></a></li>
						</ul>
					</div>	
					<div class="searchArea">
						<input class="searchBox" type="text" name="searchBox">
						<input class="searchButton" type="submit" name="search" value="Search" >
					</div>
					<div id="horizontalMenuArea">
						<ul class="horizMenu">
							<li class="horizMenu"><a class="horizMenu" href="${contextPath}/index">Home</a></li>
							<li class="horizMenu"><a class="horizMenu" href="${contextPath}/pattern/createPattern">Paper Pattern</a></li>
							<li class="horizMenu"><a class="horizMenu" href="${contextPath}/exam/createExamPattern">Exam</a></li>
							<li class="horizMenu"><a class="horizMenu" href="${contextPath}/subjects">Subject</a></li>
							<li class="horizMenu"><a class="horizMenu" href="${contextPath}/questions">Question</a></li>
							<li class="horizMenu"><a class="horizMenu" href="#">Contact US</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
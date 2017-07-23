<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter Test</title>
<style type="text/css">

div {
	display: block;
}
#foot{
	width: 100%;
	height: 200px;
	background: #1B2631;
	border-radius: 10px 10px 0px 0px;
}

#footerInfo{
	width: 100%;
	position: absolute;
	margin-top: 50px;
}

ul.footerMenu{
	list-style-type: none;
	margin: 0;
	padding: 10px;
	width: 220px;
	display: inline-block;
	float: left;
	//background-color: white;
}
li.footerMenu{
	border-bottom:0px;
	padding: 10px 25px 5px 45px;
}
li.footerMenu:first-child{
	font-size: 15px;
	color: white;
}
li.footerMenu a.footerMenu{
	color: #589ADB;
	padding: 10px 15px 20px;
	text-decoration: none;
	font-size: 12px;
}


</style>
</head>
<body bgcolor="#F2F3F4">
	<div id="foot">
		<div id="footerInfo">
			<ul class="footerMenu">
				<li class="footerMenu">Information</li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">Home</a></li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">About US</a></li>
			</ul>
			<ul class="footerMenu">
				<li class="footerMenu">Collections</li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">Category One</a></li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">Category Two</a></li>
			</ul>
			<ul class="footerMenu">
				<li class="footerMenu">My Account</li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">My Account</a></li>
			</ul>
			<ul class="footerMenu">
				<li class="footerMenu">Follow US</li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">Twitter</a></li>
				<li class="footerMenu"><a class="footerMenu" href="index.html">FaceBook</a></li>
			</ul>
		</div>
		<hr class="footHR">
	</div>
</div>
</body>
</html>
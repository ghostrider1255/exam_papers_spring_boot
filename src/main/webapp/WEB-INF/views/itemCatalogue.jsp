<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter Test</title>
<style type="text/css">

div {
	display: block;
}

#body{
	width: 1200px;
	min-height: 2300px;
	overflow-y: auto;
	margin: 0 auto;
	background: #F2F3F4;
	padding: 0;
	//border: 2px ridge black;
}

#category{
	width: 200px;
	height: 1400px;
	background-color: #F2F3F4;
	position: absolute;
	float: left;
	//border: 2px ridge yellow;
}

#container{
	width: 980px;
	height: 1150px;
	background-color: #F2F3F4;
	float: right;
	//padding: 20px 0px 20px 20px;
	//border: 2px ridge red;
}

#itemHeader{
	display: inline-block;
	width: 900px;
	height: 45px;
	background-color: white;
	text-align: left;
	font-size: 20px;
	border: 1px ridge;
	margin: 20px;
}
#item{
	display: inline-block;
	float: left;
	width: 270px;
	height: 300px;
	margin: 20px 20px 20px 20px;
	background-color: white;
	//border: 2px ridge red;
}

ul.ulcategory{
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 200px;
	background-color: white;
}
li.ulcategory{
border-bottom: 2px solid #F2F3F4;

}
li.ulcategory:last-child {
    border-bottom: none;
}
li a.ulcategory{
	display: block;
	color: #000;
	padding: 8px 10px;
	text-decoration: none;
	font-size: 13px;
}
li a.ulcategory:hover{
	background-color: white;
	color: blue;
}

img.itemImg {
	height: 205px;
	width: 205px;
	align: center;
	padding: 0px 10px 10px 30px;
	//border: 1px solid red;
	top: 0px;
}
div.itemRate{
	font-size: 25px;
	color: #589ADB;
	font-weight: bold;
	padding: 0px 80px;
	align: center;
}
div.itemDesc{
	font-size: 18px;
	color: grey;
	font-family: "Verdana",Sans-serif;
	//font-weight: bold;
	padding: 10px 20px;
}
</style>
</head>
<body bgcolor="#F2F3F4">
	<dir id="body">
		<div id="category">
			<ul class="ulcategory">
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Category One</a></li>
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Category Tow</a></li>
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Category Three</a></li>
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Category Four</a></li>
			</ul>
			&nbsp;<br>
			&nbsp;
			<ul class="ulcategory">
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Filter One</a></li>
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Filter Tow</a></li>
  				<li class="ulcategory"><a class="ulcategory" href="index.html">Filter Three</a></li>
			</ul>
		</div>
		<div id="container">
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <div id="itemHeader"> &nbsp; &nbsp; Items List</div>
		<div id="item">
		<form:form commandName="imageList">
			<c:if test="${!empty imageList}">
				<c:set var="contextPath" value="${pageContext.request.contextPath}" />
				<c:forEach items="${imageList}" var="image">
					<img class="itemImg" alt="cake one" src="${contextPath}/index/imageDisplay?img_id=${image.img_id}" > 
				</c:forEach>
			</c:if>
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black currenttt</div>
		</form:form> 
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/black_forest.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black current</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/black_forest.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black current</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="itemHeader"> &nbsp; &nbsp; Items List</div>
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/black_forest.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black current</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/black_forest.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black current</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/black_forest.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black current</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/button_scotch.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Buttor Scotch</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		<div id="item"> 
			<img class="itemImg" alt="cake one" src="images/black_forest.jpeg" > 
			<div class="itemRate">&#x20b9;100.00</div>
			<div class="itemDesc">Black current</div>
		</div> &nbsp; &nbsp; &nbsp; &nbsp;
		</div>
	</dir>
</div>
</body>
</html>
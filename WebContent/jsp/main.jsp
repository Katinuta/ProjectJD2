<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
	.main {
		background:  url("jsp/img/main_1.jpg")  ;
		background-repeat: no-repeat;
		background-position:center center;
		height: 100vh;
		background-size: 100%;
		
	}
	#box2 {
		position:absolute;
		top: 50px;
		left: 50px;
		font-size: 25px;
		font-weight: bold;
		font-style: italic;
	}
	#box1{
		position:absolute;
		left:30%;
		top: 40px;
	}
	</style>
	<title>Main page</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
</head>
<body >
	<div class="main">
	 	<div id="box1" align="center">
			<c:import url="/controller">
				<c:param name="command" value="subject"/>
			</c:import>
		</div>
		<div id="box2" align="center">
			<c:out value="${sessionScope.user.name}"/>
			<c:out value="${sessionScope.user.surname}"/><br/>
			<c:url var="logout" value="/controller">
					<c:param name="command" value="logout"/>
			</c:url>
			<a href="${logout}">Exit</a>
		</div>
	</div>
</body>
</html>
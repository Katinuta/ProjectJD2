<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; UTF-8">
		<title>Result </title>
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
				top: 20px;
				left: 50px;
				font-size: 25px;
				font-weight: bold;
				font-style: italic;
			}
			#box3{
				position:absolute;
				top: 100px;
				left:600px;
				font-size: 25px;
				font-weight: bold;
				font-style: italic;
			}
		</style>
	</head>
	<body class="main">
		<div id="box2">
			<c:out value="${sessionScope.user.name}"/>
			<c:out value="${sessionScope.user.surname}"/><br/>
			<c:url var="saveResult" value="/controller">
				<c:param name="countRightQuestion" value="${countRightQuestion}"/>
				<c:param name="command" value="saveResult"/>
			</c:url>
			<a href="${saveResult}">Return to main page</a>
		</div>
		<div id="box3">
			<c:out value="${sessionScope.subject.name}"/><br/>
			<c:out value="${sessionScope.test.name}"/><br/>
			<c:out value="${countRightQuestion}"/>
			<c:out value=" from "/>
			<c:out value="${countQuestion}"/>
		</div>
	</body>
</html>
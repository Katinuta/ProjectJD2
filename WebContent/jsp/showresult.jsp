<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			body{
				font-size: 20px;
				font-weight: bold;
				font-style: italic;
			}
		</style>
	</head>
	<body>
		 <c:if test="${result.result!=0}">
			<c:out value="${result.result}"/>
		</c:if>
	</body>
</html>
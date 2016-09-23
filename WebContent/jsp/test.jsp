<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>Insert title here</title>
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
			left:500px;
			width:400px;
			top: 40px;
			font-size: 30px;
			font-weight: bolder;
			text-align: center;
		}
		.td1col{
			width:300px;
			font-size: 24px;
			font-weight: bold;
			font-style: italic;
			text-align: center;
		}
		.td2col{
			width:115px;
			text-align: center;
		}
		#box3{
			position:absolute;
			top: 80px;
			left:400px;
		}
		table{
			 border-collapse:separate;
			 border-spacing: 7px 11px;
		}
		button{
			font-size: 16px;
			font-weight: bold;
		}
	</style>
	</head>
	<body class="main">
		<div >
			<div id="box2">
				<c:out value="${sessionScope.user.name}"/>
				<c:out value="${sessionScope.user.surname}"/><br>
				<c:url var="tomain" value="/controller">
					<c:param name="command" value="returnMain"/>
				</c:url>
				<a href="${tomain}">Return to main page</a><br/>
				<c:url var="logout" value="/controller">
					<c:param name="command" value="logout"/>
				</c:url>
				<a href="${logout}">Exit</a>
			</div>
			<div id="box1">
				<c:out value="${sessionScope.subject.name}"/>
			</div>
			<div id="box3">
				<table>
					<c:forEach var="test" items="${testList}" >
					<tr >
						<td class="td1col">
							<c:out value="${test.name}"/>
							</td>
						<td class="td2col">
							<c:if test="${user.access}">
								<form action="controller" method="GET">
									<input type="hidden" name="command" value="deletetest"/>
									<button  type="submit" value="${test.testId}" name="button">Delete Test</button>
								</form>
							</c:if>
							<c:if test="${!user.access}">
								<c:import url="/controller">
									<c:param name="command" value="lookResult"/>
									<c:param name="testId" value="${test.testId}"/>
								</c:import>
							</c:if>
							
						</td>
						<td class="td2col">
							<c:choose>
								<c:when test="${user.access}">
									<form action="controller" method="GET">
										<input type="hidden" name="command" value="changeTest"/>
										<button  type="submit" value="${test.testId}" name="button">Change Test</button>
									</form>
								</c:when>
								<c:otherwise>
									<c:if test="${result.result!=0}">
										<button  type="submit" value="${test.testId}" name="button" disabled>Pass the Test</button>
									</c:if>
									<c:if test="${result.result==0}">
										<form action="controller" method="POST">
											<input type="hidden" name="command" value="choosepasstest"/>
											<button  type="submit" value="${test.testId}" name="button">Pass the Test</button>
										</form>	
									</c:if>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					</c:forEach>
					<tr/>
					<tr>
					
						<c:if test="${user.access}">
						<td class="td2col">
							<form action="controller" method="POST">
								<input type="hidden" name="command" value="addTest"/>
								<button  type="submit" value="new" name="buttom">Create new</button>
							</form>
							</td>	
						</c:if>
					</tr>
				</table>
			</div>
		</div>	
	</body>
</html>
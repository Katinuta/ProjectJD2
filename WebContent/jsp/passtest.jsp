<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
		.main {
			background:  url("jsp/img/main_1.jpg") ;
			background-repeat: no-repeat;
			background-position:center center;
			height: 100vh;
			background-size: 100%;
		
		}
		#box2 {
			position:absolute;
			top: 40px;
			left: 50px;
			font-size: 25px;
			font-weight: bold;
			font-style: italic;
		}
		#box3{
			position:absolute;
			top: 20px;
			left:600px;
			font-size: 25px;
			font-weight: bold;
			font-style: italic;
		}
		#box4{
				position:absolute;
				top: 80px;
				left:500px;
			}
		button{
			font-size: 14px;
			font-weight: bold;
		}
		.td1col{
			width:100px;
			font-size: 20px;
			font-weight: bold;
			font-style: italic;
			text-align: center;
		}
		.td2col{
			font-size: 20px;
		}
			</style>
	</head>
	<body class="main">
		<div id="box2">
			<c:out value="${sessionScope.user.name}"/>
			<c:out value="${sessionScope.user.surname}"/><br/>
		</div>
		<div id="box3">
			<c:out value="${sessionScope.subject.name }"/><br/>
			<c:out value="${sessionScope.test.name }"/><br/>
		</div>
		<form action="controller" method="POST">
			<input type="hidden" name="command" value="checktest">
			<div id="box4">
				<table>
					<c:forEach var="question" items="${listQuestion}" varStatus="theCountQuestion">
						<tr>
							<td class="td1col">
								<c:out value="Question ${theCountQuestion.count}"/>
							</td>
							<td class="td2col">
								<c:out value="${question.content}"/>
							</td>
						</tr>
						<c:forEach var="answer" items="${listAnswer}" varStatus="theCountAnswer">
							<c:if test="${answer.questionId==question.questionId}">
								<tr>
									<td></td>
									<td class="td2col">
										<c:out value="${answer.content}" />
									</td>
									<td>
										<input type="checkbox" name="${answer.answerId}">
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
					<tr>
						<td/>
						<td>
							<button type="submit">Submit</button>
						</td>
					</tr>
				</table>
			</div>	
		</form>
	</body>
</html>
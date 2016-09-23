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
		top: 20px;
		left: 50px;
		font-size: 25px;
		font-weight: bold;
		font-style: italic;
	}
	#box1{
		position:absolute;
		left:500px;
		width:400px;
		top: 20px;
		font-size: 30px;
		font-weight: bolder;
		text-align: center;
	}
	#box3{
		position:absolute;
		top: 60px;
		left:500px;
	}
	#box4{
		position:absolute;
		top: 500px;
		left:500px;
	}
	.td1col{
		width:100px;
		font-size: 16px;
		font-weight: bold;
		font-style: italic;
		text-align: center;
	}
	button{
		font-size: 14px;
		font-weight: bold;
	}
</style>
<title>Insert title here</title>
</head>
<body class="main">
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
	
	<c:if test="${empty sessionScope.test.name}">
		<form action="controller" method="GET">
			<input type="hidden" name="command" value="addQuestion"/>
	<div id="box3">
		<table>
			<tr>
				<td class="td1col">
					<label for="nametest">Input name of the test</label>
				</td>
				<td>
					<input type="text" name="nametest" id="nametest"/>
				</td>
		
			</tr>
			<tr>
				<td></td>
				<td>
					<button type="submit">Add question</button>
				</td>
			</tr>
		</table>
	</div>	
		</form>
	</c:if>
	<c:if test="${not empty sessionScope.test.name}">
	<form method="GET" action="controller">
		<input type="hidden" name="command" value="savetest"/>
		<div id="box3">
		<table>
			<tr>
				<td class="td1col">
					<label for="nametest">Name test: </label>
				</td>
				<td>
					<c:choose>
						<c:when test="${sessionScope.change}">
							<input type="text" value="${sessionScope.test.name}" name="changeName"/>
						</c:when>
						<c:otherwise>
							<c:out value="${sessionScope.test.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td></td>
			</tr>
		<c:forEach var="question" items="${listQuestion}" varStatus="theCountQuestion">
			<tr>
			
				<td class="td1col">
					<c:out value="Question ${theCountQuestion.count}"></c:out>
				</td>
				<td>
					<c:choose>
						<c:when test="${sessionScope.change}">
						<input type="text" value="${question.content}" name="${question.questionId}"/>
						</c:when>
						<c:otherwise>
						<c:out value="${question.content}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="td1col">
					<c:url var="deleteUrl" value="/controller">
						<c:param name="idQuestion" value="${question.questionId}"/>
						<c:param name="command" value="deleteQuestion"/>
					</c:url>
					<a href="${deleteUrl}">delete</a>
				</td>
			</tr>
			<c:forEach var="answer" items="${listAnswer}" varStatus="theCount">
				<tr>
				
					<c:if test="${answer.questionId==question.questionId}">
					<td></td>
						<td>
							<c:choose>
								<c:when test="${change}">
									<input type="text" value="${answer.content}" name="${answer.answerId}">
								</c:when>
								<c:otherwise>
									<c:out value="${answer.content}" />
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${answer.rightAnswer}">
									<input type="checkbox" checked name="checkbox${answer.answerId}"/>
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="checkbox${answer.answerId}"/>
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:forEach>
		<tr>
			<td></td>
			<td>
				<button type="submit">Cохранить тест</button>
			</td>
		</tr>
	</table>
	</div>
	</form>
	
	<form action="controller" method="POST">
			<input type="hidden" name="command" value="addQuestion"/>
		<div id="box4">
		<table>
		
			<tr>
				<td></td>
				<td>
				<button type="submit">Add question</button>
				</td>
			</tr>
			
		</table>
		</div>
		</form>
	</c:if>
	
</body>
</html>
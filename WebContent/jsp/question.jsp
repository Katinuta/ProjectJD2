<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
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
			top: 60px;
			left:500px;
		}
		.td1col{
			width:100px;
			font-size: 20px;
			font-weight: bold;
			font-style: italic;
			text-align: center;
		}
		button{
			font-size: 14px;
			font-weight: bold;
		}
		</style>
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
		<form action="controller" method="GET">
		<input type="hidden" name="command" value="addanswers">
			<div id="box3">
				<table>
					<tr >
						<td class="td1col">
							<label for="question">Question: </label>
						</td>
						<td colspan="2">
							<textarea id="question" name="question" cols="30" rows="5"></textarea><br/>
						</td>
					</tr>
					<tr>
						<td class="td1col">
							<label for="answer">Answers: </label>
						</td>
						
					</tr>
					<tr>
						<td></td>
						<td>
							<input id="answer" type="text" name="answer1"/>
						</td>
						<td>
							<input type="checkbox" name="checkbox1">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input id="answer" type="text" name="answer2"/>
						</td>
						<td>
							<input type="checkbox" name="checkbox2">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input id="answer" type="text" name="answer3"/>
						</td>
						<td>
							<input type="checkbox" name="checkbox3">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input id="answer" type="text" name="answer4"/>
						</td>
						<td>
							<input type="checkbox" name="checkbox4">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input id="answer" type="text" name="answer5"/>
						</td>
						<td>
							<input type="checkbox" name="checkbox5">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<button type="submit">Save</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
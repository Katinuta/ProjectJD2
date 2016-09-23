<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>Registration</title>
	<style type="text/css">
		.main {
			background:  url("jsp/img/main_1.jpg")  ;
			background-repeat: no-repeat;
			background-position:center center;
			height: 100vh;
			background-size: 100%;
		}
		#box3{
			position:absolute;
			top: 80px;
			left:500px;
			width:600px;
		}
		.td1col{
			width:100px;
			font-size: 24px;
			font-weight: bold;
			font-style: italic;
			text-align: left
		}
		.td2col{
			width:250px;
		}
		legend{
			font-size: 30px;
			font-weight: bolder;
			text-align: center;
		}
		button{
			font-size: 20px;
		}
		input{
			width:250px;
			height: 25px;
		}
	</style>
</head>
	<body class="main">
		<form action="controller" method="POST">
			<input type="hidden" name="command" value="registration"/>
			<div id="box3">
				<fieldset  style="width: 60%">
					<legend>Input your information </legend>
					<table>
						<tr>
							<td class="td1col">
								<label for="name">Name: </label>
							</td>
							<td class="td2col">
								<input type="text" id="name" name="name" placeholder="Name" required pattern="^[a-zA-Zа-яА-Я]+$">
							</td>
						</tr>
						<tr>
							<td class="td1col">
								<label for="surname">Surname:</label>
							</td>
							<td class="td2col">
								<input type="text" id="surname" name="surname" placeholder="Surname" required pattern="^[a-zA-Zа-яА-Я]+$">
							</td>
						</tr>
						<tr>
							<td class="td1col">
								<label for="login">Login:</label>
							</td>
							<td class="td2col">
								<input type="text" id="login" name="login" placeholder="Login" required>
							</td>
						</tr>
						<tr>
							<td class="td1col">
								<label for="password">Password:</label>
							</td>
							<td class="td2col">
								<input type="password" id="password" name="password" placeholder="Password" required>
							</td>
						</tr>
						<tr class="td1col">
							<td>${errorRegMessage}</td>
							<td>
								<button type="submit" name="savebutton">Book</button>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login</title>
		<style>
			.block { 
			    width: 260px; 
				height: 110px;
			    background:  #e0e0d1;
			    border: solid 1px black; 
				font-size: 20px;
				margin-left: 35%;
				margin-right: 40%; 
				padding: 15px;
				margin-top:15%;
			   }
			  #but{
				width: 120px;
				height: 25px;
			  }
			   font{
			  	 font-size:20px;
			   }
			   #forbut{
			   	font-size:16px;
			   }
		</style>
	</head>
	<body>
		<div>
			<div class="block">
				<form action="controller" method="POST" >
					<input type="hidden" name="command" value="Login" />				
					<table>
						<tr>
							<td>
								<label for="login"><font>Login</font></label>
							</td>
							<td>
								<input type="text" id="login" name="login" required >
							</td>
						</tr>
						<tr>
							<td>
								<label for="password"><font>Password</font></label>
								</td>
							<td>
								<input type="password" id="password" name="password" required >
							</td>
						</tr>
						<tr >
							<td>${errorLoginMessage}</td>
							<td >
								<button id="but" type="submit" ><font id="forbut">Sign in</font></button>
							</td>
						</tr>
						<tr>
							<td/>
							<td>
								<c:url var="reg" value="/controller">
									<c:param name="command" value="openform"/>
								</c:url>
								<a href="${reg}">Registration</a>
							</td>
						</tr>
					</table>
				</form>
			</div>	
		</div>	
	</body>
</html>
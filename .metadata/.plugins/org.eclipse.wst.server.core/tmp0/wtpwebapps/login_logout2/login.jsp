<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 로그인 </h1>
	<form action= "LoginServlet" method="post">
		<label for = "ID"> 아이디 </label>
		<input type="text" id="ID" name="ID" required><br>
		
		<label for="PASSWORD"> 비밀번호 </label>
		<input type="password" id="PASSWORD" name="PASSWORD" required><br>
		
		<input type="submit" value="로그인">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>홈페이지에 오신걸 환영합니다.</h1>
<!-- 만약에 로그인인 상태일경우 로그인 버튼을 숨기고 싶고
	로그아웃 버튼이 보이게 하고 싶을 경우
 -->
 <%
 	if (session.getAttribute("PASSWORD") != null){
 %>
 	<a href="logout.jsp">로그아웃</a>
 <%
 	} else{
 %>
	<a href="login.jsp">로그인</a>
 <%
	}
 %>
 
 <%
   String loginError = (String) request.getAttribute("loginError");

 	if(loginError != null) {
 %>
 <script>
 	displayLoginFail();
 </script>

<%
 	}
%>
</body>
</html>
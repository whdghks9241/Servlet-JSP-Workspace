<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>ȸ�������� ����!</h1>
<H2> ȸ������ ���� : </H2>
<P> ȸ����ȣ : <%= session.getAttribute("mno") %></P>
<p> �̸� : <%= session.getAttribute("mname") %></p>
<p> �̸��� : <%= session.getAttribute("memail")  %></p>
<p> ���� ���� : <%= session.getAttribute("mbirth") %></p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="com.kh.product.Product"%>
<%@ page import="com.kh.product.ProductDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>��ǰ �� ����</h1>
<%
	// String = id ���� �������ڴ�.
	String productIdValue = request.getParameter("productId");

	int productId = Integer.parseInt(productIdValue);
	
	ProductDAO productDAO = new ProductDAO();
	Product products = productDAO.getProductId(productId);
%>

<p> ��ǰ ID : <%= products.getProductId() %></p>
<p> ��ǰ�� : <%= products.getProductName() %></p>
<p> ī�װ��� : <%= products.getCategory() %></p>
<p> ���� : <%= products.getProductId() %></p>
<p> ���� : <%= products.getStockQuantity() %></p>

</body>
</html>
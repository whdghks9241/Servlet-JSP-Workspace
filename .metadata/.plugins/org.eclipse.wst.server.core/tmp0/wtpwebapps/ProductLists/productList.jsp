<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.kh.product.Product"%>
<%@ page import="com.kh.product.ProductDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ���</title>
</head>
<body>
	<h1>��ǰ���</h1>
	<table border ="1">
		<tr>
			<th> ���� ID </th>
			<th> ���ĸ� </th>
			<th> ī�װ��� </th>
			<th> ���� </th>
			<th> ���� </th>
		</tr>
		<%
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.getAllProducts();
			
			for (Product p : products) {
				
				%>
				
				<tr>
					<td><a href="productDetail.jsp?productId=<%=p.getProductId() %>"><%=p.getProductId() %> </a></td>
					<td><%=p.getProductName() %> </td>
					<td><%=p.getCategory() %> </td>
					<td><%=p.getPrice() %> </td>
					<td><%=p.getStockQuantity() %> </td>
				</tr>
				<% 
			}
		
		%>
		
	</table>
</body>
</html>

<!-- 

	<ul>
		  < %
			// ��ǰ ����� ArrayList�� ����
			// ArrayList<Product> products = new ArrayList<>();
			// products.add(new Product(1, "��Ʈ��", "������ǰ", 900.00, 10));
			// products.add(new Product(2, "��Ʈ��", "������ǰ", 900.00, 10));
			
			ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
			
			// for���� Ȱ���ؼ� ��ǰ����� �ݺ��ؼ� ǥ���� �� ����
			for (Product p : products) {
			%>
				<li> < %= p.getProductName() %> �� ���� < %=p.getPrice() %></li>	
			< %
			} 
			% >
	</ul>

 -->
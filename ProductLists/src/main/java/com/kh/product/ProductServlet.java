package com.kh.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khcafe";
	private static final String jdbcPassword = "khcafe";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 해당 방식으로도실행 가능
			// Class.forName("oracle.jdbc.dirver.OracleDriver");
			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
			// SQL 쿼리
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			// 제품 목록을 저장할 장바구니 같은 ArrayList;
			ArrayList<Product> productList = new ArrayList<>();	
			
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String category = rs.getString("category");
				double price = rs.getDouble("price");
				int stock_quantity = rs.getInt("stock_quantity");
				
				Product product = new Product(productId, productName, category, price, stock_quantity);
				// productList : 제품들을 하나씩 add 해서 넣어줌
				productList.add(product);
			}
			
			// JSP 제품목록 페이지로 제품 모곩을 전달하자
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/productList.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

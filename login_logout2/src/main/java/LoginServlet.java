

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "SM";
		String jdbcPassword = "SM1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("여와지냐?");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			// 로그인시 사용되는 아이디
			// 로그인시 사용되는 비밀번호
			String ID = request.getParameter("ID");
			String PASSWORD = request.getParameter("PASSWORD");

			System.out.println("ID : " + ID);
			System.out.println("PASSWORD : " + PASSWORD);
			// select 일치하는 유저가 있는지 체크
			String sql = "SELECT * FROM  USERINFO WHERE  ID = ? AND PASSWORD = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			System.out.println("sql : " + sql); ;
			preparedStatement.setString(1, ID);
			preparedStatement.setString(2, PASSWORD);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("size : " + resultSet.getRow()); ;
			
			// 로그인을 하는 유저는 1명이기 때문에 if 문을 활용하여 하나의 유저만 조회한다.
			if (resultSet.next()) {

				System.out.println("성공");
				// 일치하는 값이 존재하면 로그인 성공에 따라 정보를 가지고 옴
				// HttpSession - 웹에서 클라이언트와 서버 간에 정보를 유지하고 공유하는데 사용
				
				// 현재 http 요청에 대한 세션을 가져옴
				// 처음 http session 요청이 처음 올 때 세션이 없으면 새로운 세션을 생성하고 이미 세션이 존재하면 해당 세션을 가지고 옴
				HttpSession session = request.getSession();
				session.setAttribute("USER_ID", resultSet.getInt("USER_ID"));
				session.setAttribute("ID", resultSet.getString("ID"));
				session.setAttribute("PASSWORD", resultSet.getString("PASSWORD"));
				session.setAttribute("PHONE_NUMBER", resultSet.getString("PHONE_NUMBER"));
				session.setAttribute("EMAIL", resultSet.getString("EMAIL"));
				session.setAttribute("ADDRESS", resultSet.getString("ADDRESS"));

				// 로그인 시간을 30부ㄴ으로 지정
				// Inactive : 비활성화 활동하지 않는상태
				//interval = 간격
				session.setMaxInactiveInterval(1800);
				
				
				response.sendRedirect("login_success.jsp");
			
			} else {
				System.out.println("실패");
				// 만약에 로그인이 안된다면
				// 속성에 loginError 이름으로 속성을 저장하고, 로그인에러가 true로 설정을 했기 때문에 
				// 로그인 오류가 발생했음을 나타내는 속성이름과 속성값을 추가
				request.setAttribute("loginError", "true");
				// getRequestDispatcher(경로) :우리가 설정한 경로로 이동하기 위한 객체 반환
				// forward(request, response) : 현재 페이지에 실행이 중단됨
				// 지금까지 가지고 있는 데이터를 클라이언트한테 응답으로 보내고 결과를 표시함
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}



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
			
			System.out.println("��������?");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			// �α��ν� ���Ǵ� ���̵�
			// �α��ν� ���Ǵ� ��й�ȣ
			String ID = request.getParameter("ID");
			String PASSWORD = request.getParameter("PASSWORD");

			System.out.println("ID : " + ID);
			System.out.println("PASSWORD : " + PASSWORD);
			// select ��ġ�ϴ� ������ �ִ��� üũ
			String sql = "SELECT * FROM  USERINFO WHERE  ID = ? AND PASSWORD = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			System.out.println("sql : " + sql); ;
			preparedStatement.setString(1, ID);
			preparedStatement.setString(2, PASSWORD);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("size : " + resultSet.getRow()); ;
			
			// �α����� �ϴ� ������ 1���̱� ������ if ���� Ȱ���Ͽ� �ϳ��� ������ ��ȸ�Ѵ�.
			if (resultSet.next()) {

				System.out.println("����");
				// ��ġ�ϴ� ���� �����ϸ� �α��� ������ ���� ������ ������ ��
				// HttpSession - ������ Ŭ���̾�Ʈ�� ���� ���� ������ �����ϰ� �����ϴµ� ���
				
				// ���� http ��û�� ���� ������ ������
				// ó�� http session ��û�� ó�� �� �� ������ ������ ���ο� ������ �����ϰ� �̹� ������ �����ϸ� �ش� ������ ������ ��
				HttpSession session = request.getSession();
				session.setAttribute("USER_ID", resultSet.getInt("USER_ID"));
				session.setAttribute("ID", resultSet.getString("ID"));
				session.setAttribute("PASSWORD", resultSet.getString("PASSWORD"));
				session.setAttribute("PHONE_NUMBER", resultSet.getString("PHONE_NUMBER"));
				session.setAttribute("EMAIL", resultSet.getString("EMAIL"));
				session.setAttribute("ADDRESS", resultSet.getString("ADDRESS"));

				// �α��� �ð��� 30�Τ����� ����
				// Inactive : ��Ȱ��ȭ Ȱ������ �ʴ»���
				//interval = ����
				session.setMaxInactiveInterval(1800);
				
				
				response.sendRedirect("login_success.jsp");
			
			} else {
				System.out.println("����");
				// ���࿡ �α����� �ȵȴٸ�
				// �Ӽ��� loginError �̸����� �Ӽ��� �����ϰ�, �α��ο����� true�� ������ �߱� ������ 
				// �α��� ������ �߻������� ��Ÿ���� �Ӽ��̸��� �Ӽ����� �߰�
				request.setAttribute("loginError", "true");
				// getRequestDispatcher(���) :�츮�� ������ ��η� �̵��ϱ� ���� ��ü ��ȯ
				// forward(request, response) : ���� �������� ������ �ߴܵ�
				// ���ݱ��� ������ �ִ� �����͸� Ŭ���̾�Ʈ���� �������� ������ ����� ǥ����
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
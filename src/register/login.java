package register;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String execute() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs1 = null,rs2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			String sql="select PassWord from teacher where UserName = \'"+username + "\'";
			rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				String realPassword = rs1.getString("PassWord");
				if (realPassword.equals(password))
					return "SUCCESS";
				else
					return "ERRORNOMATCH";
			} 
			sql="select PassWord from student where UserName = \'"+username + "\'";
			rs2 = stmt.executeQuery(sql);
			if (rs2.next()) {
				String realPassword = rs2.getString("PassWord");
				if (realPassword.equals(password))
					return "SUCCESS";
				else
					return "ERRORNOMATCH";
			}
			return "ERROREXISTED";
		}catch(ClassNotFoundException ex) {
			ex.getMessage();
			ex.printStackTrace();
			return "ERROR";
		}catch(SQLException e) { 
			e.getSQLState();
			e.printStackTrace();
			return "ERROR";
		}finally {
			conn.close();
		}
	}
}

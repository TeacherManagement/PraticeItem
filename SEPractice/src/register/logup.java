package register;

//import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Map;
import com.opensymphony.xwork2.ActionContext;

public class logup {
	private String username;
	private String password;
	private String verifyPassword;
	private String identity;
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
	public String getVerifyPassword() {
		return verifyPassword;
	}
	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String execute() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs1 = null,rs2 = null;
		int exist = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			if (!password.equals(verifyPassword))
				return "ERRORNOMATCH";
			String sql = "select * from teacher where UserName = \'"+username+"\'";
			rs1 = stmt.executeQuery(sql);
			if (rs1.next())
				exist = 1;
			sql = "select * from student where UserName = \'"+username+"\'";
			rs2 = stmt.executeQuery(sql);
			if (rs2.next())
				exist = 1;
			if (exist == 1)
				return "ERROREXISTED";
			if (identity.equals("teacher")) {
				sql = "insert into teacher (UserName,PassWord) values (\'" + username + "\',\'" + password + "\')";
				stmt.execute(sql);
			}
			else {
				sql = "insert into student (UserName,PassWord) values (\'" + username + "\',\'" + password + "\')";
				stmt.execute(sql);
			}
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			java.util.Map<String, Object> session = actionContext.getSession();    //取得session
			session.put("username", username);       //把用户数据放入session
			/*String sql = "select * from book where  = \'"+updateBook+"\'";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				updateBookTitle = rs.getString("Title");
				updateBookAuthor = rs.getString("AuthorID");
				updateBookPublisher = rs.getString("Publisher");
				updateBookDate = rs.getString("PublishDate");
				updateBookPrice = rs.getString("Price");
			}*/
			if (identity.equals("teacher"))
				return "SUCCESS";
			else
				return "SUCCESSSTUDENT";
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

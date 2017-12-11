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
	//创建一个新的账户
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
				//将新用户的用户名和密码插入注册表
				sql = "insert into teacher (UserName,PassWord) values (\'" + username + "\',\'" + password + "\')";
				stmt.execute(sql);
				//老师有额外的行程表
				sql = "CREATE TABLE "+username+"Cal ("
						+ "CalID int unsigned primary key auto_increment,"
						+ "Date date not null,"
						+ "Time time not null,"
						+ "EndTime time not null,"
						+ "Bea varchar(200) not null,"
						+ "Released tinyint(4),"
						+ "Booked tinyint(4),"
						+ "BookUser varchar(20),"
						+ "BookName varchar(20),"
						+ "BookSchool varchar(20),"
						+ "BookDepartment varchar(20),"
						+ "BookMajor varchar(20) "
						+ ") charset=utf8;";
				//System.out.println(sql);
				if(0 != stmt.executeLargeUpdate(sql))
						System.out.println("something wrong!!");
				//老师有额外的荣誉奖励表
				sql = "CREATE TABLE "+username+"honor ("
						+"HonorID int unsigned primary key auto_increment,"
						+"Date varchar(15) not null,"
						+"HonorItem varchar(40) not null"
						+") charset=utf8;";
				if(0 != stmt.executeLargeUpdate(sql))
					System.out.println("something wrong!!");
				//老师还有额外的基金项
				sql = "CREATE TABLE "+username+"fund ("
						+"FundID int unsigned primary key auto_increment,"
						+"FundName varchar(45) not null,"
						+"FundSource varchar(45) not null,"
						+"FundDate date not null,"
						+"FundMoney varchar(20) not null"
						+") charset=utf8;";
				stmt.executeLargeUpdate(sql);
				//老师还有科研成果项
				sql = "CREATE TABLE "+username+"ach ("
						+"AchID int unsigned primary key auto_increment,"
						+"AchName varchar(45) not null,"
						+"AchSource varchar(45) not null,"
						+"AchStart date not null,"
						+"AchEnd date not null,"
						+"AchRole varchar(20) not null,"
						+"AchType varchar(20) not null,"
						+"AchMoney varchar(20),"
						+"AchState varchar(10)"
						+") charset=utf8;";
				if(0 != stmt.executeLargeUpdate(sql))
					System.out.println("something wrong!!");
			}
			else {
				sql = "insert into student (UserName,PassWord) values (\'" + username + "\',\'" + password + "\')";
				stmt.execute(sql);   //创建一个学生预约表
				sql = "CREATE TABLE "+username+"books ("
						+"BookID int unsigned primary key auto_increment,"//预约编号
						+"BookUser varchar(45) not null,"   //预约的老师的用户名
						+"BookName varchar(45),"     //老师的姓名
						+"BookDate date not null,"   //预约日期
						+"BookStart time not null,"    //预约开始时间
						+"BookEnd time not null,"     //预约结束时间
						+"BookItem varchar(200) not null"    //预约事项
						+") charset=utf8;";
				stmt.executeLargeUpdate(sql);
			}
			//将用户名存起来，以便以后取用
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			java.util.Map<String, Object> session = actionContext.getSession();    //取得session
			session.put("username", username);       //把用户数据放入session
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
	//封装
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
}

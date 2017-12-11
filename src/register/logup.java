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
	//����һ���µ��˻�
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
				//�����û����û������������ע���
				sql = "insert into teacher (UserName,PassWord) values (\'" + username + "\',\'" + password + "\')";
				stmt.execute(sql);
				//��ʦ�ж�����г̱�
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
				//��ʦ�ж��������������
				sql = "CREATE TABLE "+username+"honor ("
						+"HonorID int unsigned primary key auto_increment,"
						+"Date varchar(15) not null,"
						+"HonorItem varchar(40) not null"
						+") charset=utf8;";
				if(0 != stmt.executeLargeUpdate(sql))
					System.out.println("something wrong!!");
				//��ʦ���ж���Ļ�����
				sql = "CREATE TABLE "+username+"fund ("
						+"FundID int unsigned primary key auto_increment,"
						+"FundName varchar(45) not null,"
						+"FundSource varchar(45) not null,"
						+"FundDate date not null,"
						+"FundMoney varchar(20) not null"
						+") charset=utf8;";
				stmt.executeLargeUpdate(sql);
				//��ʦ���п��гɹ���
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
				stmt.execute(sql);   //����һ��ѧ��ԤԼ��
				sql = "CREATE TABLE "+username+"books ("
						+"BookID int unsigned primary key auto_increment,"//ԤԼ���
						+"BookUser varchar(45) not null,"   //ԤԼ����ʦ���û���
						+"BookName varchar(45),"     //��ʦ������
						+"BookDate date not null,"   //ԤԼ����
						+"BookStart time not null,"    //ԤԼ��ʼʱ��
						+"BookEnd time not null,"     //ԤԼ����ʱ��
						+"BookItem varchar(200) not null"    //ԤԼ����
						+") charset=utf8;";
				stmt.executeLargeUpdate(sql);
			}
			//���û������������Ա��Ժ�ȡ��
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			java.util.Map<String, Object> session = actionContext.getSession();    //ȡ��session
			session.put("username", username);       //���û����ݷ���session
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
	//��װ
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

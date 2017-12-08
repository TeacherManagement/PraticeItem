package register;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;

//import com.opensymphony.xwork2.ActionContext;


public class login {
	
	//登陆操作，根据用户名区分学生或老师身份
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
				{	
					ActionContext actionContext = ActionContext.getContext();   //取到struts容器
					java.util.Map<String, Object> session = actionContext.getSession();    //取得session
					session.put("username", username);       //把用户数据放入session
					getTeacherDbValue(conn);
					return "SUCCESS";
				}
				else
					return "ERRORNOMATCH";
			} 
			sql="select PassWord from student where UserName = \'"+username + "\'";
			rs2 = stmt.executeQuery(sql);
			if (rs2.next()) {
				String realPassword = rs2.getString("PassWord");
				if (realPassword.equals(password))
				{	
					ActionContext actionContext = ActionContext.getContext();   //取到struts容器
					java.util.Map<String, Object> session = actionContext.getSession();    //取得session
					session.put("username", username);       //把用户数据放入session
					
					getStudentDbValue(conn);
					return "SUCCESSstudent";
				}
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
	//老师或学生上传自己的头像
	public String upload() throws SQLException, ClassNotFoundException {
		ActionContext actionContext = ActionContext.getContext();   //取到struts容器
		Map<String, Object> session = actionContext.getSession();    //取得session
		username=(String) session.get("username");       //从session取得用户
		int t=1;
		Connection conn = null;
		Statement stmt=null;
		ResultSet rst=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			String sql = "select Name from teacher where UserName = '"+username+"';";
			rst = stmt.executeQuery(sql);
			
			if (rst.next())
				getTeacherDbValue(conn);
			else
			{
				getStudentDbValue(conn);
				t=0;
			}
		}finally {
			conn.close();
		}
		//System.out.println(photoFileName+":"+photoContentType);
        //普通字段：
        //上传字段：上传到某个文件夹。存到应用的images目录下
        String realPath = ServletActionContext.getServletContext().getRealPath("/images");
        //System.out.println(realPath);
        File directory = new File(realPath);
        if(!directory.exists()){
            directory.mkdirs();
        }
        try {
            FileUtils.copyFile(photo, new File(directory, username+".jpg"));
            if (t == 1)
            	return "SUCCESS";
            else
            	return "sSUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

	}
	//以下为与老师有关的操作，可以依据函数名判断函数实现的功能
	public String editTeacherBasic() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "update teacher set Name=\'"+name+"\',School=\'";
			sql += school+"\',Address=\'";
			sql += address+"\',Department=\'";
			sql += department+"\',Major=\'";
			sql += major+"\',Telephone=\'";
			sql += telephone+"\',Homepage=\'";
			sql += homepage+"\' where UserName = \'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			if (sex.equals("male"))
				sql = "update teacher set Gender=1 where UserName=\'"+username+"\'";
			else if (sex.equals("female"))
				sql = "update teacher set Gender=0 where UserName=\'"+username+"\'";
			else
				sql = "";
			if (!sql.equals(""))
				stmt.executeUpdate(sql);
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String editTeacherHonor() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql;
			//String sql = "update teacher set AcademicTitle =\"" + MyHonor + "\" where UserName=\'"+username+"\'";
			//System.out.println(sql);
			//stmt.executeUpdate(sql);
			
			if (SciAcademician != null && SciAcademician.equals("SA"))
				sql = "update teacher set SciAcademician=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set SciAcademician=0 where UserName=\'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			if (EngAcademician != null && EngAcademician.equals("EA"))
				sql = "update teacher set EngAcademician=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set EngAcademician=0 where UserName=\'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);

			if (YangtzeScholor != null && YangtzeScholor.equals("YS"))
				sql = "update teacher set YangtzeScholor=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set YangtzeScholor=0 where UserName=\'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			if (DrSupvisor != null && DrSupvisor.equals("DS"))
				sql = "update teacher set DrSupervisor=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set DrSupervisor=0 where UserName=\'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String addTeacherHonor() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "INSERT INTO "+username+"honor (Date,HonorItem) values('"
						+honorDate+"','"+honorItem+"');";
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String changeTeacherHonor() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "UPDATE "+username+"honor set Date='"
						+honorDate+"',HonorItem='"
						+honorItem+"' where HonorID='"+honorID+"';";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String delTeacherHonor() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "DELETE FROM "+username+"honor WHERE HonorID='"+honorID+"';";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	//老师向自己的行程表中添加新的行程
 	public String editTeacherCal() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "INSERT INTO "+username+"cal (Date,Time,EndTime,Bea) VALUES (\'"
						+newDate+"\',\'"
						+newTime+"\',\'"
						+newEndTime+"\',\'"
						+newBea+"\');";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String deleteTeacherCal() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//删除教师行程表中指定id的行程
			String sql = "delete from "+username+"cal where CalID='"+calID+"';";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String releaseTeacherCal() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//发布教师行程表中指定id的行程
			String sql = "update "+username+"cal set Released = '1' where CalID = '"+calID+"';";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String cancelrelTeacherCal() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//发布教师行程表中指定id的行程
			String sql = "update "+username+"cal set Released = '0' where CalID = '"+calID+"';";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String acceptTeacherCal() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//发布教师行程表中指定id的行程
			String sql = "update "+username+"cal set Booked = '1' where CalID = '"+calID+"';";
			stmt.executeUpdate(sql);
			sql = "select * from "+username+"cal where CalID = '"+calID+"';";
			rs = stmt.executeQuery(sql);
			String bookuser=null,bookdate=null,bookstart=null;
			String bookend=null,bookitem=null;
			if(rs.next()) {
				bookuser = rs.getString("BookUser");
				bookdate = rs.getString("Date");
				bookstart = rs.getString("Time");
				bookend = rs.getString("EndTime");
				bookitem = rs.getString("Bea");
			}
			sql = "update "+bookuser+"books set BookUser = '1' where BookDate = '"
					+bookdate+"' and BookStart='"
					+bookstart+"' and BookEnd='"
					+bookend+"' and BookItem = '"
					+bookitem+"';";
			stmt.executeUpdate(sql);
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String rejectTeacherCal() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//发布教师行程表中指定id的行程
			String sql = "update "+username+"cal set Booked = '0' where CalID = '"+calID+"';";
			stmt.executeUpdate(sql);
			sql = "select * from "+username+"cal where CalID = '"+calID+"';";
			rs = stmt.executeQuery(sql);
			String bookuser=null,bookdate=null,bookstart=null;
			String bookend=null,bookitem=null;
			if(rs.next()) {
				bookuser = rs.getString("BookUser");
				bookdate = rs.getString("Date");
				bookstart = rs.getString("Time");
				bookend = rs.getString("EndTime");
				bookitem = rs.getString("Bea");
			}
			sql = "update "+bookuser+"books set BookUser = '0' where BookDate = '"
					+bookdate+"' and BookStart='"
					+bookstart+"' and BookEnd='"
					+bookend+"' and BookItem = '"
					+bookitem+"';";
			stmt.executeUpdate(sql);
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String batchCalDelete() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//批量删除教师行程表中指定id的行程
			for (int i = 0;i < batchCals.size();i++)
			{
				String sql = "delete from "+username+"cal where CalID='"+batchCals.get(i)+"';";
				stmt.executeUpdate(sql);
			}
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String batchCalRelease() throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//批量发布教师行程表中指定id的行程
			for (int i = 0;i < batchCals.size();i++)
			{
				String sql = "update "+username+"cal set Released = '1' where CalID = '"+batchCals.get(i)+"';";
				stmt.executeUpdate(sql);
			}
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String batchCalCanRel() throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//批量取消发布教师行程表中指定id的行程
			for (int i = 0;i < batchCals.size();i++)
			{
				String sql = "update "+username+"cal set Released = '0' where CalID = '"+batchCals.get(i)+"';";
				stmt.executeUpdate(sql);
			}
			getTeacherDbValue(conn);
			return "SUCCESS";
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
 	public String editTeacherFund() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新教师基金表中特定基金项目
			String sql = "UPDATE "+username+"fund SET FundName='"
						+fundName+"',FundSource = '"
						+fundSource+"',FundDate = '"
						+fundDate+"',FundMoney = '"
						+fundMoney+"' where FundID = '"+fundID+"';";
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
 	public String delTeacherFund() throws SQLException {
 		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//删除教师基金表中特定基金项目
			String sql = "DELETE FROM "+username+"fund where FundID = '"+fundID+"'";
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
 	public String addTeacherFund() throws SQLException {
 		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//向教师基金表中添加特定基金项目
			String sql = "INSERT INTO "+username+"fund ("
						+"FundName,FundSource,FundDate,FundMoney) values ('"
						+fundName+"','"
						+fundSource+"','"
						+fundDate+"','"
						+fundMoney+"');";
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String delTeacherAch() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//删除教师基金表中特定基金项目
			String sql = "DELETE FROM "+username+"ach where AchID = '"+achID+"'";
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
 	public String editTeacherAch() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "UPDATE "+username+"ach set AchName='"
						+achName+"',AchSource='"
						+achSource+"',AchStart='"
						+achStart+"',AchEnd='"
						+achEnd+"',AchRole='"
						+achRole+"',AchType='"
						+achType+"',AchMoney='"
						+achMoney+"',AchState='"
						+achState+"' where AchID = '"+achID+"';";
			stmt.executeUpdate(sql);
			
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String addTeacherAch() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//向教师项目表添加新的项目
			String sql = "INSERT INTO "+username+"ach (AchName,AchSource,AchStart,"
					+"AchEnd,AchType,AchRole,AchMoney,AchState) VALUES ('"
					+achName+"','"
					+achSource+"','"
					+achStart+"','"
					+achEnd+"','"
					+achType+"','"
					+achRole+"','"
					+achMoney+"','"
					+achState+"');";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			getTeacherDbValue(conn);
			return "SUCCESS";
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
	public String showStudentPage() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
		    stmt = conn.createStatement();
		  //将被访问的学生存起来，以便以后取用
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			java.util.Map<String, Object> session = actionContext.getSession();    //取得session
			session.put("visited", StudentUser);       //把用户数据放入session
			visited=StudentUser;
			String sql = "select * from student where UserName = \'"+StudentUser+"\'";
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				name = rs.getString("Name");
				if (name == null || name.equals(""))
					name="暂未填写";
				
				school = rs.getString("School");
				if (school == null || school.equals(""))
					school="暂未填写";
				
				sex = rs.getString("Gender");
				if (sex == null)
					sex = "暂未填写";
				else if (sex.equals("1"))
					sex = "男";
				else
					sex = "女";
				
				address = rs.getString("Address");
				if (address == null || address.equals(""))
					address="暂未填写";
				
				department = rs.getString("Department");
				if (department == null || department.equals(""))
					department="暂未填写";
				
				major = rs.getString("Major");
				if (major == null || major.equals(""))
					major="暂未填写";
				
				telephone = rs.getString("Telephone");
				if (telephone == null || telephone.equals(""))
					telephone="暂未填写";
				sql = "select * from "+visited+"books";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					AllLea.add(rs.getString("BookID"));
					AllLea.add(rs.getString("BookUser"));
					AllLea.add(rs.getString("BookName"));
					AllLea.add(rs.getString("BookDate"));
					AllLea.add(rs.getString("BookStart"));
					AllLea.add(rs.getString("BookEnd"));
					AllLea.add(rs.getString("BookItem"));
				}
			}
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
		return "SUCCESS";
	}
	private void getTeacherDbValue(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = conn.createStatement();
		String sql="";
		sql="select * from teacher where UserName = \'"+username+"\'";
		rs = stmt.executeQuery(sql);
		rs.next();
		name = rs.getString("Name");
		if (name == null || name.equals(""))
			name="暂未填写";
		
		school = rs.getString("School");
		if (school == null || school.equals(""))
			school="暂未填写";
		
		sex = rs.getString("Gender");
		if (sex == null)
			sex = "暂未填写";
		else if (sex.equals("1"))
			sex = "男";
		else
			sex = "女";
		
		address = rs.getString("Address");
		if (address == null || address.equals(""))
			address="暂未填写";
		
		department = rs.getString("Department");
		if (department == null || department.equals(""))
			department="暂未填写";
		
		major = rs.getString("Major");
		if (major == null || major.equals(""))
			major="暂未填写";
		
		telephone = rs.getString("Telephone");
		if (telephone == null || telephone.equals(""))
			telephone="暂未填写";
		
		homepage = rs.getString("Homepage");
		if (homepage == null || homepage.equals(""))
			homepage="暂未填写";
		
		if (rs.getBoolean("SciAcademician"))
			SciAcademician = "科学院院士";
		else
			SciAcademician = "";
		
		if (rs.getBoolean("EngAcademician"))
			EngAcademician = "工程院院士";
		else
			EngAcademician = "";
		
		if (rs.getBoolean("YangtzeScholor"))
			YangtzeScholor = "长江学者";
		else
			YangtzeScholor = "";
		if (rs.getBoolean("DrSupervisor"))
			DrSupvisor = "博士生导师";
		else
			DrSupvisor = "";
		//从老师的行程表中取出所有行程
		ALLCal.clear();
		sql = "select * from "+username+"cal";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ALLCal.add(rs.getString("Date"));
			ALLCal.add(rs.getString("Time"));
			ALLCal.add(rs.getString("Bea"));
			ALLCal.add(rs.getString("Released"));
			ALLCal.add(rs.getString("Booked"));
			ALLCal.add(rs.getString("EndTime"));
			ALLCal.add(rs.getString("BookUser"));
			ALLCal.add(rs.getString("BookName"));
			ALLCal.add(rs.getString("BookSchool"));
			ALLCal.add(rs.getString("BookDepartment"));
			ALLCal.add(rs.getString("BookMajor"));
			AllCalID.add(rs.getString("CalID"));
		}
		//取出老师所有的荣誉奖励
		MyHonor.clear();
		sql = "select * from "+username+"honor";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			MyHonor.add(rs.getString("HonorID"));
			MyHonor.add(rs.getString("Date"));
			MyHonor.add(rs.getString("HonorItem"));
		}
		//System.out.println(MyHonor);
		//查询老师所有的基金
		//AllFund.clear();
		sql = "select * from "+username+"fund;";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			AllFund.add(rs.getString("FundID"));
			AllFund.add(rs.getString("FundName"));
			AllFund.add(rs.getString("FundSource"));
			AllFund.add(rs.getString("FundDate"));
			AllFund.add(rs.getString("FundMoney"));
		}
		AllFund.get(1);
		//System.out.println(AllFund);
		//查询老师所有的科研项目
		sql = "select * from "+username+"ach";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			AllAch.add(rs.getString("AchID"));
			AllAch.add(rs.getString("AchName"));
			AllAch.add(rs.getString("AchSource"));
			AllAch.add(rs.getString("AchStart"));
			AllAch.add(rs.getString("AchEnd"));
			AllAch.add(rs.getString("AchRole"));
			AllAch.add(rs.getString("AchType"));
			AllAch.add(rs.getString("AchMoney"));
			AllAch.add(rs.getString("AchState"));
		}
	}

	//以上为与老师有关的操作
	
	
	//以下为与学生有关的操作
	public String editStudentBasic() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "update student set Name=\'"+name+"\',School=\'";
			sql += school+"\',Address=\'";
			sql += address+"\',Department=\'";
			sql += department+"\',Major=\'";
			sql += major+"\',Telephone=\'";
			sql += telephone+"\' where UserName = \'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			if (sex.equals("male"))
				sql = "update student set Gender=1 where UserName=\'"+username+"\'";
			else if (sex.equals("female"))
				sql = "update student set Gender=0 where UserName=\'"+username+"\'";
			else
				sql = "";
			if (!sql.equals(""))
				stmt.executeUpdate(sql);
			getStudentDbValue(conn);
			return "SUCCESS";
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
	private void getStudentDbValue(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = conn.createStatement();
		String sql=null;
		sql="select * from student where UserName = \'"+username+"\'";
		rs = stmt.executeQuery(sql);
		rs.next();
		name = rs.getString("Name");
		if (name == null || name.equals(""))
			name="暂未填写";
		
		school = rs.getString("School");
		if (school == null || school.equals(""))
			school="暂未填写";
		
		sex = rs.getString("Gender");
		if (sex == null)
			sex = "暂未填写";
		else if (sex.equals("1"))
			sex = "男";
		else
			sex = "女";
		
		address = rs.getString("Address");
		if (address == null || address.equals(""))
			address="暂未填写";
		
		department = rs.getString("Department");
		if (department == null || department.equals(""))
			department="暂未填写";
		
		major = rs.getString("Major");
		if (major == null || major.equals(""))
			major="暂未填写";
		
		telephone = rs.getString("Telephone");
		if (telephone == null || telephone.equals(""))
			telephone="暂未填写";
		sql = "select * from "+username+"books";
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			AllLea.add(rs.getString("BookID"));
			AllLea.add(rs.getString("BookUser"));
			AllLea.add(rs.getString("BookName"));
			AllLea.add(rs.getString("BookDate"));
			AllLea.add(rs.getString("BookStart"));
			AllLea.add(rs.getString("BookEnd"));
			AllLea.add(rs.getString("BookItem"));
		}
	}
	public String editStudentLea() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户			
			//更新基本信息的数据库
			String sql = "update student set LearningDir =\"" + AllLea + "\" where UserName=\'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			getStudentDbValue(conn);
			return "SUCCESS";
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
	public String searchByName() throws SQLException {
		/*HttpServletRequest request;
		HttpSession session = request.getSession(); 
		session.setAttribute("temp", temp);	*/
		//System.out.println(exeNameSearch);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
		    stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户
			String sql = "select * from teacher where Name = \'"+teacherName+"\'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				NameList.add(rs.getString("UserName"));
				NameList.add(rs.getString("Name"));
				NameList.add(rs.getString("School"));
				NameList.add(rs.getString("Department"));
				NameList.add(rs.getString("Major"));
			}
			getStudentDbValue(conn);
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
		return "SUCCESS";
	}
	public String searchByFilter() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户
			
			
			//System.out.println("alert");
			String sql = "select * from teacher where ";
			int first=0;
			if (SciAcademician != null || EngAcademician != null || YangtzeScholor != null || DrSupvisor != null)
			{
				if (SciAcademician != null && SciAcademician.equals("SA")) {
					if (first == 0) {
						sql += "SciAcademician = '1' ";
						first = 1;
					}
					else
						sql += "and SciAcademician = '1' ";
				}
				if (EngAcademician != null && EngAcademician.equals("EA")) {
					if (first == 0) {
						sql += "EngAcademician = '1' ";
						first = 1;
					}
					else
						sql += "and EngAcademician = '1' ";
				}
				if (YangtzeScholor != null && YangtzeScholor.equals("YS")) {
					if (first == 0) {
						sql += "YangtzeScholor = '1' ";
						first = 1;
					}
					else
						sql += "and YangtzeScholor = '1' ";
				}
				if (DrSupvisor != null && DrSupvisor.equals("DS")) {
					if (first == 0) {
						sql += "DrSupervisor = '1' ";
						first = 1;
					}
					else
						sql += "and DrSupervisor = '1' ";
				}
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					FilterList.add(rs.getString("UserName"));
					FilterList.add(rs.getString("Name"));
					FilterList.add(rs.getString("School"));
					FilterList.add(rs.getString("Department"));
					FilterList.add(rs.getString("Major"));
				}
				//System.out.println(FilterList);
			}
			getStudentDbValue(conn);
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
		return "SUCCESS";
	}
	public String showTeacherPage() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
		    stmt = conn.createStatement();
		  //将被访问的老师存起来，以便以后取用
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			java.util.Map<String, Object> session = actionContext.getSession();    //取得session
			session.put("visited", requestParam);       //把用户数据放入session
			visited=requestParam;
			String sql = "select * from teacher where UserName = \'"+requestParam+"\'";
			//username = requestParam;
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				name = rs.getString("Name");
				if (name == null || name.equals(""))
					name="暂未填写";
				
				school = rs.getString("School");
				if (school == null || school.equals(""))
					school="暂未填写";
				
				sex = rs.getString("Gender");
				if (sex == null)
					sex = "暂未填写";
				else if (sex.equals("1"))
					sex = "男";
				else
					sex = "女";
				
				address = rs.getString("Address");
				if (address == null || address.equals(""))
					address="暂未填写";
				
				department = rs.getString("Department");
				if (department == null || department.equals(""))
					department="暂未填写";
				
				major = rs.getString("Major");
				if (major == null || major.equals(""))
					major="暂未填写";
				
				telephone = rs.getString("Telephone");
				if (telephone == null || telephone.equals(""))
					telephone="暂未填写";
				
				homepage = rs.getString("Homepage");
				if (homepage == null || homepage.equals(""))
					homepage="暂未填写";
				
				if (rs.getBoolean("SciAcademician"))
					SciAcademician = "科学院院士";
				else
					SciAcademician = "";
				
				if (rs.getBoolean("EngAcademician"))
					EngAcademician = "工程院院士";
				else
					EngAcademician = "";
				
				if (rs.getBoolean("YangtzeScholor"))
					YangtzeScholor = "长江学者";
				else
					YangtzeScholor = "";
				if (rs.getBoolean("DrSupervisor"))
					DrSupvisor = "博士生导师";
				else
					DrSupvisor = "";
			}
			//从老师的行程表中取出所有发布的行程
			ALLCal.clear();
			sql = "select * from "+requestParam+"cal where Released = '1'";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ALLCal.add(rs.getString("Date"));
				ALLCal.add(rs.getString("Time"));
				ALLCal.add(rs.getString("Bea"));
				//ALLCal.add(rs.getString("Released"));
				ALLCal.add(rs.getString("Booked"));
				ALLCal.add(rs.getString("EndTime"));
				AllCalID.add(rs.getString("CalID"));
			}
			MyHonor.clear();
			sql = "select * from "+requestParam+"honor";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MyHonor.add(rs.getString("HonorID"));
				MyHonor.add(rs.getString("Date"));
				MyHonor.add(rs.getString("HonorItem"));
			}
			//查询老师所有的基金
			sql = "select * from "+requestParam+"fund";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AllFund.add(rs.getString("FundID"));
				AllFund.add(rs.getString("FundName"));
				AllFund.add(rs.getString("FundSource"));
				AllFund.add(rs.getString("FundDate"));
				AllFund.add(rs.getString("FundMoney"));
			}
			//查询老师所有的科研项目
			sql = "select * from "+requestParam+"ach";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AllAch.add(rs.getString("AchID"));
				AllAch.add(rs.getString("AchName"));
				AllAch.add(rs.getString("AchSource"));
				AllAch.add(rs.getString("AchStart"));
				AllAch.add(rs.getString("AchEnd"));
				AllAch.add(rs.getString("AchRole"));
				AllAch.add(rs.getString("AchType"));
				AllAch.add(rs.getString("AchMoney"));
				AllAch.add(rs.getString("AchState"));
			}
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
		return "SUCCESS";
	}
	public String bookTeacher() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
		    stmt = conn.createStatement();
			//取得用户名
			ActionContext actionContext = ActionContext.getContext();   //取到struts容器
			Map<String, Object> session = actionContext.getSession();    //取得session
			username=(String) session.get("username");       //从session取得用户
			visited = (String) session.get("visited");
			rs = stmt.executeQuery("select * from student where UserName = '"+username+"';");
			String sn = null,ss=null,sd=null,sm=null;
			if (rs.next()) {
				sn=rs.getString("Name");
				ss=rs.getString("School");
				sd=rs.getString("Department");
				sm=rs.getString("Major");
			}
			String sql = "INSERT INTO "+visited+"cal (Date,Time,EndTime,Bea,"
						+"Booked,BookUser,BookName,BookSchool,BookDepartment,BookMajor)"
						+" values ('"+bookDate+"','"
						+bookStart+"','"
						+bookEnd+"','"
						+bookExtra+"','"
						+"0','"
						+username+"','"
						+sn+"','"
						+ss+"','"
						+sd+"','"
						+sm+"');";
			stmt.executeUpdate(sql);
			sql = "select Name from teacher where UserName = '"+visited+"';";
			rs = stmt.executeQuery(sql);
			if (rs.next())
				sn = rs.getString("Name");
			sql = "INSERT INTO "+username+"books (BookUser,BookName,BookDate,BookStart,"
					+"BookEnd,BookItem) VALUES ('"
					+"0','"
					+sn+"','"
					+bookDate+"','"
					+bookStart+"','"
					+bookEnd+"','"
					+bookExtra+"');";
			stmt.executeUpdate(sql);
			getStudentDbValue(conn);
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
		return "SUCCESS";
	}
	//登陆用到的变量
	private String username;
	private String visited;
	
	private String password;
	//老师或学生上传自己的头像
	private File photo;//和表单的上传字段名保持一致。类型是File类型的
	private String photoFileName;//上传的文件名
    private String photoContentType;//上传文件的MIME类型

	//基本信息用到的变量
	private String name;
	private String school;
	private String sex;
	private String address;
	private String department;
	private String major;
	private String telephone;
	private String homepage;
	//荣誉称号用到的变量
	private String SciAcademician;
	private String EngAcademician;
	private String YangtzeScholor;
	private String DrSupvisor;
	private ArrayList<String> MyHonor = new ArrayList<String>();//教师所有荣誉奖励的事项，包括其id值
	private String honorID;
	private String honorDate;
	private String honorItem;
	//行程日历用到的变量
	private ArrayList<String> ALLCal = new ArrayList<String>();
	private ArrayList<String> AllCalID = new ArrayList<String>();
	private String calID;
	private ArrayList<String> batchCals = new ArrayList<String>();//批量处理教师行程时用到的行程id
		//学生预约某一个老师
	private String bookDate;
	private String bookStart;
	private String bookEnd;
	private String bookExtra;
	private String StudentUser;//教师查看预约自己的学生用到的该学生用户名
	
	//基金用到的变量
	private ArrayList<String> AllFund = new ArrayList<String>();
	private String fundID;
	private String fundName;
	private String fundSource;
	private String fundDate;
	private String fundMoney;
	
	//科研成果用到的的变量
	private ArrayList<String> AllAch = new ArrayList<String>();
	private String achID;
	private String achName;
	private String achSource;
	private String achStart;
	private String achEnd;
	private String achType;
	private String achRole;
	private String achMoney;
	private String achState;
	
	//学生感兴趣的学习方向
	private ArrayList<String> AllLea = new ArrayList<String>();
	//学生依靠姓名检索教师
	private String teacherName;
	//不同检索方式
	private String exeNameSearch;
	private String exeFilterSearch;
	private ArrayList<String> NameList = new ArrayList<String>();
	private ArrayList<String> FilterList = new ArrayList<String>();
	private String requestParam;//
	private String newDate;//新添加日程的日期；
	private String newTime;//新添加行程的时间
	private String newEndTime;
	private String newBea;//新的行程
	
	public String getNewEndTime() {
		return newEndTime;
	}
	public void setNewEndTime(String newEndTime) {
		this.newEndTime = newEndTime;
	}
	public String getNewDate() {
		return newDate;
	}
	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}
	public String getNewTime() {
		return newTime;
	}
	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}
	public String getNewBea() {
		return newBea;
	}
	public void setNewBea(String newBea) {
		this.newBea = newBea;
	}
	public String getRequestParam() {
		return requestParam;
	}
	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
	public ArrayList<String> getFilterList() {
		return FilterList;
	}
	public void setFilterList(ArrayList<String> filterList) {
		FilterList = filterList;
	}
	public ArrayList<String> getNameList() {
		return NameList;
	}
	public void setNameList(ArrayList<String> nameList) {
		NameList = nameList;
	}
	public String getExeNameSearch() {
		return exeNameSearch;
	}
	public void setExeNameSearch(String exeNameSearch) {
		this.exeNameSearch = exeNameSearch;
	}
	public String getExeFilterSearch() {
		return exeFilterSearch;
	}
	public void setExeFilterSearch(String exeFilterSearch) {
		this.exeFilterSearch = exeFilterSearch;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public ArrayList<String> getAllLea() {
		return AllLea;
	}
	public void setAllLea(ArrayList<String> allLea) {
		AllLea = allLea;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVisited() {
		return visited;
	}
	public void setVisited(String visited) {
		this.visited = visited;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getSciAcademician() {
		return SciAcademician;
	}
	public void setSciAcademician(String sciAcademician) {
		SciAcademician = sciAcademician;
	}
	public String getEngAcademician() {
		return EngAcademician;
	}
	public void setEngAcademician(String engAcademician) {
		EngAcademician = engAcademician;
	}
	public String getYangtzeScholor() {
		return YangtzeScholor;
	}
	public void setYangtzeScholor(String yangtzeScholor) {
		YangtzeScholor = yangtzeScholor;
	}
	public String getDrSupvisor() {
		return DrSupvisor;
	}
	public void setDrSupvisor(String drSupvisor) {
		DrSupvisor = drSupvisor;
	}
	public void setMyHonor(ArrayList<String> myHonor) {
		MyHonor = myHonor;
	}
	public ArrayList<String> getMyHonor() {
		return MyHonor;
	}
	public String getHonorDate() {
		return honorDate;
	}
	public String getHonorID() {
		return honorID;
	}
	public void setHonorID(String honorID) {
		this.honorID = honorID;
	}
	public void setHonorDate(String honorDate) {
		this.honorDate = honorDate;
	}
	public String getHonorItem() {
		return honorItem;
	}
	public void setHonorItem(String honorItem) {
		this.honorItem = honorItem;
	}
	public ArrayList<String> getALLCal() {
		return ALLCal;
	}
	public void setALLCal(ArrayList<String> aLLCal) {
		ALLCal = aLLCal;
	}
	public String getStudentUser() {
		return StudentUser;
	}
	public void setStudentUser(String studentUser) {
		StudentUser = studentUser;
	}
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	public String getBookStart() {
		return bookStart;
	}
	public void setBookStart(String bookStart) {
		this.bookStart = bookStart;
	}
	public String getBookEnd() {
		return bookEnd;
	}
	public void setBookEnd(String bookEnd) {
		this.bookEnd = bookEnd;
	}
	public String getBookExtra() {
		return bookExtra;
	}
	public void setBookExtra(String bookExtra) {
		this.bookExtra = bookExtra;
	}
	public ArrayList<String> getBatchCals() {
		return batchCals;
	}
	public void setBatchCals(ArrayList<String> batchCals) {
		this.batchCals = batchCals;
	}
	public String getCalID() {
		return calID;
	}
	public void setCalID(String calID) {
		this.calID = calID;
	}
	public ArrayList<String> getAllCalID() {
		return AllCalID;
	}
	public void setAllCalID(ArrayList<String> allCalID) {
		AllCalID = allCalID;
	}
	public ArrayList<String> getAllFund() {
		return AllFund;
	}
	public void setAllFund(ArrayList<String> allFund) {
		AllFund = allFund;
	}
	public String getFundID() {
		return fundID;
	}
	public void setFundID(String fundID) {
		this.fundID = fundID;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getFundDate() {
		return fundDate;
	}
	public void setFundDate(String fundDate) {
		this.fundDate = fundDate;
	}
	public String getFundMoney() {
		return fundMoney;
	}
	public void setFundMoney(String fundMoney) {
		this.fundMoney = fundMoney;
	}
	public ArrayList<String> getAllAch() {
		return AllAch;
	}
	public void setAllAch(ArrayList<String> allAch) {
		AllAch = allAch;
	}
<<<<<<< HEAD
	public String getAchID() {
		return achID;
	}
	public void setAchID(String achID) {
		this.achID = achID;
	}
	public String getAchName() {
		return achName;
	}
	public void setAchName(String achName) {
		this.achName = achName;
	}
	public String getAchSource() {
		return achSource;
	}
	public void setAchSource(String achSource) {
		this.achSource = achSource;
	}
	public String getAchStart() {
		return achStart;
	}
	public void setAchStart(String achStart) {
		this.achStart = achStart;
	}
	public String getAchEnd() {
		return achEnd;
	}
	public void setAchEnd(String achEnd) {
		this.achEnd = achEnd;
	}
	public String getAchType() {
		return achType;
	}
	public void setAchType(String achType) {
		this.achType = achType;
	}
	public String getAchRole() {
		return achRole;
	}
	public void setAchRole(String achRole) {
		this.achRole = achRole;
	}
	public String getAchMoney() {
		return achMoney;
	}
	public void setAchMoney(String achMoney) {
		this.achMoney = achMoney;
	}
	public String getAchState() {
		return achState;
	}
	public void setAchState(String achState) {
		this.achState = achState;
	}
=======
	
	
>>>>>>> 37ac2dc64a14ff566e0cb55ed9f33d348b29688f
}

package teacherOp;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

import com.opensymphony.xwork2.ActionContext;

public class editBasicInfo {
	private String username;
	private String name;
	private String school;
	private String sex;
	private String address;
	private String department;
	private String major;
	private String telephone;
	private String homepage;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String execute() throws SQLException, UnsupportedEncodingException {
		/*HttpServletRequest req = null;
		HttpServletResponse resp = null;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");*/
		Connection conn = null;
		Statement stmt = null;
		/*ResultSet rs1 = null,rs2 = null;
		int exist = 0;*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sepractice?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			ActionContext actionContext = ActionContext.getContext();   //»°µΩstruts»›∆˜
			Map<String, Object> session = actionContext.getSession();    //»°µ√session
			username=(String) session.get("username");       //¥”session»°µ√”√ªß
			System.out.println(address);
			System.out.println(address);
			System.out.println(address);
			System.out.println(address);
			String sql = "update teacher set Name=\'"+name+"\',School=\'";
			sql += school+"\',Address=\'";
			sql += address+"\',Department=\'";
			sql += department+"\',Major=\'";
			sql += major+"\',Telephone=\'";
			sql += telephone+"\',Homepage=\'";
			sql += homepage+"\' where UserName = \'"+username+"\'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			if (sex.equals("male"))
				sql = "update teacher set Gender=1 where UserName=\'"+username+"\'";
			else if (sex.equals("female"))
				sql = "update teacher set Gender=0 where UserName=\'"+username+"\'";
			else
				sql = "";
			if (!sql.equals(""))
				stmt.executeUpdate(sql);

			if (name.equals(""))
				name="‘›Œ¥ÃÓ–¥";
			if (school.equals(""))
				school="‘›Œ¥ÃÓ–¥";
			if (department.equals(""))
				department="‘›Œ¥ÃÓ–¥";
			if (major.equals(""))
				major="‘›Œ¥ÃÓ–¥";
			if (address.equals(""))
				address="‘›Œ¥ÃÓ–¥";
			if (telephone.equals(""))
				telephone="‘›Œ¥ÃÓ–¥";
			if (homepage.equals(""))
				homepage="‘›Œ¥ÃÓ–¥";
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
}

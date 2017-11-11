package register;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

//import com.opensymphony.xwork2.ActionContext;


public class login {
	
	//登陆操作
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
	//以下为与老师有关的操作
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

			/*if (name.equals(""))
				name="暂未填写";
			if (school.equals(""))
				school="暂未填写";
			if (department.equals(""))
				department="暂未填写";
			if (major.equals(""))
				major="暂未填写";
			if (address.equals(""))
				address="暂未填写";
			if (telephone.equals(""))
				telephone="暂未填写";
			if (homepage.equals(""))
				homepage="暂未填写";*/
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
			String sql = "update teacher set AcademicTitle =\"" + MyHonor + "\" where UserName=\'"+username+"\'";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			
			if (SciAcademician != null && SciAcademician.equals("SA"))
				sql = "update teacher set SciAcademician=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set SciAcademician=0 where UserName=\'"+username+"\'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			if (EngAcademician != null && EngAcademician.equals("EA"))
				sql = "update teacher set EngAcademician=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set EngAcademician=0 where UserName=\'"+username+"\'";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			if (YangtzeScholor != null && YangtzeScholor.equals("YS"))
				sql = "update teacher set YangtzeScholor=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set YangtzeScholor=0 where UserName=\'"+username+"\'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			if (DrSupvisor != null && DrSupvisor.equals("DS"))
				sql = "update teacher set DrSupervisor=1 where UserName=\'"+username+"\'";
			else
				sql = "update teacher set DrSupervisor=0 where UserName=\'"+username+"\'";
			System.out.println(sql);
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
			String sql = "update teacher set Calendar =\"" + AllCal + "\" where UserName=\'"+username+"\'";
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
			//更新基本信息的数据库
			String sql = "update teacher set Fund =\"" + AllFund + "\" where UserName=\'"+username+"\'";
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
			String sql = "update teacher set SciAchievement =\"" + AllAch + "\" where UserName=\'"+username+"\'";
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

		MyHonor = rs.getString("AcademicTitle");
		if (MyHonor == null || MyHonor.equals(""))
			MyHonor = "暂未填写";
		
		AllCal = rs.getString("Calendar");
		if (AllCal == null || AllCal.equals(""))
			AllCal = "暂未填写";
		
		AllFund = rs.getString("Fund");
		if (AllFund== null || AllFund.equals(""))
			AllFund = "暂未填写";
		
		AllAch = rs.getString("SciAchievement");
		if (AllAch== null || AllAch.equals(""))
			AllAch = "暂未填写";
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
		
		AllLea = rs.getString("LearningDir");
		if (AllLea== null || AllLea.equals(""))
			AllLea = "暂未填写";
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
	public String searchByName() {
		
		return "SUCCESS";
	}
	public String searchByFilter() {
		
		return "SUCCESS";
	}
	//登陆用到的变量
	private String username;
	private String password;
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
	private String MyHonor;
	//行程日历用到的变量
	private String AllCal;
	//基金用到的变量
	private String AllFund;
	//科研成果用到的的变量
	private String AllAch;
	//学生感兴趣的学习方向
	private String AllLea;
	//学生依靠姓名检索教师
	private String teacherName;
	//不同检索方式
	private String exeNameSearch;
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
	private String exeFilterSearch;
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getAllLea() {
		return AllLea;
	}
	public void setAllLea(String allLea) {
		AllLea = allLea;
	}
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
	public String getMyHonor() {
		return MyHonor;
	}
	public void setMyHonor(String myHonor) {
		MyHonor = myHonor;
	}
	public String getAllCal() {
		return AllCal;
	}
	public void setAllCal(String allCal) {
		AllCal = allCal;
	}
	public String getAllFund() {
		return AllFund;
	}
	public void setAllFund(String allFund) {
		AllFund = allFund;
	}
	public String getAllAch() {
		return AllAch;
	}
	public void setAllAch(String allAch) {
		AllAch = allAch;
	}
}

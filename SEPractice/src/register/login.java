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
	
	//��½����
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
					ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
					java.util.Map<String, Object> session = actionContext.getSession();    //ȡ��session
					session.put("username", username);       //���û����ݷ���session
					
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
					ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
					java.util.Map<String, Object> session = actionContext.getSession();    //ȡ��session
					session.put("username", username);       //���û����ݷ���session
					
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
	//����Ϊ����ʦ�йصĲ���
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
				name="��δ��д";
			if (school.equals(""))
				school="��δ��д";
			if (department.equals(""))
				department="��δ��д";
			if (major.equals(""))
				major="��δ��д";
			if (address.equals(""))
				address="��δ��д";
			if (telephone.equals(""))
				telephone="��δ��д";
			if (homepage.equals(""))
				homepage="��δ��д";*/
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
			name="��δ��д";
		
		school = rs.getString("School");
		if (school == null || school.equals(""))
			school="��δ��д";
		
		sex = rs.getString("Gender");
		if (sex == null)
			sex = "��δ��д";
		else if (sex.equals("1"))
			sex = "��";
		else
			sex = "Ů";
		
		address = rs.getString("Address");
		if (address == null || address.equals(""))
			address="��δ��д";
		
		department = rs.getString("Department");
		if (department == null || department.equals(""))
			department="��δ��д";
		
		major = rs.getString("Major");
		if (major == null || major.equals(""))
			major="��δ��д";
		
		telephone = rs.getString("Telephone");
		if (telephone == null || telephone.equals(""))
			telephone="��δ��д";
		
		homepage = rs.getString("Homepage");
		if (homepage == null || homepage.equals(""))
			homepage="��δ��д";
		
		if (rs.getBoolean("SciAcademician"))
			SciAcademician = "��ѧԺԺʿ";
		else
			SciAcademician = "";
		
		if (rs.getBoolean("EngAcademician"))
			EngAcademician = "����ԺԺʿ";
		else
			EngAcademician = "";
		
		if (rs.getBoolean("YangtzeScholor"))
			YangtzeScholor = "����ѧ��";
		else
			YangtzeScholor = "";
		if (rs.getBoolean("DrSupervisor"))
			DrSupvisor = "��ʿ����ʦ";
		else
			DrSupvisor = "";

		MyHonor = rs.getString("AcademicTitle");
		if (MyHonor == null || MyHonor.equals(""))
			MyHonor = "��δ��д";
		
		AllCal = rs.getString("Calendar");
		if (AllCal == null || AllCal.equals(""))
			AllCal = "��δ��д";
		
		AllFund = rs.getString("Fund");
		if (AllFund== null || AllFund.equals(""))
			AllFund = "��δ��д";
		
		AllAch = rs.getString("SciAchievement");
		if (AllAch== null || AllAch.equals(""))
			AllAch = "��δ��д";
	}
	//����Ϊ����ʦ�йصĲ���
	//����Ϊ��ѧ���йصĲ���
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
			name="��δ��д";
		
		school = rs.getString("School");
		if (school == null || school.equals(""))
			school="��δ��д";
		
		sex = rs.getString("Gender");
		if (sex == null)
			sex = "��δ��д";
		else if (sex.equals("1"))
			sex = "��";
		else
			sex = "Ů";
		
		address = rs.getString("Address");
		if (address == null || address.equals(""))
			address="��δ��д";
		
		department = rs.getString("Department");
		if (department == null || department.equals(""))
			department="��δ��д";
		
		major = rs.getString("Major");
		if (major == null || major.equals(""))
			major="��δ��д";
		
		telephone = rs.getString("Telephone");
		if (telephone == null || telephone.equals(""))
			telephone="��δ��д";
		
		AllLea = rs.getString("LearningDir");
		if (AllLea== null || AllLea.equals(""))
			AllLea = "��δ��д";
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
			//ȡ���û���
			ActionContext actionContext = ActionContext.getContext();   //ȡ��struts����
			Map<String, Object> session = actionContext.getSession();    //ȡ��session
			username=(String) session.get("username");       //��sessionȡ���û�			
			//���»�����Ϣ�����ݿ�
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
	//��½�õ��ı���
	private String username;
	private String password;
	//������Ϣ�õ��ı���
	private String name;
	private String school;
	private String sex;
	private String address;
	private String department;
	private String major;
	private String telephone;
	private String homepage;
	//�����ƺ��õ��ı���
	private String SciAcademician;
	private String EngAcademician;
	private String YangtzeScholor;
	private String DrSupvisor;
	private String MyHonor;
	//�г������õ��ı���
	private String AllCal;
	//�����õ��ı���
	private String AllFund;
	//���гɹ��õ��ĵı���
	private String AllAch;
	//ѧ������Ȥ��ѧϰ����
	private String AllLea;
	//ѧ����������������ʦ
	private String teacherName;
	//��ͬ������ʽ
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

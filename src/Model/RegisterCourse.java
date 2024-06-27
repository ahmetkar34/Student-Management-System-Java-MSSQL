package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class RegisterCourse {
	int id, courseid, userid, registeredsection;
	String coursename, studentname;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public RegisterCourse(int id, int courseid, int userid, String coursename, String studentname,
			int registeredsection) {
		super();
		this.id = id;
		this.courseid = courseid;
		this.userid = userid;
		this.coursename = coursename;
		this.studentname = studentname;
		this.registeredsection = registeredsection;

	}

	public RegisterCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<RegisterCourse> getRegisteredCoursesList(int userid) throws SQLException {
		ArrayList<RegisterCourse> list = new ArrayList<>();
		RegisterCourse obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT rg.id,rg.course_id,rg.user_id,rg.student_name,rg.registered_section,ct.credit,ct.coursename "
					+ "FROM registercourse rg " + "LEFT JOIN coursetable ct ON rg.course_id=ct.id "
					+ "WHERE rg.user_id=" + userid;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				obj = new RegisterCourse(rs.getInt("id"), rs.getInt("course_id"), rs.getInt("user_id"),
						rs.getString("coursename"), rs.getString("student_name"), rs.getInt("registered_section"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;

	}

	public ArrayList<RegisterCourse> getRegisteredStudentsList(int courseid,String cName,int teacherSection) throws SQLException {
		ArrayList<RegisterCourse> list = new ArrayList<>();
		RegisterCourse obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT rc.id,rc.course_id,rc.user_id,rc.student_name,rc.registered_section " + "FROM registercourse rc "
					+ "LEFT JOIN teacherassign ta ON rc.course_id=ta.course_id " + "WHERE rc.course_id= "+courseid+"AND ta.section="+teacherSection;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				obj = new RegisterCourse(rs.getInt("id"), rs.getInt("course_id"), rs.getInt("user_id"),
						cName, rs.getString("student_name"), rs.getInt("registered_section"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;

	}

	public boolean addRegisterCourse(int courseid, int userid, String studentname, int registersection,String coursename)
			throws SQLException {
		String query = "INSERT INTO registercourse(course_id,user_id,student_name,registered_section,course_name) VALUES(?,?,?,?,?)";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, courseid);
			preparedStatement.setInt(2, userid);
			preparedStatement.setString(3, studentname);
			preparedStatement.setInt(4, registersection);
			preparedStatement.setString(5, coursename);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();

		}

		if (key) {
			return true;
		} else
			return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getRegisteredsection() {
		return registeredsection;
	}

	public void setRegisteredsection(int registeredsection) {
		this.registeredsection = registeredsection;
	}

}

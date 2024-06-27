package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Grade {

	int id, studentid, courseid, studentgrade;
	String studentname,gradetype,courseName;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Grade(int id, int studentid, int courseid, int studentgrade, String studentname, String gradetype, String courseName) {
		super();
		this.id = id;
		this.studentid = studentid;
		this.courseid = courseid;
		this.studentgrade = studentgrade;
		this.studentname = studentname;
		this.gradetype = gradetype;
		this.courseName = courseName;
	}

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Grade> getGradeList(int courseid , int section) throws SQLException {
		ArrayList<Grade> list = new ArrayList<>();
		Grade obj;
		Connection con = conn.connDB();
		try {

			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT registercourse.registered_section,gradetable.id,gradetable.course_id,gradetable.student_grade,gradetable.student_id,gradetable.grade_type,gradetable.course_name,gradetable.student_name FROM registercourse RIGHT JOIN gradetable ON registercourse.course_id=gradetable.course_id WHERE gradetable.course_id="
							+ courseid + "AND registercourse.registered_section = " + section);
			while (rs.next()) {
				obj = new Grade(rs.getInt("id"), rs.getInt("student_id"), rs.getInt("course_id"),
						rs.getInt("student_grade"),rs.getString("student_name"),rs.getString("grade_type"),rs.getString("course_name"));
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
	
	public ArrayList<Grade> getStudentGradeList(int userid) throws SQLException {
		ArrayList<Grade> list = new ArrayList<>();
		Grade obj;
		Connection con = conn.connDB();
		try {

			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT usertable.personname,gradetable.id,gradetable.course_id,gradetable.student_grade,gradetable.student_id,gradetable.grade_type,gradetable.course_name FROM usertable RIGHT JOIN gradetable ON usertable.id=gradetable.student_id WHERE gradetable.student_id="
							+ userid);
			while (rs.next()) {
				obj = new Grade(rs.getInt("id"), rs.getInt("student_id"), rs.getInt("course_id"),
						rs.getInt("student_grade"),rs.getString("personname"),rs.getString("grade_type"),rs.getString("course_name"));
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

	public boolean addGrade(int studentid, int courseid, int grade, String gradetype, String coursename, String studentname) throws SQLException {
		String query = "INSERT INTO gradetable(student_id,course_id,student_grade,grade_type,course_name,student_name) VALUES(?,?,?,?,?,?)";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, studentid);
			preparedStatement.setInt(2, courseid);
			preparedStatement.setInt(3, grade);
			preparedStatement.setString(4, gradetype);
			preparedStatement.setString(5, coursename);
			preparedStatement.setString(6, studentname);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			st.close();

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

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getStudentgrade() {
		return studentgrade;
	}

	public void setStudentgrade(int studentgrade) {
		this.studentgrade = studentgrade;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getGradetype() {
		return gradetype;
	}

	public void setGradetype(String gradetype) {
		this.gradetype = gradetype;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
	

}

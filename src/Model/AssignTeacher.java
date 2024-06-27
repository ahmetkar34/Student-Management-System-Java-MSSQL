package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class AssignTeacher {
	private int teacherid, teachersection,courseid;
	String teachername, teacherusername;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public ArrayList<AssignTeacher> getAssignedTeacherList(int courseid) throws SQLException {
		ArrayList<AssignTeacher> list = new ArrayList<>();
		AssignTeacher obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT ut.personname,ut.username,ta.user_id,ta.section,ta.course_id " + "FROM teacherassign ta "
					+ "LEFT JOIN usertable ut ON ta.user_id=ut.id " + "WHERE ta.course_id = ";
			rs = st.executeQuery(sql + courseid);
			while (rs.next()) {
				obj = new AssignTeacher(rs.getInt("user_id"), rs.getInt("section"), rs.getString("username"),
						rs.getString("personname"),rs.getInt("course_id"));
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

	public AssignTeacher getFetch(int courseid,int userid) {
		Connection con = conn.connDB();
		AssignTeacher at = new AssignTeacher();
		try {
			String sql = "SELECT ut.personname,ut.username,ta.user_id,ta.section,ta.course_id FROM teacherassign ta LEFT JOIN usertable ut ON ta.user_id=ut.id WHERE ta.course_id = ? AND ta.user_id = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, courseid);
			st.setInt(2, userid);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				at.setTeacherid(rs.getInt("user_id"));
				at.setTeachername(rs.getString("personname"));
				at.setTeacherusername(rs.getString("username"));
				at.setTeachersection(rs.getInt("section"));
				at.setCourseid(rs.getInt("course_id"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return at;

	}

	public AssignTeacher(int teacherid, int teachersection, String teacherusername, String teachername,int courseid) {
		super();
		this.teacherid = teacherid;
		this.teachersection = teachersection;
		this.teacherusername = teacherusername;
		this.teachername = teachername;
		this.courseid=courseid;
	}

	public AssignTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public int getTeachersection() {
		return teachersection;
	}

	public void setTeachersection(int teachersection) {
		this.teachersection = teachersection;
	}

	public String getTeacherusername() {
		return teacherusername;
	}

	public void setTeacherusername(String teacherusername) {
		this.teacherusername = teacherusername;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	

}

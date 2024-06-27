package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.*;

public class Course {
	private int id, credit, qtysection;
	String courseName;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Course() {
	};

	public ArrayList<Course> getCourseList() throws SQLException {
		ArrayList<Course> list = new ArrayList<>();
		Course obj;
		Connection con = conn.connDB();
		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM coursetable");
			while (rs.next()) {
				obj = new Course(rs.getInt("id"), rs.getString("coursename"), rs.getInt("credit"), rs.getInt("qty_section"));
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
	
	

	public ArrayList<Course> getCoursesITeachList(int userid) throws SQLException {
		ArrayList<Course> list = new ArrayList<>();
		Course obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT ct.id,ct.credit,ct.coursename,ct.qty_section,ta.course_id,ta.user_id,ta.section " + "FROM teacherassign ta "
					+ "LEFT JOIN coursetable ct ON ta.course_id=ct.id " + "WHERE ta.user_id = ";
			rs = st.executeQuery(sql + userid);
			while (rs.next()) {
				obj = new Course(rs.getInt("id"), rs.getString("coursename"), rs.getInt("credit"), rs.getInt("section"));
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

	public Course getFetch(int id) {
		Connection con = conn.connDB();
		Course c = new Course();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM coursetable WHERE id=" + id);
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setCourseName(rs.getString("coursename"));
				c.setCredit(rs.getInt("credit"));
				c.setQtysection(rs.getInt("qty_section"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;

	}
	
	

	public boolean addCourse(String cName, int credit, int qtysection) throws SQLException {
		String query = "INSERT INTO coursetable(coursename,credit,qty_section) VALUES(?,?,?)";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, cName);
			preparedStatement.setInt(2, credit);
			preparedStatement.setInt(3, qtysection);
			
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

	public boolean deleteCourse(int id) throws SQLException {
		String query = "DELETE FROM coursetable WHERE id=?" + "DELETE FROM teacherassign WHERE course_id=?"
				+ "DELETE FROM registercourse WHERE course_id=?" + "DELETE FROM gradetable WHERE course_id=?"
				+ "DELETE FROM schedulecourse WHERE courseid=?";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, id);
			preparedStatement.setInt(3, id);
			preparedStatement.setInt(4, id);
			preparedStatement.setInt(5, id);

			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else
			return false;
	}

	public boolean updateCourse(int id, String cName, int ects, int qtysection) throws SQLException {
		String query = "UPDATE coursetable SET coursename = ? , credit = ? , qty_section = ? WHERE id=?";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, cName);
			preparedStatement.setInt(2, ects);
			preparedStatement.setInt(3, qtysection);
			preparedStatement.setInt(4, id);
			
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else
			return false;
	}

	public Course(int id, String courseName, int credit, int qtysection) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.credit = credit;
		this.qtysection = qtysection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getQtysection() {
		return qtysection;
	}

	public void setQtysection(int qtysection) {
		this.qtysection = qtysection;
	}
	
	

}

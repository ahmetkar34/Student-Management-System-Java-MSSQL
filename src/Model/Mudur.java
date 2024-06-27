package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.*;

public class Mudur extends user {
	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	String SelectedID;
	int selectedKey , selectedSection;

	public Mudur(int id, String username, String userpassword, String personname, String usertype) {
		super(id, username, userpassword, personname, usertype);
		// TODO Auto-generated constructor stub
	}

	public Mudur() {
	}

	public ArrayList<user> getStudentList() throws SQLException {
		ArrayList<user> list = new ArrayList<>();
		user obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM usertable WHERE usertype='Ogrenci' ");
			while (rs.next()) {
				obj = new user(rs.getInt("id"), rs.getString("username"), rs.getString("userpassword"),
						rs.getString("personname"), rs.getString("usertype"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<user> getTeacherList() throws SQLException {
		ArrayList<user> list = new ArrayList<>();
		user obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM usertable WHERE usertype='Ogretmen' ");
			while (rs.next()) {
				obj = new user(rs.getInt("id"), rs.getString("username"), rs.getString("userpassword"),
						rs.getString("personname"), rs.getString("usertype"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	public ArrayList<user> getCourseTeacherList(int course) throws SQLException {
		ArrayList<user> list = new ArrayList<>();
		user obj;
		try {
			st = con.createStatement();
			 String sql = "SELECT u.id, u.username, u.userpassword, u.personname, u.usertype " +
                     "FROM teacherassign t " +
                     "LEFT JOIN usertable u ON t.user_id = u.id " +
                         "WHERE t.course_id = ";
			rs = st.executeQuery(sql+course);
			while (rs.next()) {
				obj = new user(rs.getInt("id"), rs.getString("username"), rs.getString("userpassword"),
						rs.getString("personname"), rs.getString("usertype"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	



	// add student
	public boolean addStudent(String username, String userpassword, String personname) throws SQLException {
		String query = "INSERT INTO usertable" + "(username,userpassword,personname,usertype) VALUES" + "(?,?,?,?	)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, userpassword);
			preparedStatement.setString(3, personname);
			preparedStatement.setString(4, "Ogrenci");
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

	// add teacher
	public boolean addTeacher(String username, String userpassword, String personname) throws SQLException {
		String query = "INSERT INTO usertable" + "(username,userpassword,personname,usertype) VALUES" + "(?,?,?,?	)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, userpassword);
			preparedStatement.setString(3, personname);
			preparedStatement.setString(4, "Ogretmen");
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

	// add teacher assign
	public boolean addTeacherAssign(int courseid, int userid, int section, String cName) throws SQLException {
		String query = "INSERT INTO teacherassign" + "(course_id,user_id,section,course_name) VALUES" + "(?,?,?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM teacherassign WHERE course_id=" + courseid + "AND user_id=" + userid);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, courseid);
				preparedStatement.setInt(2, userid);
				preparedStatement.setInt(3, section);
				preparedStatement.setString(4, cName);
				preparedStatement.executeUpdate();
				key = true;

			} else {
				key = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else
			return false;
	}

	// delete student
	public boolean deleteStudent(int id) throws SQLException {
		String query = "DELETE FROM usertable WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
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

	// delete teacher
	public boolean deleteTeacher(int id) throws SQLException {
		String query = "DELETE FROM usertable WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
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
	

	

	public boolean updateStudent(int id, String username, String userpassword, String personname) throws SQLException {
		String query = "UPDATE usertable SET username = ? , userpassword = ? , personname = ? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, userpassword);
			preparedStatement.setString(3, personname);
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
	
	public void setSelectedID(String text) {
		this.SelectedID=text;
	}
	
	public String getSelectedID() {
		return SelectedID;
	}

	public int getSelectedKey() {
		return selectedKey;
	}

	public void setSelectedKey(int selectedKey) {
		this.selectedKey = selectedKey;
	}

	public int getSelectedSection() {
		return selectedSection;
	}

	public void setSelectedSection(int selectedSection) {
		this.selectedSection = selectedSection;
	}
	
	
	
	

}

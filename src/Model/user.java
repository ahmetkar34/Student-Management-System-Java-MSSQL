package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.*;

public class user {
	private int id;
	String username, userpassword, personname, usertype;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public user(int id, String username, String userpassword, String personname, String usertype) {
		this.id = id;
		this.username = username;
		this.userpassword = userpassword;
		this.personname = personname;
		this.usertype = usertype;
	}

	public user() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public boolean updatePassword(int id, String newpassword, String oldpassword) throws SQLException {
		Connection con = conn.connDB();
		String checkOldPassQuery = "SELECT userpassword FROM usertable WHERE id=?";

		boolean key = false;
		try {
			PreparedStatement st = con.prepareStatement(checkOldPassQuery);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString("userpassword");
				if (dbPass.equals(oldpassword)) {
					String updateQuery = "UPDATE usertable SET userpassword = ? WHERE id=?";
					PreparedStatement stmt = con.prepareStatement(updateQuery);
					stmt.setString(1, newpassword);
					stmt.setInt(2, id);
					stmt.executeUpdate();
					key = true;

				} else {
					key = false;
				}
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

}

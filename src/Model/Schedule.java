package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Schedule {
	private int id, courseid, coursesection;
	String starttime, endtime, dayofweek, coursename, scheduleselsection;;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Schedule(int id, int courseid, String starttime, String endtime, String dayofweek, String coursename,
			int coursesection) {
		super();
		this.id = id;
		this.courseid = courseid;
		this.coursesection = coursesection;
		this.starttime = starttime;
		this.endtime = endtime;
		this.dayofweek = dayofweek;
		this.coursename = coursename;
	}

	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getCoursesection() {
		return coursesection;
	}

	public void setCoursesection(int coursesection) {
		this.coursesection = coursesection;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getScheduleselsection() {
		return scheduleselsection;
	}

	public void setScheduleselsection(String scheduleselsection) {
		this.scheduleselsection = scheduleselsection;
	}

	public boolean addSchedule(int cID, String startTime, String endTime, String DayOfWeek, int cSection)
			throws SQLException {
		String query = "INSERT INTO schedulecourse(courseid,start_time,end_time,day_of_week,schedule_section) VALUES(?,?,?,?,?)";
		boolean key = false;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, cID);
			preparedStatement.setString(2, startTime);
			preparedStatement.setString(3, endTime);
			preparedStatement.setString(4, DayOfWeek);
			preparedStatement.setInt(5, cSection);
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

	public ArrayList<Schedule> getScheduleList(String day) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<>();
		Schedule obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT ct.id ,ct.coursename,sc.id,sc.courseid,sc.start_time,sc.end_time,sc.day_of_week, sc.schedule_section "
					+ "FROM coursetable ct " + "RIGHT JOIN schedulecourse sc ON ct.id=sc.courseid "
					+ "WHERE sc.day_of_week = ";

			rs = st.executeQuery(sql + "'" + day + "'");
			while (rs.next()) {
				obj = new Schedule(rs.getInt("id"), rs.getInt("courseid"), rs.getString("start_time"),
						rs.getString("end_time"), rs.getString("day_of_week"), rs.getString("coursename"),
						rs.getInt("schedule_section"));
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

	public ArrayList<Schedule> getStudentScheduleList(int studentid) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<>();
		Schedule obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT sc.id,sc.courseid,sc.start_time,sc.end_time,sc.day_of_week,sc.schedule_section,rc.course_name FROM schedulecourse sc LEFT JOIN registercourse rc ON sc.courseid=rc.course_id WHERE rc.user_id="
					+ studentid + "ORDER BY " + " CASE day_of_week " + " WHEN 'Pazartesi' THEN 1 "
					+ " WHEN 'Sali' THEN 2 " + " WHEN 'Carsamba' THEN 3 " + " WHEN 'Persembe' THEN 4 "
					+ " WHEN 'Cuma' THEN 5 " + " WHEN 'Cumartesi' THEN 6 " + " WHEN 'Pazar' THEN 7 "
					+ " ELSE 8 " + " END, " + " start_time; ";

			rs = st.executeQuery(sql);
			while (rs.next()) {
				obj = new Schedule(rs.getInt("id"), rs.getInt("courseid"), rs.getString("start_time"),
						rs.getString("end_time"), rs.getString("day_of_week"), rs.getString("course_name"),
						rs.getInt("schedule_section"));
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
	
	public ArrayList<Schedule> getTeacherScheduleList(int teacherid) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<>();
		Schedule obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			String sql = "SELECT sc.id,sc.courseid,sc.start_time,sc.end_time,sc.day_of_week,sc.schedule_section,ta.course_name FROM schedulecourse sc LEFT JOIN teacherassign ta ON sc.courseid=ta.course_id WHERE ta.user_id="
					+ teacherid + "ORDER BY " + " CASE day_of_week " + " WHEN 'Pazartesi' THEN 1 "
					+ " WHEN 'Sali' THEN 2 " + " WHEN 'Carsamba' THEN 3 " + " WHEN 'Persembe' THEN 4 "
					+ " WHEN 'Cuma' THEN 5 " + " WHEN 'Cumartesi' THEN 6 " + " WHEN 'Pazar' THEN 7 "
					+ " ELSE 8 " + " END, " + " start_time; ";

			rs = st.executeQuery(sql);
			while (rs.next()) {
				obj = new Schedule(rs.getInt("id"), rs.getInt("courseid"), rs.getString("start_time"),
						rs.getString("end_time"), rs.getString("day_of_week"), rs.getString("course_name"),
						rs.getInt("schedule_section"));
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

}

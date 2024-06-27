package views;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentScheduleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Student student = new Student();
	Schedule schedule = new Schedule();
	private JPanel contentPane;
	private DefaultTableModel schedulemodel = null;
	private Object[] scheduleData = null;
	private JTable table_schedule;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentScheduleGUI frame = new StudentScheduleGUI(student);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public StudentScheduleGUI(Student student) throws SQLException {
		setTitle("Öğrenci Ders Programı");
		setResizable(false);
		
		schedulemodel = new DefaultTableModel();
		Object[] colschedule = new Object[4];
		colschedule[0] = "Gün";
		colschedule[1] = "Ders Adı";
		colschedule[2] = "Başlangıç";
		colschedule[3] = "Bitiş";
		schedulemodel.setColumnIdentifiers(colschedule);
		scheduleData = new Object[4];
		for (int i = 0; i < schedule.getStudentScheduleList(student.getId()).size(); i++) {
			scheduleData[0] = schedule.getStudentScheduleList(student.getId()).get(i).getDayofweek();
			scheduleData[1] = schedule.getStudentScheduleList(student.getId()).get(i).getCoursename();
			scheduleData[2] = schedule.getStudentScheduleList(student.getId()).get(i).getStarttime();
			scheduleData[3] = schedule.getStudentScheduleList(student.getId()).get(i).getEndtime();
			schedulemodel.addRow(scheduleData);
		}
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		contentPane.add(scrollPane);
		
		table_schedule = new JTable(schedulemodel);
		scrollPane.setViewportView(table_schedule);
	}
}

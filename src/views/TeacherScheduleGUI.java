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

public class TeacherScheduleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Teacher teacher = new Teacher();
	Schedule schedule = new Schedule();
	private JPanel contentPane;
	private JTable table_teacherschedule;
	private DefaultTableModel schedulemodel = null;
	private Object[] scheduleData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherScheduleGUI frame = new TeacherScheduleGUI(teacher);
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
	public TeacherScheduleGUI(Teacher teacher) throws SQLException {
		
		schedulemodel = new DefaultTableModel();
		Object[] colschedule = new Object[4];
		colschedule[0] = "Gün";
		colschedule[1] = "Ders Adı";
		colschedule[2] = "Başlangıç";
		colschedule[3] = "Bitiş";
		schedulemodel.setColumnIdentifiers(colschedule);
		scheduleData = new Object[4];
		for (int i = 0; i < schedule.getTeacherScheduleList(teacher.getId()).size(); i++) {
			scheduleData[0] = schedule.getTeacherScheduleList(teacher.getId()).get(i).getDayofweek();
			scheduleData[1] = schedule.getTeacherScheduleList(teacher.getId()).get(i).getCoursename();
			scheduleData[2] = schedule.getTeacherScheduleList(teacher.getId()).get(i).getStarttime();
			scheduleData[3] = schedule.getTeacherScheduleList(teacher.getId()).get(i).getEndtime();
			schedulemodel.addRow(scheduleData);
		}
		
		
		
		
		setTitle("Ogretmen Ders Programı");
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
		
		table_teacherschedule = new JTable(schedulemodel);
		scrollPane.setViewportView(table_teacherschedule);
	}
}

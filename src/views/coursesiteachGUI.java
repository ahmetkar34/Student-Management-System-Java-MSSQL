package views;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Model.Course;
import Model.Teacher;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class coursesiteachGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Teacher teacher=new Teacher();
	Course course = new Course();
	private JTable table_CoursesITeach;
	private DefaultTableModel coursesiteachmodel = null;
	private Object[] coursesiteachData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coursesiteachGUI frame = new coursesiteachGUI(teacher);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public coursesiteachGUI(Teacher teacher) {
		
		coursesiteachmodel = new DefaultTableModel();
		Object[] colCourseName = new Object[3];
		colCourseName[0] = "Kurs Adi";
		colCourseName[1] = "Kredi";
		colCourseName[2] = "Atandığım Şube";
		coursesiteachmodel.setColumnIdentifiers(colCourseName);
		coursesiteachData = new Object[3];
		try {
			for (int i = 0; i < course.getCoursesITeachList(teacher.getId()).size(); i++) {
				coursesiteachData[0]=course.getCoursesITeachList(teacher.getId()).get(i).getCourseName();
				coursesiteachData[1]=course.getCoursesITeachList(teacher.getId()).get(i).getCredit();
				coursesiteachData[2]=course.getCoursesITeachList(teacher.getId()).get(i).getQtysection();
				
				coursesiteachmodel.addRow(coursesiteachData);



			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		setTitle("Verilen Dersler");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 252, 309);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane w_CoursesITeachScroll = new JScrollPane();
		w_CoursesITeachScroll.setBounds(10, 11, 216, 248);
		contentPane.add(w_CoursesITeachScroll);
		
		table_CoursesITeach = new JTable(coursesiteachmodel);
		w_CoursesITeachScroll.setViewportView(table_CoursesITeach);
		TableColumn col0 = table_CoursesITeach.getColumnModel().getColumn(0);
		col0.setPreferredWidth(70);
		TableColumn col1 = table_CoursesITeach.getColumnModel().getColumn(1);
		col1.setPreferredWidth(30);
		TableColumn col2 = table_CoursesITeach.getColumnModel().getColumn(2);
		col2.setPreferredWidth(80);
		
	}
}

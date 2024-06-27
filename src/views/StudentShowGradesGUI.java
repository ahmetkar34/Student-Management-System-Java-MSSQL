package views;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Model.*;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class StudentShowGradesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Student student = new Student();
	Grade grade = new Grade();
	private JPanel contentPane;
	private JTable table_grade;
	private DefaultTableModel grademodel = null;
	private Object[] gradeData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentShowGradesGUI frame = new StudentShowGradesGUI(student);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public StudentShowGradesGUI(Student student) throws SQLException {
		grademodel = new DefaultTableModel();
		Object[] colgrade = new Object[4];
		colgrade[0] = "Ders ID";
		colgrade[1] = "Ders Adı";
		colgrade[2] = "Not";
		colgrade[3] = "Sınav Türü";
		grademodel.setColumnIdentifiers(colgrade);
		gradeData = new Object[4];
		for (int i = 0; i < grade.getStudentGradeList(student.getId()).size(); i++) {
			gradeData[0] = grade.getStudentGradeList(student.getId()).get(i).getCourseid();
			gradeData[1] = grade.getStudentGradeList(student.getId()).get(i).getCourseName();
			gradeData[2] = grade.getStudentGradeList(student.getId()).get(i).getStudentgrade();
			gradeData[3] = grade.getStudentGradeList(student.getId()).get(i).getGradetype();
			grademodel.addRow(gradeData);
		}

		setTitle("Notlarımı Göster");
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

		table_grade = new JTable(grademodel);
		scrollPane.setViewportView(table_grade);
		TableColumn col0 = table_grade.getColumnModel().getColumn(0);
		col0.setPreferredWidth(20);
		TableColumn col1 = table_grade.getColumnModel().getColumn(1);
		col1.setPreferredWidth(120);
		TableColumn col2 = table_grade.getColumnModel().getColumn(2);
		col2.setPreferredWidth(30);
		TableColumn col3 = table_grade.getColumnModel().getColumn(3);
		col3.setPreferredWidth(20);
	}
}

package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import Model.*;
import Helper.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class AssignStudentToCourseGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Student student = new Student();
	Course course = new Course();
	RegisterCourse rc = new RegisterCourse();
	private JPanel contentPane;
	private JTextField fld_selCourseID;
	private JTextField fld_selCourseCredit;
	private JTable table_course;
	private DefaultTableModel coursemodel = null;
	private Object[] courseData = null;
	private JTable table_registeredCourses;
	private DefaultTableModel registeredcoursemodel = null;
	private Object[] registeredcourseData = null;
	private DefaultComboBoxModel<Integer> selectSectionRegisterModel = new DefaultComboBoxModel<Integer>();
	private JTextField fld_selCourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignStudentToCourseGUI frame = new AssignStudentToCourseGUI(student);
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
	public AssignStudentToCourseGUI(Student student) throws SQLException {
		coursemodel = new DefaultTableModel();
		Object[] colCourse = new Object[3];
		colCourse[0] = "ID";
		colCourse[1] = "Ders Adı";
		colCourse[2] = "Ders AKTS";

		coursemodel.setColumnIdentifiers(colCourse);
		courseData = new Object[3];
		for (int i = 0; i < course.getCourseList().size(); i++) {
			courseData[0] = course.getCourseList().get(i).getId();
			courseData[1] = course.getCourseList().get(i).getCourseName();
			courseData[2] = course.getCourseList().get(i).getCredit();
			coursemodel.addRow(courseData);
		}

		registeredcoursemodel = new DefaultTableModel();
		Object[] colregistered = new Object[2];
		colregistered[0] = "Ders Adı";
		colregistered[1] = "Kayıt Olunan Şube";

		registeredcoursemodel.setColumnIdentifiers(colregistered);
		registeredcourseData = new Object[2];
		for (int i = 0; i < rc.getRegisteredCoursesList(student.getId()).size(); i++) {
			registeredcourseData[0] = rc.getRegisteredCoursesList(student.getId()).get(i).getCoursename();
			registeredcourseData[1] = rc.getRegisteredCoursesList(student.getId()).get(i).getRegisteredsection();
			registeredcoursemodel.addRow(registeredcourseData);
		}

		setTitle("Ogrenci Bilgi Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel w_studentassigncourse = new JPanel();
		w_studentassigncourse.setBackground(new Color(255, 255, 255));
		w_studentassigncourse.setBounds(10, 51, 714, 399);
		contentPane.add(w_studentassigncourse);
		w_studentassigncourse.setLayout(null);

		JScrollPane w_scrollCourse = new JScrollPane();
		w_scrollCourse.setBounds(10, 37, 265, 351);
		w_studentassigncourse.add(w_scrollCourse);

		table_course = new JTable(coursemodel);
		w_scrollCourse.setViewportView(table_course);

		table_course.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				fld_selCourseID.setText(table_course.getValueAt(table_course.getSelectedRow(), 0).toString());
				fld_selCourseCredit.setText(table_course.getValueAt(table_course.getSelectedRow(), 2).toString());
				fld_selCourse.setText(table_course.getValueAt(table_course.getSelectedRow(), 1).toString());
				updateSectionRegisterModel();

			}
		});

		JScrollPane w_scrollRegistredCourses = new JScrollPane();
		w_scrollRegistredCourses.setBounds(439, 37, 265, 351);
		w_studentassigncourse.add(w_scrollRegistredCourses);

		table_registeredCourses = new JTable(registeredcoursemodel);
		w_scrollRegistredCourses.setViewportView(table_registeredCourses);
		TableColumn col0 = table_registeredCourses.getColumnModel().getColumn(0);
		col0.setPreferredWidth(80);
		TableColumn col1 = table_registeredCourses.getColumnModel().getColumn(1);
		col1.setPreferredWidth(20);

		JButton btn_registerCourse = new JButton("Kayıt Ol");
		btn_registerCourse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (fld_selCourseID.getText().length() == 0) {
					Helper.showMsg("Lütfen Bir Ders Seçiniz!");

				} else {

					try {
						int selCourseID = Integer.parseInt(fld_selCourseID.getText());
						int selSelectSection = (int) selectSectionRegisterModel.getSelectedItem();
						String coursename = fld_selCourse.getText();
						boolean control = rc.addRegisterCourse(selCourseID, student.getId(), student.getPersonname(),
								selSelectSection,coursename);
						if (control) {
							Helper.showMsg("Kayıt Başarılı!");
							DefaultTableModel clearModel = (DefaultTableModel) table_registeredCourses.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < rc.getRegisteredCoursesList(student.getId()).size(); i++) {
								registeredcourseData[0] = rc.getRegisteredCoursesList(student.getId()).get(i)
										.getCoursename();
								registeredcourseData[1] = rc.getRegisteredCoursesList(student.getId()).get(i)
										.getRegisteredsection();
								registeredcoursemodel.addRow(registeredcourseData);
							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_registerCourse.setBounds(302, 294, 104, 33);
		w_studentassigncourse.add(btn_registerCourse);

		fld_selCourseID = new JTextField();
		fld_selCourseID.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selCourseID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_selCourseID.setEditable(false);
		fld_selCourseID.setBackground(new Color(192, 192, 192));
		fld_selCourseID.setBounds(302, 156, 38, 33);
		w_studentassigncourse.add(fld_selCourseID);
		fld_selCourseID.setColumns(10);

		fld_selCourseCredit = new JTextField();
		fld_selCourseCredit.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selCourseCredit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_selCourseCredit.setEditable(false);
		fld_selCourseCredit.setBackground(new Color(192, 192, 192));
		fld_selCourseCredit.setColumns(10);
		fld_selCourseCredit.setBounds(376, 156, 38, 33);
		w_studentassigncourse.add(fld_selCourseCredit);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(310, 131, 19, 22);
		w_studentassigncourse.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Kredi");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(376, 131, 40, 22);
		w_studentassigncourse.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("Dersler");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(99, 11, 66, 22);
		w_studentassigncourse.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Kayıtlı Olduğum Dersler");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(462, 11, 221, 22);
		w_studentassigncourse.add(lblNewLabel_2_1);

		JComboBox select_RegisterSection = new JComboBox(selectSectionRegisterModel);
		select_RegisterSection.setBounds(337, 228, 38, 33);
		w_studentassigncourse.add(select_RegisterSection);

		JLabel lblNewLabel_3_1 = new JLabel("Şube");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(337, 200, 40, 22);
		w_studentassigncourse.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4 = new JLabel("Seçilen Ders");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(311, 72, 95, 22);
		w_studentassigncourse.add(lblNewLabel_4);

		fld_selCourse = new JTextField();
		fld_selCourse.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selCourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_selCourse.setBackground(new Color(192, 192, 192));
		fld_selCourse.setBounds(286, 95, 143, 33);
		w_studentassigncourse.add(fld_selCourse);
		fld_selCourse.setColumns(10);

		JLabel lblNewLabel = new JLabel("Ders Kayıt Ekranı");
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 26));
		lblNewLabel.setBounds(270, 0, 193, 44);
		contentPane.add(lblNewLabel);

	}

	private void updateSectionRegisterModel() {
		int selCourse = (int) table_course.getValueAt(table_course.getSelectedRow(), 0);
		int qtysection = course.getFetch(selCourse).getQtysection();

		selectSectionRegisterModel.removeAllElements();
		for (int i = qtysection; i >= 1; i--) {
			selectSectionRegisterModel.addElement(i);
		}
	}
}

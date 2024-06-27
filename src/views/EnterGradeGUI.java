package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Model.*;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import Helper.*;

public class EnterGradeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Teacher teacher = new Teacher();
	RegisterCourse rc = new RegisterCourse();
	Course course = new Course();
	Grade grade = new Grade();
	AssignTeacher at = new AssignTeacher();
	private JPanel contentPane;
	private JTable table_registeredstudent;
	private JTable table_grades;
	private JTextField fld_selStudentName;
	private JTextField fld_selStudentID;
	private JTextField fld_grade;
	private DefaultTableModel registeredstudentmodel = null;
	private Object[] registeredstudentData = null;
	private DefaultTableModel grademodel = null;
	private Object[] gradeData = null;
	private HashMap<Integer, String> courseMap = new HashMap<Integer, String>();
	private DefaultComboBoxModel<String> gradetype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterGradeGUI frame = new EnterGradeGUI(teacher);
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
	public EnterGradeGUI(Teacher teacher) throws SQLException {
		setTitle("Not Giris Ekranı");
		registeredstudentmodel = new DefaultTableModel();
		Object[] colregistered = new Object[3];
		colregistered[0] = "Öğrenci ID";
		colregistered[1] = "Öğrenci Adi";
		colregistered[2] = "Şube";
		registeredstudentmodel.setColumnIdentifiers(colregistered);
		registeredstudentData = new Object[3];

		grademodel = new DefaultTableModel();
		Object[] colgrade = new Object[4];
		colgrade[0] = "ID";
		colgrade[1] = "Öğrenci Adı";
		colgrade[2] = "Not";
		colgrade[3] = "Sınav Türü";
		grademodel.setColumnIdentifiers(colgrade);
		gradeData = new Object[4];

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNotGiriEkran = new JLabel("Not Giriş Ekranı");
		lblNotGiriEkran.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 26));
		lblNotGiriEkran.setBounds(286, 0, 193, 44);
		contentPane.add(lblNotGiriEkran);

		JPanel w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBounds(10, 55, 714, 399);
		contentPane.add(w_pane);
		w_pane.setLayout(null);

		JComboBox select_courses = new JComboBox();
		select_courses.setBounds(255, 21, 207, 35);
		w_pane.add(select_courses);
		select_courses.addItem(new Item(0, "Lütfen Bir Seçim Yapınız!"));

		for (int i = 0; i < course.getCoursesITeachList(teacher.getId()).size(); i++) {

			select_courses.addItem(new Item(course.getCoursesITeachList(teacher.getId()).get(i).getId(),
					course.getCoursesITeachList(teacher.getId()).get(i).getCourseName()));

		}

		select_courses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox c = (JComboBox) e.getSource();
				Item item = (Item) c.getSelectedItem();
				int tSection = at.getFetch(item.getKey(), teacher.getId()).getTeachersection();
				if (item.getKey() == 0) {
					DefaultTableModel clearModel = (DefaultTableModel) table_registeredstudent.getModel();
					clearModel.setRowCount(0);
					DefaultTableModel ClearModel = (DefaultTableModel) table_grades.getModel();
					ClearModel.setRowCount(0);
				} else {

					DefaultTableModel clearModel = (DefaultTableModel) table_registeredstudent.getModel();
					clearModel.setRowCount(0);
					DefaultTableModel ClearModel = (DefaultTableModel) table_grades.getModel();
					ClearModel.setRowCount(0);

					try {
						for (int i = 0; i < rc.getRegisteredStudentsList(item.getKey(), item.getValue(), tSection)
								.size(); i++) {
							registeredstudentData[0] = rc
									.getRegisteredStudentsList(item.getKey(), item.getValue(), tSection).get(i)
									.getUserid();
							registeredstudentData[1] = rc
									.getRegisteredStudentsList(item.getKey(), item.getValue(), tSection).get(i)
									.getStudentname();
							registeredstudentData[2] = rc
									.getRegisteredStudentsList(item.getKey(), item.getValue(), tSection).get(i)
									.getRegisteredsection();
							registeredstudentmodel.addRow(registeredstudentData);

						}

						for (int i = 0; i < grade.getGradeList(item.getKey(), tSection).size(); i++) {
							gradeData[0] = grade.getGradeList(item.getKey(), tSection).get(i).getStudentid();
							gradeData[1] = grade.getGradeList(item.getKey(), tSection).get(i).getStudentname();
							gradeData[2] = grade.getGradeList(item.getKey(), tSection).get(i).getStudentgrade();
							gradeData[3] = grade.getGradeList(item.getKey(), tSection).get(i).getGradetype();
							grademodel.addRow(gradeData);
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		JLabel lblNewLabel_2 = new JLabel("Kayıtlı Öğrenciler");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(49, 11, 156, 22);
		w_pane.add(lblNewLabel_2);

		JScrollPane w_registeredStudent = new JScrollPane();
		w_registeredStudent.setBounds(10, 44, 232, 344);
		w_pane.add(w_registeredStudent);

		table_registeredstudent = new JTable(registeredstudentmodel);
		w_registeredStudent.setViewportView(table_registeredstudent);
		table_registeredstudent.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					fld_selStudentID.setText(
							table_registeredstudent.getValueAt(table_registeredstudent.getSelectedRow(), 0).toString());
					fld_selStudentName.setText(
							table_registeredstudent.getValueAt(table_registeredstudent.getSelectedRow(), 1).toString());

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		JLabel lblNewLabel_2_1 = new JLabel("Notlar");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(564, 11, 56, 22);
		w_pane.add(lblNewLabel_2_1);

		JScrollPane w_scrollGrades = new JScrollPane();
		w_scrollGrades.setBounds(472, 44, 232, 344);
		w_pane.add(w_scrollGrades);

		table_grades = new JTable(grademodel);
		w_scrollGrades.setViewportView(table_grades);
		TableColumn col0 = table_grades.getColumnModel().getColumn(0);
		col0.setPreferredWidth(30);
		TableColumn col1 = table_grades.getColumnModel().getColumn(1);
		col1.setPreferredWidth(100);
		TableColumn col2 = table_grades.getColumnModel().getColumn(2);
		col2.setPreferredWidth(35);
		TableColumn col3 = table_grades.getColumnModel().getColumn(3);
		col3.setPreferredWidth(70);

		JLabel lblNewLabel = new JLabel("Verdiğim Dersler");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(293, 0, 137, 25);
		w_pane.add(lblNewLabel);

		JLabel lblSeilenrenciAd = new JLabel("Seçilen Öğrenci Adı:");
		lblSeilenrenciAd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSeilenrenciAd.setBounds(281, 67, 169, 25);
		w_pane.add(lblSeilenrenciAd);

		fld_selStudentName = new JTextField();
		fld_selStudentName.setEditable(false);
		fld_selStudentName.setBackground(new Color(192, 192, 192));
		fld_selStudentName.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selStudentName.setFont(new Font("Tahoma", Font.BOLD, 16));
		fld_selStudentName.setBounds(255, 92, 207, 35);
		w_pane.add(fld_selStudentName);
		fld_selStudentName.setColumns(10);

		JLabel lblSeilenrenciId = new JLabel("Seçilen Öğrenci ID:");
		lblSeilenrenciId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSeilenrenciId.setBounds(281, 138, 169, 25);
		w_pane.add(lblSeilenrenciId);

		fld_selStudentID = new JTextField();
		fld_selStudentID.setEditable(false);
		fld_selStudentID.setBackground(new Color(192, 192, 192));
		fld_selStudentID.setFont(new Font("Tahoma", Font.BOLD, 16));
		fld_selStudentID.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selStudentID.setColumns(10);
		fld_selStudentID.setBounds(255, 163, 207, 35);
		w_pane.add(fld_selStudentID);

		JLabel lblNotGirii = new JLabel("Not Girişi:");
		lblNotGirii.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNotGirii.setBounds(321, 283, 82, 25);
		w_pane.add(lblNotGirii);

		fld_grade = new JTextField();
		fld_grade.setHorizontalAlignment(SwingConstants.CENTER);
		fld_grade.setFont(new Font("Tahoma", Font.BOLD, 16));
		fld_grade.setColumns(10);
		fld_grade.setBounds(321, 307, 82, 35);
		w_pane.add(fld_grade);

		JButton btn_gradesave = new JButton("Kaydet");
		btn_gradesave.addActionListener(new ActionListener() {
			private String selItem = "Lütfen Bir Seçim Yapınız...";

			public void actionPerformed(ActionEvent e) {
				if (fld_selStudentID.getText().length() == 0 || fld_selStudentName.getText().length() == 0
						|| fld_grade.getText().length() == 0 || gradetype.getSelectedItem() == selItem) {
					Helper.showMsg("fill");

				} else {

					try {
						Item studentitem = (Item) select_courses.getSelectedItem();
						int selStudentid = Integer.parseInt(fld_selStudentID.getText());
						int studentgrade = Integer.parseInt(fld_grade.getText());
						int tSection = at.getFetch(studentitem.getKey(), teacher.getId()).getTeachersection();
						String selGradeType = (String) gradetype.getSelectedItem();
						String selStudentName = fld_selStudentName.getText();
						boolean control = grade.addGrade(selStudentid, studentitem.getKey(), studentgrade, selGradeType,
								studentitem.getValue(), selStudentName);

						if (control) {
							Helper.showMsg("Not Başarıyla Eklendi!");
							fld_selStudentName.setText(null);
							fld_selStudentID.setText(null);
							fld_grade.setText(null);

							DefaultTableModel ClearModel = (DefaultTableModel) table_grades.getModel();
							ClearModel.setRowCount(0);

							for (int i = 0; i < grade.getGradeList(studentitem.getKey(), tSection).size(); i++) {
								gradeData[0] = grade.getGradeList(studentitem.getKey(), tSection).get(i).getStudentid();
								gradeData[1] = grade.getGradeList(studentitem.getKey(), tSection).get(i)
										.getStudentname();
								gradeData[2] = grade.getGradeList(studentitem.getKey(), tSection).get(i)
										.getStudentgrade();
								gradeData[3] = grade.getGradeList(studentitem.getKey(), tSection).get(i).getGradetype();
								grademodel.addRow(gradeData);
							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_gradesave.setBounds(293, 353, 137, 35);
		w_pane.add(btn_gradesave);

		JLabel lblSnavTr = new JLabel("Sınav Türü:");
		lblSnavTr.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSnavTr.setBounds(310, 212, 93, 25);
		w_pane.add(lblSnavTr);

		String items[] = { "Lütfen Bir Seçim Yapınız...", "Vize", "Final" };
		gradetype = new DefaultComboBoxModel<>(items);
		JComboBox select_gradeType = new JComboBox(gradetype);
		select_gradeType.setFont(new Font("Tahoma", Font.BOLD, 14));
		select_gradeType.setBounds(255, 235, 207, 35);
		w_pane.add(select_gradeType);

	}

}

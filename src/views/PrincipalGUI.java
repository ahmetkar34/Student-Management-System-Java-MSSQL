package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Helper.*;
import Model.AssignTeacher;
import Model.Course;
import Model.Mudur;
import Model.Schedule;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class PrincipalGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Mudur principal = new Mudur();
	Course course = new Course();
	Schedule schedule = new Schedule();
	AssignTeacher assignteacher = new AssignTeacher();
	private JPanel w_pane;
	private JTextField fld_studentName;
	private JTextField fld_studentNumber;
	private JTextField fld_studentPass;
	private JTextField fld_studentID;
	private JTable table_student;
	private DefaultTableModel studentmodel = null;
	private Object[] studentData = null;
	private JTextField fld_teacherName;
	private JTextField fld_teacherNumber;
	private JTextField fld_teacherPass;
	private JTextField fld_teacherID;
	private DefaultTableModel teachermodel = null;
	private Object[] teacherData = null;
	private JTable table_teacher;
	private JTable table_course;
	private DefaultTableModel coursemodel = null;
	private Object[] courseData = null;
	private JTable table_assignTeacher;
	private DefaultTableModel assignteachermodel = null;
	private Object[] assignteacherData = null;
	private JTextField fld_selectedCourse;
	private JTextField fld_selectedCourseDeleteRow;
	private JTextField fld_selectedCourse_2;
	private JTable table_course_1;
	private JTable table_schedules;
	private DefaultTableModel schedulemodel = null;
	private Object[] scheduleData = null;
	private DefaultComboBoxModel<Integer> selectsectionModel = new DefaultComboBoxModel<Integer>();
	private DefaultComboBoxModel<Integer> selectschedulesectionModel;
	private HashMap<Integer, String> teacherMap = new HashMap<Integer, String>();
	private DefaultComboBoxModel<String> selteacher = new DefaultComboBoxModel<String>();
	private JPasswordField fld_oldPass;
	private JPasswordField fld_newPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalGUI frame = new PrincipalGUI(principal);
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
	public PrincipalGUI(Mudur principal) throws SQLException {

		studentmodel = new DefaultTableModel();
		Object[] colStudentName = new Object[4];
		colStudentName[0] = "ID";
		colStudentName[1] = "Ad Soyad";
		colStudentName[2] = "Ogrenci No";
		colStudentName[3] = "Sifre";
		studentmodel.setColumnIdentifiers(colStudentName);
		studentData = new Object[4];
		for (int i = 0; i < principal.getStudentList().size(); i++) {
			studentData[0] = principal.getStudentList().get(i).getId();
			studentData[1] = principal.getStudentList().get(i).getPersonname();
			studentData[2] = principal.getStudentList().get(i).getUsername();
			studentData[3] = principal.getStudentList().get(i).getUserpassword();
			studentmodel.addRow(studentData);

		}

		teachermodel = new DefaultTableModel();
		Object[] colTeacherName = new Object[4];
		colTeacherName[0] = "ID";
		colTeacherName[1] = "Ad Soyad";
		colTeacherName[2] = "Ogretmen No";
		colTeacherName[3] = "Sifre";
		teachermodel.setColumnIdentifiers(colTeacherName);
		teacherData = new Object[4];
		for (int i = 0; i < principal.getTeacherList().size(); i++) {
			teacherData[0] = principal.getTeacherList().get(i).getId();
			teacherData[1] = principal.getTeacherList().get(i).getPersonname();
			teacherData[2] = principal.getTeacherList().get(i).getUsername();
			teacherData[3] = principal.getTeacherList().get(i).getUserpassword();
			teachermodel.addRow(teacherData);
		}

		coursemodel = new DefaultTableModel();
		Object[] colCourse = new Object[4];
		colCourse[0] = "ID";
		colCourse[1] = "Ders Adı";
		colCourse[2] = "Kredi";
		colCourse[3] = "Şube Sayisi";
		coursemodel.setColumnIdentifiers(colCourse);

		courseData = new Object[4];
		for (int i = 0; i < course.getCourseList().size(); i++) {
			courseData[0] = course.getCourseList().get(i).getId();
			courseData[1] = course.getCourseList().get(i).getCourseName();
			courseData[2] = course.getCourseList().get(i).getCredit();
			courseData[3] = course.getCourseList().get(i).getQtysection();

			coursemodel.addRow(courseData);
		}

		assignteachermodel = new DefaultTableModel();
		Object[] colassignteacher = new Object[3];
		colassignteacher[0] = "OgretmenNO";
		colassignteacher[1] = "OgretmenAdi";
		colassignteacher[2] = "Atanan Şube";
		assignteachermodel.setColumnIdentifiers(colassignteacher);
		assignteacherData = new Object[3];

		schedulemodel = new DefaultTableModel();
		Object[] colschedule = new Object[4];
		colschedule[0] = "Gün";
		colschedule[1] = "Ders Adi";
		colschedule[2] = "Başlangıç Saat";
		colschedule[3] = "Bitiş Saat";
		schedulemodel.setColumnIdentifiers(colschedule);
		scheduleData = new Object[4];

		setTitle("Ogrenci Otomasyonu Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(204, 204, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, sayın " + principal.getPersonname());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 289, 32);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaunchScreen lGUI = new LaunchScreen();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(635, 20, 89, 23);
		w_pane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 76, 714, 374);
		w_pane.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int tabIndex = tabbedPane.getSelectedIndex();
				if (tabIndex == 2) {

					try {
						teacherMap.clear();
						for (int i = 0; i < principal.getTeacherList().size(); i++) {

							// select_teacher.addItem(new Item(principal.getTeacherList().get(i).getId(),
							// principal.getTeacherList().get(i).getPersonname()));

							teacherMap.put(principal.getTeacherList().get(i).getId(),
									principal.getTeacherList().get(i).getPersonname());

						}

						updateTeacherSelectModel();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		JPanel w_pane_1 = new JPanel();
		w_pane_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Ogrenci Kayıt", null, w_pane_1, null);
		w_pane_1.setLayout(null);

		JLabel lbl_adsoyad = new JLabel("Ad Soyad");
		lbl_adsoyad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_adsoyad.setBounds(550, 11, 73, 24);
		w_pane_1.add(lbl_adsoyad);

		fld_studentName = new JTextField();
		fld_studentName.setBounds(550, 33, 149, 30);
		w_pane_1.add(fld_studentName);
		fld_studentName.setColumns(10);

		JLabel lbl_ogrencino = new JLabel("Ogrenci No");
		lbl_ogrencino.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_ogrencino.setBounds(550, 74, 94, 24);
		w_pane_1.add(lbl_ogrencino);

		fld_studentNumber = new JTextField();
		fld_studentNumber.setColumns(10);
		fld_studentNumber.setBounds(550, 96, 149, 30);
		w_pane_1.add(fld_studentNumber);

		JLabel lbl_sifre = new JLabel("Şifre");
		lbl_sifre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_sifre.setBounds(550, 137, 73, 24);
		w_pane_1.add(lbl_sifre);

		fld_studentPass = new JTextField();
		fld_studentPass.setColumns(10);
		fld_studentPass.setBounds(550, 159, 149, 30);
		w_pane_1.add(fld_studentPass);

		JButton btn_studentsave = new JButton("Kaydet");
		btn_studentsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_studentName.getText().length() == 0 || fld_studentPass.getText().length() == 0
						|| fld_studentNumber.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = principal.addStudent(fld_studentNumber.getText(), fld_studentPass.getText(),
								fld_studentName.getText());
						if (control) {
							Helper.showMsg("Öğrenci Başarıyla Eklendi!");
							fld_studentNumber.setText(null);
							fld_studentPass.setText(null);
							fld_studentName.setText(null);
							updateStudentModel();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_studentsave.setBounds(550, 200, 149, 30);
		w_pane_1.add(btn_studentsave);

		JLabel lbl_studentID = new JLabel("Ogrenci ID");
		lbl_studentID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_studentID.setBounds(550, 241, 94, 24);
		w_pane_1.add(lbl_studentID);

		fld_studentID = new JTextField();
		fld_studentID.setBackground(new Color(192, 192, 192));
		fld_studentID.setColumns(10);
		fld_studentID.setBounds(550, 263, 149, 30);
		w_pane_1.add(fld_studentID);

		JButton btn_studentdelete = new JButton("Sil");
		btn_studentdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_studentID.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					int selectID = Integer.parseInt(fld_studentID.getText());
					try {
						boolean control = principal.deleteStudent(selectID);
						if (control) {
							Helper.showMsg("Silme işlemi başarıyla gerçekleşti...");
							updateStudentModel();
							fld_studentID.setText(null);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_studentdelete.setBounds(550, 304, 149, 30);
		w_pane_1.add(btn_studentdelete);

		JScrollPane w_scrollStudent = new JScrollPane();
		w_scrollStudent.setBounds(10, 11, 523, 324);
		w_pane_1.add(w_scrollStudent);

		table_student = new JTable(studentmodel);
		w_scrollStudent.setViewportView(table_student);

		table_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_studentID.setText(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table_student.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
					String selectName = table_student.getValueAt(table_student.getSelectedRow(), 1).toString();
					String selectUsername = table_student.getValueAt(table_student.getSelectedRow(), 2).toString();
					String selectPass = table_student.getValueAt(table_student.getSelectedRow(), 3).toString();

					try {
						principal.updateStudent(selectID, selectUsername, selectPass, selectName);
						Helper.showMsg("Güncelleme İslemi Basarılı...");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		JPanel w_pane_2 = new JPanel();
		w_pane_2.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Öğretmen Kayıt", null, w_pane_2, null);
		w_pane_2.setLayout(null);

		JScrollPane w_scrollTeacher = new JScrollPane();
		w_scrollTeacher.setBounds(10, 11, 523, 324);
		w_pane_2.add(w_scrollTeacher);

		table_teacher = new JTable(teachermodel);
		w_scrollTeacher.setViewportView(table_teacher);

		table_teacher.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					fld_teacherID.setText(table_teacher.getValueAt(table_teacher.getSelectedRow(), 0).toString());

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		table_teacher.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_teacher.getValueAt(table_teacher.getSelectedRow(), 0).toString());
					String selectName = table_teacher.getValueAt(table_teacher.getSelectedRow(), 1).toString();
					String selectUsername = table_teacher.getValueAt(table_teacher.getSelectedRow(), 2).toString();
					String selectPass = table_teacher.getValueAt(table_teacher.getSelectedRow(), 3).toString();

					try {
						principal.updateStudent(selectID, selectUsername, selectPass, selectName);
						Helper.showMsg("Güncelleme İslemi Basarılı...");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		fld_teacherName = new JTextField();
		fld_teacherName.setColumns(10);
		fld_teacherName.setBounds(550, 33, 149, 30);
		w_pane_2.add(fld_teacherName);

		JLabel lbl_teacherName = new JLabel("Ad Soyad");
		lbl_teacherName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_teacherName.setBounds(550, 11, 73, 24);
		w_pane_2.add(lbl_teacherName);

		fld_teacherNumber = new JTextField();
		fld_teacherNumber.setColumns(10);
		fld_teacherNumber.setBounds(550, 96, 149, 30);
		w_pane_2.add(fld_teacherNumber);

		JLabel lbl_teacherNumber = new JLabel("Ogretmen No");
		lbl_teacherNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_teacherNumber.setBounds(550, 74, 94, 24);
		w_pane_2.add(lbl_teacherNumber);

		JLabel lbl_teacherPass = new JLabel("Şifre");
		lbl_teacherPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_teacherPass.setBounds(550, 137, 73, 24);
		w_pane_2.add(lbl_teacherPass);

		fld_teacherPass = new JTextField();
		fld_teacherPass.setColumns(10);
		fld_teacherPass.setBounds(550, 159, 149, 30);
		w_pane_2.add(fld_teacherPass);

		JButton btn_teacherSave = new JButton("Kaydet");
		btn_teacherSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_teacherName.getText().length()==0 || fld_teacherNumber.getText().length() == 0
						|| fld_teacherPass.getText().length() == 0) {

					Helper.showMsg("fill");

				} else {
					boolean control;
					try {
						control = principal.addTeacher(fld_teacherNumber.getText(), fld_teacherPass.getText(),
								fld_teacherName.getText());
						if (control) {
							Helper.showMsg("Öğretmen Başarıyla Kaydedildi!");
							fld_teacherNumber.setText(null);
							fld_teacherPass.setText(null);
							fld_teacherName.setText(null);
							updateTeacherModel();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_teacherSave.setBounds(550, 200, 149, 30);
		w_pane_2.add(btn_teacherSave);

		JLabel lbl_teacherID = new JLabel("Ogretmen ID");
		lbl_teacherID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_teacherID.setBounds(550, 241, 94, 24);
		w_pane_2.add(lbl_teacherID);

		fld_teacherID = new JTextField();
		fld_teacherID.setColumns(10);
		fld_teacherID.setBackground(Color.LIGHT_GRAY);
		fld_teacherID.setBounds(550, 263, 149, 30);
		fld_teacherID.setEditable(false);
		w_pane_2.add(fld_teacherID);

		JButton btn_teacherDelete = new JButton("Sil");
		btn_teacherDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_teacherID.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					int selectID = Integer.parseInt(fld_teacherID.getText());
					try {
						boolean control = principal.deleteTeacher(selectID);
						if (control) {
							Helper.showMsg("Silme işlemi başarıyla gerçekleşti...");
							try {
								teacherMap.remove(selectID);
								updateTeacherSelectModel();

							} catch (Exception e2) {
								// TODO: handle exception
							}
							updateTeacherModel();
							fld_teacherID.setText(null);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_teacherDelete.setBounds(550, 304, 149, 30);
		w_pane_2.add(btn_teacherDelete);

		JPanel w_pane_3 = new JPanel();
		w_pane_3.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Ders İşlemleri", null, w_pane_3, null);
		w_pane_3.setLayout(null);

		JScrollPane w_scrollCourses = new JScrollPane();
		w_scrollCourses.setBounds(10, 11, 231, 324);
		w_pane_3.add(w_scrollCourses);

		table_course = new JTable(coursemodel);
		w_scrollCourses.setViewportView(table_course);
		TableColumn col0 = table_course.getColumnModel().getColumn(0);
		col0.setPreferredWidth(30);
		TableColumn col1 = table_course.getColumnModel().getColumn(1);
		col1.setPreferredWidth(100);
		TableColumn col2 = table_course.getColumnModel().getColumn(2);
		col2.setPreferredWidth(35);
		TableColumn col3 = table_course.getColumnModel().getColumn(3);
		col3.setPreferredWidth(70);

		table_course.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					fld_selectedCourseDeleteRow
							.setText(table_course.getValueAt(table_course.getSelectedRow(), 0).toString());
					fld_selectedCourse.setText(table_course.getValueAt(table_course.getSelectedRow(), 0).toString());
					updateSectionModel();

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		JLabel lbl_dersEkle = new JLabel("Ders Ekle");
		lbl_dersEkle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersEkle.setBounds(280, 5, 73, 24);
		w_pane_3.add(lbl_dersEkle);

		JButton btn_courseSave = new JButton("Ekle(Açılır Ekran)");
		btn_courseSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addcourseGUI acGUI = new addcourseGUI(principal);
				acGUI.setVisible(true);
				acGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateCourseModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});

			}
		});
		btn_courseSave.setBounds(280, 27, 149, 30);
		w_pane_3.add(btn_courseSave);

		JScrollPane w_scrollAcademician = new JScrollPane();
		w_scrollAcademician.setBounds(468, 11, 231, 324);
		w_pane_3.add(w_scrollAcademician);

		table_assignTeacher = new JTable();
		w_scrollAcademician.setViewportView(table_assignTeacher);

		JComboBox select_teacher = new JComboBox(selteacher);
		select_teacher.setBounds(280, 264, 114, 30);

		select_teacher.addActionListener(new ActionListener() {
			private String selectedKey;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					JComboBox c = (JComboBox) e.getSource();
					String selectedItem = (String) c.getSelectedItem();
					selectedKey = selectedItem.split(" - ")[0];
					int selKey = Integer.parseInt(selectedKey);
					principal.setSelectedKey(selKey);

				} catch (Exception e2) {
					// TODO: handle exception
				}

				// Item item = (Item) c.getSelectedItem();

				// System.out.println(item.getKey()+" : "+item.getValue());

			}
		});
		w_pane_3.add(select_teacher);

		JButton btn_assignCourse = new JButton("Ders Ataması Yap");
		btn_assignCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_course.getSelectedRow();
				Object selItem = selteacher.getSelectedItem();
				int selSection=(int) selectsectionModel.getSelectedItem();
				if (selItem != null && selItem.equals(selteacher.getElementAt(0))) {
					Helper.showMsg("fill");
				} else if (selRow >= 0) {
					String selCourse = table_course.getModel().getValueAt(selRow, 0).toString();
					String selCourseName=table_course.getModel().getValueAt(selRow, 1).toString();
					int selCourseID = Integer.parseInt(selCourse);
					try {
						boolean control = principal.addTeacherAssign(selCourseID, principal.getSelectedKey(),
								selSection,selCourseName);
						if (control) {
							Helper.showMsg("İşlem başarılı...");
						} else {
							Helper.showMsg("Hata Oluştu....");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					Helper.showMsg("fill");

			}
		});
		btn_assignCourse.setBounds(280, 305, 149, 30);
		w_pane_3.add(btn_assignCourse);

		JLabel lbl_atamaYap = new JLabel("Akademisyen Seçimi");
		lbl_atamaYap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_atamaYap.setBounds(280, 239, 126, 30);
		w_pane_3.add(lbl_atamaYap);

		JLabel lbl_dersEkle_1 = new JLabel("Verenleri Listele");
		lbl_dersEkle_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersEkle_1.setBounds(280, 155, 149, 24);
		w_pane_3.add(lbl_dersEkle_1);

		JButton btn_assignCourseSelect = new JButton("Seç");
		btn_assignCourseSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_course.getSelectedRow();
				if (selRow >= 0) {
					String selCourse = table_course.getModel().getValueAt(selRow, 0).toString();
					int selCourseID = Integer.parseInt(selCourse);

					DefaultTableModel clearModel = (DefaultTableModel) table_assignTeacher.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < assignteacher.getAssignedTeacherList(selCourseID).size(); i++) {
							assignteacherData[0] = assignteacher.getAssignedTeacherList(selCourseID).get(i)
									.getTeacherusername();
							assignteacherData[1] = assignteacher.getAssignedTeacherList(selCourseID).get(i)
									.getTeachername();
							assignteacherData[2] = assignteacher.getAssignedTeacherList(selCourseID).get(i)
									.getTeachersection();
							assignteachermodel.addRow(assignteacherData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_assignTeacher.setModel(assignteachermodel);

				} else {
					Helper.showMsg("fill");
				}
			}
		});
		btn_assignCourseSelect.setBounds(280, 209, 149, 30);
		w_pane_3.add(btn_assignCourseSelect);

		JLabel lbl_dersEkle_1_1 = new JLabel("Seçilen Dersi ");
		lbl_dersEkle_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersEkle_1_1.setBounds(280, 142, 149, 24);
		w_pane_3.add(lbl_dersEkle_1_1);

		fld_selectedCourse = new JTextField();
		fld_selectedCourse.setBackground(new Color(192, 192, 192));
		fld_selectedCourse.setFont(new Font("Tahoma", Font.PLAIN, 28));
		fld_selectedCourse.setColumns(10);
		fld_selectedCourse.setBounds(280, 177, 149, 30);
		fld_selectedCourse.setEditable(false);
		w_pane_3.add(fld_selectedCourse);

		fld_selectedCourseDeleteRow = new JTextField();
		fld_selectedCourseDeleteRow.setFont(new Font("Tahoma", Font.PLAIN, 28));
		fld_selectedCourseDeleteRow.setEditable(false);
		fld_selectedCourseDeleteRow.setColumns(10);
		fld_selectedCourseDeleteRow.setBackground(Color.LIGHT_GRAY);
		fld_selectedCourseDeleteRow.setBounds(280, 82, 149, 30);
		w_pane_3.add(fld_selectedCourseDeleteRow);

		JButton btn_courseDelete = new JButton("Sil");
		btn_courseDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_selectedCourseDeleteRow.getText().length() == 0) {
					Helper.showMsg("Lütfen Bir Seçim Yapınız!!!");
				} else {
					int selectedID = Integer.parseInt(fld_selectedCourseDeleteRow.getText());
					try {
						boolean control = course.deleteCourse(selectedID);
						if (control) {
							Helper.showMsg("Silme işlemi Başarılı!");
							fld_selectedCourseDeleteRow.setText(null);
							fld_selectedCourse.setText(null);
							updateCourseModel();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_courseDelete.setBounds(280, 114, 73, 30);
		w_pane_3.add(btn_courseDelete);

		JLabel lbl_courseDeleteEdit = new JLabel("Seçilen Dersi Sil/Düzenle");
		lbl_courseDeleteEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_courseDeleteEdit.setBounds(280, 57, 166, 24);
		w_pane_3.add(lbl_courseDeleteEdit);

		JButton btn_courseUpdate = new JButton("Edit");
		btn_courseUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(fld_selectedCourseDeleteRow.getText());
				Course selectCourse = course.getFetch(selectedID);
				editcourseGUI ecGUI = new editcourseGUI(selectCourse);
				ecGUI.setVisible(true);
				ecGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateCourseModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

			}
		});
		btn_courseUpdate.setBounds(356, 114, 73, 30);
		w_pane_3.add(btn_courseUpdate);

		JLabel lblNewLabel_1 = new JLabel(" Şube");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(396, 247, 46, 14);
		w_pane_3.add(lblNewLabel_1);

		JComboBox select_section = new JComboBox(selectsectionModel);
		select_section.setBounds(396, 264, 33, 30);
		w_pane_3.add(select_section);
		selectsectionModel.setSelectedItem("-");
		

		JPanel w_pane_4 = new JPanel();
		w_pane_4.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Ders Programı kayıt", null, w_pane_4, null);
		w_pane_4.setLayout(null);

		JScrollPane w_scrollCourses_1 = new JScrollPane();
		w_scrollCourses_1.setBounds(10, 11, 231, 324);
		w_pane_4.add(w_scrollCourses_1);

		table_course_1 = new JTable(coursemodel);
		w_scrollCourses_1.setViewportView(table_course_1);

		table_course_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					fld_selectedCourse_2
							.setText(table_course_1.getValueAt(table_course_1.getSelectedRow(), 0).toString());
					String selID = table_course_1.getValueAt(table_course_1.getSelectedRow(), 0).toString();
					principal.setSelectedID(selID);
					updateScheduleSectionModel();

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		JScrollPane w_scrollSchedule = new JScrollPane();
		w_scrollSchedule.setBounds(468, 11, 231, 324);
		w_pane_4.add(w_scrollSchedule);

		table_schedules = new JTable();
		w_scrollSchedule.setViewportView(table_schedules);

		JLabel lbl_dersEkle_1_2 = new JLabel("Seçili Ders ID:");
		lbl_dersEkle_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersEkle_1_2.setBounds(280, 11, 99, 24);
		w_pane_4.add(lbl_dersEkle_1_2);

		fld_selectedCourse_2 = new JTextField();
		fld_selectedCourse_2.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selectedCourse_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		fld_selectedCourse_2.setEditable(false);
		fld_selectedCourse_2.setColumns(10);
		fld_selectedCourse_2.setBackground(Color.LIGHT_GRAY);
		fld_selectedCourse_2.setBounds(280, 36, 93, 30);
		w_pane_4.add(fld_selectedCourse_2);

		JButton btn_assignScheduleSelect = new JButton("Düzenle");
		btn_assignScheduleSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseScheduleGUI acsGUI = new addCourseScheduleGUI(principal);
				acsGUI.setVisible(true);
			}
		});
		btn_assignScheduleSelect.setBounds(280, 69, 149, 30);
		w_pane_4.add(btn_assignScheduleSelect);

		JLabel lbl_atamaYap_1 = new JLabel("Gün Seçimi Yapın");
		lbl_atamaYap_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_atamaYap_1.setBounds(280, 241, 149, 30);
		w_pane_4.add(lbl_atamaYap_1);

		String item[] = { "Lütfen Bir Gün Seçiniz...", "Pazartesi", "Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi",
				"Pazar" };
		JComboBox select_day = new JComboBox(item);
		select_day.setSelectedIndex(0);
		select_day.setBounds(280, 264, 149, 30);
		w_pane_4.add(select_day);

		JButton btn_showDayScheduleList = new JButton("Gün Programını Göster");
		btn_showDayScheduleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (select_day.getSelectedIndex() == 0) {
					Helper.showMsg("Lütfen Bir Gün Seçimi Yapın!");
				} else {
					try {
						DefaultTableModel clearModel = (DefaultTableModel) table_schedules.getModel();
						clearModel.setRowCount(0);
						for (int i = 0; i < schedule.getScheduleList(select_day.getSelectedItem().toString())
								.size(); i++) {
							scheduleData[0] = schedule.getScheduleList(select_day.getSelectedItem().toString()).get(i)
									.getDayofweek();
							scheduleData[1] = schedule.getScheduleList(select_day.getSelectedItem().toString()).get(i)
									.getCoursename();
							scheduleData[2] = schedule.getScheduleList(select_day.getSelectedItem().toString()).get(i)
									.getStarttime();
							scheduleData[3] = schedule.getScheduleList(select_day.getSelectedItem().toString()).get(i)
									.getEndtime();
							schedulemodel.addRow(scheduleData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_schedules.setModel(schedulemodel);
				}

			}
		});
		btn_showDayScheduleList.setBounds(280, 305, 149, 30);
		w_pane_4.add(btn_showDayScheduleList);

		selectschedulesectionModel = new DefaultComboBoxModel<>();
		JComboBox select_schedule_section = new JComboBox(selectschedulesectionModel);
		select_schedule_section.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select_schedule_section.setBounds(383, 36, 44, 30);
		w_pane_4.add(select_schedule_section);

		select_schedule_section.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String selScheduleSection = select_schedule_section.getSelectedItem().toString();
					int selectedScheduleSection=Integer.parseInt(selScheduleSection);
					principal.setSelectedSection(selectedScheduleSection);
					

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		JLabel lbl_dersEkle_1_2_1 = new JLabel("Şube:");
		lbl_dersEkle_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersEkle_1_2_1.setBounds(383, 12, 44, 24);
		w_pane_4.add(lbl_dersEkle_1_2_1);
		
		JPanel w_pane_5 = new JPanel();
		w_pane_5.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Şifremi Güncelle", null, w_pane_5, null);
		w_pane_5.setLayout(null);
		
		JButton btn_updatepass = new JButton("Güncelle");
		btn_updatepass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldpassword=fld_oldPass.getText();
				String newpassword = fld_newPass.getText();
				try {
					boolean control = principal.updatePassword(principal.getId(), newpassword, oldpassword);
					if(control) {
						Helper.showMsg("Şifreniz Başarıyla Güncellendi!");
					}
					else {
						Helper.showMsg("Eski Şifrenizin Doğruluğunu Kontrol Ediniz!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_updatepass.setBounds(256, 223, 148, 35);
		w_pane_5.add(btn_updatepass);
		
		JLabel lbl_oldpass = new JLabel("Mevcut Şifre:");
		lbl_oldpass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_oldpass.setBounds(256, 54, 90, 24);
		w_pane_5.add(lbl_oldpass);
		
		fld_oldPass = new JPasswordField();
		fld_oldPass.setBounds(256, 76, 148, 30);
		w_pane_5.add(fld_oldPass);
		
		JLabel lbl_newPass = new JLabel("Yeni Şifre:");
		lbl_newPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_newPass.setBounds(256, 132, 73, 24);
		w_pane_5.add(lbl_newPass);
		
		fld_newPass = new JPasswordField();
		fld_newPass.setBounds(256, 154, 148, 30);
		w_pane_5.add(fld_newPass);
	}

	public void updateStudentModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_student.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < principal.getStudentList().size(); i++) {
			studentData[0] = principal.getStudentList().get(i).getId();
			studentData[1] = principal.getStudentList().get(i).getPersonname();
			studentData[2] = principal.getStudentList().get(i).getUsername();
			studentData[3] = principal.getStudentList().get(i).getUserpassword();
			studentmodel.addRow(studentData);

		}

	}

	public void updateTeacherModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_teacher.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < principal.getTeacherList().size(); i++) {
			teacherData[0] = principal.getTeacherList().get(i).getId();
			teacherData[1] = principal.getTeacherList().get(i).getPersonname();
			teacherData[2] = principal.getTeacherList().get(i).getUsername();
			teacherData[3] = principal.getTeacherList().get(i).getUserpassword();
			teachermodel.addRow(teacherData);
		}

	}

	public void updateCourseModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_course.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < course.getCourseList().size(); i++) {
			courseData[0] = course.getCourseList().get(i).getId();
			courseData[1] = course.getCourseList().get(i).getCourseName();
			courseData[2] = course.getCourseList().get(i).getCredit();
			courseData[3] = course.getCourseList().get(i).getQtysection();

			coursemodel.addRow(courseData);
		}

	}

	private void updateSectionModel() {
		int selectedRow = table_course.getSelectedRow();
		int selectedValue = (int) table_course.getValueAt(selectedRow, 3);

		selectsectionModel.removeAllElements();
		for (int i = selectedValue; i >= 1; i--) {
			selectsectionModel.addElement(i);
		}
	}

	private void updateScheduleSectionModel() {
		int selectedRow = table_course_1.getSelectedRow();
		int selectedValue = (int) table_course_1.getValueAt(selectedRow, 3);

		selectschedulesectionModel.removeAllElements();
		for (int i = selectedValue; i >= 1; i--) {
			selectschedulesectionModel.addElement(i);
		}
	}

	private void updateTeacherSelectModel() {
		selteacher.removeAllElements();
		selteacher.addElement("Seçim Yapınız...");
		selteacher.setSelectedItem("Seçim Yapınız...");
		for (Map.Entry<Integer, String> entry : teacherMap.entrySet()) {
			// select_teacher.addItem(entry.getKey() + " - " + entry.getValue());
			selteacher.addElement(entry.getKey() + " - " + entry.getValue());
		}
	}

}

package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Course;
import Model.Mudur;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;

public class editcourseGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField fld_courseEditName;
	private JTextField fld_courseEditCredit;
	private static Course course;
	private JTextField fld_courseSection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editcourseGUI frame = new editcourseGUI(course);
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
	public editcourseGUI(Course course) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 240, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_dersAdi = new JLabel("Ders Adi");
		lbl_dersAdi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersAdi.setBounds(29, 11, 73, 24);
		contentPane.add(lbl_dersAdi);
		
		fld_courseEditName = new JTextField();
		fld_courseEditName.setColumns(10);
		fld_courseEditName.setBounds(29, 33, 149, 30);
		fld_courseEditName.setText(course.getCourseName());
		contentPane.add(fld_courseEditName);
		
		JLabel lbl_dersAKTS = new JLabel("Ders AKTS");
		lbl_dersAKTS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersAKTS.setBounds(29, 89, 73, 24);
		contentPane.add(lbl_dersAKTS);
		
		fld_courseEditCredit = new JTextField();
		fld_courseEditCredit.setColumns(10);
		fld_courseEditCredit.setBounds(29, 111, 149, 30);
		fld_courseEditCredit.setText(Integer.toString(course.getCredit()));
		contentPane.add(fld_courseEditCredit);
		
		JButton btn_editcourseSave = new JButton("Düzenlemeyi Kaydet");
		btn_editcourseSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean control=course.updateCourse(course.getId(),fld_courseEditName.getText(),Integer.parseInt(fld_courseEditCredit.getText()),Integer.parseInt(fld_courseSection.getText()));
					if(control) {
						Helper.showMsg("Düzenleme İşlemi Başarılı!");
						dispose();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_editcourseSave.setBounds(29, 230, 149, 30);
		contentPane.add(btn_editcourseSave);
		
		JLabel lbl_dersSection = new JLabel("Şube Sayısı");
		lbl_dersSection.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersSection.setBounds(29, 167, 77, 24);
		contentPane.add(lbl_dersSection);
		
		fld_courseSection = new JTextField();
		fld_courseSection.setText(Integer.toString(course.getQtysection()));
		fld_courseSection.setColumns(10);
		fld_courseSection.setBounds(29, 189, 149, 30);
		contentPane.add(fld_courseSection);
	}

}

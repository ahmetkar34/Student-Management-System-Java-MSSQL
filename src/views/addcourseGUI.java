package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Course;
import Model.Mudur;
import Helper.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addcourseGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Mudur principal = new Mudur();
	Course course = new Course();
	private JPanel contentPane;
	private JTextField fld_courseName;
	private JTextField fld_courseCredit;
	private JTextField fld_qtySection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addcourseGUI frame = new addcourseGUI(principal);
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
	public addcourseGUI(Mudur principal) {
		setTitle("Ders Ekle");
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
		lbl_dersAdi.setBounds(34, 11, 73, 24);
		contentPane.add(lbl_dersAdi);

		fld_courseName = new JTextField();
		fld_courseName.setColumns(10);
		fld_courseName.setBounds(34, 33, 149, 30);

		contentPane.add(fld_courseName);

		JLabel lbl_dersAKTS = new JLabel("Ders AKTS");
		lbl_dersAKTS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersAKTS.setBounds(34, 74, 73, 24);
		contentPane.add(lbl_dersAKTS);

		fld_courseCredit = new JTextField();
		fld_courseCredit.setColumns(10);
		fld_courseCredit.setBounds(34, 96, 149, 30);
		contentPane.add(fld_courseCredit);

		JButton btn_addcourseSave = new JButton("Dersi Kaydet");
		btn_addcourseSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_courseName.getText().length() == 0 || fld_courseCredit.getText().length() == 0
						|| fld_qtySection.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						String courseCredit = fld_courseCredit.getText();
						int convertedCredit = Integer.parseInt(courseCredit);
						String qtySection = fld_qtySection.getText();
						int convertedSection = Integer.parseInt(qtySection);

						if (course.addCourse(fld_courseName.getText(), convertedCredit, convertedSection)) {
							Helper.showMsg("Ekleme Basarili...");
							fld_courseName.setText(null);
							fld_courseCredit.setText(null);
							fld_qtySection.setText(null);
							dispose();

						}
						

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addcourseSave.setBounds(34, 217, 149, 30);
		contentPane.add(btn_addcourseSave);

		JLabel lbl_qtySection = new JLabel("Ders Şube Sayısı");
		lbl_qtySection.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_qtySection.setBounds(34, 137, 117, 24);
		contentPane.add(lbl_qtySection);

		fld_qtySection = new JTextField();
		fld_qtySection.setColumns(10);
		fld_qtySection.setBounds(34, 159, 149, 30);
		contentPane.add(fld_qtySection);
	}
}

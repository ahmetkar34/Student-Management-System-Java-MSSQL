package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.*;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class studentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Student student=new Student();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentGUI frame = new studentGUI(student);
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
	public studentGUI(Student student) {
		setTitle("Ogrenci Bilgi Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_welcomeStudent = new JLabel("Hoşgeldiniz, sayın "+student.getPersonname());
		lbl_welcomeStudent.setForeground(Color.BLACK);
		lbl_welcomeStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_welcomeStudent.setBackground(Color.WHITE);
		lbl_welcomeStudent.setBounds(10, 11, 289, 32);
		contentPane.add(lbl_welcomeStudent);
		
		JButton btn_signoutStudent = new JButton("Çıkış Yap");
		btn_signoutStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaunchScreen lsGUI = new LaunchScreen();
				lsGUI.setVisible(true);
				dispose();
			}
		});
		btn_signoutStudent.setBounds(635, 20, 89, 23);
		contentPane.add(btn_signoutStudent);
		
		JLabel lbl_dersKayıt = new JLabel("Ders Kaydı Yap");
		lbl_dersKayıt.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dersKayıt.setForeground(Color.BLACK);
		lbl_dersKayıt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersKayıt.setBackground(Color.WHITE);
		lbl_dersKayıt.setBounds(32, 74, 115, 27);
		contentPane.add(lbl_dersKayıt);
		
		JButton btn_assignstudentToCourse = new JButton("Seç");
		btn_assignstudentToCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AssignStudentToCourseGUI rcGUI = new AssignStudentToCourseGUI(student);
					rcGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_assignstudentToCourse.setBounds(20, 97, 143, 32);
		contentPane.add(btn_assignstudentToCourse);
		
		JLabel lbl_dersKayıt_1 = new JLabel("Notlarımı Göster");
		lbl_dersKayıt_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dersKayıt_1.setForeground(Color.BLACK);
		lbl_dersKayıt_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersKayıt_1.setBackground(Color.WHITE);
		lbl_dersKayıt_1.setBounds(303, 74, 115, 27);
		contentPane.add(lbl_dersKayıt_1);
		
		JButton btn_showgrades = new JButton("Seç");
		btn_showgrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StudentShowGradesGUI ssgGUI = new StudentShowGradesGUI(student);
					ssgGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_showgrades.setBounds(292, 97, 137, 32);
		contentPane.add(btn_showgrades);
		
		JLabel lbl_dersKayıt_1_1 = new JLabel("Ders Programı");
		lbl_dersKayıt_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dersKayıt_1_1.setForeground(Color.BLACK);
		lbl_dersKayıt_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersKayıt_1_1.setBackground(Color.WHITE);
		lbl_dersKayıt_1_1.setBounds(601, 74, 98, 27);
		contentPane.add(lbl_dersKayıt_1_1);
		
		JButton btn_showSchedule = new JButton("Seç");
		btn_showSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StudentScheduleGUI ssGUI = new StudentScheduleGUI(student);
					ssGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_showSchedule.setBounds(581, 97, 137, 32);
		contentPane.add(btn_showSchedule);
		
		JLabel lbl_updatepass = new JLabel("Şifremi Güncelle");
		lbl_updatepass.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_updatepass.setForeground(Color.BLACK);
		lbl_updatepass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_updatepass.setBackground(Color.WHITE);
		lbl_updatepass.setBounds(32, 183, 115, 27);
		contentPane.add(lbl_updatepass);
		
		JButton btn_updatePass = new JButton("Seç");
		btn_updatePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePasswordGUI upGUI = new UpdatePasswordGUI(student);
				upGUI.setVisible(true);
			}
		});
		btn_updatePass.setBounds(20, 206, 143, 32);
		contentPane.add(btn_updatePass);
	}
}

package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.*;
import Helper.*;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class teacherGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Teacher teacher=new Teacher();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teacherGUI frame = new teacherGUI(teacher);
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
	public teacherGUI(Teacher teacher) {
		setResizable(false);
		setTitle("Ogrenci Otomasyonu Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, sayın "+teacher.getPersonname());
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 289, 32);
		contentPane.add(lblNewLabel);
		
		JButton btn_signout = new JButton("Çıkış Yap");
		btn_signout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaunchScreen lGUI=new LaunchScreen();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btn_signout.setBounds(635, 20, 89, 23);
		contentPane.add(btn_signout);
		
		JButton btn_coursesiteach = new JButton("Listele");
		btn_coursesiteach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursesiteachGUI citGUI = new coursesiteachGUI(teacher);
				citGUI.setVisible(true);
			}
		});
		btn_coursesiteach.setBounds(20, 95, 137, 32);
		contentPane.add(btn_coursesiteach);
		
		JLabel lbl_verilendersler = new JLabel("Verdiğim Dersler");
		lbl_verilendersler.setForeground(new Color(0, 0, 0));
		lbl_verilendersler.setBackground(new Color(255, 255, 255));
		lbl_verilendersler.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_verilendersler.setBounds(35, 69, 113, 27);
		contentPane.add(lbl_verilendersler);
		
		JLabel lbl_verilendersler_1 = new JLabel("Not Girişi Yap");
		lbl_verilendersler_1.setForeground(Color.BLACK);
		lbl_verilendersler_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_verilendersler_1.setBackground(Color.WHITE);
		lbl_verilendersler_1.setBounds(317, 69, 92, 27);
		contentPane.add(lbl_verilendersler_1);
		
		JButton btn_coursesiteach_1 = new JButton("Seç");
		btn_coursesiteach_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EnterGradeGUI egGUI= new EnterGradeGUI(teacher);
					egGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btn_coursesiteach_1.setBounds(296, 95, 137, 32);
		contentPane.add(btn_coursesiteach_1);
		
		JLabel lbl_dersKayıt_1_1 = new JLabel("Ders Programı");
		lbl_dersKayıt_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dersKayıt_1_1.setForeground(Color.BLACK);
		lbl_dersKayıt_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersKayıt_1_1.setBackground(Color.WHITE);
		lbl_dersKayıt_1_1.setBounds(607, 69, 98, 27);
		contentPane.add(lbl_dersKayıt_1_1);
		
		JButton btn_showSchedule = new JButton("Seç");
		btn_showSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TeacherScheduleGUI tsGUI = new TeacherScheduleGUI(teacher);
					tsGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_showSchedule.setBounds(587, 92, 137, 32);
		contentPane.add(btn_showSchedule);
		
		JButton btn_updatePass = new JButton("Seç");
		btn_updatePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePasswordGUI upGUI = new UpdatePasswordGUI(teacher);
				upGUI.setVisible(true);
			}
		});
		btn_updatePass.setBounds(20, 193, 137, 32);
		contentPane.add(btn_updatePass);
		
		JLabel lbl_verilendersler_2 = new JLabel("Şifremi Güncelle");
		lbl_verilendersler_2.setForeground(Color.BLACK);
		lbl_verilendersler_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_verilendersler_2.setBackground(Color.WHITE);
		lbl_verilendersler_2.setBounds(35, 168, 113, 27);
		contentPane.add(lbl_verilendersler_2);
	}
}

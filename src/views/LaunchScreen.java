package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.*;


public class LaunchScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_studentnumber;
	private JPasswordField fld_studentPassword;
	private JPasswordField fld_principalPassword;
	private JTextField fld_principalnumber;
	private DBConnection conn = new DBConnection();
	private JTextField fld_teachernumber;
	private JPasswordField fld_teacherPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaunchScreen frame = new LaunchScreen();
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
	public LaunchScreen() {
		setResizable(false);
		setTitle("Ogrenci Bilgi Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 569);
		w_pane = new JPanel();
		w_pane.setForeground(new Color(0, 0, 0));
		w_pane.setBackground(new Color(204, 204, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_welcometext = new JLabel("OGRENCI BILGI SISTEMI");
		lbl_welcometext.setBounds(208, 69, 399, 62);
		lbl_welcometext.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 40));
		w_pane.add(lbl_welcometext);

		JTabbedPane w_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedPane.setBounds(10, 177, 846, 340);
		w_pane.add(w_tabbedPane);

		JPanel w_studentlogin = new JPanel();
		w_studentlogin.setBackground(new Color(255, 255, 255));
		w_tabbedPane.addTab("Ogrenci Girisi", null, w_studentlogin, null);
		w_studentlogin.setLayout(null);

		JLabel lbl_ogrencino = new JLabel("Ogrenci No:");
		lbl_ogrencino.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbl_ogrencino.setBounds(10, 37, 134, 57);
		w_studentlogin.add(lbl_ogrencino);

		JLabel lbl_ogrencisifre = new JLabel("Şifre:");
		lbl_ogrencisifre.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbl_ogrencisifre.setBounds(40, 132, 63, 50);
		w_studentlogin.add(lbl_ogrencisifre);

		fld_studentnumber = new JTextField();
		fld_studentnumber.setBackground(Color.LIGHT_GRAY);
		fld_studentnumber.setBounds(143, 52, 226, 35);
		w_studentlogin.add(fld_studentnumber);
		fld_studentnumber.setColumns(10);

		fld_studentPassword = new JPasswordField();
		fld_studentPassword.setBackground(Color.LIGHT_GRAY);
		fld_studentPassword.setBounds(143, 144, 226, 35);
		w_studentlogin.add(fld_studentPassword);

		JButton btn_studentlogin = new JButton("Giriş Yap");
		btn_studentlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_studentnumber.getText().length() == 0 || fld_studentPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM usertable");
						while (rs.next()) {
							if (fld_studentnumber.getText().equals(rs.getString("username"))
									&& fld_studentPassword.getText().equals(rs.getString("userpassword"))) {
								Student student=new Student();
								student.setId(rs.getInt("id"));
								student.setPersonname(rs.getString("personname"));
								student.setUsername(rs.getString("username"));
								student.setUserpassword(rs.getString("userpassword"));
								student.setUsertype(rs.getString("usertype"));
								studentGUI sGUI=new studentGUI(student);
								sGUI.setVisible(true);
								dispose();
								
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_studentlogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_studentlogin.setBounds(226, 247, 272, 54);
		w_studentlogin.add(btn_studentlogin);

		JPanel w_teacherlogin = new JPanel();
		w_teacherlogin.setBackground(new Color(255, 255, 255));
		w_tabbedPane.addTab("Ogretmen Girisi", null, w_teacherlogin, null);
		w_teacherlogin.setLayout(null);

		JLabel lbl_teacherno = new JLabel("Ogretmen No:");
		lbl_teacherno.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbl_teacherno.setBounds(10, 37, 144, 57);
		w_teacherlogin.add(lbl_teacherno);

		fld_teachernumber = new JTextField();
		fld_teachernumber.setColumns(10);
		fld_teachernumber.setBackground(Color.LIGHT_GRAY);
		fld_teachernumber.setBounds(171, 52, 226, 35);
		w_teacherlogin.add(fld_teachernumber);

		JLabel lbl_teacherpass = new JLabel("Şifre:");
		lbl_teacherpass.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbl_teacherpass.setBounds(40, 132, 63, 50);
		w_teacherlogin.add(lbl_teacherpass);

		fld_teacherPassword = new JPasswordField();
		fld_teacherPassword.setBackground(Color.LIGHT_GRAY);
		fld_teacherPassword.setBounds(171, 144, 226, 35);
		w_teacherlogin.add(fld_teacherPassword);

		JButton btn_teacherlogin = new JButton("Giriş Yap");
		btn_teacherlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_teachernumber.getText().length() == 0 && fld_teacherPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM usertable");
						while (rs.next()) {
							if (fld_teachernumber.getText().equals(rs.getString("username"))
									&& fld_teacherPassword.getText().equals(rs.getString("userpassword"))) {
								Teacher teacher = new Teacher();
								teacher.setId(rs.getInt("id"));
								teacher.setPersonname(rs.getString("personname"));
								teacher.setUsername(rs.getString("username"));
								teacher.setUserpassword(rs.getString("userpassword"));
								teacher.setUsertype(rs.getString("usertype"));
								teacherGUI tGUI = new teacherGUI(teacher);
								tGUI.setVisible(true);
								dispose();

							}

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_teacherlogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_teacherlogin.setBounds(226, 247, 272, 54);
		w_teacherlogin.add(btn_teacherlogin);

		JPanel w_principallogin = new JPanel();
		w_principallogin.setBackground(new Color(255, 255, 255));
		w_tabbedPane.addTab("Mudur Girisi", null, w_principallogin, null);
		w_principallogin.setLayout(null);

		JLabel lbl_principalno = new JLabel("Mudur No:");
		lbl_principalno.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbl_principalno.setBounds(20, 37, 134, 57);
		w_principallogin.add(lbl_principalno);

		JLabel lbl_principalpassword = new JLabel("Şifre:");
		lbl_principalpassword.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbl_principalpassword.setBounds(40, 132, 63, 50);
		w_principallogin.add(lbl_principalpassword);

		fld_principalPassword = new JPasswordField();
		fld_principalPassword.setBackground(Color.LIGHT_GRAY);
		fld_principalPassword.setBounds(143, 144, 226, 35);
		w_principallogin.add(fld_principalPassword);

		fld_principalnumber = new JTextField();
		fld_principalnumber.setColumns(10);
		fld_principalnumber.setBackground(Color.LIGHT_GRAY);
		fld_principalnumber.setBounds(143, 52, 226, 35);
		w_principallogin.add(fld_principalnumber);

		JButton btn_principalLogin = new JButton("Giriş Yap");
		btn_principalLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_principalnumber.getText().length() == 0 || fld_principalPassword.getText().length() == 0) {

					Helper.showMsg("fill");

				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM usertable");
						while (rs.next()) {
							if (fld_principalnumber.getText().equals(rs.getString("username"))
									&& fld_principalPassword.getText().equals(rs.getString("userpassword"))) {
								Mudur principal = new Mudur();
								principal.setId(rs.getInt("id"));
								principal.setUserpassword("userpassword");
								principal.setUsername(rs.getString("username"));
								principal.setPersonname(rs.getString("personname"));
								principal.setUsertype(rs.getString("usertype"));
								PrincipalGUI pGUI = new PrincipalGUI(principal);
								pGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}
			}
		});
		btn_principalLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_principalLogin.setBounds(226, 247, 272, 54);
		w_principallogin.add(btn_principalLogin);

	}
}

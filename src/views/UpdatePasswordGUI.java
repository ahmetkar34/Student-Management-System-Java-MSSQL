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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdatePasswordGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static user user = new user();
	private JPanel contentPane;
	private JPasswordField fld_newpass;
	private JPasswordField fld_oldpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePasswordGUI frame = new UpdatePasswordGUI(user);
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
	public UpdatePasswordGUI(user user) {
		setTitle("Şifre Güncelle");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 260, 260);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_updatepass = new JButton("Güncelle");
		btn_updatepass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPass=fld_oldpass.getText();
				String newPass=fld_newpass.getText();
				try {
					boolean control = user.updatePassword(user.getId(), newPass, oldPass);
					if(control) {
						Helper.showMsg("Şifreniz Başarıyla Güncellendi!");
					}
					else {
						Helper.showMsg("Lütfen Eski Şifrenizin Doğruluğunu Kontrol Ediniz!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_updatepass.setBounds(49, 180, 148, 35);
		contentPane.add(btn_updatepass);
		
		JLabel lbl_newPass = new JLabel("Yeni Şifre:");
		lbl_newPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_newPass.setBounds(49, 89, 73, 24);
		contentPane.add(lbl_newPass);
		
		fld_newpass = new JPasswordField();
		fld_newpass.setBounds(49, 111, 148, 30);
		contentPane.add(fld_newpass);
		
		JLabel lbl_oldpass = new JLabel("Mevcut Şifre:");
		lbl_oldpass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_oldpass.setBounds(49, 11, 90, 24);
		contentPane.add(lbl_oldpass);
		
		fld_oldpass = new JPasswordField();
		fld_oldpass.setBounds(49, 33, 148, 30);
		contentPane.add(fld_oldpass);
	}
}

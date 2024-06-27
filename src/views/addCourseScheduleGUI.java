package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Mudur;
import Model.Schedule;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.SwingConstants;

public class addCourseScheduleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Mudur principal = new Mudur();
	Schedule schedule = new Schedule();
	private JPanel contentPane;
	private JTextField fld_selectedcourseid_2;
	private JTextField fld_startTime;
	private JTextField fld_finishTime;
	private JTextField fld_schedulesection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCourseScheduleGUI frame = new addCourseScheduleGUI(principal);
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
	public addCourseScheduleGUI(Mudur principal) {
		setTitle("schedule");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 390);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_dersId = new JLabel("Seçilen Ders ID:");
		lbl_dersId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_dersId.setBounds(40, 0, 166, 24);
		contentPane.add(lbl_dersId);

		fld_selectedcourseid_2 = new JTextField();
		fld_selectedcourseid_2.setHorizontalAlignment(SwingConstants.CENTER);
		fld_selectedcourseid_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_selectedcourseid_2.setText(principal.getSelectedID());
		fld_selectedcourseid_2.setBackground(new Color(192, 192, 192));
		fld_selectedcourseid_2.setEditable(false);
		fld_selectedcourseid_2.setColumns(10);
		fld_selectedcourseid_2.setBounds(40, 22, 149, 30);
		contentPane.add(fld_selectedcourseid_2);

		JLabel lbl_baslangicsaat = new JLabel("Başlangıç Saati:");
		lbl_baslangicsaat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_baslangicsaat.setBounds(40, 126, 107, 24);
		contentPane.add(lbl_baslangicsaat);

		fld_startTime = new JTextField();
		fld_startTime.setHorizontalAlignment(SwingConstants.CENTER);
		fld_startTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_startTime.setColumns(10);
		fld_startTime.setBounds(40, 148, 149, 30);
		contentPane.add(fld_startTime);

		JLabel lbl_bitissaat = new JLabel("Bitiş Saati:");
		lbl_bitissaat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_bitissaat.setBounds(40, 189, 73, 24);
		contentPane.add(lbl_bitissaat);

		fld_finishTime = new JTextField();
		fld_finishTime.setHorizontalAlignment(SwingConstants.CENTER);
		fld_finishTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_finishTime.setColumns(10);
		fld_finishTime.setBounds(40, 211, 149, 30);
		contentPane.add(fld_finishTime);

		JLabel lbl_günsec = new JLabel("Gün Seçimi:");
		lbl_günsec.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_günsec.setBounds(40, 252, 107, 24);
		contentPane.add(lbl_günsec);

		String[] items = { "Lütfen Bir Gün Seçiniz...", "Pazartesi", "Sali", "Carsamba", "Persembe", "Cuma",
				"Cumartesi", "Pazar" };
		JComboBox select_day = new JComboBox(items);
		select_day.setSelectedIndex(0);
		select_day.setBounds(40, 274, 149, 30);
		contentPane.add(select_day);

		JButton btn_scheduleSave = new JButton("Kaydet");
		btn_scheduleSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_startTime.getText().length() == 0 || fld_finishTime.getText().length() == 0
						|| select_day.getSelectedIndex() == 0) {
					Helper.showMsg("Lütfen Eksik Alanları Doldurunuz!");
				} else {

					try {
						int selCourseID = Integer.parseInt(fld_selectedcourseid_2.getText());
						int selCourseSection=Integer.parseInt(fld_schedulesection.getText());
						String startTime = fld_startTime.getText().toString();
						String finishTime = fld_finishTime.getText().toString();
						String dayofweek = (String) select_day.getSelectedItem();
						
						schedule.addSchedule(selCourseID, startTime, finishTime, dayofweek,selCourseSection);
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_scheduleSave.setBounds(40, 315, 149, 30);
		contentPane.add(btn_scheduleSave);
		
		JLabel lbl_subesec = new JLabel("Seçilen Ders Şubesi:");
		lbl_subesec.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_subesec.setBounds(40, 63, 184, 24);
		contentPane.add(lbl_subesec);
		
		fld_schedulesection = new JTextField();
		fld_schedulesection.setHorizontalAlignment(SwingConstants.CENTER);
		fld_schedulesection.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_schedulesection.setBackground(new Color(192, 192, 192));
		fld_schedulesection.setEditable(false);
		fld_schedulesection.setColumns(10);
		fld_schedulesection.setBounds(40, 85, 149, 30);
		fld_schedulesection.setText(Integer.toString(principal.getSelectedSection()));
		contentPane.add(fld_schedulesection);
	}
}

package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta=new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle(" Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 380);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbladsoyad = new JLabel("Ad Soyad");
		lbladsoyad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lbladsoyad.setBounds(151, 11, 233, 28);
		w_pane.add(lbladsoyad);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(42, 50, 305, 42);
		w_pane.add(fld_name);
		
		JLabel lblTcNo = new JLabel("     TC No");
		lblTcNo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblTcNo.setBounds(139, 101, 98, 14);
		w_pane.add(lblTcNo);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(42, 126, 305, 42);
		w_pane.add(fld_tcno);
		
		JLabel lblsifre = new JLabel("Şifre");
		lblsifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblsifre.setBounds(163, 179, 233, 28);
		w_pane.add(lblsifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(42, 209, 305, 42);
		w_pane.add(fld_pass);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setBounds(10, 271, 136, 59);
		w_pane.add(btn_backto);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_pass.getText   ().length()==0|| fld_name.getText().length()==0) {
					Helper.showMsg("fill");
					
				}
				else {
					try {
						boolean control=hasta.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login=new LoginGUI();
							login.setVisible(true);
							dispose();
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_register.setBounds(200, 272, 147, 58);
		w_pane.add(btn_register);
	}
}

package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn=new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 418);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(170, 234, 227));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hastanelogo.jpg")));
		lbl_logo.setBounds(175, 11, 100, 55);
		w_pane.add(lbl_logo);
		
		JTabbedPane w_tabbPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbPane.setBounds(24, 161, 423, 195);
		w_pane.add(w_tabbPane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabbPane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcKimlikNumaras = new JLabel("   TC. Kimlik Numarası:");
		lblTcKimlikNumaras.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblTcKimlikNumaras.setBounds(0, 15, 200, 28);
		w_hastaLogin.add(lblTcKimlikNumaras);
		
		JLabel lblsifre = new JLabel("   Şifre:");
		lblsifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblsifre.setBounds(0, 80, 200, 28);
		w_hastaLogin.add(lblsifre);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		fld_hastaTc.setBounds(196, 19, 187, 28);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btnRegister = new JButton("Kayıt Ol");
		btnRegister.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI=new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btnRegister.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnRegister.setBounds(26, 119, 168, 37);
		w_hastaLogin.add(btnRegister);
		
		JButton btnhastagiris =  new JButton("Giriş Yap");
		btnhastagiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length()==0 || fld_hastaPass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					boolean key= true;
					try {
						Connection con= conn.connDb();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");	
						while(rs.next()) {
							if(fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")){
									Hasta hasta =new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI=new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key=false;
								}
								
							}
							
							
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
					if(key) {
						Helper.showMsg("Böyle bir hasta bulunamadı. Lütfen kayıt olunuz");
				
					}
				}
				
			}
		});
		btnhastagiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnhastagiris.setBounds(215, 119, 168, 37);
		w_hastaLogin.add(btnhastagiris);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(196, 80, 187, 28);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabbPane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JPanel w_hastaLogin_1 = new JPanel();
		w_hastaLogin_1.setLayout(null);
		w_hastaLogin_1.setBackground(Color.WHITE);
		w_hastaLogin_1.setBounds(0, 0, 418, 167);
		w_doctorLogin.add(w_hastaLogin_1);
		
		JLabel lblTcKimlikNumaras_1 = new JLabel("   TC. Kimlik Numarası:");
		lblTcKimlikNumaras_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblTcKimlikNumaras_1.setBounds(0, 15, 200, 28);
		w_hastaLogin_1.add(lblTcKimlikNumaras_1);
		
		JLabel lblifre_1 = new JLabel("   Şifre:");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblifre_1.setBounds(0, 80, 200, 28);
		w_hastaLogin_1.add(lblifre_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(196, 19, 187, 28);
		w_hastaLogin_1.add(fld_doctorTc);
		
		JButton btndoktorgiris = new JButton("Giriş Yap");
		btndoktorgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length()==0 || fld_doctorPass.getText().length()==0 ) {
					Helper.showMsg("fill");
					
				}
				else {
					
					try {
						Connection con= conn.connDb();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")){
									Bashekim bhekim=new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI=new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor=new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI=new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
									
									
								}
							}
							
							
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btndoktorgiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btndoktorgiris.setBounds(73, 119, 272, 37);
		w_hastaLogin_1.add(btndoktorgiris);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(196, 80, 187, 28);
		w_hastaLogin_1.add(fld_doctorPass);
		
		JLabel lblNewLabel = new JLabel("     Hastane Yönetim Sistemine Hoş Geldiniz. ");
		lblNewLabel.setBounds(53, 71, 375, 28);
		w_pane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
	}
}

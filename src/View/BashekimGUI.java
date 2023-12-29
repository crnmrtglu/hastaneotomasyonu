package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	
	static Bashekim bashekim=new Bashekim();
    Clinic clinic =new Clinic();
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_adsoyad;
	private JTextField fld_tcno;
	private JTextField fld_sifre;
	private JTextField fld_id;
	private JTable table_doctor;
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;
	private JTable table_clinic;
	private JTextField fld_clinicname;
	private DefaultTableModel clinicModel=null;
	private Object[] clinicData=null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		doctorModel=new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="Ad Soyad";
		colDoctorName[2]="TC No";
		colDoctorName[3]="Sifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getPassword();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getName();
			doctorModel.addRow(doctorData);
			
			
		}
		clinicModel=new DefaultTableModel();
		Object[] colClinic=new Object[2];
		colClinic[0]="ID";
		colClinic[1]="Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData=new Object[2];
		for(int i=0;i<clinic.getList().size();i++) {
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		DefaultTableModel workerModel=new DefaultTableModel();
		Object[] colWorker=new Object[2];
		colWorker[0]="ID";
		colWorker[1]="Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData=new Object[2];
		
		
		setBackground(new Color(255, 255, 255));
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoş Geldiniz, Sayın " + bashekim.getName());
		lblNewLabel.setBounds(25, 11, 193, 41);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
			
		});
		btnNewButton.setBounds(618, 21, 89, 23);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		w_pane.add(btnNewButton);
		
		JTabbedPane doktor_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		doktor_tabbedPane.setBounds(25, 109, 638, 330);
		w_pane.add(doktor_tabbedPane);
		
		JPanel doktor_panel = new JPanel();
		doktor_tabbedPane.addTab("Doktor Yönetim Sistemi", null, doktor_panel, null);
		doktor_panel.setBounds(25, 100, 105, 41);
		doktor_panel.setLayout(null);
		
		JLabel lbladsoyad = new JLabel("Ad Soyad");
		lbladsoyad.setBounds(495, 25, 55, 14);
		lbladsoyad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		doktor_panel.add(lbladsoyad);
		
		fld_adsoyad = new JTextField();
		fld_adsoyad.setBounds(439, 50, 160, 20);
		fld_adsoyad.setColumns(10);
		doktor_panel.add(fld_adsoyad);
		
		fld_tcno = new JTextField();
		fld_tcno.setBounds(439, 116, 160, 20);
		fld_tcno.setColumns(10);
		doktor_panel.add(fld_tcno);
		
		fld_sifre = new JTextField();
		fld_sifre.setBounds(439, 182, 160, 20);
		fld_sifre.setColumns(10);
		doktor_panel.add(fld_sifre);
		
		JLabel lblTcNo = new JLabel("TC No");
		lblTcNo.setBounds(495, 91, 55, 14);
		lblTcNo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		doktor_panel.add(lblTcNo);
		
		JLabel lblsifre = new JLabel("Şifre");
		lblsifre.setBounds(495, 157, 55, 14);
		lblsifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		doktor_panel.add(lblsifre);
		
		JLabel lblkullanici = new JLabel("Kullanici id");
		lblkullanici.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblkullanici.setBounds(10, 250, 75, 14);
		doktor_panel.add(lblkullanici);
		
		fld_id = new JTextField();
		fld_id.setColumns(10);
		fld_id.setBounds(0, 271, 160, 20);
		doktor_panel.add(fld_id);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_adsoyad.getText().length()==0 || fld_sifre.getText().length()==0 || fld_tcno.getText().length()==0  ) {
					Helper.showMsg("fill");
				}
				else {
					try {
						boolean control=bashekim.addDoctor(fld_tcno.getText(), fld_sifre.getText(),fld_adsoyad.getText());
						if(control) {
							Helper.showMsg("success");
							fld_adsoyad.setText(null);
							fld_tcno.setText(null);
							fld_sifre.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				}
			
				
			}
		});
		btn_addDoctor.setBounds(474, 213, 100, 40);
		doktor_panel.add(btn_addDoctor);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 0, 425, 248);
		doktor_panel.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_id.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
				} catch (Exception ex) {
		
				}
				
				
			}
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if(e.getType()== TableModelEvent.UPDATE) {
					int selectID=Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName=table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno=table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass=table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					try {
						boolean control= bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		doktor_tabbedPane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 12, 215, 279);
		w_clinic.add(w_scrollClinic);
		
		
		clinicMenu=new JPopupMenu();
		JMenuItem updateMenu=new JMenuItem("güncelle");
		JMenuItem deleteMenu=new JMenuItem("sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		updateMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selID=Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString()); 
				Clinic selectClinic=clinic.getFetch(selID);
				
				UpdateClinicGUI updateGUI=new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter(){
				 public void windowClosed(WindowEvent e) {
					 try {
						updateClinicModel();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				});
				
				
			}
			
		});
		deleteMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Helper.confirm("sure")) {
					int selID=Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString()); 
				
				try {
					if(clinic.deleteClinic(selID)) {
						Helper.showMsg("success");
						
						updateClinicModel();
					}
					else {
						Helper.showMsg("error");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				}
			}
			});
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point= e.getPoint();
				int selectedRow=table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow,selectedRow);
				
			}
		});
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lbladsoyad_1 = new JLabel("Poliklinik Adı");
		lbladsoyad_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbladsoyad_1.setBounds(262, 12, 103, 14);
		w_clinic.add(lbladsoyad_1);
		
		fld_clinicname = new JTextField();
		fld_clinicname.setColumns(10);
		fld_clinicname.setBounds(235, 27, 130, 20);
		w_clinic.add(fld_clinicname);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicname.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						if(clinic.addClinic(fld_clinicname.getText())) {
							Helper.showMsg("success");
							fld_clinicname.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setBounds(245, 58, 100, 40);
		w_clinic.add(btn_addClinic);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(375, 11, 209, 280);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(242, 211, 103, 29);
		for(int i=0; i<bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(),bashekim.getDoctorList().get(i).getPassword()));
		}
		select_doctor.addActionListener(e->{
			JComboBox c=(JComboBox) e.getSource();
			Item item=(Item) c.getSelectedItem();
			System.out.println(item.getKey()+":"+ item.getValue());
		});
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_clinic.getSelectedRow();
				if(selRow>=0) {
					String selClinic=table_clinic.getModel().getValueAt(selRow,0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					Item doctorItem=(Item)select_doctor.getSelectedItem();
					try {
						boolean control=bashekim.addWorker(doctorItem.getKey(), selClinicID);
						
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel=(DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0;i<bashekim.getClinicDoctorList(selClinicID).size();i++) {
								workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getPassword();		
						         workerModel.addRow(workerData);	
							}
							table_worker.setModel(workerModel);
			
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					Helper.showMsg("lutfen bir poliklinik seciniz");
				}
			}
		});
		btn_addWorker.setBounds(245, 251, 100, 40);
		w_clinic.add(btn_addWorker);
		
		JLabel lbladsoyad_1_1 = new JLabel("      Poliklinik Adı");
		lbladsoyad_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		lbladsoyad_1_1.setBounds(245, 120, 103, 14);
		w_clinic.add(lbladsoyad_1_1);
		
		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_clinic.getSelectedRow();
				if(selRow>=0) {
					String selClinic=table_clinic.getModel().getValueAt(selRow,0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					DefaultTableModel clearModel=(DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0;i<bashekim.getClinicDoctorList(selClinicID).size();i++) {
							workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getPassword();		
					         workerModel.addRow(workerData);	
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
					
					
					
				}
				else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz! ");
				}
			}
		});
		btn_workerSelect.setBounds(245, 134, 100, 40);
		w_clinic.add(btn_workerSelect);
	
			

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_id.getText().length()==0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz!");
				}
				else {
					if(Helper.confirm("sure")) {
						int selectID=Integer.parseInt(fld_id.getText());
						try {
							boolean control=bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_id.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
				
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSil.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnSil.setBounds(181, 270, 100, 23);
		doktor_panel.add(btnSil);
		
		
		
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getPassword();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getName();
			doctorModel.addRow(doctorData);
			}
			
		
	}
	

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<clinic.getList().size();i++) {
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
	
		}
	}		
}

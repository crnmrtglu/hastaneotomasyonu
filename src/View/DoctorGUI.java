package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_Pane;
	private static Doctor doctor=new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData =null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DoctorGUI(Doctor doctor) throws SQLException {
		whourModel=new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
		for(int i=0;i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		
		setBackground(new Color(255, 255, 255));
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_Pane = new JPanel();
		w_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_Pane);
		w_Pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoş Geldiniz"+doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 193, 41);
		w_Pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnNewButton.setBounds(620, 21, 89, 23);
		w_Pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(23, 75, 638, 330);
		w_Pane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_tab.addTab("Calisma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 126, 20);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10.:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", ""}));
		select_time.setBounds(159, 11, 81, 22);
		w_whour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date="";
		
			
                try {
                	date=sdf.format(select_date.getDate());
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
                if(date.length()==0) {
					Helper.showMsg("Lutfen gecerli bir tarih giriniz");
				}
                else {
                	String time= " " + select_time.getSelectedItem().toString() + ":00";
                	String selectDate= date + time;
                		
				try {
					boolean control=doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
				
					if(control) {
						Helper.showMsg("success");
						updateWhourModel(doctor);
					}
					else {
						Helper.showMsg("error");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
                }
				String time=" "+ select_time.getSelectedItem().toString() + ":00";
				String selectDate= date + time;
				
			}
		});
		btn_addWhour.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_addWhour.setBounds(265, 11, 89, 23);
		w_whour.add(btn_addWhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 40, 613, 262);
		w_whour.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_whour.getSelectedRow();
				if(selRow>=0) {
					String selectRow=table_whour.getModel().getValueAt(selRow,0).toString();
					int selID=Integer.parseInt(selectRow);
					boolean control;
					try {
						control= doctor.deleteWhour(selID);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}
						else {
							Helper.showMsg("error");
						}
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				else {
					Helper.showMsg("lutfen bir tarih seciniz");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_deleteWhour.setBounds(534, 11, 89, 23);
		w_whour.add(btn_deleteWhour);
	}
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
			
		
	}
}

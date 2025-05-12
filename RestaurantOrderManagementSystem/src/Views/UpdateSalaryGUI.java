package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.*;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateSalaryGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Manager manager = new Manager();
	private JPanel contentPane;
	private HashMap<Integer, String> staffMap = new HashMap<Integer, String>();
	private DefaultComboBoxModel<String> selStaff = new DefaultComboBoxModel<String>();
	private JTextField fld_currentSalary;
	private JTextField fld_updateSalary;
	private int selUserID;
	private Point initialClick;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSalaryGUI frame = new UpdateSalaryGUI(manager);
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
	public UpdateSalaryGUI(Manager manager) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for (int i = 0; i < manager.getStaffList().size(); i++) {
			staffMap.put(manager.getStaffList().get(i).getUser_id(), manager.getStaffList().get(i).getPersonname());
		}
		
		selStaff.addElement("Seçim Yapınız!");
		for (Map.Entry<Integer, String> entry : staffMap.entrySet()) {

			selStaff.addElement(entry.getKey() + " - " + entry.getValue());
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int thisX = getLocation().x;
				int thisY = getLocation().y;
				int xMoved = e.getX() - initialClick.x;
				int yMoved = e.getY() - initialClick.y;
				int x = thisX + xMoved;
				int y = thisY + yMoved;
				setLocation(x, y);

			}

		});
		
		JLabel lblPersonelMaaGncelle = new JLabel(" Personel Maaş Güncelle");
		lblPersonelMaaGncelle.setForeground(Color.WHITE);
		lblPersonelMaaGncelle.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblPersonelMaaGncelle.setBounds(215, 11, 225, 36);
		contentPane.add(lblPersonelMaaGncelle);
		
		JLabel lblPersonelSeimi = new JLabel("Personel Seçimi");
		lblPersonelSeimi.setForeground(Color.WHITE);
		lblPersonelSeimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPersonelSeimi.setBounds(255, 82, 122, 24);
		contentPane.add(lblPersonelSeimi);
		
		JComboBox select_staff = new JComboBox(selStaff);
		select_staff.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select_staff.setBounds(205, 106, 225, 34);
		((JLabel) select_staff.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		select_staff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox c = (JComboBox) e.getSource();
				String selItem = (String) c.getSelectedItem();
				
				if (c.getSelectedIndex() == 0) {
					fld_currentSalary.setText(null);
					fld_updateSalary.setText(null);

				} else {
					try {
						selUserID = Integer.parseInt(selItem.split(" - ")[0]);
						fld_currentSalary.setText(manager.getSalary(selUserID));

					} catch (Exception e2) {
						// TODO: handle exception
					}

				}
				
			}
		});
		contentPane.add(select_staff);
		
		
		
		
		
		JLabel lblPersonelSeimi_1_1_1 = new JLabel("Güncel Maaş:");
		lblPersonelSeimi_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1.setBounds(255, 171, 134, 24);
		contentPane.add(lblPersonelSeimi_1_1_1);
		
		fld_currentSalary = new JTextField();
		fld_currentSalary.setForeground(new Color(255, 0, 0));
		fld_currentSalary.setHorizontalAlignment(SwingConstants.CENTER);
		fld_currentSalary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_currentSalary.setColumns(10);
		fld_currentSalary.setBackground(new Color(192, 192, 192));
		fld_currentSalary.setBounds(205, 193, 225, 34);
		contentPane.add(fld_currentSalary);
		
		JLabel lblPersonelSeimi_1_1_1_1 = new JLabel("<html>Güncellenmek <br> İstenen Miktar  </html>");
		lblPersonelSeimi_1_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1_1.setBounds(255, 249, 134, 36);
		contentPane.add(lblPersonelSeimi_1_1_1_1);
		
		fld_updateSalary = new JTextField();
		fld_updateSalary.setHorizontalAlignment(SwingConstants.CENTER);
		fld_updateSalary.setForeground(new Color(0, 128, 255));
		fld_updateSalary.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_updateSalary.setColumns(10);
		fld_updateSalary.setBackground(new Color(255, 255, 255));
		fld_updateSalary.setBounds(205, 289, 225, 34);
		contentPane.add(fld_updateSalary);
		
		JButton btn_save = new JButton("Güncelle");
		btn_save.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				try {
					boolean control = manager.updateSalary(selUserID, Integer.parseInt(fld_updateSalary.getText()));
					if(control) {
						Helper.showMsg("Güncelleme Başarılı!");
						fld_currentSalary.setText(fld_updateSalary.getText());
						fld_updateSalary.setText(null);
						
						
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_save.setForeground(Color.BLACK);
		btn_save.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_save.setBounds(244, 360, 152, 34);
		contentPane.add(btn_save);
		
		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(UpdateSalaryGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(600, 0, 50, 50);
		contentPane.add(btn_exit);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(UpdateSalaryGUI.class.getResource("/img/background_img.jpg")));
		lbl_background.setBounds(0, 0, 650, 500);
		contentPane.add(lbl_background);
	}
}

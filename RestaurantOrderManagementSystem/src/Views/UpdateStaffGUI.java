package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.*;
import Helper.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UpdateStaffGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Manager manager = new Manager();
	private JPanel contentPane;
	private Point initialClick;
	private JTextField fld_id;
	private JTextField fld_personname;
	private JTextField fld_username;
	private JTextField fld_password;
	private JRadioButton rdbtn_Manager;
	private JRadioButton rdbtn_Waiter;
	private JRadioButton rdbtn_Chef;
	private ButtonGroup roleGroup;
	private HashMap<Integer, String> staffMap = new HashMap<Integer, String>();
	private DefaultComboBoxModel<String> selStaff = new DefaultComboBoxModel<String>();
	public int selUserId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStaffGUI frame = new UpdateStaffGUI(manager);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public UpdateStaffGUI(Manager manager) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 511, 579);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
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

		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(UpdateStaffGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(461, 0, 50, 50);
		contentPane.add(btn_exit);

		JLabel lblPersonelBilgiGncelle = new JLabel("Personel Bilgi Güncelle");
		lblPersonelBilgiGncelle.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblPersonelBilgiGncelle.setForeground(new Color(255, 255, 255));
		lblPersonelBilgiGncelle.setBounds(151, 0, 204, 36);
		contentPane.add(lblPersonelBilgiGncelle);

		JComboBox select_staff = new JComboBox(selStaff);
		select_staff.setFont(new Font("Tahoma", Font.PLAIN, 18));
		select_staff.setBounds(127, 66, 225, 34);
		((JLabel) select_staff.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		select_staff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox c = (JComboBox) e.getSource();
				String selItem = (String) c.getSelectedItem();
				Manager m = new Manager();

				if (c.getSelectedIndex() == 0) {
					fld_id.setText(null);
					fld_personname.setText(null);
					fld_username.setText(null);
					fld_password.setText(null);
					roleGroup.clearSelection();

					Helper.showMsg("Lütfen Bir Personel Seçiniz !");

				} else {
					try {
						int selUserId = Integer.parseInt(selItem.split(" - ")[0]);
						int selId = m.getFetch(selUserId).getUser_id();
						String role = m.getFetch(selUserId).getUserrole();
						fld_id.setText(String.valueOf(selId));
						fld_personname.setText(m.getFetch(selUserId).getPersonname());
						fld_username.setText(m.getFetch(selUserId).getUsername());
						fld_password.setText(m.getFetch(selUserId).getUserpassword());

						if (role.equals("Yönetici")) {

							rdbtn_Manager.setSelected(true);

						} else if (role.equals("Garson")) {
							rdbtn_Waiter.setSelected(true);
						} else {
							rdbtn_Chef.setSelected(true);
						}

					} catch (Exception e2) {
						// TODO: handle exception
					}

				}

			}
		});
		contentPane.add(select_staff);

		JLabel lblPersonelSeimi = new JLabel("Personel Seçimi");
		lblPersonelSeimi.setForeground(new Color(255, 255, 255));
		lblPersonelSeimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPersonelSeimi.setBounds(178, 43, 122, 24);
		contentPane.add(lblPersonelSeimi);

		JLabel lblPersonelSeimi_1 = new JLabel("Personel ID:");
		lblPersonelSeimi_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1.setBounds(186, 111, 140, 24);
		contentPane.add(lblPersonelSeimi_1);

		fld_id = new JTextField();
		fld_id.setHorizontalAlignment(SwingConstants.CENTER);
		fld_id.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_id.setEditable(false);
		fld_id.setBackground(new Color(128, 128, 128));
		fld_id.setColumns(10);
		fld_id.setBounds(130, 135, 225, 34);
		contentPane.add(fld_id);

		JLabel lblPersonelSeimi_1_1_1 = new JLabel(" Personel Adı:");
		lblPersonelSeimi_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1.setBounds(180, 183, 134, 24);
		contentPane.add(lblPersonelSeimi_1_1_1);

		fld_personname = new JTextField();
		fld_personname.setHorizontalAlignment(SwingConstants.CENTER);
		fld_personname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_personname.setColumns(10);
		fld_personname.setBackground(new Color(255, 255, 255));
		fld_personname.setBounds(130, 205, 225, 34);
		contentPane.add(fld_personname);

		JLabel lblPersonelSeimi_1_1_1_1_1 = new JLabel(" Kullanıcı Adı:");
		lblPersonelSeimi_1_1_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1_1_1.setBounds(178, 248, 134, 24);
		contentPane.add(lblPersonelSeimi_1_1_1_1_1);

		fld_username = new JTextField();
		fld_username.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_username.setHorizontalAlignment(SwingConstants.CENTER);
		fld_username.setColumns(10);
		fld_username.setBackground(Color.WHITE);
		fld_username.setBounds(130, 271, 225, 34);
		contentPane.add(fld_username);

		JLabel lblPersonelSeimi_1_1_1_1_1_1 = new JLabel(" Şifre:");
		lblPersonelSeimi_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1_1_1_1.setBounds(213, 314, 54, 24);
		contentPane.add(lblPersonelSeimi_1_1_1_1_1_1);

		fld_password = new JTextField();
		fld_password.setHorizontalAlignment(SwingConstants.CENTER);
		fld_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_password.setColumns(10);
		fld_password.setBackground(Color.WHITE);
		fld_password.setBounds(130, 338, 225, 34);
		contentPane.add(fld_password);

		JLabel lblnvan_1 = new JLabel("  Ünvan:");
		lblnvan_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblnvan_1.setForeground(Color.WHITE);
		lblnvan_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblnvan_1.setBounds(164, 387, 152, 34);
		contentPane.add(lblnvan_1);

		rdbtn_Manager = new JRadioButton("Yönetici");
		rdbtn_Manager.setOpaque(false);
		rdbtn_Manager.setForeground(Color.WHITE);
		rdbtn_Manager.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtn_Manager.setBounds(127, 415, 63, 23);
		contentPane.add(rdbtn_Manager);

		rdbtn_Waiter = new JRadioButton("Garson");
		rdbtn_Waiter.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_Waiter.setOpaque(false);
		rdbtn_Waiter.setForeground(Color.WHITE);
		rdbtn_Waiter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtn_Waiter.setBounds(192, 415, 108, 23);
		contentPane.add(rdbtn_Waiter);

		rdbtn_Chef = new JRadioButton("Şef");
		rdbtn_Chef.setOpaque(false);
		rdbtn_Chef.setForeground(Color.WHITE);
		rdbtn_Chef.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtn_Chef.setBounds(304, 415, 51, 23);
		contentPane.add(rdbtn_Chef);

		roleGroup = new ButtonGroup();
		roleGroup.add(rdbtn_Manager);
		roleGroup.add(rdbtn_Waiter);
		roleGroup.add(rdbtn_Chef);

		rdbtn_Manager.setActionCommand(rdbtn_Manager.getText());
		rdbtn_Waiter.setActionCommand(rdbtn_Waiter.getText());
		rdbtn_Chef.setActionCommand(rdbtn_Chef.getText());

		JButton btn_save = new JButton("Güncelle");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (fld_id.getText().length() == 0 || roleGroup.getSelection() == null) {
						Helper.showMsg("Lütfen Personel Seçimi Yapınız!");
					} else {

						String selRole = roleGroup.getSelection().getActionCommand();

						boolean check = manager.updateStaff(Integer.parseInt(fld_id.getText()), fld_username.getText(),
								fld_password.getText(), fld_personname.getText(), selRole);
						if (check) {
							Helper.showMsg("Güncelleme İşlemi Başarılı!");
						} else
							Helper.showMsg("Başarısız!");

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_save.setForeground(Color.BLACK);
		btn_save.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_save.setBounds(164, 445, 152, 34);
		contentPane.add(btn_save);

		JButton btn_deleteStaff = new JButton("<html>Personel <br> Bilgilerini <br> Sil!</html>");
		btn_deleteStaff.addActionListener(new ActionListener() {
			private String selId;

			public void actionPerformed(ActionEvent e) {
				selId = fld_id.getText();
				if (selId.length() == 0) {
					Helper.showMsg("Lütfen Bir Personel Seçimi Yapınız!");
				} else {
					try {
						boolean control = manager.deleteStaff(Integer.parseInt(selId));
						if (control) {
							fld_id.setText(null);
							fld_username.setText(null);
							fld_password.setText(null);
							fld_personname.setText(null);
							roleGroup.clearSelection();
							staffMap.clear();
							selStaff.removeAllElements();

							Helper.showMsg("Personel Başarıyla Silindi!");

							for (int i = 0; i < manager.getStaffList().size(); i++) {
								staffMap.put(manager.getStaffList().get(i).getUser_id(),
										manager.getStaffList().get(i).getPersonname());
							}
							selStaff.addElement("Seçim Yapınız!");

							for (Map.Entry<Integer, String> entry : staffMap.entrySet()) {

								selStaff.addElement(entry.getKey() + " - " + entry.getValue());
							}

						} else
							Helper.showMsg("Personel Silme İşlemi Başarısız!");
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_deleteStaff.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_deleteStaff.setForeground(new Color(255, 255, 255));
		btn_deleteStaff.setBackground(new Color(255, 0, 0));
		btn_deleteStaff.setBounds(397, 495, 114, 84);
		contentPane.add(btn_deleteStaff);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UpdateStaffGUI.class.getResource("/img/background_img.jpg")));
		lblNewLabel_1.setBounds(0, 0, 511, 579);
		contentPane.add(lblNewLabel_1);
	}
}

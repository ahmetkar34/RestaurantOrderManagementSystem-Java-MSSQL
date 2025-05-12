package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Helper.*;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class AddMenuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Manager manager = new Manager();
	private JPanel contentPane;
	private Point initialClick;
	private JTextField fld_itemName;
	private JComboBox select_itemtype;
	private JTable table_menu;
	private DefaultTableModel menuModel = null;
	private Object[] menuData = null;
	private ButtonGroup categoryGroup;
	private JTextField fld_itemPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMenuGUI frame = new AddMenuGUI(manager);
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
	public AddMenuGUI(Manager manager) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 592);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		menuModel = new DefaultTableModel();
		Object[] colMenu = new Object[4];
		colMenu[0] = "Id";
		colMenu[1] = "Name";
		colMenu[2] = "Price";
		colMenu[3] = "Type";
		menuModel.setColumnIdentifiers(colMenu);
		menuData = new Object[4];
		for (int i = 0; i < manager.getMenuList().size(); i++) {
			menuData[0] = manager.getMenuList().get(i).getItem_id();
			menuData[1] = manager.getMenuList().get(i).getItem_name();
			menuData[2] = manager.getMenuList().get(i).getItem_price();
			menuData[3] = manager.getMenuList().get(i).getItem_category();
			
			
			menuModel.addRow(menuData);
		}

		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(AddMenuGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(820, 0, 50, 50);
		contentPane.add(btn_exit);

		JLabel lblMenEkle = new JLabel("Menü Ekle");
		lblMenEkle.setForeground(Color.WHITE);
		lblMenEkle.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblMenEkle.setBounds(392, 0, 85, 36);
		contentPane.add(lblMenEkle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 129, 323, 312);
		contentPane.add(scrollPane);

		table_menu = new JTable(menuModel);
		scrollPane.setViewportView(table_menu);

		JLabel lblPersonelSeimi = new JLabel("Ürün Adı:");
		lblPersonelSeimi.setForeground(Color.WHITE);
		lblPersonelSeimi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi.setBounds(594, 129, 85, 24);
		contentPane.add(lblPersonelSeimi);

		JLabel lblPersonelSeimi_1_1_1_1 = new JLabel("Ürün Türü:");
		lblPersonelSeimi_1_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1_1.setBounds(590, 304, 100, 36);
		contentPane.add(lblPersonelSeimi_1_1_1_1);

		JButton btn_save = new JButton("Ekle");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selItemName = fld_itemName.getText();
				String selItemType = select_itemtype.getSelectedItem().toString();
				Integer selItemPrice = Integer.parseInt(fld_itemPrice.getText());
				if (fld_itemName.getText().length() == 0 || select_itemtype.getSelectedIndex() == 0) {
					Helper.showMsg("Lütfen Boş Alanları Doldurunuz!");
				} else {

					try {
						boolean control = manager.addMenu(selItemName, selItemType,selItemPrice);
						if (control) {
							Helper.showMsg("Başarıyla Eklendi!");
							fld_itemName.setText(null);
							fld_itemPrice.setText(null);
							select_itemtype.setSelectedIndex(0);
							categoryGroup.clearSelection();
							DefaultTableModel clearModel = (DefaultTableModel) table_menu.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < manager.getMenuList().size(); i++) {
								menuData[0] = manager.getMenuList().get(i).getItem_id();
								menuData[1] = manager.getMenuList().get(i).getItem_name();
								menuData[2] = manager.getMenuList().get(i).getItem_price();
								menuData[3] = manager.getMenuList().get(i).getItem_category();
								menuModel.addRow(menuData);
							}

						} else
							Helper.showMsg("Bir Hata Oluştu!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_save.setForeground(Color.BLACK);
		btn_save.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_save.setBounds(567, 407, 152, 34);
		contentPane.add(btn_save);

		String[] items = { "Seçim Yapınız...", "Yemek", "İçecek", "Tatlı" };
		select_itemtype = new JComboBox(items);
		select_itemtype.setFont(new Font("Tahoma", Font.BOLD, 18));
		select_itemtype.setBounds(528, 333, 225, 34);
		contentPane.add(select_itemtype);

		fld_itemName = new JTextField();
		fld_itemName.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_itemName.setBackground(new Color(255, 255, 255));
		fld_itemName.setBounds(528, 154, 225, 32);
		contentPane.add(fld_itemName);
		fld_itemName.setColumns(10);

		JLabel lblFilterByCategory = new JLabel("Filter By Category:");
		lblFilterByCategory.setForeground(Color.WHITE);
		lblFilterByCategory.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFilterByCategory.setBounds(84, 74, 188, 24);
		contentPane.add(lblFilterByCategory);

		JRadioButton rdbtn_yemek = new JRadioButton("Yemek");
		rdbtn_yemek.setForeground(new Color(255, 255, 255));
		rdbtn_yemek.setBounds(56, 95, 77, 23);
		rdbtn_yemek.setOpaque(false);
		contentPane.add(rdbtn_yemek);

		JRadioButton rdbtn_tatli = new JRadioButton("Tatlı");
		rdbtn_tatli.setOpaque(false);
		rdbtn_tatli.setForeground(Color.WHITE);
		rdbtn_tatli.setBounds(135, 95, 57, 23);
		contentPane.add(rdbtn_tatli);

		JRadioButton rdbtn_içecek = new JRadioButton("İçecek");
		rdbtn_içecek.setOpaque(false);
		rdbtn_içecek.setForeground(Color.WHITE);
		rdbtn_içecek.setBounds(202, 95, 85, 23);
		contentPane.add(rdbtn_içecek);

		categoryGroup = new ButtonGroup();
		categoryGroup.add(rdbtn_yemek);
		categoryGroup.add(rdbtn_tatli);
		categoryGroup.add(rdbtn_içecek);

		ActionListener categoryListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JRadioButton selectedButton = (JRadioButton) e.getSource();
				DefaultTableModel clearModel = (DefaultTableModel) table_menu.getModel();
				clearModel.setRowCount(0);
				try {
					for (int i = 0; i < manager.getMenuListbyCategory(selectedButton.getText()).size(); i++) {
						menuData[0] = manager.getMenuListbyCategory(selectedButton.getText()).get(i).getItem_id();
						menuData[1] = manager.getMenuListbyCategory(selectedButton.getText()).get(i).getItem_name();
						menuData[2] = manager.getMenuListbyCategory(selectedButton.getText()).get(i).getItem_price();
						menuData[3] = manager.getMenuListbyCategory(selectedButton.getText()).get(i).getItem_category();
						
						menuModel.addRow(menuData);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};

		rdbtn_yemek.addActionListener(categoryListener);
		rdbtn_tatli.addActionListener(categoryListener);
		rdbtn_içecek.addActionListener(categoryListener);

		JButton btnNewButton = new JButton("<html>Restore <br> Default </html>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryGroup.clearSelection();
				DefaultTableModel clearModel = (DefaultTableModel) table_menu.getModel();
				clearModel.setRowCount(0);
				try {
					for (int i = 0; i < manager.getMenuList().size(); i++) {
						menuData[0] = manager.getMenuList().get(i).getItem_id();
						menuData[1] = manager.getMenuList().get(i).getItem_name();
						menuData[2] = manager.getMenuList().get(i).getItem_price();
						menuData[3] = manager.getMenuList().get(i).getItem_category();
						menuModel.addRow(menuData);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(293, 82, 77, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblrnFiyat = new JLabel("Ürün Fiyat:");
		lblrnFiyat.setForeground(Color.WHITE);
		lblrnFiyat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblrnFiyat.setBounds(590, 218, 114, 24);
		contentPane.add(lblrnFiyat);
		
		fld_itemPrice = new JTextField();
		fld_itemPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_itemPrice.setColumns(10);
		fld_itemPrice.setBackground(Color.WHITE);
		fld_itemPrice.setBounds(528, 243, 225, 32);
		contentPane.add(fld_itemPrice);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddMenuGUI.class.getResource("/img/background_img.jpg")));
		lblNewLabel.setBounds(0, 0, 877, 592);
		contentPane.add(lblNewLabel);
	}
}

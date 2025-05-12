package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//import org.bouncycastle.pqc.legacy.math.linearalgebra.IntegerFunctions;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class UpdateTabGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Waiter waiter = new Waiter();
	private Menu menu = new Menu();
	private JPanel contentPane;
	private JTextField fld_quantity_1;
	private JTextField fld_quantity_2;
	private JTextField fld_quantity_3;
	private JTextField fld_quantity_4;
	private JTextField fld_quantity_5;
	private JTextField fld_quantity_6;
	private JTextField fld_quantity_7;
	private JTextField fld_quantity_8;
	private JTextField fld_quantity_9;
	private JTextField fld_totalAmount;
	private JLabel lbl_billid;
	private JComboBox<String> select_Item_1 = new JComboBox<>();
	private JComboBox<String> select_Item_2 = new JComboBox<>();
	private JComboBox<String> select_Item_3 = new JComboBox<>();
	private JComboBox<String> select_Item_4 = new JComboBox<>();
	private JComboBox<String> select_Item_5 = new JComboBox<>();
	private JComboBox<String> select_Item_6 = new JComboBox<>();
	private JComboBox<String> select_Item_7 = new JComboBox<>();
	private JComboBox<String> select_Item_8 = new JComboBox<>();
	private JComboBox<String> select_Item_9 = new JComboBox<>();
	private JComboBox<String> select_table = new JComboBox<>();
	private JComboBox<String>[] itemComboBoxes;
	private JTextField[] quantityFields;
	private HashMap<Integer, String> itemMap = new HashMap<Integer, String>();
	private List<Menu> menuList = menu.getMenuList();
	private HashMap<Integer, String> tableMap = new HashMap<Integer, String>();
	private List<Table> tableList = waiter.getOccupiedTableList();
	private HashMap<Integer, Integer> priceMap = new HashMap<Integer, Integer>();
	private JLabel[] priceLabels;
	private JButton[] addButtons;
	private JButton[] removeButtons;
	private List<Integer> itemIds = new ArrayList<>();
	private List<Integer> quantities = new ArrayList<>();
	private Point initialClick;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateTabGUI frame = new UpdateTabGUI();
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
	public UpdateTabGUI() throws SQLException {
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int i = 0; i < menuList.size(); i++) {
			itemMap.put(menuList.get(i).getItem_id(), menuList.get(i).getItem_name());

		}

		for (int i = 0; i < tableList.size(); i++) {
			tableMap.put(tableList.get(i).getTable_id(), tableList.get(i).getTable_name());
		}

		for (int i = 0; i < menuList.size(); i++) {
			priceMap.put(menuList.get(i).getItem_id(), menuList.get(i).getItem_price());

		}

		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(400, 0, 50, 50);
		contentPane.add(btn_exit);

		JLabel lblrnSeimi = new JLabel("Ürün Seçimi:");
		lblrnSeimi.setForeground(Color.WHITE);
		lblrnSeimi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblrnSeimi.setBounds(67, 127, 116, 24);
		contentPane.add(lblrnSeimi);

		JLabel lblBFiyat_1 = new JLabel("B.Fiyat");
		lblBFiyat_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBFiyat_1.setForeground(Color.WHITE);
		lblBFiyat_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBFiyat_1.setBounds(234, 113, 60, 56);
		contentPane.add(lblBFiyat_1);

		JLabel lblAdet = new JLabel("Adet");
		lblAdet.setForeground(Color.WHITE);
		lblAdet.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdet.setBounds(303, 127, 50, 24);
		contentPane.add(lblAdet);

		select_Item_1 = new JComboBox();
		select_Item_1.setBounds(22, 157, 202, 38);
		contentPane.add(select_Item_1);

		select_Item_2 = new JComboBox();
		select_Item_2.setBounds(22, 206, 202, 38);
		contentPane.add(select_Item_2);

		select_Item_3 = new JComboBox();
		select_Item_3.setBounds(22, 255, 202, 38);
		contentPane.add(select_Item_3);

		select_Item_4 = new JComboBox();
		select_Item_4.setBounds(22, 304, 202, 38);
		contentPane.add(select_Item_4);

		select_Item_5 = new JComboBox();
		select_Item_5.setBounds(22, 353, 202, 38);
		contentPane.add(select_Item_5);

		select_Item_6 = new JComboBox();
		select_Item_6.setBounds(22, 402, 202, 38);
		contentPane.add(select_Item_6);

		select_Item_7 = new JComboBox();
		select_Item_7.setBounds(22, 451, 202, 38);
		contentPane.add(select_Item_7);

		select_Item_8 = new JComboBox();
		select_Item_8.setBounds(22, 500, 202, 38);
		contentPane.add(select_Item_8);

		select_Item_9 = new JComboBox();
		select_Item_9.setBounds(22, 549, 202, 38);
		contentPane.add(select_Item_9);

		JLabel lbl_price_1 = new JLabel("  0.00 ₺");
		lbl_price_1.setForeground(Color.WHITE);
		lbl_price_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_1.setBackground(Color.WHITE);
		lbl_price_1.setBounds(243, 157, 41, 38);
		contentPane.add(lbl_price_1);

		JLabel lbl_price_2 = new JLabel("  0.00 ₺");
		lbl_price_2.setForeground(Color.WHITE);
		lbl_price_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_2.setBackground(Color.WHITE);
		lbl_price_2.setBounds(242, 206, 41, 38);
		contentPane.add(lbl_price_2);

		JLabel lbl_price_3 = new JLabel("  0.00 ₺");
		lbl_price_3.setForeground(Color.WHITE);
		lbl_price_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_3.setBackground(Color.WHITE);
		lbl_price_3.setBounds(242, 255, 41, 38);
		contentPane.add(lbl_price_3);

		JLabel lbl_price_4 = new JLabel("  0.00 ₺");
		lbl_price_4.setForeground(Color.WHITE);
		lbl_price_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_4.setBackground(Color.WHITE);
		lbl_price_4.setBounds(242, 304, 41, 38);
		contentPane.add(lbl_price_4);

		JLabel lbl_price_5 = new JLabel("  0.00 ₺");
		lbl_price_5.setForeground(Color.WHITE);
		lbl_price_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_5.setBackground(Color.WHITE);
		lbl_price_5.setBounds(242, 353, 41, 38);
		contentPane.add(lbl_price_5);

		JLabel lbl_price_6 = new JLabel("  0.00 ₺");
		lbl_price_6.setForeground(Color.WHITE);
		lbl_price_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_6.setBackground(Color.WHITE);
		lbl_price_6.setBounds(242, 402, 41, 38);
		contentPane.add(lbl_price_6);

		JLabel lbl_price_7 = new JLabel("  0.00 ₺");
		lbl_price_7.setForeground(Color.WHITE);
		lbl_price_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_7.setBackground(Color.WHITE);
		lbl_price_7.setBounds(242, 451, 41, 38);
		contentPane.add(lbl_price_7);

		JLabel lbl_price_8 = new JLabel("  0.00 ₺");
		lbl_price_8.setForeground(Color.WHITE);
		lbl_price_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_8.setBackground(Color.WHITE);
		lbl_price_8.setBounds(242, 500, 41, 38);
		contentPane.add(lbl_price_8);

		JLabel lbl_price_9 = new JLabel("  0.00 ₺");
		lbl_price_9.setForeground(Color.WHITE);
		lbl_price_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_9.setBackground(Color.WHITE);
		lbl_price_9.setBounds(242, 549, 41, 38);
		contentPane.add(lbl_price_9);

		fld_quantity_1 = new JTextField();
		fld_quantity_1.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_1.setColumns(10);
		fld_quantity_1.setBounds(302, 157, 41, 38);
		contentPane.add(fld_quantity_1);

		fld_quantity_2 = new JTextField();
		fld_quantity_2.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_2.setColumns(10);
		fld_quantity_2.setBounds(303, 206, 41, 38);
		contentPane.add(fld_quantity_2);

		fld_quantity_3 = new JTextField();
		fld_quantity_3.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_3.setColumns(10);
		fld_quantity_3.setBounds(303, 255, 41, 38);
		contentPane.add(fld_quantity_3);

		fld_quantity_4 = new JTextField();
		fld_quantity_4.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_4.setColumns(10);
		fld_quantity_4.setBounds(303, 304, 41, 38);
		contentPane.add(fld_quantity_4);

		fld_quantity_5 = new JTextField();
		fld_quantity_5.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_5.setColumns(10);
		fld_quantity_5.setBounds(303, 353, 41, 38);
		contentPane.add(fld_quantity_5);

		fld_quantity_6 = new JTextField();
		fld_quantity_6.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_6.setColumns(10);
		fld_quantity_6.setBounds(303, 402, 41, 38);
		contentPane.add(fld_quantity_6);

		fld_quantity_7 = new JTextField();
		fld_quantity_7.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_7.setColumns(10);
		fld_quantity_7.setBounds(303, 451, 41, 38);
		contentPane.add(fld_quantity_7);

		fld_quantity_8 = new JTextField();
		fld_quantity_8.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_8.setColumns(10);
		fld_quantity_8.setBounds(303, 500, 41, 38);
		contentPane.add(fld_quantity_8);

		fld_quantity_9 = new JTextField();
		fld_quantity_9.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_9.setColumns(10);
		fld_quantity_9.setBounds(303, 549, 41, 38);
		contentPane.add(fld_quantity_9);

		JButton btn_addItem_1 = new JButton("");
		btn_addItem_1.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_1.setOpaque(false);
		btn_addItem_1.setContentAreaFilled(false);
		btn_addItem_1.setBorderPainted(false);
		btn_addItem_1.setBounds(353, 157, 41, 38);
		contentPane.add(btn_addItem_1);

		JButton btn_addItem_2 = new JButton("");
		btn_addItem_2.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_2.setOpaque(false);
		btn_addItem_2.setContentAreaFilled(false);
		btn_addItem_2.setBorderPainted(false);
		btn_addItem_2.setBounds(353, 206, 41, 38);
		contentPane.add(btn_addItem_2);

		JButton btn_addItem_3 = new JButton("");
		btn_addItem_3.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_3.setOpaque(false);
		btn_addItem_3.setContentAreaFilled(false);
		btn_addItem_3.setBorderPainted(false);
		btn_addItem_3.setBounds(353, 255, 41, 38);
		contentPane.add(btn_addItem_3);

		JButton btn_addItem_4 = new JButton("");
		btn_addItem_4.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_4.setOpaque(false);
		btn_addItem_4.setContentAreaFilled(false);
		btn_addItem_4.setBorderPainted(false);
		btn_addItem_4.setBounds(353, 304, 41, 38);
		contentPane.add(btn_addItem_4);

		JButton btn_addItem_5 = new JButton("");
		btn_addItem_5.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_5.setOpaque(false);
		btn_addItem_5.setContentAreaFilled(false);
		btn_addItem_5.setBorderPainted(false);
		btn_addItem_5.setBounds(353, 353, 41, 38);
		contentPane.add(btn_addItem_5);

		JButton btn_addItem_6 = new JButton("");
		btn_addItem_6.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_6.setOpaque(false);
		btn_addItem_6.setContentAreaFilled(false);
		btn_addItem_6.setBorderPainted(false);
		btn_addItem_6.setBounds(353, 402, 41, 38);
		contentPane.add(btn_addItem_6);

		JButton btn_addItem_7 = new JButton("");
		btn_addItem_7.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_7.setOpaque(false);
		btn_addItem_7.setContentAreaFilled(false);
		btn_addItem_7.setBorderPainted(false);
		btn_addItem_7.setBounds(353, 451, 41, 38);
		contentPane.add(btn_addItem_7);

		JButton btn_addItem_8 = new JButton("");
		btn_addItem_8.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_8.setOpaque(false);
		btn_addItem_8.setContentAreaFilled(false);
		btn_addItem_8.setBorderPainted(false);
		btn_addItem_8.setBounds(353, 500, 41, 38);
		contentPane.add(btn_addItem_8);

		JButton btn_addItem_9 = new JButton("");
		btn_addItem_9.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_9.setOpaque(false);
		btn_addItem_9.setContentAreaFilled(false);
		btn_addItem_9.setBorderPainted(false);
		btn_addItem_9.setBounds(353, 549, 41, 38);
		contentPane.add(btn_addItem_9);

		JButton btn_removeItem_1 = new JButton("");
		btn_removeItem_1.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_1.setOpaque(false);
		btn_removeItem_1.setContentAreaFilled(false);
		btn_removeItem_1.setBorderPainted(false);
		btn_removeItem_1.setBounds(399, 157, 41, 38);
		contentPane.add(btn_removeItem_1);

		JButton btn_removeItem_2 = new JButton("");
		btn_removeItem_2.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_2.setOpaque(false);
		btn_removeItem_2.setContentAreaFilled(false);
		btn_removeItem_2.setBorderPainted(false);
		btn_removeItem_2.setBounds(399, 206, 41, 38);
		contentPane.add(btn_removeItem_2);

		JButton btn_removeItem_3 = new JButton("");
		btn_removeItem_3.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_3.setOpaque(false);
		btn_removeItem_3.setContentAreaFilled(false);
		btn_removeItem_3.setBorderPainted(false);
		btn_removeItem_3.setBounds(399, 255, 41, 38);
		contentPane.add(btn_removeItem_3);

		JButton btn_removeItem_4 = new JButton("");
		btn_removeItem_4.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_4.setOpaque(false);
		btn_removeItem_4.setContentAreaFilled(false);
		btn_removeItem_4.setBorderPainted(false);
		btn_removeItem_4.setBounds(399, 304, 41, 38);
		contentPane.add(btn_removeItem_4);

		JButton btn_removeItem_5 = new JButton("");
		btn_removeItem_5.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_5.setOpaque(false);
		btn_removeItem_5.setContentAreaFilled(false);
		btn_removeItem_5.setBorderPainted(false);
		btn_removeItem_5.setBounds(399, 353, 41, 38);
		contentPane.add(btn_removeItem_5);

		JButton btn_removeItem_6 = new JButton("");
		btn_removeItem_6.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_6.setOpaque(false);
		btn_removeItem_6.setContentAreaFilled(false);
		btn_removeItem_6.setBorderPainted(false);
		btn_removeItem_6.setBounds(399, 402, 41, 38);
		contentPane.add(btn_removeItem_6);

		JButton btn_removeItem_7 = new JButton("");
		btn_removeItem_7.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_7.setOpaque(false);
		btn_removeItem_7.setContentAreaFilled(false);
		btn_removeItem_7.setBorderPainted(false);
		btn_removeItem_7.setBounds(399, 451, 41, 38);
		contentPane.add(btn_removeItem_7);

		JButton btn_removeItem_8 = new JButton("");
		btn_removeItem_8.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_8.setOpaque(false);
		btn_removeItem_8.setContentAreaFilled(false);
		btn_removeItem_8.setBorderPainted(false);
		btn_removeItem_8.setBounds(399, 500, 41, 38);
		contentPane.add(btn_removeItem_8);

		JButton btn_removeItem_9 = new JButton("");
		btn_removeItem_9.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_9.setOpaque(false);
		btn_removeItem_9.setContentAreaFilled(false);
		btn_removeItem_9.setBorderPainted(false);
		btn_removeItem_9.setBounds(399, 549, 41, 38);
		contentPane.add(btn_removeItem_9);

		JLabel lblToplamFiyat = new JLabel("Toplam Fiyat:");
		lblToplamFiyat.setHorizontalAlignment(SwingConstants.CENTER);
		lblToplamFiyat.setForeground(Color.WHITE);
		lblToplamFiyat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblToplamFiyat.setBounds(155, 613, 137, 24);
		contentPane.add(lblToplamFiyat);

		fld_totalAmount = new JTextField();
		fld_totalAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		fld_totalAmount.setText("0.00 ₺");
		fld_totalAmount.setHorizontalAlignment(SwingConstants.CENTER);
		fld_totalAmount.setEditable(false);
		fld_totalAmount.setColumns(10);
		fld_totalAmount.setBackground(Color.LIGHT_GRAY);
		fld_totalAmount.setBounds(303, 616, 86, 24);
		contentPane.add(fld_totalAmount);

		JButton btn_save = new JButton("Adisyonu Güncelle");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bills tab = new Bills();
				itemIds.clear();
				quantities.clear();
				processSelections();
				String billText = lbl_billid.getText().replace("Bill ID: ", "");
				int tAmount = Integer.parseInt(fld_totalAmount.getText().replace("₺", "").trim());

				try {

					boolean chkStatus = tab.updateBill(billText, tAmount, itemIds, quantities);
					if (chkStatus) {
						Helper.showMsg("Güncelleme İşlemi Başarılı!");
						select_table.setSelectedIndex(0);

					}

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btn_save.setBounds(135, 651, 148, 38);
		contentPane.add(btn_save);

		JSeparator separator = new JSeparator();
		separator.setBounds(22, 118, 407, 9);
		contentPane.add(separator);

		JLabel lblMasaSeimi = new JLabel("Masa Seçimi:");
		lblMasaSeimi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasaSeimi.setForeground(Color.WHITE);
		lblMasaSeimi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMasaSeimi.setBounds(59, 46, 124, 35);
		contentPane.add(lblMasaSeimi);

		itemComboBoxes = new JComboBox[] { select_Item_1, select_Item_2, select_Item_3, select_Item_4, select_Item_5,
				select_Item_6, select_Item_7, select_Item_8, select_Item_9 };
		quantityFields = new JTextField[] { fld_quantity_1, fld_quantity_2, fld_quantity_3, fld_quantity_4,
				fld_quantity_5, fld_quantity_6, fld_quantity_7, fld_quantity_8, fld_quantity_9 };
		priceLabels = new JLabel[] { lbl_price_1, lbl_price_2, lbl_price_3, lbl_price_4, lbl_price_5, lbl_price_6,
				lbl_price_7, lbl_price_8, lbl_price_9 };

		addButtons = new JButton[] { btn_addItem_1, btn_addItem_2, btn_addItem_3, btn_addItem_4, btn_addItem_5,
				btn_addItem_6, btn_addItem_7, btn_addItem_8, btn_addItem_9 };

		removeButtons = new JButton[] { btn_removeItem_1, btn_removeItem_2, btn_removeItem_3, btn_removeItem_4,
				btn_removeItem_5, btn_removeItem_6, btn_removeItem_7, btn_removeItem_8, btn_removeItem_9 };

		select_table = new JComboBox();
		select_table.setBounds(190, 46, 200, 35);
		contentPane.add(select_table);
		select_table.addItem("Seçim Yapınız");
		for (String itemName : tableMap.values()) {
			select_table.addItem(itemName);
		}
		select_table.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {

					if (select_table.getSelectedIndex() == 0) {
						lbl_billid.setText("");
						fld_totalAmount.setText("0.00 ₺");
						for (JComboBox<String> comboBox : itemComboBoxes) {
							comboBox.removeAllItems();
						}
						for (JTextField field : quantityFields) {
							field.setText("");
						}
					} else {

						String selected = (String) select_table.getSelectedItem();
						Integer selectedId = tableMap.entrySet().stream()
								.filter(entry -> entry.getValue().equals(selected)).map(Map.Entry::getKey).findFirst()
								.orElse(null);

						Bills bill = Bills.getBillByTableId(selectedId);

						for (JComboBox<String> comboBox : itemComboBoxes) {
							comboBox.removeAllItems();
						}
						for (JTextField field : quantityFields) {
							field.setText("");
						}

						if (bill != null) {

							fld_totalAmount.setText(String.valueOf(bill.getTotal_amount()) + " " + "₺");
							lbl_billid.setText("Bill ID: " + bill.getBill_id());

							for (int i = 0; i < itemComboBoxes.length; i++) {
								final int index = i;

								itemComboBoxes[i].removeAllItems();
								itemComboBoxes[i].addItem("Ürün Ekleyebilirsiniz.");

								for (Integer itemId : itemMap.keySet()) {
									itemComboBoxes[i]
											.addItem(itemId + " - " + itemMap.getOrDefault(itemId, "Bilinmiyor"));
								}

								if (i < bill.getItems().size()) {
									int selectedItemId = bill.getItems().get(i).getItem_id();
									String selectedItemString = selectedItemId + " - "
											+ itemMap.getOrDefault(selectedItemId, "Bilinmiyor");
									itemComboBoxes[i].setSelectedItem(selectedItemString);
									quantityFields[i].setText(String.valueOf(bill.getItems().get(i).getQty()));
								}

								updatePriceLabel(index);
								itemComboBoxes[i].addActionListener(a -> updatePriceLabel(index));
							}

							SwingUtilities.invokeLater(() -> calculateTotalAmount());

						}

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		for (JTextField quantityField : quantityFields) {
			quantityField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					calculateTotalAmount();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					calculateTotalAmount();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					calculateTotalAmount();
				}
			});
		}

		for (int i = 0; i < quantityFields.length; i++) {
			final int index = i;

			addButtons[i].addActionListener(e -> {
				String text = quantityFields[index].getText().trim();
				int value = text.matches("\\d+") ? Integer.parseInt(text) : 0;
				quantityFields[index].setText(String.valueOf(value + 1));

			});

			removeButtons[i].addActionListener(e -> {
				String text = quantityFields[index].getText().trim();
				int value = text.matches("\\d+") ? Integer.parseInt(text) : 0;
				quantityFields[index].setText(String.valueOf(Math.max(value - 1, 0)));

			});
		}
      
		for (int i = 0; i < itemComboBoxes.length; i++) {
			final int index = i;

			itemComboBoxes[i].addActionListener(e -> {
				String selectedText = (String) itemComboBoxes[index].getSelectedItem();

				if (selectedText != null && selectedText.equals("Ürün Ekleyebilirsiniz.")) {
					quantityFields[index].setText(""); // Boş bırak
				}
			});
		}

		JLabel lblAdisyonGncelle = new JLabel("Adisyon Güncelle");
		lblAdisyonGncelle.setForeground(Color.WHITE);
		lblAdisyonGncelle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdisyonGncelle.setBounds(155, 11, 235, 24);
		contentPane.add(lblAdisyonGncelle);

		lbl_billid = new JLabel("");
		lbl_billid.setForeground(new Color(255, 255, 255));
		lbl_billid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_billid.setBounds(67, 102, 343, 14);
		contentPane.add(lbl_billid);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UpdateTabGUI.class.getResource("/img/background_formatted.jpg")));
		lblNewLabel.setBounds(0, 0, 450, 700);
		contentPane.add(lblNewLabel);

	}

	private void updatePriceLabel(int index) {
		String selectedItem = (String) itemComboBoxes[index].getSelectedItem();
		if (selectedItem != null && selectedItem.contains(" - ")) {
			int productId = Integer.parseInt(selectedItem.split(" - ")[0].trim());
			int price = priceMap.getOrDefault(productId, 0);
			priceLabels[index].setText("  " + price + " ₺");
		} else {
			priceLabels[index].setText("  0.00 ₺");
		}
	}

	private void calculateTotalAmount() {
		int total = 0;

		for (int i = 0; i < priceLabels.length; i++) {

			String priceText = priceLabels[i].getText().replace("₺", "").trim();
			String quantityText = quantityFields[i].getText().trim();

			if (!priceText.isEmpty() && !quantityText.isEmpty()) {
				try {
					int price = Integer.parseInt(priceText);
					int quantity = Integer.parseInt(quantityText);
					total += price * quantity;
				} catch (NumberFormatException e) {

				}
			}
		}

		fld_totalAmount.setText(total + " ₺");

	}

	private void processSelections() {

		for (int i = 0; i < itemComboBoxes.length; i++) {
			String selectedText = (String) itemComboBoxes[i].getSelectedItem();
			String quantityText = quantityFields[i].getText().trim();

			if (!selectedText.equals("Ürün Ekleyebilirsiniz.") && !quantityText.isEmpty()) {
				try {
					String[] parts = selectedText.split(" - ");
					int itemId = Integer.parseInt(parts[0].trim());
					int quantity = Integer.parseInt(quantityText.trim());

					itemIds.add(itemId);
					quantities.add(quantity);
				} catch (NumberFormatException e) {

				}
			} else {
				System.out.println("❌ Geçersiz giriş -> Atlandı!");
			}
		}

	}
}

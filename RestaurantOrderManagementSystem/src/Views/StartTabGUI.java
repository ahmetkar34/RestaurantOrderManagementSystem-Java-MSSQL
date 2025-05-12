package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.*;
import Helper.*;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.UUID;
import javax.swing.JSeparator;

public class StartTabGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Waiter waiter = new Waiter();
	private Menu menu = new Menu();
	private Table table = new Table();
	private Bills bill = new Bills();
	private final String billUUID;
	private JPanel contentPane;
	private Point initialClick;
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
	private JTextField fld_waiter;
	private JComboBox<String> select_Item_1 = new JComboBox<>();
	private JComboBox<String> select_Item_2 = new JComboBox<>();
	private JComboBox<String> select_Item_3 = new JComboBox<>();
	private JComboBox<String> select_Item_4 = new JComboBox<>();
	private JComboBox<String> select_Item_5 = new JComboBox<>();
	private JComboBox<String> select_Item_6 = new JComboBox<>();
	private JComboBox<String> select_Item_7 = new JComboBox<>();
	private JComboBox<String> select_Item_8 = new JComboBox<>();
	private JComboBox<String> select_Item_9 = new JComboBox<>();
	private JComboBox<String> select_table;
	private HashMap<Integer, String> itemMap = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> priceMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> tableMap = new HashMap<Integer, String>();
	private List<Menu> menuList = menu.getMenuList();
	private List<Table> tableList = waiter.getAvailableTableList();
	private List<Integer> itemIds = new ArrayList<>();
	private List<Integer> quantities = new ArrayList<>();
	private JComboBox<String>[] itemComboBoxes;
	private JLabel[] priceLabels;
	private JTextField[] quantityFields;
	private JButton[] addButtons;
	private JButton[] removeButtons;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartTabGUI frame = new StartTabGUI(waiter);
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
	public StartTabGUI(Waiter waiter) throws SQLException {
		billUUID = UUID.randomUUID().toString();
		System.out.println(billUUID);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int i = 0; i < menuList.size(); i++) {
			itemMap.put(menuList.get(i).getItem_id(), menuList.get(i).getItem_name());

		}

		for (int i = 0; i < menuList.size(); i++) {
			priceMap.put(menuList.get(i).getItem_id(), menuList.get(i).getItem_price());

		}

		for (int i = 0; i < tableList.size(); i++) {
			tableMap.put(tableList.get(i).getTable_id(), tableList.get(i).getTable_name());
		}

		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(400, 0, 50, 50);
		contentPane.add(btn_exit);

		select_Item_1 = new JComboBox();
		select_Item_1.setBounds(22, 67, 202, 38);
		contentPane.add(select_Item_1);

		fld_quantity_1 = new JTextField();
		fld_quantity_1.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_1.setBounds(302, 67, 41, 38);
		contentPane.add(fld_quantity_1);
		fld_quantity_1.setColumns(10);

		JLabel lblrnSeimi = new JLabel("Ürün Seçimi:");
		lblrnSeimi.setForeground(Color.WHITE);
		lblrnSeimi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblrnSeimi.setBounds(67, 37, 116, 24);
		contentPane.add(lblrnSeimi);

		JLabel lblAdet = new JLabel("Adet");
		lblAdet.setForeground(Color.WHITE);
		lblAdet.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdet.setBounds(303, 37, 50, 24);
		contentPane.add(lblAdet);

		JButton btn_addItem_1 = new JButton("");
		btn_addItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_addItem_1.setOpaque(false);
		btn_addItem_1.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_1.setBounds(353, 67, 41, 38);
		btn_addItem_1.setContentAreaFilled(false);
		btn_addItem_1.setBorderPainted(false);
		contentPane.add(btn_addItem_1);

		JButton btn_removeItem_1 = new JButton("");
		btn_removeItem_1.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_1.setBounds(399, 67, 41, 38);
		btn_removeItem_1.setOpaque(false);
		btn_removeItem_1.setContentAreaFilled(false);
		btn_removeItem_1.setBorderPainted(false);
		contentPane.add(btn_removeItem_1);

		select_Item_2 = new JComboBox();
		select_Item_2.setBounds(22, 116, 202, 38);
		contentPane.add(select_Item_2);

		fld_quantity_2 = new JTextField();
		fld_quantity_2.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_2.setColumns(10);
		fld_quantity_2.setBounds(303, 116, 41, 38);
		contentPane.add(fld_quantity_2);

		JButton btn_addItem_2 = new JButton("");
		btn_addItem_2.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_2.setOpaque(false);
		btn_addItem_2.setContentAreaFilled(false);
		btn_addItem_2.setBorderPainted(false);
		btn_addItem_2.setBounds(353, 116, 41, 38);
		contentPane.add(btn_addItem_2);

		JButton btn_removeItem_2 = new JButton("");
		btn_removeItem_2.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_2.setOpaque(false);
		btn_removeItem_2.setContentAreaFilled(false);
		btn_removeItem_2.setBorderPainted(false);
		btn_removeItem_2.setBounds(399, 116, 41, 38);
		contentPane.add(btn_removeItem_2);

		select_Item_3 = new JComboBox();
		select_Item_3.setBounds(22, 165, 202, 38);
		contentPane.add(select_Item_3);

		fld_quantity_3 = new JTextField();
		fld_quantity_3.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_3.setColumns(10);
		fld_quantity_3.setBounds(303, 165, 41, 38);
		contentPane.add(fld_quantity_3);

		JButton btn_addItem_3 = new JButton("");
		btn_addItem_3.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_3.setOpaque(false);
		btn_addItem_3.setContentAreaFilled(false);
		btn_addItem_3.setBorderPainted(false);
		btn_addItem_3.setBounds(353, 165, 41, 38);
		contentPane.add(btn_addItem_3);

		JButton btn_removeItem_3 = new JButton("");
		btn_removeItem_3.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_3.setOpaque(false);
		btn_removeItem_3.setContentAreaFilled(false);
		btn_removeItem_3.setBorderPainted(false);
		btn_removeItem_3.setBounds(399, 165, 41, 38);
		contentPane.add(btn_removeItem_3);

		select_Item_4 = new JComboBox();
		select_Item_4.setBounds(22, 214, 202, 38);
		contentPane.add(select_Item_4);

		fld_quantity_4 = new JTextField();
		fld_quantity_4.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_4.setColumns(10);
		fld_quantity_4.setBounds(303, 214, 41, 38);
		contentPane.add(fld_quantity_4);

		JButton btn_addItem_4 = new JButton("");
		btn_addItem_4.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_4.setOpaque(false);
		btn_addItem_4.setContentAreaFilled(false);
		btn_addItem_4.setBorderPainted(false);
		btn_addItem_4.setBounds(353, 214, 41, 38);
		contentPane.add(btn_addItem_4);

		JButton btn_removeItem_4 = new JButton("");
		btn_removeItem_4.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_4.setOpaque(false);
		btn_removeItem_4.setContentAreaFilled(false);
		btn_removeItem_4.setBorderPainted(false);
		btn_removeItem_4.setBounds(399, 214, 41, 38);
		contentPane.add(btn_removeItem_4);

		select_Item_5 = new JComboBox();
		select_Item_5.setBounds(22, 263, 202, 38);
		contentPane.add(select_Item_5);

		fld_quantity_5 = new JTextField();
		fld_quantity_5.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_5.setColumns(10);
		fld_quantity_5.setBounds(303, 263, 41, 38);
		contentPane.add(fld_quantity_5);

		JButton btn_addItem_5 = new JButton("");
		btn_addItem_5.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_5.setOpaque(false);
		btn_addItem_5.setContentAreaFilled(false);
		btn_addItem_5.setBorderPainted(false);
		btn_addItem_5.setBounds(353, 263, 41, 38);
		contentPane.add(btn_addItem_5);

		JButton btn_removeItem_5 = new JButton("");
		btn_removeItem_5.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_5.setOpaque(false);
		btn_removeItem_5.setContentAreaFilled(false);
		btn_removeItem_5.setBorderPainted(false);
		btn_removeItem_5.setBounds(399, 263, 41, 38);
		contentPane.add(btn_removeItem_5);

		select_Item_6 = new JComboBox();
		select_Item_6.setBounds(22, 312, 202, 38);
		contentPane.add(select_Item_6);

		fld_quantity_6 = new JTextField();
		fld_quantity_6.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_6.setColumns(10);
		fld_quantity_6.setBounds(303, 312, 41, 38);
		contentPane.add(fld_quantity_6);

		JButton btn_addItem_6 = new JButton("");
		btn_addItem_6.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_6.setOpaque(false);
		btn_addItem_6.setContentAreaFilled(false);
		btn_addItem_6.setBorderPainted(false);
		btn_addItem_6.setBounds(353, 312, 41, 38);
		contentPane.add(btn_addItem_6);

		JButton btn_removeItem_6 = new JButton("");
		btn_removeItem_6.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_6.setOpaque(false);
		btn_removeItem_6.setContentAreaFilled(false);
		btn_removeItem_6.setBorderPainted(false);
		btn_removeItem_6.setBounds(399, 312, 41, 38);
		contentPane.add(btn_removeItem_6);

		select_Item_7 = new JComboBox();
		select_Item_7.setBounds(22, 361, 202, 38);
		contentPane.add(select_Item_7);

		fld_quantity_7 = new JTextField();
		fld_quantity_7.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_7.setColumns(10);
		fld_quantity_7.setBounds(303, 361, 41, 38);
		contentPane.add(fld_quantity_7);

		JButton btn_addItem_7 = new JButton("");
		btn_addItem_7.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_7.setOpaque(false);
		btn_addItem_7.setContentAreaFilled(false);
		btn_addItem_7.setBorderPainted(false);
		btn_addItem_7.setBounds(353, 361, 41, 38);
		contentPane.add(btn_addItem_7);

		JButton btn_removeItem_7 = new JButton("");
		btn_removeItem_7.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_7.setOpaque(false);
		btn_removeItem_7.setContentAreaFilled(false);
		btn_removeItem_7.setBorderPainted(false);
		btn_removeItem_7.setBounds(399, 361, 41, 38);
		contentPane.add(btn_removeItem_7);

		select_Item_8 = new JComboBox();
		select_Item_8.setBounds(22, 410, 202, 38);
		contentPane.add(select_Item_8);

		fld_quantity_8 = new JTextField();
		fld_quantity_8.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_8.setColumns(10);
		fld_quantity_8.setBounds(303, 410, 41, 38);
		contentPane.add(fld_quantity_8);

		JButton btn_addItem_8 = new JButton("");
		btn_addItem_8.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_8.setOpaque(false);
		btn_addItem_8.setContentAreaFilled(false);
		btn_addItem_8.setBorderPainted(false);
		btn_addItem_8.setBounds(353, 410, 41, 38);
		contentPane.add(btn_addItem_8);

		JButton btn_removeItem_8 = new JButton("");
		btn_removeItem_8.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_8.setOpaque(false);
		btn_removeItem_8.setContentAreaFilled(false);
		btn_removeItem_8.setBorderPainted(false);
		btn_removeItem_8.setBounds(399, 410, 41, 38);
		contentPane.add(btn_removeItem_8);

		select_Item_9 = new JComboBox();
		select_Item_9.setBounds(22, 459, 202, 38);
		contentPane.add(select_Item_9);

		fld_quantity_9 = new JTextField();
		fld_quantity_9.setHorizontalAlignment(SwingConstants.CENTER);
		fld_quantity_9.setColumns(10);
		fld_quantity_9.setBounds(303, 459, 41, 38);
		contentPane.add(fld_quantity_9);

		JButton btn_addItem_9 = new JButton("");
		btn_addItem_9.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/AddButton.png")));
		btn_addItem_9.setOpaque(false);
		btn_addItem_9.setContentAreaFilled(false);
		btn_addItem_9.setBorderPainted(false);
		btn_addItem_9.setBounds(353, 459, 41, 38);
		contentPane.add(btn_addItem_9);

		JButton btn_removeItem_9 = new JButton("");
		btn_removeItem_9.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/RemoveButton.png")));
		btn_removeItem_9.setOpaque(false);
		btn_removeItem_9.setContentAreaFilled(false);
		btn_removeItem_9.setBorderPainted(false);
		btn_removeItem_9.setBounds(399, 459, 41, 38);
		contentPane.add(btn_removeItem_9);

		fld_totalAmount = new JTextField();
		fld_totalAmount.setEditable(false);
		fld_totalAmount.setHorizontalAlignment(SwingConstants.CENTER);
		fld_totalAmount.setText("0.00 ₺");
		fld_totalAmount.setBackground(new Color(192, 192, 192));
		fld_totalAmount.setBounds(303, 526, 86, 24);
		contentPane.add(fld_totalAmount);
		fld_totalAmount.setColumns(10);

		JLabel lblToplamFiyat = new JLabel("Toplam Fiyat:");
		lblToplamFiyat.setHorizontalAlignment(SwingConstants.CENTER);
		lblToplamFiyat.setForeground(Color.WHITE);
		lblToplamFiyat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblToplamFiyat.setBounds(155, 523, 137, 24);
		contentPane.add(lblToplamFiyat);

		JLabel lblToplamGarson = new JLabel("Açan Garson:");
		lblToplamGarson.setHorizontalAlignment(SwingConstants.CENTER);
		lblToplamGarson.setForeground(Color.WHITE);
		lblToplamGarson.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblToplamGarson.setBounds(155, 558, 137, 24);
		contentPane.add(lblToplamGarson);

		fld_waiter = new JTextField();
		fld_waiter.setEditable(false);
		fld_waiter.setColumns(10);
		fld_waiter.setBackground(Color.LIGHT_GRAY);
		fld_waiter.setBounds(302, 560, 86, 24);
		contentPane.add(fld_waiter);
		fld_waiter.setText(waiter.getPersonname());

		JLabel lblMasa = new JLabel("Masa:");
		lblMasa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMasa.setForeground(Color.WHITE);
		lblMasa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMasa.setBounds(203, 591, 80, 24);
		contentPane.add(lblMasa);

		select_table = new JComboBox();
		select_table.setBounds(303, 595, 85, 23);
		contentPane.add(select_table);
		select_table.addItem("Seçim Yapınız");
		for (String itemName : tableMap.values()) {
			select_table.addItem(itemName);
		}

		JLabel lbl_price_1 = new JLabel("  0.00 ₺");
		lbl_price_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_1.setForeground(new Color(255, 255, 255));
		lbl_price_1.setBackground(new Color(255, 255, 255));
		lbl_price_1.setBounds(242, 72, 41, 38);
		contentPane.add(lbl_price_1);

		JLabel lbl_price_2 = new JLabel("  0.00 ₺");
		lbl_price_2.setForeground(new Color(255, 255, 255));
		lbl_price_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_2.setBackground(Color.WHITE);
		lbl_price_2.setBounds(242, 116, 41, 38);
		contentPane.add(lbl_price_2);

		JLabel lbl_price_3 = new JLabel("  0.00 ₺");
		lbl_price_3.setForeground(Color.WHITE);
		lbl_price_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_3.setBackground(Color.WHITE);
		lbl_price_3.setBounds(242, 165, 41, 38);
		contentPane.add(lbl_price_3);

		JLabel lbl_price_4 = new JLabel("  0.00 ₺");
		lbl_price_4.setForeground(Color.WHITE);
		lbl_price_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_4.setBackground(Color.WHITE);
		lbl_price_4.setBounds(242, 214, 41, 38);
		contentPane.add(lbl_price_4);

		JLabel lbl_price_5 = new JLabel("  0.00 ₺");
		lbl_price_5.setForeground(new Color(255, 255, 255));
		lbl_price_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_5.setBackground(Color.WHITE);
		lbl_price_5.setBounds(242, 263, 41, 38);
		contentPane.add(lbl_price_5);

		JLabel lbl_price_6 = new JLabel("  0.00 ₺");
		lbl_price_6.setForeground(Color.WHITE);
		lbl_price_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_6.setBackground(Color.WHITE);
		lbl_price_6.setBounds(242, 312, 41, 38);
		contentPane.add(lbl_price_6);

		JLabel lbl_price_7 = new JLabel("  0.00 ₺");
		lbl_price_7.setForeground(Color.WHITE);
		lbl_price_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_7.setBackground(Color.WHITE);
		lbl_price_7.setBounds(242, 361, 41, 38);
		contentPane.add(lbl_price_7);

		JLabel lbl_price_8 = new JLabel("  0.00 ₺");
		lbl_price_8.setForeground(Color.WHITE);
		lbl_price_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_8.setBackground(Color.WHITE);
		lbl_price_8.setBounds(242, 410, 41, 38);
		contentPane.add(lbl_price_8);

		JLabel lbl_price_9 = new JLabel("  0.00 ₺");
		lbl_price_9.setForeground(Color.WHITE);
		lbl_price_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_price_9.setBackground(Color.WHITE);
		lbl_price_9.setBounds(242, 459, 41, 38);
		contentPane.add(lbl_price_9);

		JLabel lblBFiyat_1 = new JLabel("<html>Birim <br> Fiyat</html>");
		lblBFiyat_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBFiyat_1.setForeground(Color.WHITE);
		lblBFiyat_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBFiyat_1.setBounds(233, 11, 60, 56);
		contentPane.add(lblBFiyat_1);

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

		itemComboBoxes = new JComboBox[] { select_Item_1, select_Item_2, select_Item_3, select_Item_4, select_Item_5,
				select_Item_6, select_Item_7, select_Item_8, select_Item_9 };

		priceLabels = new JLabel[] { lbl_price_1, lbl_price_2, lbl_price_3, lbl_price_4, lbl_price_5, lbl_price_6,
				lbl_price_7, lbl_price_8, lbl_price_9 };

		quantityFields = new JTextField[] { fld_quantity_1, fld_quantity_2, fld_quantity_3, fld_quantity_4,
				fld_quantity_5, fld_quantity_6, fld_quantity_7, fld_quantity_8, fld_quantity_9 };

		addButtons = new JButton[] { btn_addItem_1, btn_addItem_2, btn_addItem_3, btn_addItem_4, btn_addItem_5,
				btn_addItem_6, btn_addItem_7, btn_addItem_8, btn_addItem_9 };

		removeButtons = new JButton[] { btn_removeItem_1, btn_removeItem_2, btn_removeItem_3, btn_removeItem_4,
				btn_removeItem_5, btn_removeItem_6, btn_removeItem_7, btn_removeItem_8, btn_removeItem_9 };

		for (int i = 0; i < itemComboBoxes.length; i++) {
			JComboBox<String> comboBox = itemComboBoxes[i];
			comboBox.removeAllItems();
			comboBox.addItem("Lütfen Seçim Yapınız.");

			for (Map.Entry<Integer, String> entry : itemMap.entrySet()) {
				comboBox.addItem(entry.getKey() + " - " + entry.getValue());
			}

			final int index = i;
			comboBox.addActionListener(e -> updatePriceLabel(index));

		}

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

		JButton btn_save = new JButton("Adisyonu Kaydet");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processSelections();
				int totalPrice = Integer.parseInt(fld_totalAmount.getText().replace("₺", "").trim());
				String selectedItem = (String) select_table.getSelectedItem();
				Integer selectedId = tableMap.entrySet().stream().filter(entry -> entry.getValue().equals(selectedItem))
						.map(Map.Entry::getKey).findFirst().orElse(null);

				try {
					boolean chkStatus = bill.addBill(billUUID, selectedId, waiter.getUser_id(), totalPrice, itemIds,
							quantities);
					if (chkStatus) {
						Helper.showMsg("Adisyon Başarıyla Kaydedildi!");
					
						boolean statusChange = table.updateTableStatustoOccupied(selectedId);

						if (statusChange) {
							// System.out.println("Masa durumu değişikliği gerçekleşti!");
						} else {
							// System.out.println("Masa durumu değişikliği gerçekleşmedi");
						}
						
						dispose();

					} else {
						Helper.showMsg("Başarısız Kayıt!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btn_save.setBounds(135, 640, 148, 38);
		contentPane.add(btn_save);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StartTabGUI.class.getResource("/img/background_formatted.jpg")));
		lblNewLabel.setBounds(0, 0, 450, 700);
		contentPane.add(lblNewLabel);
		setUndecorated(true);

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

			if (!selectedText.equals("Lütfen Seçim Yapınız.") && !quantityText.isEmpty()) {
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

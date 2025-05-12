package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.util.EventObject;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class AddTableGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Manager manager = new Manager();
	private Table table = new Table();
	private JPanel contentPane;
	private Point initialClick;
	private JTextField fld_tableName;
	private JTextField fld_capacity;
	private ButtonGroup locationButtonGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTableGUI frame = new AddTableGUI(manager);
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
	public AddTableGUI(Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
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

		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(AddTableGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(600, 0, 50, 50);
		contentPane.add(btn_exit);

		JLabel lblPersonelMaaGncelle = new JLabel("Masa Ekle");
		lblPersonelMaaGncelle.setForeground(Color.WHITE);
		lblPersonelMaaGncelle.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		lblPersonelMaaGncelle.setBounds(272, 11, 90, 36);
		contentPane.add(lblPersonelMaaGncelle);

		String[] items = { "Seçim Yapınız...", "İç Mekan", "Dış Mekan" };

		JLabel lblPersonelSeimi_1_1_1 = new JLabel("Masa Adı");
		lblPersonelSeimi_1_1_1.setForeground(Color.WHITE);
		lblPersonelSeimi_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPersonelSeimi_1_1_1.setBounds(274, 221, 90, 24);
		contentPane.add(lblPersonelSeimi_1_1_1);

		fld_tableName = new JTextField();
		fld_tableName.setHorizontalAlignment(SwingConstants.CENTER);
		fld_tableName.setForeground(Color.RED);
		fld_tableName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_tableName.setColumns(10);
		fld_tableName.setBackground(new Color(255, 255, 255));
		fld_tableName.setBounds(203, 243, 225, 34);
		contentPane.add(fld_tableName);

		JButton btn_save = new JButton("Ekle");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_capacity.getText().length() == 0 || fld_tableName.getText().length() == 0 || locationButtonGroup.getSelection()== null) {
					Helper.showMsg("Lütfen Boş Alanları Doldurunuz!");

				} else {
					try {
					
						String selectLocation = locationButtonGroup.getSelection().getActionCommand();
						

						boolean control = table.addTable(fld_tableName.getText(),selectLocation,Integer.parseInt(fld_capacity.getText()));
						if (control) {
							Helper.showMsg("Masa Başarıyla Eklendi!");
							fld_capacity.setText(null);
							fld_tableName.setText(null);
							locationButtonGroup.clearSelection();
						} else {
							Helper.showMsg("Something Wrong...");
						}
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_save.setForeground(Color.BLACK);
		btn_save.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_save.setBounds(244, 409, 152, 34);
		contentPane.add(btn_save);

		JLabel lblKapasite = new JLabel("Kapasite");
		lblKapasite.setForeground(Color.WHITE);
		lblKapasite.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKapasite.setBounds(274, 128, 122, 24);
		contentPane.add(lblKapasite);

		fld_capacity = new JTextField();
		fld_capacity.setHorizontalAlignment(SwingConstants.CENTER);
		fld_capacity.setForeground(Color.RED);
		fld_capacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_capacity.setColumns(10);
		fld_capacity.setBackground(Color.WHITE);
		fld_capacity.setBounds(203, 154, 225, 34);
		contentPane.add(fld_capacity);
		
		JLabel lblMekanSeimi = new JLabel("Mekan Seçimi:");
		lblMekanSeimi.setForeground(Color.WHITE);
		lblMekanSeimi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMekanSeimi.setBounds(251, 306, 158, 24);
		contentPane.add(lblMekanSeimi);
		
		JRadioButton rdbtn_out = new JRadioButton("Dış Mekan");
		rdbtn_out.setOpaque(true);
		rdbtn_out.setForeground(new Color(0, 0, 0));
		rdbtn_out.setBounds(338, 337, 90, 23);
		contentPane.add(rdbtn_out);
		
		JRadioButton rdbtn_in = new JRadioButton("İç Mekan");
		rdbtn_in.setOpaque(true);
		rdbtn_in.setForeground(new Color(0, 0, 0));
		rdbtn_in.setBounds(203, 337, 77, 23);
		contentPane.add(rdbtn_in);
		
		locationButtonGroup = new ButtonGroup();
		locationButtonGroup.add(rdbtn_in);
		locationButtonGroup.add(rdbtn_out);
		
		 rdbtn_in.setActionCommand("In");
	     rdbtn_out.setActionCommand("Out");
	       
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddTableGUI.class.getResource("/img/background_img.jpg")));
		lblNewLabel.setBounds(0, 0, 650, 500);
		contentPane.add(lblNewLabel);

	}
}

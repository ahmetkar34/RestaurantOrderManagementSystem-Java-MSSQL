package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Model.Manager;
import Helper.*;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddStaffGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Manager manager = new Manager();
	private JPanel contentPane;
	private JTextField fld_personname;
	private JTextField fld_surname;
	private JTextField fld_username;
	private JLabel lblifre;
	private JPasswordField fld_pass;
	private JTextField fld_salary;
	private Point initialClick;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStaffGUI frame = new AddStaffGUI(manager);
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
	public AddStaffGUI(Manager manager) {
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 511, 579);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 252, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick=e.getPoint();
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
				setLocation(x,y);
				
			}
			
		});

		JLabel lblNewLabel = new JLabel("Adı:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(166, 58, 152, 34);
		contentPane.add(lblNewLabel);

		fld_personname = new JTextField();
		fld_personname.setHorizontalAlignment(SwingConstants.CENTER);
		fld_personname.setBounds(140, 87, 203, 34);
		contentPane.add(fld_personname);
		fld_personname.setColumns(10);

		JLabel lblSoyad = new JLabel("Soyadı:");
		lblSoyad.setForeground(new Color(255, 255, 255));
		lblSoyad.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoyad.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoyad.setBounds(166, 129, 152, 34);
		contentPane.add(lblSoyad);

		fld_surname = new JTextField();
		fld_surname.setHorizontalAlignment(SwingConstants.CENTER);
		fld_surname.setColumns(10);
		fld_surname.setBounds(140, 158, 203, 34);
		contentPane.add(fld_surname);

		JLabel lblKullancAd = new JLabel("Kullanıcı Adı:");
		lblKullancAd.setForeground(new Color(255, 255, 255));
		lblKullancAd.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullancAd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKullancAd.setBounds(166, 203, 152, 34);
		contentPane.add(lblKullancAd);

		fld_username = new JTextField();
		fld_username.setHorizontalAlignment(SwingConstants.CENTER);
		fld_username.setColumns(10);
		fld_username.setBounds(140, 232, 203, 34);
		contentPane.add(fld_username);

		lblifre = new JLabel("Şifre:");
		lblifre.setForeground(new Color(255, 255, 255));
		lblifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblifre.setBounds(166, 277, 152, 34);
		contentPane.add(lblifre);

		fld_pass = new JPasswordField();
		fld_pass.setHorizontalAlignment(SwingConstants.CENTER);
		fld_pass.setBounds(140, 306, 203, 34);
		contentPane.add(fld_pass);

		JLabel lblnvan = new JLabel("Ünvan:");
		lblnvan.setForeground(new Color(255, 255, 255));
		lblnvan.setHorizontalAlignment(SwingConstants.CENTER);
		lblnvan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblnvan.setBounds(166, 421, 152, 34);
		contentPane.add(lblnvan);

		JRadioButton rdbtn_Manager = new JRadioButton("Yönetici");
		rdbtn_Manager.setForeground(new Color(255, 255, 255));
		rdbtn_Manager.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtn_Manager.setOpaque(false);
		rdbtn_Manager.setBounds(140, 449, 63, 23);
		contentPane.add(rdbtn_Manager);

		JRadioButton rdbtn_Waiter = new JRadioButton("Garson");
		rdbtn_Waiter.setForeground(new Color(255, 255, 255));
		rdbtn_Waiter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtn_Waiter.setOpaque(false);
		rdbtn_Waiter.setBounds(220, 449, 70, 23);
		contentPane.add(rdbtn_Waiter);

		JRadioButton rdbtn_Chef = new JRadioButton("Şef");
		rdbtn_Chef.setForeground(new Color(255, 255, 255));
		rdbtn_Chef.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtn_Chef.setOpaque(false);
		rdbtn_Chef.setBounds(292, 449, 51, 23);
		contentPane.add(rdbtn_Chef);

		ButtonGroup TitleGroup = new ButtonGroup();
		TitleGroup.add(rdbtn_Manager);
		TitleGroup.add(rdbtn_Waiter);
		TitleGroup.add(rdbtn_Chef);

		JLabel lblMaa = new JLabel("Maaş:");
		lblMaa.setForeground(new Color(255, 255, 255));
		lblMaa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaa.setBounds(166, 351, 152, 34);
		contentPane.add(lblMaa);

		JButton btn_save = new JButton("Kaydet");
		btn_save.setForeground(new Color(0, 0, 0));
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_pass.getText().length() == 0 && fld_personname.getText().length() == 0
						&& fld_salary.getText().length() == 0 && fld_surname.getText().length() == 0
						&& fld_username.getText().length() == 0 && TitleGroup.getSelection() == null) {
					Helper.showMsg("Lütfen Boş Alanları Doldurunuz!");

				}
				else {
					try {
						String personname = fld_personname.getText() + " " + fld_surname.getText();
						int salary = Integer.parseInt(fld_salary.getText());
						String SelOption = null;
						if (rdbtn_Manager.isSelected()) {
							SelOption = rdbtn_Manager.getText();
						} else if (rdbtn_Waiter.isSelected()) {
							SelOption = rdbtn_Waiter.getText();
						} else {
							SelOption = rdbtn_Chef.getText();
						}
						boolean check = manager.addStaff(fld_username.getText(), fld_pass.getText(), personname, SelOption,
								salary);

						if (check) {
							Helper.showMsg("Personel Başarıyla Eklendi !");
						} else {
							Helper.showMsg("Bir Hata Oluştu !");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

				

			}
		});

		fld_salary = new JTextField();
		fld_salary.setHorizontalAlignment(SwingConstants.CENTER);
		fld_salary.setColumns(10);
		fld_salary.setBounds(140, 380, 203, 34);
		contentPane.add(fld_salary);
		btn_save.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_save.setBounds(166, 479, 152, 34);
		contentPane.add(btn_save);

		JLabel lblNewLabel_1_1 = new JLabel("Personel Ekle");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 21));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(140, 0, 203, 80);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btn_exit.setIcon(new ImageIcon(AddStaffGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(461, 0, 50, 50);
		contentPane.add(btn_exit);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(AddStaffGUI.class.getResource("/img/background_img.jpg")));
		lblNewLabel_1.setBounds(0, 0, 511, 579);
		contentPane.add(lblNewLabel_1);
	}
}

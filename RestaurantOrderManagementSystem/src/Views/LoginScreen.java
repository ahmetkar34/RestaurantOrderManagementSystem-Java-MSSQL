package Views;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.*;
import Model.*;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_username;
	private JPasswordField fld_password;
	private DBConnection conn = new DBConnection();
	private Point initialClick;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() throws SQLException {
		
		setUndecorated(true);
		setResizable(false);
		setTitle("Restoran Sipariş Yönetimi Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 584);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
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

		JLabel lblNewLabel_1 = new JLabel("Restoran Sipariş Yönetimi Sistemi");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 55));
		lblNewLabel_1.setBounds(39, 41, 836, 80);
		contentPane.add(lblNewLabel_1);

		JButton btn_signin = new JButton("Giriş Yap");
		btn_signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_username.getText().length() == 0 && fld_password.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM usertable");
						while (rs.next()) {
							String role = rs.getString("user_role");
							if (fld_username.getText().equals(rs.getString("user_username"))
									&& fld_password.getText().equals(rs.getString("user_password"))) {
								// System.out.println("giriş başarılı");
								switch (role) {

								case "Yönetici":
									Manager manager = new Manager();
									manager.setUser_id(rs.getInt("user_id"));
									manager.setPersonname(rs.getString("user_personname"));
									manager.setUsername(rs.getString("user_username"));
									manager.setUserpassword(rs.getString("user_password"));
									manager.setUserrole(rs.getString("user_role"));
									ManagerScreen mScreen = new ManagerScreen(manager);
									mScreen.setVisible(true);
									dispose();
									break;

								case "Şef":
									Chef chef = new Chef();
									chef.setUser_id(rs.getInt("user_id"));
									chef.setPersonname(rs.getString("user_personname"));
									chef.setUsername(rs.getString("user_username"));
									chef.setUserpassword(rs.getString("user_password"));
									chef.setUserrole(rs.getString("user_role"));
									ChefScreen cScreen = new ChefScreen(chef);
									cScreen.setVisible(true);
									dispose();
									break;

								case "Garson":
									Waiter waiter = new Waiter();
									waiter.setUser_id(rs.getInt("user_id"));
									waiter.setPersonname(rs.getString("user_personname"));
									waiter.setUsername(rs.getString("user_username"));
									waiter.setUserpassword(rs.getString("user_password"));
									waiter.setUserrole(rs.getString("user_role"));
									WaiterScreen wScreen = new WaiterScreen(waiter);
									wScreen.setVisible(true);
									dispose();
									break;

								default:
									System.out.println("something wrong...");
									break;

								}

							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btn_signin.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 23));
		btn_signin.setBounds(340, 400, 234, 71);
		contentPane.add(btn_signin);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("/img/user_icon.png")));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 23));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(247, 200, 70, 50);
		contentPane.add(lblNewLabel);

		fld_username = new JTextField();
		fld_username.setFont(new Font("Tahoma", Font.PLAIN, 23));
		fld_username.setBounds(340, 206, 234, 44);
		contentPane.add(fld_username);
		fld_username.setColumns(10);

		fld_password = new JPasswordField();
		fld_password.setFont(new Font("Tahoma", Font.PLAIN, 23));
		fld_password.setBounds(340, 287, 235, 44);
		contentPane.add(fld_password);
		
		JButton btn_exit = new JButton("");
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_exit.setOpaque(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(new Color(255, 255, 255));
		btn_exit.setIcon(new ImageIcon(LoginScreen.class.getResource("/img/exit_icon.png")));
		btn_exit.setBounds(871, 0, 50, 50);
		contentPane.add(btn_exit);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(LoginScreen.class.getResource("/img/icon_lock.png")));
		lblNewLabel_3_1.setBounds(262, 281, 41, 50);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(
				new ImageIcon(LoginScreen.class.getResource("/img/background_img.jpg")));
		lblNewLabel_2.setBounds(0, 0, 921, 584);
		contentPane.add(lblNewLabel_2);

	}
}

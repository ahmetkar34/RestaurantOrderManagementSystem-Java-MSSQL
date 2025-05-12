package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.*;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class ManagerScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Manager manager = new Manager();
	private Point initialClick;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerScreen frame = new ManagerScreen(manager);
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
	public ManagerScreen(Manager manager) {
		setTitle("Restoran Sipariş Yönetimi Sistemi");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 921, 584);
		contentPane = new JPanel();
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
		
		JLabel lblNewLabel_1 = new JLabel("Yönetici Hesabı");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(722, 11, 139, 59);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Hoşgeldiniz, Sayın "+ manager.getPersonname());
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(10, 11, 359, 59);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		btn_exit.setIcon(new ImageIcon(ManagerScreen.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(871, 0, 50, 50);
		contentPane.add(btn_exit);
		
		JPanel w_pane = new JPanel();
		w_pane.setOpaque(false);
		w_pane.setBounds(10, 81, 901, 492);
		contentPane.add(w_pane);
		w_pane.setLayout(new GridLayout(3,3,200,90));
		
		JButton btn_AddStaff = new JButton("<html>Personel <br> Ekle/Çıkar </html>");
		btn_AddStaff.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btn_AddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStaffGUI asGUI = new AddStaffGUI(manager);
				asGUI.setVisible(true);
				
			}
		});
		w_pane.add(btn_AddStaff);
		
		JButton btn_UpdateStaff = new JButton("<html>Personel <br> Bilgilerini <br> Güncelle/Sil </html>");
		btn_UpdateStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateStaffGUI usGUI = new UpdateStaffGUI(manager);
					usGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_UpdateStaff.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_UpdateStaff);
		
		JButton btn_UpdateSalary = new JButton("<html> Personel <br> Maaş <br> Güncelle </html>");
		btn_UpdateSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateSalaryGUI uSalary = new UpdateSalaryGUI(manager);
					uSalary.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_UpdateSalary.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_UpdateSalary);
		
		JButton btn_AddMenu = new JButton("<html> Menü <br> Ekle </html>");
		btn_AddMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddMenuGUI addMenu = new AddMenuGUI(manager);
					addMenu.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_AddMenu.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_AddMenu);
		
		JButton btn_UpdateMenuPrices = new JButton("<html> Menü Fiyat <br> Güncelle </html>");
		btn_UpdateMenuPrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateMenuPriceGUI umpGUI = new UpdateMenuPriceGUI(manager);
					umpGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_UpdateMenuPrices.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_UpdateMenuPrices);
		
		JButton btn_CheckBill = new JButton("<html> Adisyon <br> Bastır </html>");
		btn_CheckBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintTabGUI ptGUI = new PrintTabGUI();
					ptGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_CheckBill.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_CheckBill);
		
		JButton btn_AddTable = new JButton("<html> Masa <br> Ekle </html>");
		btn_AddTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTableGUI atGUI = new AddTableGUI(manager);
				atGUI.setVisible(true);
				
			}
		});
		btn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_AddTable);
		
		JButton btn_WatchOrders = new JButton("<html> Sipariş <br> İzle </html>");
		btn_WatchOrders.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_WatchOrders);
		
		JButton btn_TableAvailability = new JButton("<html> Masa Müsaitlik <br> Kontrolü </html>");
		btn_TableAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CheckTableStatusGUI ctsGUI = new CheckTableStatusGUI();
					ctsGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_TableAvailability.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_TableAvailability);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ManagerScreen.class.getResource("/img/background_img.jpg")));
		lblNewLabel.setBounds(0, 0, 921, 584);
		contentPane.add(lblNewLabel);
	}
}

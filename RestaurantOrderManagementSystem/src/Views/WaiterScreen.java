package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.*;
import Model.*;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Point;

public class WaiterScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Waiter waiter = new Waiter();
	private Point initialClick;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaiterScreen frame = new WaiterScreen(waiter);
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
	public WaiterScreen(Waiter waiter) {
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
		
		setTitle("Restoran Sipariş Yönetimi Sistemi");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 921, 584);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(WaiterScreen.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(871, 0, 50, 50);
		contentPane.add(btn_exit);
		
		JPanel w_pane = new JPanel();
		w_pane.setOpaque(false);
		w_pane.setBounds(10, 81, 901, 492);
		contentPane.add(w_pane);
		w_pane.setLayout(new GridLayout(3, 3, 200, 90));
		
		JButton btn_StartATab = new JButton("<html>Adisyon<br> Aç </html>");
		btn_StartATab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StartTabGUI stGUI = new StartTabGUI(waiter);
					stGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_StartATab.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_StartATab);
		
		JButton btn_UpdateTab = new JButton("<html> Adisyon <br> Güncelle </html>");
		btn_UpdateTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateTabGUI utGUI = new UpdateTabGUI();
					utGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_UpdateTab.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_UpdateTab);
		
		JButton btn_WatchOrder = new JButton("<html> Sipariş <br> İzle </html>");
		btn_WatchOrder.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_WatchOrder);
		
		JButton btn_PrintTab = new JButton("<html> Adisyon <br> Bastır </html>");
		btn_PrintTab.addActionListener(new ActionListener() {
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
		btn_PrintTab.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btn_PrintTab);
		
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
		
		JButton btnNewButton = new JButton("<html> Mutfağa Mesaj <br> İlet </html>");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		w_pane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Hoşgeldiniz, Sayın "+waiter.getPersonname());
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(10, 11, 459, 59);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Garson Hesabı");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(722, 11, 139, 59);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(WaiterScreen.class.getResource("/img/background_img.jpg")));
		lblNewLabel.setBounds(0, 0, 921, 584);
		contentPane.add(lblNewLabel);
	}
}

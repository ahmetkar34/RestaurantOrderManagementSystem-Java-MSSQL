package Views;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.*;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CheckTableStatusGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Manager manager = new Manager();
	private Table table = new Table();
	private JPanel contentPane;
	private Point initialClick;
	private JTable table_tableDB;
	private DefaultTableModel tableModel = null;
	private Object[] tableData = null;
	private JLabel lblstatustext;
	private JLabel lblstatus;
	private JButton btn_restoreDefault;
	private JRadioButton rdbtn_in = new JRadioButton("İç Mekan");
	private JRadioButton rdbtn_out = new JRadioButton("Dış Mekan");
	private JRadioButton rdbtn_2 = new JRadioButton("2");
	private JRadioButton rdbtn_4 = new JRadioButton("4");
	private JRadioButton rdbtn_6 = new JRadioButton("6");
	private JRadioButton rdbtn_occupied = new JRadioButton("Dolu");
	private JRadioButton rdbtn_empty = new JRadioButton("Boş");
	private ButtonGroup locationGroup;
	private ButtonGroup capacityGroup;
	private ButtonGroup statusGroup;
	private final JLabel lblbackground = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckTableStatusGUI frame = new CheckTableStatusGUI();
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
	public CheckTableStatusGUI() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 877, 592);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tableModel = new DefaultTableModel();
		loadTableData();

		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(CheckTableStatusGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(817, 0, 50, 50);
		contentPane.add(btn_exit);

		JLabel lblFilterTheTables = new JLabel("Filter The Tables:");
		lblFilterTheTables.setForeground(Color.WHITE);
		lblFilterTheTables.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFilterTheTables.setBounds(134, 54, 188, 24);
		contentPane.add(lblFilterTheTables);

		rdbtn_in.setOpaque(false);
		rdbtn_in.setForeground(Color.WHITE);
		rdbtn_in.setBounds(150, 85, 77, 23);
		contentPane.add(rdbtn_in);

		rdbtn_out.setOpaque(false);
		rdbtn_out.setForeground(Color.WHITE);
		rdbtn_out.setBounds(237, 85, 85, 23);
		contentPane.add(rdbtn_out);

		locationGroup = new ButtonGroup();
		locationGroup.add(rdbtn_out);
		locationGroup.add(rdbtn_in);
		rdbtn_out.setActionCommand("Out");
		rdbtn_in.setActionCommand("In");

		JButton btn_applyFilter = new JButton("Filtreyi Uygula");
		btn_applyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel clearModel = (DefaultTableModel) table_tableDB.getModel();
				clearModel.setRowCount(0);
				lblstatus.setVisible(false);
				lblstatustext.setVisible(false);
				try {
					List<Table> filteredTables = manager.getTableListbyFilter(locationGroup, capacityGroup, statusGroup);
					for (Table t : filteredTables) {
					    tableModel.addRow(new Object[]{t.getTable_id(), t.getTable_capacity(), t.getTable_name(), t.getTable_location(), t.getTable_status()});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_applyFilter.setBounds(134, 192, 146, 24);
		contentPane.add(btn_applyFilter);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 227, 313, 320);
		contentPane.add(scrollPane);

		table_tableDB = new JTable(tableModel);
		scrollPane.setViewportView(table_tableDB);
		table_tableDB.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					boolean tablestatus = (boolean) table_tableDB.getValueAt(table_tableDB.getSelectedRow(), 4);
					String tableName = table_tableDB.getValueAt(table_tableDB.getSelectedRow(), 2).toString();

					if (tablestatus) {
						lblstatus.setBackground(new Color(0, 255, 0));
						lblstatus.setText(tableName);
						lblstatustext.setText("Bu Masa Müsaittir");
						lblstatus.setVisible(true);
						lblstatustext.setVisible(true);

					} else {
						lblstatus.setBackground(new Color(255, 0, 0));
						lblstatus.setText(tableName);
						lblstatustext.setText("Bu Masa Doludur");
						lblstatus.setVisible(true);
						lblstatustext.setVisible(true);

					}
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		rdbtn_2.setOpaque(false);
		rdbtn_2.setForeground(Color.WHITE);
		rdbtn_2.setBounds(150, 126, 39, 23);
		contentPane.add(rdbtn_2);

		rdbtn_4.setOpaque(false);
		rdbtn_4.setForeground(Color.WHITE);
		rdbtn_4.setBounds(215, 126, 44, 23);
		contentPane.add(rdbtn_4);

		rdbtn_6.setOpaque(false);
		rdbtn_6.setForeground(Color.WHITE);
		rdbtn_6.setBounds(281, 126, 41, 23);
		contentPane.add(rdbtn_6);

		capacityGroup = new ButtonGroup();
		capacityGroup.add(rdbtn_2);
		capacityGroup.add(rdbtn_4);
		capacityGroup.add(rdbtn_6);
		rdbtn_2.setActionCommand("2");
		rdbtn_4.setActionCommand("4");
		rdbtn_6.setActionCommand("6");

		JLabel lblNewLabel = new JLabel("Mekan Seçimi:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(47, 86, 91, 24);
		contentPane.add(lblNewLabel);

		JLabel lblKapasite = new JLabel("Kapasite Seçimi:");
		lblKapasite.setForeground(Color.WHITE);
		lblKapasite.setBounds(47, 126, 105, 24);
		contentPane.add(lblKapasite);

		lblstatus = new JLabel("");
		lblstatus.setVisible(false);
		lblstatus.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblstatus.setOpaque(true);
		lblstatus.setBackground(new Color(240, 240, 240));
		lblstatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblstatus.setForeground(new Color(255, 255, 255));
		lblstatus.setBounds(528, 191, 221, 219);
		contentPane.add(lblstatus);

		lblstatustext = new JLabel("");
		lblstatustext.setVisible(false);
		lblstatustext.setHorizontalAlignment(SwingConstants.CENTER);
		lblstatustext.setForeground(Color.WHITE);
		lblstatustext.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblstatustext.setBounds(528, 421, 221, 24);
		contentPane.add(lblstatustext);

		JLabel lblDurumSeimi = new JLabel("Durum Seçimi:");
		lblDurumSeimi.setForeground(Color.WHITE);
		lblDurumSeimi.setBounds(47, 167, 91, 24);
		contentPane.add(lblDurumSeimi);

		rdbtn_occupied.setOpaque(false);
		rdbtn_occupied.setForeground(Color.WHITE);
		rdbtn_occupied.setBounds(150, 166, 77, 23);
		contentPane.add(rdbtn_occupied);

		rdbtn_empty.setOpaque(false);
		rdbtn_empty.setForeground(Color.WHITE);
		rdbtn_empty.setBounds(237, 166, 85, 23);
		contentPane.add(rdbtn_empty);

		statusGroup = new ButtonGroup();
		statusGroup.add(rdbtn_empty);
		statusGroup.add(rdbtn_occupied);

		rdbtn_empty.setActionCommand("true");
		rdbtn_occupied.setActionCommand("false");

		btn_restoreDefault = new JButton("<html>Restore <br> Default </html>");
		btn_restoreDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel clearModel = (DefaultTableModel) table_tableDB.getModel();
				clearModel.setRowCount(0);
				lblstatus.setVisible(false);
				lblstatustext.setVisible(false);
				locationGroup.clearSelection();
				capacityGroup.clearSelection();
				statusGroup.clearSelection();

				try {
					loadTableData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_restoreDefault.setBounds(304, 180, 77, 36);
		contentPane.add(btn_restoreDefault);
		lblbackground.setIcon(new ImageIcon(CheckTableStatusGUI.class.getResource("/img/background_img.jpg")));
		lblbackground.setBounds(0, 0, 877, 592);
		
		contentPane.add(lblbackground);

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
	}
	public void loadTableData() throws SQLException {
	    List<Table> tables = manager.getTableList();
	    Object[][] data = new Object[tables.size()][5];

	    for (int i = 0; i < tables.size(); i++) {
	        data[i][0] = tables.get(i).getTable_id();
	        data[i][1] = tables.get(i).getTable_capacity();
	        data[i][2] = tables.get(i).getTable_name();
	        data[i][3] = tables.get(i).getTable_location();
	        data[i][4] = tables.get(i).getTable_status();
	    }

	    tableModel.setDataVector(data, new Object[]{"Id", "Capacity", "Name", "Location", "Status"});
	}
}

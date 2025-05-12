package Views;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import Model.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class PrintTabGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Waiter waiter = new Waiter();
	private Table table = new Table();
	private Bills b = new Bills();
	private JPanel contentPane;
	private Menu menu = new Menu();
	private Bills bills = new Bills();
	private List<Menu> menuList = menu.getMenuList();
	private HashMap<Integer, String> itemMap = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> priceMap = new HashMap<Integer, Integer>();
	private Point initialClick;
	private List<Table> tableList = waiter.getOccupiedTableList();
	private HashMap<Integer, String> tableMap = new HashMap<Integer, String>();
	private int selTableID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintTabGUI frame = new PrintTabGUI();
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
	public PrintTabGUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
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
		
		for (int i = 0; i < menuList.size(); i++) {
			itemMap.put(menuList.get(i).getItem_id(), menuList.get(i).getItem_name());

		}
		
		for (int i = 0; i < menuList.size(); i++) {
			priceMap.put(menuList.get(i).getItem_id(), menuList.get(i).getItem_price());

		}
		
		for (int i = 0; i < tableList.size(); i++) {
			tableMap.put(tableList.get(i).getTable_id(), tableList.get(i).getTable_name());
		}
		
		
		
		JButton btnNewButton = new JButton("<html>Adisyonu Bastır <br> Ve Kapat</html>");
		btnNewButton.setBounds(148, 173, 124, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					try {
						createAndOpenPDF(selTableID,itemMap,priceMap);
						table.updateTableStatustoAvailable(selTableID);
						b.updateBillStatustoPaid(selTableID);
						dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 
				
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btn_exit = new JButton("");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_exit.setIcon(new ImageIcon(PrintTabGUI.class.getResource("/img/exit_icon.png")));
		btn_exit.setOpaque(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setBackground(Color.WHITE);
		btn_exit.setBounds(400, 0, 50, 50);
		contentPane.add(btn_exit);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(108, 85, 219, 40);
		contentPane.add(comboBox);
		comboBox.addItem("Lütfen Seçim Yapınız...");
		for (Map.Entry<Integer, String> entry : tableMap.entrySet()) {
			comboBox.addItem(entry.getKey() + " - " + entry.getValue());
		}
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox c = (JComboBox) e.getSource();
				String selItem = (String) c.getSelectedItem();
				selTableID = Integer.parseInt(selItem.split(" - ")[0]);
			}
		});
		
		
		JLabel lblAdisyonBastr = new JLabel("Adisyon Bastır");
		lblAdisyonBastr.setForeground(Color.WHITE);
		lblAdisyonBastr.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdisyonBastr.setBounds(153, 26, 134, 24);
		contentPane.add(lblAdisyonBastr);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrintTabGUI.class.getResource("/img/background_img.jpg")));
		lblNewLabel.setBounds(0, 0, 450, 300);
		contentPane.add(lblNewLabel);
	}
	
	
	
	public static void createAndOpenPDF(int tableid, HashMap<Integer, String> itemMap, HashMap<Integer, Integer> priceMap) throws IOException, SQLException {
	    Bills bills = new Bills();
	    Bills bill = bills.getBillWithTableID(tableid);
	    List<BillItems> items = bill.getItems();

	    // **Daha geniş fiş boyutu**
	    PDRectangle receiptSize = new PDRectangle(300, 500);
	    PDDocument document = new PDDocument();
	    PDPage page = new PDPage(receiptSize);
	    document.addPage(page);

	    PDPageContentStream contentStream = new PDPageContentStream(document, page);
	    float margin = 15;
	    float yPosition = 480;
	    float lineHeight = 16f;

	    // **Başlık**
	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
	    float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Restoran Adisyonu") / 1000 * 14;
	    contentStream.beginText();
	    contentStream.newLineAtOffset((receiptSize.getWidth() - titleWidth) / 2, yPosition);
	    contentStream.showText("Restoran Adisyonu");
	    contentStream.endText();
	    yPosition -= 20;

	    // **Bill ID ve Tarih**
	    contentStream.setFont(PDType1Font.HELVETICA, 10);
	    float billTextWidth = PDType1Font.HELVETICA.getStringWidth("Bill ID: " + bill.getBill_id()) / 1000 * 10;
	    float centerX = (receiptSize.getWidth() - billTextWidth) / 2;

	    contentStream.beginText();
	    contentStream.newLineAtOffset(centerX, yPosition);
	    contentStream.showText("Bill ID: " + bill.getBill_id());
	    contentStream.endText();
	    yPosition -= 15;

	    // **Tarih Ekleme**
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
	    String formattedDate = bill.getBilldate().format(formatter);
	    contentStream.beginText();
	    contentStream.newLineAtOffset(centerX, yPosition);
	    contentStream.showText("Tarih: " + formattedDate);
	    contentStream.endText();
	    yPosition -= 20;

	    // **Başlıklar**
	    float column1 = margin;
	    float column2 = receiptSize.getWidth() - 110;
	    float column3 = receiptSize.getWidth() - 60; // Daha sola çekildi
	    float column4 = receiptSize.getWidth() - 25; // "TL" sabit konumda

	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
	    contentStream.beginText();
	    contentStream.newLineAtOffset(column1, yPosition);
	    contentStream.showText("Urun");
	    contentStream.endText();

	    contentStream.beginText();
	    contentStream.newLineAtOffset(column2, yPosition);
	    contentStream.showText("Adet");
	    contentStream.endText();

	    contentStream.beginText();
	    contentStream.newLineAtOffset(column3, yPosition);
	    contentStream.showText("Fiyat");
	    contentStream.endText();

	    yPosition -= 10;

	    // **Çizgi**
	    contentStream.beginText();
	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	    contentStream.newLineAtOffset(margin, yPosition);
	    contentStream.showText("------------------------------------------------------------------");
	    contentStream.endText();
	    yPosition -= 15;

	    // **Ürün Listesi**
	    contentStream.setFont(PDType1Font.COURIER_BOLD, 10);

	    if (items != null && !items.isEmpty()) {
	        for (BillItems item : items) {
	            String productName = itemMap.get(item.getItem_id());
	            int quantity = item.getQty();
	            int price = priceMap.get(item.getItem_id()) * quantity;

	            // **Ürün İsmi**
	            contentStream.beginText();
	            contentStream.newLineAtOffset(column1, yPosition);
	            contentStream.showText(convertTurkishToASCII(productName));
	            contentStream.endText();

	            // **Adet**
	            contentStream.beginText();
	            contentStream.newLineAtOffset(column2, yPosition);
	            contentStream.showText(String.valueOf(quantity));
	            contentStream.endText();

	         // **Fiyat - Sağdan sola kaydırılarak TL'ye yakınlaştırıldı**
	            float textWidth = PDType1Font.COURIER_BOLD.getStringWidth(String.valueOf(price)) / 1000 * 10;
	            float adjustedX = column4 - textWidth - 10; // TL'den çok uzak kalmaması için "-10" ekledim

	            contentStream.beginText();
	            contentStream.newLineAtOffset(adjustedX, yPosition);
	            contentStream.showText(String.valueOf(price));
	            contentStream.endText();

	            // **TL Sabitleme**
	            contentStream.beginText();
	            contentStream.newLineAtOffset(column4, yPosition);
	            contentStream.showText("TL");
	            contentStream.endText();

	            yPosition -= lineHeight;
	        }
	    } else {
	        contentStream.beginText();
	        contentStream.newLineAtOffset(column1, yPosition);
	        contentStream.showText("Urun bulunamadi.");
	        contentStream.endText();
	        yPosition -= lineHeight;
	    }

	    // **Alt Çizgi**
	    yPosition -= 10;
	    contentStream.beginText();
	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	    contentStream.newLineAtOffset(margin, yPosition);
	    contentStream.showText("------------------------------------------------------------------");
	    contentStream.endText();
	    yPosition -= 20;

	    // **Ödeme Yöntemi**
	    contentStream.setFont(PDType1Font.HELVETICA, 10);
	    contentStream.beginText();
	    contentStream.newLineAtOffset(margin, yPosition);
	    contentStream.showText("Odeme Yontemi: Kredi Karti");
	    contentStream.endText();
	    yPosition -= 20;

	    // **Toplam Tutar (Sağdan sola hizalandı)**
	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	    contentStream.beginText();
	    contentStream.newLineAtOffset(margin, yPosition);
	    contentStream.showText("Toplam: ");
	    contentStream.endText();

	    float totalWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(String.valueOf(bill.getTotal_amount())) / 1000 * 12;
	    float totalX = column3 - totalWidth;

	    contentStream.beginText();
	    contentStream.newLineAtOffset(totalX, yPosition);
	    contentStream.showText(String.valueOf(bill.getTotal_amount()));
	    contentStream.endText();

	    contentStream.beginText();
	    contentStream.newLineAtOffset(column3 + 10, yPosition); // "+10" ile TL'yi biraz sola çekiyoruz
	    contentStream.showText("TL");
	    contentStream.endText();

	    yPosition -= 40;

	    // **"Afiyet Olsun!" mesajını tam ortaya almak için genişlik hesapla**
	    String thanksMessage = "Afiyet olsun! Yine bekleriz.";
	    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
	    float thanksWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(thanksMessage) / 1000 * 10;
	    float thanksX = (receiptSize.getWidth() - thanksWidth) / 2;

	    contentStream.beginText();
	    contentStream.newLineAtOffset(thanksX, yPosition);
	    contentStream.showText(thanksMessage);
	    contentStream.endText();

	    contentStream.close();

	    // **PDF Kaydet ve Aç**
	    Path tempFilePath = Files.createTempFile("receipt", ".pdf");
	    document.save(tempFilePath.toFile());
	    document.close();

	    openPDF(tempFilePath);
	}



	public static void openPDF(Path filePath) throws IOException {
	    if (Desktop.isDesktopSupported()) {
	        Desktop.getDesktop().open(filePath.toFile());
	    }
	}
	
	public static String convertTurkishToASCII(String text) {
	    return text.replace("Ş", "S")
	               .replace("Ğ", "G")
	               .replace("İ", "I")
	               .replace("Ü", "U")
	               .replace("Ö", "O")
	               .replace("Ç", "C")
	               .replace("ş", "s")
	               .replace("ğ", "g")
	               .replace("ı", "i")
	               .replace("ü", "u")
	               .replace("ö", "o")
	               .replace("ç", "c");
	}
}

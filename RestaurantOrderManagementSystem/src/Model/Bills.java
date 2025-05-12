package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Helper.DBConnection;

public class Bills {
	DBConnection conn = new DBConnection();
	Connection con = conn.connDB();
	private String bill_id;
	private int table_id, user_id, total_amount;
	private LocalDateTime billdate;
	private boolean billstatus;
	private List<BillItems> items;

	public Bills(String bill_id, int table_id, int user_id, int total_amount, LocalDateTime billdate,
			boolean billstatus) {
		super();
		this.bill_id = bill_id;
		this.table_id = table_id;
		this.user_id = user_id;
		this.total_amount = total_amount;
		this.billdate = billdate;
		this.billstatus = billstatus;
		this.items = new ArrayList<>();
	}

	public Bills() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean addBill(String billId, int tableId, int waiterID, int totalAmount, List<Integer> productIds,
			List<Integer> quantities) throws SQLException {

		boolean key = false;

		PreparedStatement billStmt = null;
		PreparedStatement itemStmt = null;
		try {
			con.setAutoCommit(false);

			String sqlBill = "INSERT INTO billtable (bill_id, table_id, user_id, total_amount, created_at, status) VALUES (?, ?, ?, ?,GETDATE(),1)";
			billStmt = con.prepareStatement(sqlBill);
			billStmt.setString(1, billId);
			billStmt.setInt(2, tableId);
			billStmt.setInt(3, waiterID);
			billStmt.setInt(4, totalAmount);
			billStmt.executeUpdate();

			String sqlItems = "INSERT INTO billitemtable (bill_id, item_id, qty) VALUES (?, ?, ?)";
			itemStmt = con.prepareStatement(sqlItems);

			for (int i = 0; i < productIds.size(); i++) {
				itemStmt.setString(1, billId);
				itemStmt.setInt(2, productIds.get(i));
				itemStmt.setInt(3, quantities.get(i));
				itemStmt.addBatch(); // Toplu işlem için
			}

			itemStmt.executeBatch();
			con.commit();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else
			return false;
	}

	public boolean updateBill(String billId, int totalAmount, List<Integer> productIds, List<Integer> quantities)
			throws SQLException {

		boolean key = false;
		PreparedStatement updateTotalStmt = null;
		PreparedStatement deleteItemsStmt = null;
		PreparedStatement insertItemStmt = null;

		try {
			con.setAutoCommit(false);

			String sqlUpdateTotal = "UPDATE billtable SET total_amount = ? WHERE bill_id = ?";
			updateTotalStmt = con.prepareStatement(sqlUpdateTotal);
			updateTotalStmt.setInt(1, totalAmount);
			updateTotalStmt.setString(2, billId);
			updateTotalStmt.executeUpdate();

			System.out.println("Silinmeye çalışılan bill_id: " + billId);

			String sqlDeleteItems = "DELETE FROM billitemtable WHERE bill_id = ?";
			deleteItemsStmt = con.prepareStatement(sqlDeleteItems);
			deleteItemsStmt.setString(1, billId);
			int rowsDeleted = deleteItemsStmt.executeUpdate();
			System.out.println("Silinen satır sayısı: " + rowsDeleted);

			System.out.println("Yeni eklenecek ürün sayısı: " + productIds.size());

			String sqlInsertItem = "INSERT INTO billitemtable (bill_id, item_id, qty) VALUES (?, ?, ?)";
			insertItemStmt = con.prepareStatement(sqlInsertItem);

			for (int i = 0; i < productIds.size(); i++) {
				insertItemStmt.setString(1, billId);
				insertItemStmt.setInt(2, productIds.get(i));
				insertItemStmt.setInt(3, quantities.get(i));
				insertItemStmt.addBatch();
			}

			insertItemStmt.executeBatch(); // Toplu ekleme
			con.commit();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback(); // Hata olursa işlemi geri al
		} finally {
			if (updateTotalStmt != null)
				updateTotalStmt.close();
			if (deleteItemsStmt != null)
				deleteItemsStmt.close();
			if (insertItemStmt != null)
				insertItemStmt.close();

			con.setAutoCommit(true);
		}

		return key;

	}

	public static Bills getBillByTableId(int tableId) throws SQLException {
		String sql = "SELECT b.bill_id, b.table_id, b.user_id, b.total_amount, b.created_at, b.status, "
				+ "bi.billitem_id, bi.item_id, bi.qty " + "FROM billtable b "
				+ "JOIN billitemtable bi ON b.bill_id = bi.bill_id " + "WHERE b.table_id = ?";
		DBConnection conn = new DBConnection();
		Connection con = conn.connDB();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tableId);
		ResultSet rs = stmt.executeQuery();

		Bills bill = null;
		List<BillItems> items = new ArrayList<>();

		while (rs.next()) {
			if (bill == null) {
				bill = new Bills(rs.getString("bill_id"), rs.getInt("table_id"), rs.getInt("user_id"),
						rs.getInt("total_amount"), rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getBoolean("status"));
			}
			// Ürünleri listeye ekle
			BillItems item = new BillItems(rs.getInt("billitem_id"), rs.getInt("item_id"), rs.getInt("qty"),
					rs.getString("bill_id"));
			items.add(item);
		}

		if (bill != null) {
			bill.setItems(items);
		}

		return bill;
	}

	public static Bills getBillWithTableID(int tableID) throws SQLException {
		Bills bill = null;
		List<BillItems> items = new ArrayList<>();

		String sql = "SELECT b.bill_id, b.table_id, b.user_id, b.total_amount, b.created_at, b.status, "
				+ "bi.billitem_id, bi.bill_id, bi.item_id, bi.qty " + "FROM billtable b "
				+ "JOIN billitemtable bi ON b.bill_id = bi.bill_id " + "WHERE b.table_id = ?";
		
		DBConnection conn = new DBConnection();
		Connection con = conn.connDB();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, tableID);

		ResultSet rs = ps.executeQuery();
		int userID = 0;
		String billid = "";
		int totalAmount = 0;
		LocalDateTime orderDate = null;
		boolean status = false;

		while (rs.next()) {

			if (bill == null) {
                billid = rs.getString("bill_id");
				userID = rs.getInt("user_id");
				totalAmount = rs.getInt("total_amount");
				orderDate = rs.getTimestamp("created_at").toLocalDateTime();
				status = rs.getBoolean("status");

				bill = new Bills(billid, tableID, userID, totalAmount, orderDate, status);

			}

			int billitemId = rs.getInt("billitem_id");
			int itemId = rs.getInt("item_id");
			int qty = rs.getInt("qty");

			items.add(new BillItems(billitemId, itemId, qty, billid));

		}

		if (bill != null) {
			bill.setItems(items);
		}

		return bill;
	}
	
	public boolean updateBillStatustoPaid(int tableid) throws SQLException {
		String query = "UPDATE billtable SET status=? WHERE table_id ="+tableid;
		boolean key = false;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, 0);
			ps.executeUpdate();
			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else
			return false;
	}

	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public LocalDateTime getBilldate() {
		return billdate;
	}

	public void setBilldate(LocalDateTime billdate) {
		this.billdate = billdate;
	}

	public boolean isBillstatus() {
		return billstatus;
	}

	public void setBillstatus(boolean billstatus) {
		this.billstatus = billstatus;
	}

	public List<BillItems> getItems() {
		return items;
	}

	public void addItem(BillItems item) {
		items.add(item);
	}

	public void setItems(List<BillItems> items) {
		this.items = items;
	}

}

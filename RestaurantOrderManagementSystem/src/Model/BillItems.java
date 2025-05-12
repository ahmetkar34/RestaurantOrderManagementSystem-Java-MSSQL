package Model;

import java.sql.Connection;

import Helper.DBConnection;

public class BillItems {
	DBConnection conn = new DBConnection();
	Connection con = conn.connDB();
	private int billitem_id,item_id,qty;
	private String bill_id;
	
	public BillItems(int billitem_id, int item_id, int qty, String bill_id) {
		super();
		this.billitem_id = billitem_id;
		this.item_id = item_id;
		this.qty = qty;
		this.bill_id = bill_id;
	}
	
	

	public BillItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBillitem_id() {
		return billitem_id;
	}

	public void setBillitem_id(int billitem_id) {
		this.billitem_id = billitem_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	
	
	
	

}

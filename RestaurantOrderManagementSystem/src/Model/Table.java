package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.DBConnection;

public class Table {
	int table_id, table_capacity;
	String table_name,table_location;
	boolean table_status;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDB();

	public Table(int table_id, int table_capacity, String table_name, String table_location, boolean table_status) {
		super();
		this.table_id = table_id;
		this.table_capacity = table_capacity;
		this.table_name = table_name;
		this.table_location = table_location;
		this.table_status = table_status;
	}

	public Table() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean addTable(String tableName,String tableLocation,int tableCapacity) throws SQLException {
		String query = "INSERT INTO tableDB (table_capacity,table_name,table_location,status) VALUES (?,?,?,'true') ";
		boolean key = false;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, tableCapacity);
			ps.setString(2, tableName);
			ps.setString(3, tableLocation);
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
	
	public boolean updateTableStatustoOccupied(int tableid) throws SQLException {
		String query = "UPDATE tableDB SET status=? WHERE table_id ="+tableid;
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
	
	public boolean updateTableStatustoAvailable(int tableid) throws SQLException {
		String query = "UPDATE tableDB SET status=? WHERE table_id ="+tableid;
		boolean key = false;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, 1);
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

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public int getTable_capacity() {
		return table_capacity;
	}

	public void setTable_capacity(int table_capacity) {
		this.table_capacity = table_capacity;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
	public String getTable_location() {
		return table_location;
	}

	public void setTable_location(String table_location) {
		this.table_location = table_location;
	}

	public boolean getTable_status() {
		return table_status;
	}

	public void setTable_status(boolean table_status) {
		this.table_status = table_status;
	}
	
	

}

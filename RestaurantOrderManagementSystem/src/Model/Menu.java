package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Menu {
	private int item_id,item_price;
	private String item_name,item_category;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDB();
	
	public Menu(int item_id, int item_price, String item_name, String item_category) {
		super();
		this.item_id = item_id;
		this.item_price = item_price;
		this.item_name = item_name;
		this.item_category = item_category;
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Menu> getMenuList() throws SQLException {
		ArrayList<Menu> list = new ArrayList<>();
		Menu obj;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM menutable ");
			while (rs.next()) {
				obj = new Menu(rs.getInt("item_id"),rs.getInt("price"),rs.getString("item"),rs.getString("category"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	
	
	
	
	
	
	
	

}

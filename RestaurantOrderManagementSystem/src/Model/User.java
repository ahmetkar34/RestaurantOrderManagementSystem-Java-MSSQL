package Model;

import Helper.DBConnection;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;

import Helper.*;

public class User {

	private int user_id;
	String username, userpassword, personname, userrole;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDB();

	public User(int user_id, String username, String userpassword, String personname, String userrole) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.userpassword = userpassword;
		this.personname = personname;
		this.userrole = userrole;
	}

	public User() {
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
	
	public ArrayList<Menu> getMenuListbyCategory(String itemCategory) throws SQLException {
		ArrayList<Menu> list = new ArrayList<>();
		Menu obj;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM menutable WHERE category = "+"'"+itemCategory+"'");
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
	
	public ArrayList<Table> getTableList() throws SQLException {
		ArrayList<Table> list = new ArrayList<>();
		Table obj;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tableDB ");
			while (rs.next()) {
				obj = new Table(rs.getInt("table_id"),rs.getInt("table_capacity"),rs.getString("table_name"),rs.getString("table_location"),rs.getBoolean("status"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	public ArrayList<Table> getAvailableTableList() throws SQLException {
		ArrayList<Table> list = new ArrayList<>();
		Table obj;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tableDB WHERE status=1 ");
			while (rs.next()) {
				obj = new Table(rs.getInt("table_id"),rs.getInt("table_capacity"),rs.getString("table_name"),rs.getString("table_location"),rs.getBoolean("status"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	public ArrayList<Table> getOccupiedTableList() throws SQLException {
		ArrayList<Table> list = new ArrayList<>();
		Table obj;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tableDB WHERE status=0 ");
			while (rs.next()) {
				obj = new Table(rs.getInt("table_id"),rs.getInt("table_capacity"),rs.getString("table_name"),rs.getString("table_location"),rs.getBoolean("status"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	public ArrayList<Table> getTableListbyFilter(ButtonGroup bLocation, ButtonGroup bCapacity, ButtonGroup bStatus) throws SQLException {
	    ArrayList<Table> list = new ArrayList<>();
	    Table obj;
	    String query = "SELECT * FROM tableDB WHERE 1=1 "; // Temel sorgu

	    // Kapasite için seçilen radio butonunu kontrol et
	    if (bCapacity.getSelection() != null) {
	        String capacity = bCapacity.getSelection().getActionCommand(); // ActionCommand'de kapasite değeri olduğunu varsayıyorum
	        query += " AND table_capacity = ?"; // Kapasite filtresi ekle
	    }

	    // Konum için seçilen radio butonunu kontrol et
	    if (bLocation.getSelection() != null) {
	        String location = bLocation.getSelection().getActionCommand(); // ActionCommand'de konum değeri olduğunu varsayıyorum
	        query += " AND table_location = ?"; // Konum filtresi ekle
	    }

	    // Durum için seçilen radio butonunu kontrol et
	    if (bStatus.getSelection() != null) {
	        String status = bStatus.getSelection().getActionCommand(); // ActionCommand'de durum değeri olduğunu varsayıyorum
	        query += " AND status = ?"; // Durum filtresi ekle
	    }

	    try {
	        PreparedStatement ps = con.prepareStatement(query);

	        // Seçilen değerler doğrultusunda parametreleri dinamik olarak ayarla
	        int parameterIndex = 1;

	        // Kapasiteyi ayarla
	        if (bCapacity.getSelection() != null) {
	            String capacity = bCapacity.getSelection().getActionCommand();
	            ps.setInt(parameterIndex++, Integer.parseInt(capacity)); // Kapasiteyi int olarak ayarla
	        }

	        // Konumu ayarla
	        if (bLocation.getSelection() != null) {
	            String location = bLocation.getSelection().getActionCommand();
	            ps.setString(parameterIndex++, location); // Konumu string olarak ayarla
	        }

	        // Durumu ayarla
	        if (bStatus.getSelection() != null) {
	            String status = bStatus.getSelection().getActionCommand();
	            ps.setBoolean(parameterIndex++, Boolean.parseBoolean(status)); // Durumu boolean olarak ayarla
	        }

	        // Sorguyu çalıştır
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            obj = new Table();
	            // ResultSet'ten verileri alıp Table objesine yerleştir
	            obj.setTable_id(rs.getInt("table_id"));
	            obj.setTable_capacity(rs.getInt("table_capacity"));
	            obj.setTable_name(rs.getString("table_name"));
	            obj.setTable_location(rs.getString("table_location"));
	            obj.setTable_status(rs.getBoolean("status"));
	            // Listeye ekle
	            list.add(obj);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}


	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}

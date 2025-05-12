package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.*;
import Model.*;

public class Manager extends User {

	Connection con = conn.connDB();

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int user_id, String username, String userpassword, String personname, String userrole) {
		super(user_id, username, userpassword, personname, userrole);
		// TODO Auto-generated constructor stub

	}
	
	public ArrayList<User> getStaffList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usertable ");
			while (rs.next()) {
				obj = new User(rs.getInt("user_id"), rs.getString("user_username"), rs.getString("user_password"),
						rs.getString("user_personname"), rs.getString("user_role"));
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	public Manager getFetch(int id) {
		Manager m = new Manager();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usertable WHERE user_id= "+id);
			while(rs.next()) {
				m.setUser_id(rs.getInt("user_id"));
				m.setUsername(rs.getString("user_username"));
				m.setUserpassword(rs.getString("user_password"));
				m.setPersonname(rs.getString("user_personname"));
				m.setUserrole(rs.getString("user_role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return m;
	}
	
	public String getSalary(int id) {
		String salary = null;
		
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM salarytable WHERE user_id= "+id);
			while(rs.next()) {
				salary = rs.getString("user_salary");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return salary;
	}
	
	
	
	
	
	
	
    // adds staff to user table and salary to salarytable
	public boolean addStaff(String username, String userpassword, String personname, String userrole, int salary)
			throws SQLException {
		String query = "INSERT INTO usertable" + "(user_personname,user_role,user_username,user_password) VALUES"
				+ "(?,?,?,?)";
		boolean key = false;
		ResultSet generatedKeys = null;
		PreparedStatement salaryStmt = null;
		PreparedStatement userStmt = null;
		try {
			// st = con.createStatement();
			userStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			userStmt.setString(1, personname);
			userStmt.setString(2, userrole);
			userStmt.setString(3, username);
			userStmt.setString(4, userpassword);
			int affectedRows = userStmt.executeUpdate();
			if (affectedRows > 0) {
				generatedKeys = userStmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					int userid = generatedKeys.getInt(1);
					// System.out.println(userid);
					String addSalaryQuery = "INSERT INTO salarytable (user_id,user_salary) VALUES (?,?) ";
					salaryStmt = con.prepareStatement(addSalaryQuery);
					salaryStmt.setInt(1, userid);
					salaryStmt.setInt(2, salary);
					salaryStmt.executeUpdate();
					key = true;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return true;
		} else
			return false;
	}
	
	public boolean deleteStaff(int id) throws SQLException {
		String query = "DELETE FROM usertable WHERE user_id=?";
		boolean key = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
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
	
	public boolean updateStaff(int id, String username, String userpassword, String personname, String userrole) throws SQLException {
		String query = "UPDATE usertable SET user_username = ? , user_password = ? , user_personname = ? , user_role = ? WHERE user_id=?";
		boolean key = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, userpassword);
			ps.setString(3, personname);
			ps.setString(4, userrole);
			ps.setInt(5, id);
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
	
	public boolean updateSalary(int id,int salary) throws SQLException {
		String query = "UPDATE salarytable SET user_salary = ?  WHERE user_id=?";
		boolean key = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, salary);
			ps.setInt(2, id);
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
	
	public boolean addMenu(String itemName,String itemType,int itemPrice) throws SQLException {
		String query = "INSERT INTO menutable (item,category,price) VALUES (?,?,?) ";
		boolean key = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, itemName);
			ps.setString(2, itemType);
			ps.setInt(3, itemPrice);
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
	
	public boolean updateMenuPrice(int itemid,int price) throws SQLException {
		String query = "UPDATE menutable SET price = ?  WHERE item_id=?";
		boolean key = false;
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, price);
			ps.setInt(2, itemid);
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

}

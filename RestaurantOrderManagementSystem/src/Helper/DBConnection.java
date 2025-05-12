package Helper;

import java.sql.*;

public class DBConnection {

	Connection c = null;

	public DBConnection() {

	}

	public Connection connDB() {
		try {
			String url = "**********************;databaseName=RestaurantOrderManagementSystemDB;integratedSecurity=true;encrypt=false";
			this.c = DriverManager.getConnection(url);
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

}

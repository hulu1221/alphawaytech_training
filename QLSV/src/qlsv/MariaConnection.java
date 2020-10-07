package qlsv;

import java.sql.Connection;
import java.sql.DriverManager;

public  class MariaConnection {

	public MariaConnection() {
    }
	public Connection getConnection (String dbURL, String userName, String password) {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("connecting to server " + dbURL + " ...");
			conn = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("connect successfully!");
		} catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
	}
}

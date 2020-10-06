package qlsv;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnection {
	/*	
	 * create connection
	 */
	public static Connection getConnection (String dbURL, String userName, String password) {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("connecting to server " + dbURL + " with username: '" + userName + "' and passowrd: '" + password + "'");
			conn = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("connect successfully!");
		} catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
	}
}

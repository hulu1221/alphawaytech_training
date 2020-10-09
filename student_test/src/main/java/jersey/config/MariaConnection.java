package jersey.config;

import java.sql.Connection;
import java.sql.DriverManager;
public class MariaConnection {
    private String dbURL;
    private String userName;
    private String password;
    public MariaConnection() {
        ApplicationProperties config = new ApplicationProperties().getInstance();
        this.dbURL = config.getProperty("host");
        this.userName = config.getProperty("username");
        this.password = config.getProperty("password");
    }
    public Connection getConnection () {
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

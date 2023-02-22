package gdbc;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectinManager {

    private static final String USER = "postgres";
    private static final String PASSWORD = "$Skobel2804";
    private static final String URL = "jdbc:postgresql://localhost:5432/skypro";

    private ConnectinManager(){

    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(USER,PASSWORD, URL);
    }
}

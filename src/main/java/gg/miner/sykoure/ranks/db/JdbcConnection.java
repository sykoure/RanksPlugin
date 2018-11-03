package gg.miner.sykoure.ranks.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
    private static String host = "localhost";
    private static String base = "miner_ranks";
    private static String user = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://" + host + "/" + base + "?serverTimezone=CET";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                System.err.println("Connection failed : " + e.getMessage());
            }
        }
        return connection;
    }
}

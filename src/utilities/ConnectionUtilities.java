package utilities;

import com.mysql.cj.protocol.Resultset;
import logging.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;

public class ConnectionUtilities {
    private static ConnectionUtilities instance = new ConnectionUtilities();
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tickets";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    private Connection connection;

    private ConnectionUtilities() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            Logger.getInstance().error(e.getMessage(), true);
        }
    }

    public static ConnectionUtilities getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void updateData(String query) {
        new Thread(() -> {
            try {
                Statement stm = connection.createStatement();
                stm.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public ResultSet selectData(String query) {
        ResultSet rs = null;

        try {
            Statement stm = connection.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}

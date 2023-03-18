package net.jack.atlas.backend;

import org.mariadb.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {


    private Connection connection = null;
    String url = "jdbc:mariadb://localhost:3306/records_db";
    String user = "root";
    String password = "root";

    public Connection connect() throws SQLException {

        connection = (Connection) DriverManager.getConnection(url, user, password);
        System.out.println("MariaDB: Connected Successfully.");
        return connection;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

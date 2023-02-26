package net.jack.atlas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;

    public void connect() throws SQLException {
        String DATABASE = "user_data";
        String USERNAME = "root";
        int PORT = 3306;
        String HOST = "localhost";
        String PASSWORD = "";
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" +
                DATABASE + "?useSSL=false", USERNAME, PASSWORD);
    }

    public boolean isConnected() {
        return  connection != null;
    }

    public Connection getConnection() {
        return connection;
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


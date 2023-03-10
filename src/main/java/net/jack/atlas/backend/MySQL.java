package net.jack.atlas.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "records_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private Connection connection = null;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" +
                    DATABASE + "?useSSL=false", USERNAME, PASSWORD);
            System.out.println("MySQL: Connected Successfully");
        } catch (SQLException e) {
            System.out.println("MySQL: Connection failed");
            e.printStackTrace();
        }

        return connection;
    }

    public boolean isConnected() {
        return  connection != null;
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
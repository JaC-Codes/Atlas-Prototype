package net.jack.atlas.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL {


    Connection connection = null;

    public PostgreSQL() {
    }


    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/records_db", "postgres", "root");
            System.out.println("PostgreSQL: Connected Successfully");
        } catch (SQLException e) {
            System.out.println("PostgreSQL: Connection failed");
            e.printStackTrace();
        }

        return connection;

    }

    public boolean connected() {
        return connection != null;
    }

    public void disconnect() {
        if (connected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package net.jack.atlas.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL {


    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/registration", "postgres", "root");

    public PostgreSQL() throws SQLException {
    }

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");

            if (connection != null) {
                System.out.println("PostgreSQL: Connected Successfully");
            } else {
                System.out.println("PostgreSQL: Connection failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;

    }
}

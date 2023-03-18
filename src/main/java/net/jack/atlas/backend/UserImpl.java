package net.jack.atlas.backend;


import org.bson.Document;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class UserImpl {

    private final MySQL mySql;
    private final MongoDB mongoDB;
    private final PostgreSQL postgreSQL;
    private final MariaDB mariaDB;
    private final Init init;

    private final Document document;
    private final Scanner scanner;


    String[] requestList = {"forename", "surname", "address", "postcode", "dob", "emergencyContact",
            "allergies", "testResults", "medication", "mentalHealthInfo", "currentTreatment", "pastTreatment"
    }; // 11

    public UserImpl(Init init) throws SQLException, FileNotFoundException, ClassNotFoundException {
        this.mongoDB = new MongoDB();
        this.mySql = new MySQL();
        this.postgreSQL = new PostgreSQL();
        this.mariaDB = new MariaDB();
        this.init = init;

        this.document = new Document();
        this.scanner = new Scanner(System.in);

    }

    public void SQLInput(Scanner scanner) {

        PreparedStatement ps = null;
        try {

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO person_records (id,");
            for (String item : requestList) {
                sb.append(item);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");

            for (int i = 0; i < requestList.length + 1; i++) {
                sb.append("?");
                if (i < requestList.length) {
                    sb.append(",");
                }
            }

            sb.append(")");
            String sql = sb.toString();



            String db = init.getKey();

            try {
            switch (db) {
                case "MySQL" -> ps = mySql.connect().prepareStatement(sql);
                case "PostgreSQL" -> ps = postgreSQL.connect().prepareStatement(sql);
                case "MariaDB" -> ps = mariaDB.connect().prepareStatement(sql);
            }
            } catch (Exception e) {
                e.printStackTrace();
                }



            if (ps != null) {
                ps.setString(1, generateId());
            }

            for (int i = 0; i < requestList.length; i++) {
                request(requestList[i]);
                String value = scanner.nextLine();
                if (ps != null) {
                    ps.setString(i + 2, value);
                }
            }

            if (ps != null) {
                ps.executeUpdate();
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void MongoDBInput(Scanner scanner) {

        for (String i : requestList) {
            request(i);
            String value = scanner.nextLine();
            document.put(i, value);
        }
        mongoDB.getMongo().insertOne(document);
        mongoDB.disconnect();
    }

    private void request(String message) {
        System.out.println(message);
    }

    private String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString;
    }

}

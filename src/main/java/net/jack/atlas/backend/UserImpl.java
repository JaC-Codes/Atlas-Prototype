package net.jack.atlas.backend;


import net.jack.atlas.Atlas;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class UserImpl {

    private final Atlas atlas;
    private final MySQL mySql;
    private final MongoDB mongoDB;
    private final Document document;
    private final Scanner scanner;


    String[] requestList = {"forename", "surname", "address", "postcode", "dob", "emergencyContact",
            "allergies", "testResults", "medication", "mentalHealthInfo", "currentTreatment", "pastTreatment"
    }; // 11

    public UserImpl() throws SQLException {
        this.atlas = new Atlas();
        this.mongoDB = new MongoDB();
        this.mySql = new MySQL();
        this.document = new Document();
        this.scanner = new Scanner(System.in);

    }

    public void MySQLInput(Scanner scanner, MySQL mySql) throws SQLException {

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


            PreparedStatement ps = mySql.connect().prepareStatement(sql);
            ps.setString(1, generateId());

            for (int i = 0; i < requestList.length; i++) {
                request(requestList[i]);
                String value = scanner.nextLine();
                ps.setString(i + 2, value);
            }

            ps.executeUpdate();
            mySql.disconnect();
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

    }

    public void request(String message) {
        System.out.println(message);
    }

    public String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString;
    }

    public void routeDirection(Scanner scanner) throws SQLException {
        if (mySql.isConnected()) {
            MySQLInput(scanner, mySql);
      //  } else if (mongoDB.isConnected()) {
      //      mongoInput(scanner);
        }
    }
}

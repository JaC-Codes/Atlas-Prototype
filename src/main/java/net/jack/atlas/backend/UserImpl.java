package net.jack.atlas.backend;


import net.jack.atlas.Atlas;
import net.jack.atlas.database.MongoDB;
import net.jack.atlas.database.MySQL;
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
    };

    public UserImpl() throws SQLException {
        this.atlas = new Atlas();
        this.mongoDB = new MongoDB();
        this.mySql = new MySQL();
        this.document = new Document();
        this.scanner = new Scanner(System.in);

        init();
    }

    public void init() throws SQLException {
        if (atlas.getMongoDB()) {
            mongoInput(scanner);
        } else if (atlas.getSQL()) {
            mySqlInput(scanner, mySql);
        } else {
            System.out.println("ERROR: Cannot find boolean values");
        }
    }


    public void mySqlInput(Scanner scanner, MySQL mySql) throws SQLException {
        PreparedStatement ps = mySql.getConnection().prepareStatement
                ("INSERT INTO person_records VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
        ps.setString(1, generateId());
        for (int i = 0; i < requestList.length; i++) {
            for (int j = 2; j < requestList.length + 2; i++) {
                request(requestList[i]);
                String value = scanner.nextLine();
                ps.setString(j, value);
            }
        }

        mySql.disconnect();
    }

    public void mongoInput(Scanner scanner) {

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
}

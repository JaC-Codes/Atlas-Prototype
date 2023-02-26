package net.jack.atlas.backend;


import net.jack.atlas.Atlas;
import net.jack.atlas.database.MongoDB;
import net.jack.atlas.database.MySQL;
import org.bson.Document;

import java.util.Scanner;

public class UserImpl {

    private final Atlas atlas;
    private final MySQL mySQL;
    private final MongoDB mongoDB;
    private final Document document;
    private final Scanner scanner;

    // Forename, Surname, Address, PostCode, Age, DOB, Emergency Contact, Allergies, Test Results, Medication, Mental Health Info, Current/Past Treatment

    String[] requestList = {"forename", "surname", "address", "postcode", "dob", "emergencyContact",
            "allergies", "testResults", "medication", "mentalHealthInfo", "currentTreatment", "pastTreatment"
    };

    public UserImpl() {
        this.atlas = new Atlas();
        this.mongoDB = new MongoDB();
        this.mySQL = new MySQL();
        this.document = new Document();
        this.scanner = new Scanner(System.in);

        init();
    }

    public void init() {
        if (atlas.getMongoDB()) {
            mongoInput(scanner);
        } else if (atlas.getSQL()) {
            mySqlInput(scanner, mySQL);
        } else {
            System.out.println("ERROR: Cannot find boolean values");
        }
    }


    public void mySqlInput(Scanner scanner, MySQL mySQL) {

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
}

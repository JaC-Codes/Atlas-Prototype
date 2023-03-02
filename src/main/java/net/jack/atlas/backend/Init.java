package net.jack.atlas.backend;


import net.jack.atlas.Atlas;
import net.jack.atlas.database.MongoDB;
import net.jack.atlas.database.MySQL;

import javax.json.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

public class Init {

    private final MongoDB mongo;
    private final MySQL sql;
    private final Atlas atlas;

    private String dbChoice;


    public Init() {
        this.atlas = new Atlas();
        this.mongo = new MongoDB();
        this.sql = new MySQL();
    }

    public void boot(Scanner scanner) throws IOException {

        InputStream inputStream = Atlas.class.getClassLoader().getResourceAsStream("config.json");
        JsonReader reader = Json.createReader(inputStream);


        dbSwitch(scanner, dbChoice);

        boolean dbCheck = reader.readObject().getBoolean("MYSQL");


        if (dbCheck) {
            atlas.setMySQL(true);
            atlas.setMongoDB(false);
        } else {
            atlas.setMySQL(false);
            atlas.setMongoDB(true);
        }
    }

    public void dbSelect() throws SQLException {
        Boolean mongoDB = atlas.getMongoDB();
        if (mongoDB) {
            mongo.connect();
            System.out.println("MongoDB: Connected Successfully");
        } else {
            sql.connect();
            System.out.println("MySql: Connected Successfully");
        }
    }

    public void dbSwitch(Scanner scanner, String dbChoice) {
        System.out.println("What database would you like to boot up with?");
        System.out.println("Type 'mongo' for MongoDB or 'mysql' for MySql.");
        String db = scanner.nextLine();
        switch (db) {
            case "mongo" -> {
                setDbChoice(db);
                System.out.println("MongoDB selected.");
            }
            case "mysql" -> {
                setDbChoice(db);
                System.out.println("MySql selected.");
            }
        }
    }

    public String getDbChoice() {
        return dbChoice;
    }

    public void setDbChoice(String dbChoice) {
        this.dbChoice = dbChoice;
    }
}

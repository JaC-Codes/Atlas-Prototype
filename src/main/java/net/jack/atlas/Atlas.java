package net.jack.atlas;

import net.jack.atlas.backend.UserImpl;
import net.jack.atlas.database.MongoDB;
import net.jack.atlas.database.MySQL;


import javax.json.Json;
import javax.json.JsonReader;
import java.io.InputStream;

public class Atlas {

    private static final MongoDB mongo = new MongoDB();
    private static final MySQL sql = new MySQL();

    protected static Boolean mongoDB;
    protected static Boolean mySQL;

    public static void main(String[] args) {

        InputStream inputStream;
        try {
            inputStream = Atlas.class.getClassLoader().getResourceAsStream("config.json");
            JsonReader reader = Json.createReader(inputStream);
            boolean dbCheck = reader.readObject().getBoolean("MYSQL");
            reader.close();
            if (dbCheck) {
                mySQL = true;
                mongoDB = false;
            } else {
                mongoDB = true;
                mySQL = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (mongoDB) {
                mongo.connect();
                System.out.println("Connected Successfully");
            } else {
                sql.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new UserImpl();
    }

    public Boolean getSQL() {
        return mySQL;
    }

    public Boolean getMongoDB() {
        return mongoDB;
    }
}


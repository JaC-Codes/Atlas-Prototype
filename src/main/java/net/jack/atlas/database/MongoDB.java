package net.jack.atlas.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection collection;

    public void connect() {
        client = new MongoClient();
        database = client.getDatabase("records_db");
        collection = database.getCollection("person_records");
    }

    public MongoCollection getMongo() {
        return collection;
    }

}


package net.jack.atlas.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {

    public MongoDB() {

    }

    private static MongoCollection<Document> collection;

    public void connect() {
        try (MongoClient client = new MongoClient()) {
            MongoDatabase database = client.getDatabase("records_db");
            collection = database.getCollection("person_records");
        }
    }

    public MongoCollection<Document> getMongo() {
        return collection;
    }

}


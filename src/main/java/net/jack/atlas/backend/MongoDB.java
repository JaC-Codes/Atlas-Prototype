package net.jack.atlas.backend;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {

    MongoClient client = new MongoClient();

    private static MongoCollection<Document> collection;

    public void connect() {
        try {
            MongoDatabase database = client.getDatabase("records_db");
            collection = database.getCollection("person_records");
            System.out.println("MongoDB: Connected Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* public Boolean isConnected() {
        if (client.getAddress() == null) return false;
        return null;
    }

    */

    public MongoCollection<Document> getMongo() {
        return collection;
    }

}


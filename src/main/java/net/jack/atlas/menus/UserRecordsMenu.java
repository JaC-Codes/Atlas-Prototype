package net.jack.atlas.menus;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import net.jack.atlas.backend.Init;
import net.jack.atlas.backend.MongoDB;
import org.bson.Document;

import java.util.Scanner;


public class UserRecordsMenu {


    private final Init init;
    private final Scanner scanner;
    private final MongoDB mongo;

    private String queryLabel;

    private String field;

    UserRecordsMenu(Init init) {
        this.init = init;
        this.mongo = new MongoDB();

        this.scanner = new Scanner(System.in);
    }


    public void recordsMenu() {
        System.out.println("Please select which field you will be searching for");
        System.out.println("Forename, Surname, Address");
        String fieldChoice = scanner.nextLine();


        switch (fieldChoice) {
            case "Forename" -> setField("Forename");
            case "Surname" -> setField("Surname");
            case "Address" -> setField("Address");
        }

        System.out.println("Persons " + field + ":");
        this.queryLabel = scanner.nextLine();

        // For Mongo

        switch (init.getDb()) {
            case "MongoDB" -> mongoIterate();
            case "MySQL" -> System.out.println("");
        }
    }

    public void mongoIterate() {
        Document search = new Document();
        search.put(getField(), this.queryLabel);
        FindIterable<Document> cursor = mongo.getMongo().find(search);


        try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
            while (cursorIterator.hasNext()) {
                System.out.println(cursorIterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Init getInit() {
        return init;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

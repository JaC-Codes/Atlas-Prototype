package net.jack.atlas.menus;

import net.jack.atlas.backend.DatabaseSettings;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.util.Scanner;

public class Menu {

    private final UserRecordsMenu userRecordsMenu;
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.userRecordsMenu = new UserRecordsMenu();

        menuInit(scanner);
    }


    public void menuInit(Scanner scanner) {
        System.out.println("Welcome to Atlas. \nMenu Loading up now...");

        System.out.println("""

                Atlas Menu

                Please choose from the following.""");
        System.out.println("A: User Records Search");
        System.out.println("B: User Records Input");
        System.out.println("C: Database Selection\n");

        String userRoute = scanner.nextLine();


        switch(userRoute) {
            case "A" -> System.out.println("");
            case "B" -> System.out.println("t");
            case "C" -> System.out.println();
        }
    }

}

package net.jack.atlas.menus;

import net.jack.atlas.backend.Init;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private final UserRecordsMenu userRecordsMenu;
    private final Init init;


    public Menu(Scanner scanner) throws SQLException, FileNotFoundException, ClassNotFoundException {
        this.init = new Init();
        this.userRecordsMenu = new UserRecordsMenu(init);

        init.databaseInitialize();
        menuInit(scanner);
    }


    public void menuInit(Scanner scanner) throws SQLException, FileNotFoundException, ClassNotFoundException {

        System.out.println("Welcome to Atlas. \nMenu Loading up now...");

        System.out.println("""

                Atlas Menu

                Please choose from the following.""");
        System.out.println("A: User Records Search");    /* Searches through db for specific data, dependent on database!! */
        System.out.println("B: User Records Input");     /* Inputs records (Init method already done) */

        String userRoute = scanner.nextLine();


        switch(userRoute) {
            case "A" -> userRecordsMenu.recordsMenu();
            case "B" -> init.methodInvoke();
            case "C" -> System.out.println();
        }
    }

}

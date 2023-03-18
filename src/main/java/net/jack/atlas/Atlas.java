package net.jack.atlas;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import net.jack.atlas.backend.Test;
import net.jack.atlas.menus.Menu;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Atlas {


    private static final Test test = new Test();

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException, FileNotFoundException, ClassNotFoundException {
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
        new Menu(scanner);
    }
}


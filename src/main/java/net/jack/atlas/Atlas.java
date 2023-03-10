package net.jack.atlas;


import net.jack.atlas.backend.Test;
import net.jack.atlas.menus.Menu;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Atlas {


    private static final Test test = new Test();

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException, FileNotFoundException, ClassNotFoundException {
        new Menu(scanner);
    }
}


package net.jack.atlas;

import net.jack.atlas.database.Init;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Atlas {


    private static final Init init;

    static {
        try {
            init = new Init();
        } catch (SQLException | FileNotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

    }
}


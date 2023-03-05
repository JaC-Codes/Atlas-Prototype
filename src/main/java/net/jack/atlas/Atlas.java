package net.jack.atlas;

import net.jack.atlas.backend.Init;
import net.jack.atlas.backend.UserImpl;
import net.jack.atlas.database.MySQL;

import java.sql.SQLException;
import java.util.Scanner;

public class Atlas {

    /*

            *NEED TO DO*

       Add postgresql
       Fix sql connection issue being null
       Add boot up configuration to select database
       Falsify all other db's inside config

     */

    private static final Init init = new Init();
    private static final MySQL sql = new MySQL();
    private static final Scanner scanner = new Scanner(System.in);

    private static Boolean mongoDB;
    private static Boolean mySQL;

    public static void main(String[] args) throws SQLException {
        try {
            init.boot(scanner);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            init.dbSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new UserImpl();
    }

    public void setMongoDB(Boolean mongoDB) {
        Atlas.mongoDB = mongoDB;
    }

    public void setMySQL(Boolean mySQL) {
        Atlas.mySQL = mySQL;
    }

    public Boolean getSQL() {
        return mySQL;
    }

    public Boolean getMongoDB() {
        return mongoDB;
    }
}


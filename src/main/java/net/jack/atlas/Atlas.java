package net.jack.atlas;

import net.jack.atlas.backend.DatabaseSettings;
import net.jack.atlas.backend.Init;
import net.jack.atlas.backend.UserImpl;
import net.jack.atlas.database.MySQL;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Atlas {

    /*

            *NEED TO DO*

       Add postgresql
       Add boot up configuration to select database
       Falsify all other db's inside config

     */

    private static final Init init;

    static {
        try {
            init = new Init();
        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final MySQL sql = new MySQL();
    private static final Scanner scanner = new Scanner(System.in);

    private static Boolean mongoDB;
    private static Boolean mySQL;
    private static Boolean postgreSQL;

    public static void main(String[] args) throws SQLException, FileNotFoundException {
       //new Init();

       // new UserImpl();
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

    public Boolean getMySQL() {
        return mySQL;
    }

    public Boolean getPostgreSQL() {
        return postgreSQL;
    }

    public void setPostgreSQL(Boolean postgreSQL) {
        Atlas.postgreSQL = postgreSQL;
    }
}


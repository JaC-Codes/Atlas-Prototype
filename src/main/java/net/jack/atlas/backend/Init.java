package net.jack.atlas.backend;


import net.jack.atlas.Atlas;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class Init implements DatabaseSettings {

    private final net.jack.atlas.backend.MongoDB MongoDB;
    private final net.jack.atlas.backend.MySQL MySQL;
    private final net.jack.atlas.backend.PostgreSQL PostgreSQL;
    private final Atlas atlas;
    private final UserImpl userImpl;

    private final Yaml yaml;
    private final Map<String, Object> data;
    private final Scanner scanner;

    private String dbChoice;
    private String negateDb;

    InputStream inputStream = new FileInputStream(new File("C:\\Users\\jackc\\Desktop\\Atlas-Prototype\\src\\main\\resources\\database.yml"));


    public Init() throws SQLException, FileNotFoundException, ClassNotFoundException {
        super();
        this.yaml = new Yaml();
        this.scanner = new Scanner(System.in);
        this.data = yaml.load(inputStream);
        this.atlas = new Atlas();
        this.MongoDB = new MongoDB();
        this.userImpl = new UserImpl();
        this.MySQL = new MySQL();
        this.PostgreSQL = new PostgreSQL();

        databaseInitialize();
    }


    @Override
    public void databaseInitialize() {
        @SuppressWarnings("unchecked")
        Map<String, Object> dbMaps = (Map<String, Object>) data.get("Database");


        for (Map.Entry<String, Object> entry : dbMaps.entrySet()) {
            String key = entry.getKey();
            Object checks = entry.getValue();

            if (checks.equals(Boolean.TRUE)) {
                try {
                    Class<?> clazz = Class.forName("net.jack.atlas.backend." + key);
                    Object database = clazz.getDeclaredConstructor().newInstance();
                    Method connectMethod = clazz.getDeclaredMethod("connect");
                    connectMethod.invoke(database);

                    Class<?> c2 = Class.forName("net.jack.atlas.backend." + "UserImpl");
                    Object classInst = c2.getDeclaredConstructor().newInstance();
                    Method runner = c2.getDeclaredMethod(key + "Input", Scanner.class);
                    runner.invoke(classInst, scanner);


                    break;
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


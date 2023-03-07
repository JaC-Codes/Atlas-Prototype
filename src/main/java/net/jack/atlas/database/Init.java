package net.jack.atlas.database;


import net.jack.atlas.Atlas;
import net.jack.atlas.backend.DatabaseSettings;
import net.jack.atlas.database.MongoDB;
import net.jack.atlas.database.MySQL;
import net.jack.atlas.database.PostgreSQL;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;

public class Init implements DatabaseSettings {

    private final net.jack.atlas.database.MongoDB MongoDB;
    private final net.jack.atlas.database.MySQL MySQL;
    private final net.jack.atlas.database.PostgreSQL PostgreSQL;
    private final Atlas atlas;

    private final Yaml yaml;
    private final Map<String, Object> data;

    private String dbChoice;
    private String negateDb;

    InputStream inputStream = new FileInputStream(new File("C:\\Users\\jackc\\Desktop\\Atlas-Prototype\\src\\main\\resources\\database.yml"));


    public Init() throws SQLException, FileNotFoundException, ClassNotFoundException {
        super();
        this.yaml = new Yaml();
        this.data = yaml.load(inputStream);
        this.atlas = new Atlas();
        this.MongoDB = new MongoDB();
        this.MySQL = new MySQL();
        this.PostgreSQL = new PostgreSQL();

        databaseSelect();
    }

    @Override
    public void databaseSelect() {
        @SuppressWarnings("unchecked")
        Map<String, Object> dbMaps = (Map<String, Object>) data.get("Database");

        for (Map.Entry<String, Object> entry : dbMaps.entrySet()) {
            String key = entry.getKey();
            Object checks = entry.getValue();

            if (checks.equals(Boolean.TRUE)) {
                var selectedDatabase = key;
                try {
                    Class<?> clazz = Class.forName("net.jack.atlas.database." + selectedDatabase);
                    Object database = clazz.getDeclaredConstructor().newInstance();
                    Method connectMethod = clazz.getDeclaredMethod("connect");
                    connectMethod.invoke(database);
                    break;
                }catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                        IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


package net.jack.atlas.backend;


import net.jack.atlas.Atlas;
import net.jack.atlas.database.MongoDB;
import net.jack.atlas.database.MySQL;
import net.jack.atlas.database.PostgreSQL;
import org.yaml.snakeyaml.Yaml;


import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Init implements DatabaseSettings {

    private final MongoDB mongo;
    private final MySQL sql;
    private final Atlas atlas;
    private final PostgreSQL postgre;
    private final Yaml yaml;
    private final Map<String, Object> data;

    private String dbChoice;
    private String negateDb;

    InputStream inputStream = new FileInputStream(new File("C:\\Users\\jackc\\Desktop\\Atlas-Prototype\\src\\main\\resources\\database.yml"));


    public Init() throws SQLException, FileNotFoundException {
        super();
        this.yaml = new Yaml();
        this.data = yaml.load(inputStream);
        this.atlas = new Atlas();
        this.mongo = new MongoDB();
        this.sql = new MySQL();
        this.postgre = new PostgreSQL();

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
                System.out.println(key + ": " + checks);
            }
        }
    }


}


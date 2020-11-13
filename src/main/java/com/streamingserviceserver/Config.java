package com.streamingserviceserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static final String DB_URL;
    public static final String DB_USER_ID;
    public static final String DB_PASSWORD;
    public static final boolean SEED_DB;

    /**
     * Finds the config file(s) and populates the static fields with the property objects generated by those files
     */
    static {
        String rootConfigPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        String dbConfigPath = rootConfigPath + "dbconfig.properties";

        Properties dbConfig = new Properties();

        try {
            dbConfig.load(new FileInputStream(dbConfigPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB_URL = dbConfig.getProperty("url");
        DB_USER_ID = dbConfig.getProperty("userId");
        DB_PASSWORD = dbConfig.getProperty("password");
        SEED_DB = Boolean.parseBoolean(dbConfig.getProperty("seedDb"));
    }
}

package ua.com.juja.sqlcmd.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by max on 23.10.2015.
 */
public class Configurator {
    private String filePath = "sqlcmd.properties";
    private Properties properties;

    public Configurator() {
        properties = new Properties();
        File file = new File(filePath);

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error loading config: " + file.getAbsolutePath());
        }
    }

    public String getPassword() {
        return properties.getProperty("password", "");
    }

    public String getDatabase() {
        return properties.getProperty("database", "");
    }

    public String getName() {
        return properties.getProperty("name", "");
    }
}

package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;

/**
 * Created by max on 17.11.2015.
 */

public class DataBaseManagerFactoryImpl implements DataBaseManagerFactory {

    private String className;

    @Override
    public DatabaseManager createDataBaseManager() {
        try {
            return (DatabaseManager) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }
}


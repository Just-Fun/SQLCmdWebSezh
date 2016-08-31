package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;

/**
 * Created by max on 17.11.2015.
 */

public interface DataBaseManagerFactory {
    DatabaseManager createDataBaseManager();
}

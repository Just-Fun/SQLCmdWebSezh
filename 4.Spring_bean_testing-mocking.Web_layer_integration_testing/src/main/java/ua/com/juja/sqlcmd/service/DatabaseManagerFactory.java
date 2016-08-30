package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;

/**
 * Created by oleksandr.baglai on 13.11.2015.
 */
public interface DatabaseManagerFactory {
    DatabaseManager createDatabaseManager();
}

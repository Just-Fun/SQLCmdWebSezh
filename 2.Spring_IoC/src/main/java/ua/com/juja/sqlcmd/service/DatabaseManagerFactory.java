package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;

public interface DatabaseManagerFactory {
    DatabaseManager createDatabaseManager();
}

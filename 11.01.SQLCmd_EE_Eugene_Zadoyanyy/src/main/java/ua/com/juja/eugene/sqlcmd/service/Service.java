package ua.com.juja.eugene.sqlcmd.service;

import ua.com.juja.eugene.sqlcmd.controller.UserActionLog;
import ua.com.juja.eugene.sqlcmd.model.databasemanager.DatabaseManager;

import java.util.List;

public interface Service {

    void connectToDatabase(DatabaseManager manager, String database);

    DatabaseManager connectToServer(String username, String password);

    void clearTable(DatabaseManager manager, String table);

    void createDatabase(DatabaseManager manager, String database);

    void createEntry(DatabaseManager manager, String table, String[] values);

    void createTable(DatabaseManager manager, String query);

    void disconnectFromDatabase(DatabaseManager manager);

    void dropDatabase(DatabaseManager manager, String database);

    void dropTable(DatabaseManager manager, String table);

    List<UserActionLog> findActionsByUsername(String username);

    List<List<String>> getTableData(DatabaseManager manager, String table);

    void updateRecord(DatabaseManager manager, String table, String[] values);
}

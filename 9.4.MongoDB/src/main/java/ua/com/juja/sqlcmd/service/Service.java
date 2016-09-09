package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.entity.DatabaseConnection;
import ua.com.juja.sqlcmd.model.entity.Description;
import ua.com.juja.sqlcmd.controller.UserActionLog;

import java.util.List;
import java.util.Set;

/**
 * Created by oleksandr.baglai on 30.10.2015.
 */
public interface Service {

    List<String> commandsList();

    List<Description> commandsDescription();

    DatabaseManager connect(String databaseName, String userName, String password);

    List<List<String>> find(DatabaseManager manager, String tableName);

    Set<String> tables(DatabaseManager manager);

    DatabaseConnection getDataFor(String userName, String dbName);

    List<UserActionLog> getAllFor(String userName);
}

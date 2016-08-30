package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.List;
import java.util.Set;

/**
 * Created by oleksandr.baglai on 30.10.2015.
 */
public interface Service {

    List<String> commandsList();

    DatabaseManager connect(String databaseName, String userName, String password);

    List<List<String>> find(DatabaseManager manager, String tableName);

    Set<String> tables(DatabaseManager manager);
}

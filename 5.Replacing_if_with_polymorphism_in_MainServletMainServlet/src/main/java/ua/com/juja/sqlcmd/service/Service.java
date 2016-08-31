package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.PGException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by max on 01.11.2015.
 */
public interface Service {
    Set<String> tablesList(DatabaseManager manager);

    List<String> commandsList();

    DatabaseManager connect(String databaseName, String userName, String password) throws SQLException, ClassNotFoundException;

    List<DataSet> tableData(DatabaseManager manager, String tableName);

    List<DataSet> tableDataById(DatabaseManager manager, String tableName, int id);

    Set<String> tableHead(DatabaseManager manager, String tableName);

    void clearTable(DatabaseManager manager, String tableName);

    void create(DatabaseManager manager, String tableName, String[] paramName, String[] paramValues) throws PGException;

    void update(DatabaseManager manager, String tableName, String[] paramName, String[] paramValues);
}

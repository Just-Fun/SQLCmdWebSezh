package ua.com.juja.sqlcmd.controller.web;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public class NullDatabaseManager implements DatabaseManager {
    @Override
    public List<DataSet> getTableData(String tableName) {
        return new LinkedList<>();
    }

    @Override
    public int getSize(String tableName) {
        return 0;
    }

    @Override
    public Set<String> getTableNames() {
        return new HashSet<>();
    }

    @Override
    public void connect(String database, String userName, String password) {
        // do nothing
    }

    @Override
    public void clear(String tableName) {
        // do nothing
    }

    @Override
    public void create(String tableName, DataSet input) {
        // do nothing
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        // do nothing
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        return new HashSet<>();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public List<DataSet> getTableDataById(String tableName, int id) {
        return new LinkedList<>();
    }
}

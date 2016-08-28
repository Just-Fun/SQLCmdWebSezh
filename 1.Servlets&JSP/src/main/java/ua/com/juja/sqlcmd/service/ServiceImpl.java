package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ServiceImpl implements Service {

    private DatabaseManager manager;

    public ServiceImpl() {
        manager = new JDBCDatabaseManager();
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect", "tables");
    }

    @Override
    public void connect(String databaseName, String userName, String password) {
        manager.connect(databaseName, userName, password);
    }

    @Override
    public Set<String> getTables() {
        return manager.getTableNames();
    }

}

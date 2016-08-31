package ua.com.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by max on 01.11.2015.
 */
@Component
public class ServiceImpl implements Service {


    @Autowired
    private DataBaseManagerFactory factory;

    @Override
    public DatabaseManager connect(String databaseName, String userName, String password) {
        DatabaseManager manager = factory.createDataBaseManager();
        manager.connect(databaseName, userName, password);
        return manager;
    }

    public Set<String> tablesList(DatabaseManager manager) {
        return manager.getTableNames();
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect", "tables");
    }

    @Override
    public List<DataSet> tableData(DatabaseManager manager, String tableName) {
        return manager.getTableData(tableName);
    }

    @Override
    public List<DataSet> tableDataById(DatabaseManager manager, String tableName, int id) {
        return manager.getTableDataById(tableName, id);
    }

    @Override
    public Set<String> tableHead(DatabaseManager manager, String tableName) {
        return manager.getTableColumns(tableName);
    }

    @Override
    public void clearTable(DatabaseManager manager, String tableName) {
        manager.clear(tableName);
    }

    @Override
    public void create(DatabaseManager manager, String tableName, String[] paramName, String[] paramValues) {
        DataSet input = new DataSetImpl();

        for (int i = 0; i < paramName.length; i++) {
            input.put(paramName[i], paramValues[i]);
        }

        manager.create(tableName, input);
    }

    @Override
    public void update(DatabaseManager manager, String tableName, String[] paramName, String[] paramValues) {
        DataSet input = new DataSetImpl();

        for (int i = 0; i < paramName.length; i++) {
            input.put(paramName[i], paramValues[i]);
        }

        Integer id = Integer.valueOf((String) input.get("id"));
        manager.update(tableName, id.intValue(), input);
    }
}

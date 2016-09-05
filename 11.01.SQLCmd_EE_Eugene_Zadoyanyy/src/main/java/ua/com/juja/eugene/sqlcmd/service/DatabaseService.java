package ua.com.juja.eugene.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.eugene.sqlcmd.controller.UserActionLog;
import ua.com.juja.eugene.sqlcmd.model.databasemanager.DatabaseManager;
import ua.com.juja.eugene.sqlcmd.model.entity.UserAction;
import ua.com.juja.eugene.sqlcmd.model.repository.UserActionRepository;

import java.util.*;

@Component
public abstract class DatabaseService implements Service {

    @Autowired
    private UserActionRepository userActions;

    @Override
    public void connectToDatabase(DatabaseManager manager, String database) {
        manager.connect(database, null, null);
        userActions.createAction(manager.getUser(), "SQL SERVER", String.format("CONNECT TO '%s' DATABASE", database));
    }

    @Override
    public DatabaseManager connectToServer(String username, String password) {
        DatabaseManager manager = getDatabaseManager();
        manager.connect(null, username, password);

        userActions.createAction(username, "SQL SERVER", "CONNECT TO SQL SERVER");
        return manager;
    }

    @Override
    public void clearTable(DatabaseManager manager, String table) {
        manager.clear(table);
        userActions.createAction(manager.getUser(), manager.getDatabase(), String.format("CLEAR '%s' TABLE", table));
    }

    @Override
    public void createDatabase(DatabaseManager manager, String database) {
        manager.createDatabase(database);
        userActions.createAction(manager.getUser(), "SQL SERVER", String.format("CREATE '%s' DATABASE", database));
    }

    @Override
    public void createEntry(DatabaseManager manager, String table, String[] values) {
        Set<String> columns = manager.getTableColumns(table);

        Map<String, Object> result = new LinkedHashMap<>();
        Iterator<String> iterator = columns.iterator();
        for (int index = 0; iterator.hasNext(); index++) {
            result.put(iterator.next(), values[index]);
        }
        manager.insert(table, result);
        userActions.createAction(manager.getUser(), manager.getDatabase(), String.format("CREATE ENTRY IN '%s' TABLE", table));
    }

    @Override
    public void createTable(DatabaseManager manager, String query) {
        manager.createTable(query);
        String[] parseQuery = query.split("\\(");
        userActions.createAction(manager.getUser(), manager.getDatabase(), String.format("CREATE '%s' TABLE", parseQuery[0]));
    }

    @Override
    public void disconnectFromDatabase(DatabaseManager manager) {
        String database = manager.getDatabase();

        manager.disconnectFromDatabase();

        userActions.createAction(manager.getUser(), database, "DISCONNECT FROM DATABASE");
    }

    @Override
    public void dropDatabase(DatabaseManager manager, String database) {
        manager.dropDatabase(database);
        userActions.createAction(manager.getUser(), "SQL SERVER", String.format("DROP '%s' DATABASE", database));
    }

    @Override
    public void dropTable(DatabaseManager manager, String table) {
        manager.dropTable(table);
        userActions.createAction(manager.getUser(), manager.getDatabase(), String.format("DROP '%s' TABLE ", table));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserActionLog> findActionsByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username can't be null");
        }
        List<UserActionLog> result = new LinkedList<>();
        for (UserAction action: userActions.findByUsername(username)) {
            result.add(new UserActionLog(action));
        }
        return result;
    }

    @Override
    public List<List<String>> getTableData(DatabaseManager manager, String table) {
        List<Map<String, Object>> tableData = manager.getTableData(table);
        List<String> tableColumns = new ArrayList<>(manager.getTableColumns(table));

        List<List<String>> result = new ArrayList<>();
        result.add(tableColumns);
        for (Map<String, Object> entry : tableData) {
            List<String> row = new ArrayList<>();
            for (String column : tableColumns) {
                row.add(entry.get(column).toString());
            }
            result.add(row);
        }

        userActions.createAction(manager.getUser(), manager.getDatabase(), String.format("TABLES FOR '%s' TABLE", table));
        return result;
    }

    @Override
    public void updateRecord(DatabaseManager manager, String table, String[] values) {
        Map<String, String> updateValues = new LinkedHashMap<>();
        int index = 0;
        for (String column: manager.getTableColumns(table)) {
            updateValues.put(column, values[index++]);
        }
        String primaryKeyName = manager.getPrimaryKey(table);
        String primaryKeyValue = updateValues.remove(primaryKeyName);

        manager.update(table, primaryKeyName, primaryKeyValue, updateValues);
        userActions.createAction(manager.getUser(), manager.getDatabase(),
                String.format("UPDATE RECORD IN '%s' TABLE", table));
    }

    protected abstract DatabaseManager getDatabaseManager();
}

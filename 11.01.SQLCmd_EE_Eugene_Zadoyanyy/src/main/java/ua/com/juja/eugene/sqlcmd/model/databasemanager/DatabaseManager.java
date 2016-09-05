package ua.com.juja.eugene.sqlcmd.model.databasemanager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatabaseManager {

    void clear(String table);

    void connect(String database, String user, String password);

    void createDatabase(String database);

    void createTable(String query);

    void disconnectFromDatabase();

    void dropDatabase(String database);

    void dropTable(String table);

    String getDatabase();

    Set<String> getDatabases();

    String getPrimaryKey(String table);

    Set<String> getTableColumns(String table);

    List<Map<String, Object>> getTableData(String table);

    Set<String> getTableNames();

    String getUser();

    void insert(String table, Map<String, Object> input);

    boolean isConnected();

    void update(String table, String primaryKeyName, String primaryKeyValue, Map<String, String> newValue);
}

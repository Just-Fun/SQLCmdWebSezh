package ua.com.juja.eugene.sqlcmd.model.databasemanager;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.*;

@Component
@Scope(value = "prototype")
public class PostgreSQLManager implements DatabaseManager {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DriverException("Not installed PostgreSQL JDBC driver.", e);
        }
    }

    private static final String ERROR = "It is impossible because: ";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";

    private Connection connection;
    private JdbcTemplate template;
    private boolean isConnected;
    private String user;
    private String password;
    private String database;

    @Override
    public void clear(String table) {
        template.update(String.format("DELETE FROM %s", table));
    }

    @Override
    public void connect(String database, String user, String password) {
        if (user != null && password != null) {
            this.user = user;
            this.password = password;
        }

        if (database != null) {
            connectToDatabase(database);
        } else {
            connectToServer();
        }
    }

    private void connectToDatabase(String database) {
        this.database = database;
        try {
            getConnection(database);
        } catch (SQLException e) {
            throw new DatabaseManagerException("Incorrect database name", e);
        }
        isConnected = true;
    }

    private void connectToServer() {
        try {
            getConnection("");
        } catch (SQLException e) {
            throw new DatabaseManagerException("Incorrect username or password", e);
        }
    }

    @Override
    public void createDatabase(String database) {
        template.execute(String.format("CREATE DATABASE %s", database));
    }

    @Override
    public void createTable(String query) {
        template.execute(String.format("CREATE TABLE IF NOT EXISTS %s", query));
    }

    @Override
    public void disconnectFromDatabase() {
        isConnected = false;
        connect("", user, password);
    }

    @Override
    public void dropDatabase(String database) {
        template.execute(String.format("DROP DATABASE IF EXISTS %s", database));
    }

    @Override
    public void dropTable(String table) {
        template.execute(String.format("DROP TABLE IF EXISTS %s", table));
    }

    @Override
    public Set<String> getDatabases() {
        List<String> result = template.query("SELECT datname FROM pg_database WHERE datistemplate = false;",
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getString(1);
                    }
                });
        return new LinkedHashSet<>(result);
    }

    @Override
    public String getPrimaryKey(String table) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rs = meta.getPrimaryKeys(null, null, table);
            while (rs.next()) {
                return rs.getString("COLUMN_NAME");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseManagerException(ERROR, e);
        }
    }

    @Override
    public Set<String> getTableColumns(String table) {
        List<String> result = template.query("SELECT * FROM information_schema.columns " +
                "WHERE table_schema = 'public' AND table_name = ?",
                new Object[] {table},
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getString("column_name");
                    }
                });
        return new LinkedHashSet<>(result);
    }

    @Override
    public List<Map<String, Object>> getTableData(String table) {
        return template.query("SELECT * FROM " + table,
                new RowMapper<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                        ResultSetMetaData rsmd = resultSet.getMetaData();
                        Map<String, Object> result = new LinkedHashMap<>();
                        for (int index = 0; index < rsmd.getColumnCount(); index++) {
                            result.put(rsmd.getColumnName(index + 1), resultSet.getObject(index + 1));
                        }
                        return result;
                    }
                });
    }

    @Override
    public Set<String> getTableNames() {
        List<String> result = template.query("SELECT table_name FROM information_schema.tables " +
                        "WHERE table_schema='public' AND table_type='BASE TABLE'",
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getString("table_name");
                    }
                });
        return new LinkedHashSet<>(result);
    }

    @Override
    public void insert(String table, Map<String, Object> input) {
        String rowNames = StringUtils.collectionToDelimitedString(input.keySet(), ",", "\"", "\"");
        String values = StringUtils.collectionToDelimitedString(input.values(), ",", "'", "'");
        template.update(String.format("INSERT INTO %s (%s) VALUES (%s)", table, rowNames, values));
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void update(String table, String primaryKeyName, String primaryKeyValue, Map<String, String> newValue) {
        String columns = StringUtils.collectionToDelimitedString(newValue.keySet(), ",", "\"", "\" = ?");

        template.update(String.format("UPDATE %s SET %s WHERE %s = %s",
                table, columns, primaryKeyName, primaryKeyValue), newValue.values().toArray());
    }

    @Override
    public String getDatabase() {
        return database;
    }

    @Override
    public String getUser() {
        return user;
    }

    private void closeOpenedConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseManagerException(ERROR, e);
            }
        }
    }

    private void getConnection(String database) throws SQLException {
        closeOpenedConnection();
        String url = String.format("jdbc:postgresql://%s:%s/%s", HOST, PORT, database);

        connection = DriverManager.getConnection(url, user, password);
        template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
    }
}
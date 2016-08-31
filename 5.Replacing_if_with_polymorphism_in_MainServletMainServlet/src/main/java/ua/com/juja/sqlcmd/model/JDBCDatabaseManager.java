package ua.com.juja.sqlcmd.model;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JDBCDatabaseManager implements DatabaseManager {

    private Connection connection;

    private static String GET_TABLE_NAMES =
            "SELECT table_name " +
            "FROM information_schema.tables " +
            "WHERE table_schema='public' " +
                "AND table_type='BASE TABLE'";

    private static String GET_ALL_FROM_TABLE =
            "SELECT * FROM ?";

    public static final String SELECT_5 =
            "SELECT * " +
            "FROM information_schema.columns " +
            "WHERE table_schema = 'public' " +
                "AND table_name = ?";

    public static final String SELECT_6 =
            "SELECT * FROM ? WHERE id = ?";

    public JDBCDatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DataSet> getTableData(String tableName) {
        List<DataSet> result = new ArrayList();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_FROM_TABLE))
        {
            while (rs.next()) {
                DataSet input = new DataSetImpl();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    input.put(rs.getMetaData().getColumnName(i + 1),
                            rs.getString(i + 1));
                }

                result.add(input);
            }
        } catch (SQLException e) {
            throw new PGException("Can't getTableNames fom DB ", e);
        }
        return result;
    }

    @Override
    public int getSize(String tableName) {
        return 0; // TODO implement me
    }

    @Override
    public Set<String> getTableNames() {
        Set<String> result = new HashSet<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(GET_TABLE_NAMES))
        {
            while (rs.next()) {
                result.add(rs.getString("table_name"));
            }
        } catch (SQLException e) {
            throw new PGException("Can't getTableNames fom DB ", e);
        }
        return result;
    }

    @Override
    public void connect(String database, String userName, String password) {
        try {
            String url = "jdbc:postgresql://localhost:5432/";
            connection = DriverManager.getConnection(url + database,
                    userName, password);
        } catch (SQLException e) {
            throw new PGException(e.getMessage(), e);
        }
    }

    @Override
    public void clear(String tableName) {
        try (Statement stmt = connection.createStatement()) {
            String sql = "TRUNCATE TABLE \"" + tableName + "\" ";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new PGException(e.getMessage(), e);
        }
    }

    @Override
    public void create(String tableName, DataSet input) {
        try (Statement stmt = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("INSERT INTO \"");
            sql.append(tableName);
            sql.append("\" ");
            StringBuilder sql1 = new StringBuilder();
            sql1.append(" (");
            StringBuilder sql2 = new StringBuilder();
            sql2.append(" VALUES (");
            int i = 0;
            for (String s : input.getNames()) {
                sql1.append(s);

                sql2.append("'" + input.get(s) + "'");
                if (input.getNames().size() > ++i) {
                    sql1.append(",");
                    sql2.append(",");
                } else {
                    sql1.append(")");
                    sql2.append(")");
                }
            }
            sql1.append(sql2);
            sql.append(sql1);

            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            throw new PGException("Can't insert into table " + tableName, e);
        }
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        try (Statement stmt = connection.createStatement()) {
            StringBuilder sql = new StringBuilder("UPDATE  \"");
            sql.append(tableName);
            sql.append("\" SET ");
            int i = 0;
            for (String s : newValue.getNames()) {
                sql.append(s);
                sql.append("=");

                sql.append("'" + newValue.get(s) + "'");
                if (newValue.getNames().size() > ++i) {
                    sql.append(",");
                }
            }
            sql.append(" WHERE id = " + id);
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            throw new PGException("can't insert into table " + tableName, e);
        }
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        Set<String> result = new HashSet<String>();
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_5)) {
            while (rs.next()) {
                result.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new PGException("can't getTableColumns " + tableName, e);
        }
        return result;
    }

    @Override
    public boolean isConnected() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            throw new PGException(e.getMessage(), e);
        }
    }

    @Override
    public List<DataSet> getTableDataById(String tableName, int id) {
        List<DataSet> result = new ArrayList();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_6))
        {
            while (rs.next()) {
                DataSet input = new DataSetImpl();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    input.put(rs.getMetaData().getColumnName(i + 1),
                            rs.getString(i + 1));
                }

                result.add(input);
            }
        } catch (SQLException e) {
            throw new PGException("can't getTableNames fom DB ", e);
        }
        return result;
    }
}
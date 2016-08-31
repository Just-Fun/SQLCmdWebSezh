package ua.com.juja.sqlcmd.model;

import org.junit.Ignore;

/**
 * Created by indigo on 25.08.2015.
 */
@Ignore // для него надо отдельно базу создать
public class JDBCDatabaseManagerTest extends DatabaseManagerTest {

    @Override
    public DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }
}

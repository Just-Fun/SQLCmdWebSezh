package ua.com.juja.sqlcmd.model;

/**
 * Created by indigo on 25.08.2015.
 */
public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {

    @Override
    public DatabaseManager getDatabaseManager() {
        return new InMemoryDatabaseManager();
    }
}

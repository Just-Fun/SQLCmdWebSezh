package ua.com.juja.sqlcmd.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by oleksandr.baglai on 21.08.2015.
 */
public abstract class DatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = getDatabaseManager();
        Configurator configurator= new Configurator();
        manager.connect(configurator.getDatabase(), configurator.getName(), configurator.getPassword());
    }

    public abstract DatabaseManager getDatabaseManager();

    @Test
    public void testGetAllTableNames() {
        // given
        manager.getTableData("user");
        manager.getTableData("test");

        // when
        Set<String> tableNames = manager.getTableNames();

        // then
        assertTrue("[test, user]".equals(tableNames.toString()) || "[user, test]".equals(tableNames.toString()));
    }

    @Test
    public void testGetTableData() {
        // given
        manager.clear("user");

        // when
        DataSet input = new DataSetImpl();
        input.put("name", "Stiven");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("user", input);

        // then
        List<DataSet> users = manager.getTableData("user");
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[name, password, id]", user.getNames().toString());
        assertEquals("[Stiven, pass, 13]", user.getValues().toString());
    }

    @Test
    public void testUpdateTableData() {
        // given
        manager.clear("user");

        DataSet input = new DataSetImpl();
        input.put("name", "Stiven");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("user", input);

        // when
        DataSet newValue = new DataSetImpl();
        newValue.put("password", "pass2");
        newValue.put("name", "Pup");
        newValue.put("id", "13");

        manager.update("user", 13, newValue);

        // then
        List<DataSet> users = manager.getTableData("user");
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[name, password, id]", user.getNames().toString());
        assertEquals("[Pup, pass2, 13]", user.getValues().toString());
    }

    @Test
    public void testGetColumnNames() {
        // given
        manager.clear("user");

        // when
        Set<String> columnNames = manager.getTableColumns("user");
        Set<String> set = new HashSet();
        set.add("name");
        set.add("password");
        set.add("id");
        assertTrue("Expected [name, password, id]",set.equals(columnNames));
    }

    @Test
    public void testIsConnected() {
        // then
        assertTrue(manager.isConnected());
    }

    @Test
    public void testClearTable(){
        manager.clear("user");
        assertTrue(true);
    }
}

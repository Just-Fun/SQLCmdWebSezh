package ua.com.juja.sqlcmd.model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by indigo on 21.08.2015.
 */
public abstract class DatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = getDatabaseManager();
        manager.connect("postgres", "postgres", "postgres");
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
        assertEquals("[employees, nullproba, proba12, nullproba2, proba3, proba4, proba5, rt, gt, tab, table12, test123, test15, user89, test, id, user]", tableNames.toString());
    }

    @Test
    public void testGetDatabasesNames() {

        // when
        Set<String> bases = manager.getDatabasesNames();

        // then
        assertEquals("[postgres, mydb, test89, test, test98, base1, bs12, springdb, postgrestestnew, june17, пе, test1]", bases.toString());
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
    public void testGetSize_whenSiseIs1() {
        // given
        manager.clear("user");

        // when
        DataSet input = new DataSetImpl();
        input.put("name", "Stiven");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("user", input);

        assertEquals(1, manager.getSize("user"));
    }

    @Test
    public void testGetSize_whenSiseIs2() {
        // given
        manager.clear("user");

        // when
        DataSet input = new DataSetImpl();
        input.put("name", "Stiven");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("user", input);

        DataSet input2 = new DataSetImpl();
        input2.put("name", "Stiven2");
        input2.put("password", "pass2");
        input2.put("id", 14);
        manager.create("user", input2);

        assertEquals(2, manager.getSize("user"));
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

        // then
        assertEquals("[name, password, id]", columnNames.toString());
    }

    @Test
    public void testisConnected() {
        // given
        // when
        // then
        assertTrue(manager.isConnected());
    }
}

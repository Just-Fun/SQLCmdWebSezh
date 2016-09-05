package ua.com.juja.eugene.sqlcmd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.juja.eugene.sqlcmd.model.databasemanager.DatabaseManager;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
public class DatabaseServiceTest {

    @Autowired
    DatabaseManager manager;

    @Autowired
    Service service;

    @Test
    public void testConnectToServer() {
        //given
        String username = "test";
        String password = "password";

        //when
        service.connectToServer(username, password);

        //then
        verify(manager).connect(null, username, password);
    }

    @Test
    public void testCreateEntry() {
        //given
        String table = "test";
        String[] values = {"John", "Smith"};
        when(manager.getTableColumns(table))
                .thenReturn(new LinkedHashSet<String>(Arrays.asList("username", "password")));

        Map<String, Object> expected = new LinkedHashMap<>();
        expected.put("username", "John");
        expected.put("password", "Smith");

        //when
        service.createEntry(manager, table, values);

        //then
        verify(manager).insert(table, expected);
    }

    @Test
    public void testGetTableData() {
        //given
        String table = "test";

        List<Object> row1 = new ArrayList<>();
        row1.add("hello");
        row1.add("world");
        List<Object> row2 = new ArrayList<>();
        row2.add("user");
        row2.add("qwerty");
        List<List<Object>> expected = Arrays.asList(row1, row2);

        Map<String, Object> tableData1 = new LinkedHashMap<>();
        tableData1.put("user", "hello");
        tableData1.put("pass", "world");
        Map<String, Object>  tableData2 = new LinkedHashMap<>();
        tableData2.put("user", "user");
        tableData2.put("pass", "qwerty");

        when(manager.getTableColumns(table))
                .thenReturn(new LinkedHashSet<String>(Arrays.asList("user", "pass")));
        when(manager.getTableData(table))
                .thenReturn(Arrays.asList(tableData1, tableData2));

        //when
        List<List<String>> actual = service.getTableData(manager, table);

        //then
        assertEquals(expected, actual);
    }
}

package ua.com.juja.sqlcmd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by max on 27.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-app-context.xml")
public class ServiceImplTest {

    @Autowired
    private Service service;
    private DatabaseManager manager;

    @Test
    public void shouldConnect() throws Exception {
        manager = service.connect("sqlcmd", "postgres", "postgres");
    }

    @Test
    public void shouldGetTableData() throws Exception {
        shouldConnect();

        manager.clear("user");

        DataSet input = new DataSetImpl();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "Pass");
        manager.create("user", input);

        DataSet input2 = new DataSetImpl();
        input2.put("id", 14);
        input2.put("name", "Eva");
        input2.put("password", "PassPass");
        manager.create("user", input2);

        DataSet input3 = new DataSetImpl();
        input3.put("id", 15);
        input3.put("name", "Eva1");
        input3.put("password", "PassPass1");
        manager.create("user", input3);

        // when
        List<DataSet> users = service.tableData(manager, "user");

        // then
        assertEquals("[{names:[name, password, id], " +
                "value: [Stiven, Pass, 13]}, " +
                "{names:[name, password, id], " +
                "value: [Eva, PassPass, 14]}, " +
                "{names:[name, password, id], " +
                "value: [Eva1, PassPass1, 15]}]", users.toString());
    }
}

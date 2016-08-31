package ua.com.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by max on 30.11.2015.
 * Test ServiceImp Without Spring
 */
public class ServiceImpTestWithoutSpring {

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private DatabaseManager manager;

    @Mock
    private DataBaseManagerFactory factory;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);

        when(factory.createDataBaseManager()).thenReturn(manager);
    }

    @Test
    public void test() {
        // given
        DataSet input = create(13, "Stiven", "Pass");
        DataSet input2 = create(14, "Eva", "PassPass");

        when(manager.getTableColumns("users"))
                .thenReturn(new LinkedHashSet(Arrays.asList("name", "password", "id")));
        when(manager.getTableData("users"))
                .thenReturn(Arrays.asList(input, input2));

        // when
        List<DataSet> users = service.tableData(manager, "users");

        // then
        assertEquals("[{names:[name, password, id], " +
                "value: [Stiven, Pass, 13]}, " +
                "{names:[name, password, id], " +
                "value: [Eva, PassPass, 14]}]", users.toString());
    }

    private DataSet create(int id, String name, String pass) {
        DataSet input = new DataSetImpl();
        input.put("id", id);
        input.put("name", name);
        input.put("password", pass);
        manager.create("users", input);
        return input;
    }
}



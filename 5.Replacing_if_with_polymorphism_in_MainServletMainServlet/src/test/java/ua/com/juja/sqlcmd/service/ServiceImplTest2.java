package ua.com.juja.sqlcmd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by max on 27.11.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest2 {

    private ServiceImpl service = new ServiceImpl() ;

    @Mock
    private DatabaseManager manager;

    @Test
    public void test() {
        manager.clear("users");
        DataSetImpl input = new DataSetImpl();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "Pass");
        manager.create("users", input);

        DataSetImpl input2 = new DataSetImpl();
        input2.put("id", 14);
        input2.put("name", "Eva");
        input2.put("password", "PassPass");
        manager.create("users", input2);

        when(manager.getTableColumns("users"))
                .thenReturn(new LinkedHashSet(Arrays.asList("name", "password", "id")));
        when(manager.getTableData("users"))
                .thenReturn(Arrays.<DataSet>asList(input, input2));
        // when
        List<DataSet> users = service.tableData(manager, "users");

        // then
        assertEquals("[{names:[name, password, id], " +
                "value: [Stiven, Pass, 13]}, " +
                "{names:[name, password, id], " +
                "value: [Eva, PassPass, 14]}]", users.toString());
    }
}

package ua.com.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
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

public class ServiceImplMockitoTest {

//    @InjectMocks
    private ServiceImpl service = new ServiceImpl() {
        @Override
        protected DatabaseManager getManager() {
            return manager;
        }
    };

    @Mock
    private DatabaseManager manager;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        // given

        DataSet input = new DataSetImpl();
        input.put("id", 13);
        input.put("name", "Stiven");
        input.put("password", "Pass");
        manager.create("users", input);

        DataSet input2 = new DataSetImpl();
        input2.put("id", 14);
        input2.put("name", "Eva");
        input2.put("password", "PassPass");
        manager.create("users", input2);

        when(manager.getTableColumns("users")).
                thenReturn(new LinkedHashSet<>(Arrays.asList("name", "password", "id")));
        when(manager.getTableData("users")).
                thenReturn(Arrays.asList(input, input2));

        // when
        List<List<String>> users = service.find(manager, "users");

        // then
        assertEquals("[[name, password, id], " +
                "[Stiven, Pass, 13], " +
                "[Eva, PassPass, 14]]", users.toString());
    }
}


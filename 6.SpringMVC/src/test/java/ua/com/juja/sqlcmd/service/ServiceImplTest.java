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
 * Created by oleksandr.baglai on 27.11.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
public class ServiceImplTest {

    @Autowired
    private Service service;

    @Test
    public void test() {
        // given
        DatabaseManager manager = service.connect("database", "user", "password");

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

        // when
        List<List<String>> users = service.find(manager, "users");

        // then
        assertEquals("[[name, password, id], " +
                "[Stiven, Pass, 13], " +
                "[Eva, PassPass, 14]]", users.toString());
    }
}


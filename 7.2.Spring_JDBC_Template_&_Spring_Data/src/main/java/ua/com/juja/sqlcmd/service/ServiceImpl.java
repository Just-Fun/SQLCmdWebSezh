package ua.com.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.sqlcmd.model.entity.Student;
import ua.com.juja.sqlcmd.model.*;
import ua.com.juja.sqlcmd.model.entity.UserAction;

import java.util.*;

/**
 * Created by oleksandr.baglai on 30.10.2015.
 */
@Component
public abstract class ServiceImpl implements Service {

    protected abstract DatabaseManager getManager();

    @Autowired
    private UserActionRepository userActions;

    @Autowired
    private StudentsRepository students;

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "databases", "list", "hello", "createDatabase", "student", "students");
    }

    @Override
    public DatabaseManager connect(String databaseName, String userName, String password) {
        DatabaseManager manager = getManager();
        manager.connect(databaseName, userName, password);
        userActions.save(new UserAction(userName, databaseName, "CONNECT"));
        return manager;
    }

    @Override
    public List<List<String>> find(DatabaseManager manager, String tableName) {
        List<List<String>> result = new LinkedList<>();

        List<String> columns = new LinkedList<>(manager.getTableColumns(tableName));
        List<DataSet> tableData = manager.getTableData(tableName);

        result.add(columns);
        for (DataSet dataSet : tableData) {
            List<String> row = new ArrayList<>(columns.size());
            result.add(row);
            for (String column : columns) {
                Object value = dataSet.get(column);
                if (value == null) {
                    throw new IllegalStateException(String.format(
                            "Ожидалось колонка с именем %s но ее нет, а есть %s",
                            column, dataSet));
                }
                row.add(value.toString());
            }
        }

        userActions.save(new UserAction(manager.getUserName(), manager.getDatabaseName(),
                "FIND(" + tableName +  ")"));

        return result;
    }

    @Override
    public Set<String> tables(DatabaseManager manager) {
        UserAction action = new UserAction(manager.getUserName(),
                manager.getDatabaseName(), "TABLES");
        userActions.save(action);
        
        return manager.getTableNames();
    }

    @Override
    public List<UserAction> getAllFor(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("User name cant be null!");
        }

        return userActions.findByUserName(userName);
    }

    @Override
    public List<Student> getAllForStudent() {
        return students.findAll();
    }

    @Override
    public void createStudent(String name, int age) {
        students.save(new Student(name, age));
    }
}

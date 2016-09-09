package ua.com.juja.sqlcmd.model;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ua.com.juja.sqlcmd.model.entity.DatabaseConnection;
import ua.com.juja.sqlcmd.model.entity.UserAction;

import java.util.List;

/**
 * Created by oleksandr.baglai on 26.12.2015.
 */
public class UserActionRepositoryImpl implements UserActionRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private DatabaseConnectionRepository databaseConnections;

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public void createAction(String databaseName, String userName, String action) {
        DatabaseConnection databaseConnection = databaseConnections.findByUserNameAndDatabase(userName, databaseName);
        if (databaseConnection == null) {
            databaseConnection = databaseConnections.save(new DatabaseConnection(userName, databaseName));
        }
        UserAction userAction = new UserAction(action, databaseConnection);
        mongoTemplate.save(userAction);
    }
}

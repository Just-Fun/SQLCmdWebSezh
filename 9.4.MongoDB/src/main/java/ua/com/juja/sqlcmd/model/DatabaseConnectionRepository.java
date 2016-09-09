package ua.com.juja.sqlcmd.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.juja.sqlcmd.model.entity.DatabaseConnection;

import java.util.List;

/**
 * Created by oleksandr.baglai on 19.12.2015.
 */
public interface DatabaseConnectionRepository extends MongoRepository<DatabaseConnection, Integer> {

    // TODO а надо ли нам это?
    DatabaseConnection findByUserNameAndDatabase(String userName, String database);

    List<DatabaseConnection> findByUserName(String userName);
}

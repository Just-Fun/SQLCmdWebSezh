package ua.com.juja.eugene.sqlcmd.model.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.eugene.sqlcmd.model.entity.DatabaseConnection;

public interface DatabaseConnectionRepository extends CrudRepository<DatabaseConnection, Integer> {

    DatabaseConnection findByUsernameAndDatabase(String username, String database);
}

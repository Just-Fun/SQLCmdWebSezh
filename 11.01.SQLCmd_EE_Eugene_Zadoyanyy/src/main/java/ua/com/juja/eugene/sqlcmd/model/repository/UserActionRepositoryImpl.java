package ua.com.juja.eugene.sqlcmd.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.eugene.sqlcmd.model.entity.DatabaseConnection;
import ua.com.juja.eugene.sqlcmd.model.entity.UserAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserActionRepositoryImpl implements UserActionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DatabaseConnectionRepository databaseConnections;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createAction(String username, String database, String action) {
        DatabaseConnection databaseConnection = databaseConnections.findByUsernameAndDatabase(username, database);
        if (databaseConnection == null) {
            databaseConnection = databaseConnections.save(new DatabaseConnection(username, database));
        }
        entityManager.persist(new UserAction(action, System.currentTimeMillis(), databaseConnection));
    }
}

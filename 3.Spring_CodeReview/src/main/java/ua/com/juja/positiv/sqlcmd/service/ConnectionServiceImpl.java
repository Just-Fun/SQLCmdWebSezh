package ua.com.juja.positiv.sqlcmd.service;

import org.springframework.stereotype.Component;
import ua.com.juja.positiv.sqlcmd.databasemanager.DatabaseManager;

import java.util.List;

/**
 * Created by POSITIV on 31.10.2015.
 */
@Component
public abstract class ConnectionServiceImpl implements ConnectionService {

    public abstract DatabaseManager getManager();

    @Override
    public DatabaseManager connect(String database, String user, String password) throws ServiceException {
        try {
            DatabaseManager manager = getManager();
            manager.connect(database, user, password);
            return manager;
        } catch (Exception e) {
            throw new ServiceException("Connection error", e);
        }
    }

}

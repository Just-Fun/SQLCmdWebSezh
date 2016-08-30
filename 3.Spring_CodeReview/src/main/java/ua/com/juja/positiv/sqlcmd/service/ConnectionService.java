package ua.com.juja.positiv.sqlcmd.service;

import ua.com.juja.positiv.sqlcmd.databasemanager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by POSITIV on 31.10.2015.
 */
public interface ConnectionService {

    DatabaseManager connect(String database, String user, String password)
            throws  ServiceException;

}

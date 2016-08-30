package ua.com.juja.positiv.sqlcmd.service;

import java.sql.SQLException;

/**
 * Created by oleksandr.baglai on 20.11.2015.
 */
public class ServiceException extends Exception {
    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
